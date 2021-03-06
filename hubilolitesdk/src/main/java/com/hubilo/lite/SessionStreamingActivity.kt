package com.hubilo.lite

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.PorterDuff
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.text.Html
import android.util.DisplayMetrics
import android.view.View
import android.view.ViewTreeObserver
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.webkit.*
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.SeekBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.ColorUtils
import androidx.recyclerview.widget.LinearLayoutManager
import com.hubilo.lite.apipack.*
import com.hubilo.lite.apipack.SessionDetailResponse
import com.hubilo.lite.databinding.LayoutSessionBinding
import java.io.IOException
import java.text.DecimalFormat
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*
import java.util.regex.Pattern

class SessionStreamingActivity : AppCompatActivity(), WebViewYoutubeCallBacks {

    private lateinit var binding: LayoutSessionBinding
    private var webViewYoutubeCallBacks: WebViewYoutubeCallBacks? =
        null //get all playback to handle controls
    private var currentVideoDurationInSec = 0f //current video duration
    private var videoTotalDurationInSec = 0 //total video
    private var tickPosition = 0 //to ignore webview call backs
    private var isVideoLoaded = false //check video video has been loaded
    private var videoPlaying = 0 // 1 means video is playing
    private var relTopHeight = 0
    private var relTopHeightInPotrait = 0
    private var handler: Handler? = null
    private var runnable: Runnable? = null
    private var slideUpAnimation: Animation? = null
    private var slideDownAnimation: Animation? = null
    private var mIsLandscape = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = LayoutSessionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    private fun init() {
        isVideoLoaded = false

        supportActionBar?.hide()
        val isDark = ColorUtils.calculateLuminance(window.statusBarColor) < 0.5
        val decorView = this.window.decorView
        var systemUiVisibilityFlags = decorView.systemUiVisibility
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            systemUiVisibilityFlags = if (isDark) {
                systemUiVisibilityFlags and View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR.inv()
            } else {
                systemUiVisibilityFlags and View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            }
        }
        decorView.systemUiVisibility = systemUiVisibilityFlags

        webViewYoutubeCallBacks = this

        binding.mainLayout.relTop.viewTreeObserver?.addOnGlobalLayoutListener(object :
            ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                binding.mainLayout.relTop.viewTreeObserver!!.removeOnGlobalLayoutListener(this)
                relTopHeight = binding.mainLayout.relTop.height
                if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
                    relTopHeightInPotrait = binding.mainLayout.relTop.height
                }
            }
        })

        binding.mainLayout.linMainContainerSession.setBackgroundColor(Color.WHITE)
        binding.mainLayout.relExtraSpaceSession.setBackgroundColor(Color.WHITE)
        binding.mainLayout.bottomExtraSpacing.setBackgroundColor(Color.WHITE)

        initControls()
        sessionDetailApiCall()

    }

    private fun fillSessionData(sessionDetailResponse: SessionDetailResponse) {
        binding.loading.visibility = View.GONE
        binding.mainLayout.txtSpeakerHeading.visibility = View.VISIBLE
        var time = getTimeFromInputFormat(sessionDetailResponse.agendaInfo?.startTimeMilli?.toLong() ?: 0) + " - " +
                getTimeFromInputFormat(sessionDetailResponse.agendaInfo?.endTimeMilli?.toLong() ?: 0)
        binding.mainLayout.tvStartTimeEndTime.text = time
        binding.mainLayout.tvSessionDate.text = getDateFromInputFormat(sessionDetailResponse.agendaInfo?.startTimeMilli?.toLong() ?: 0)
        binding.mainLayout.tvSessionTitle.text = sessionDetailResponse.title ?: ""
        binding.mainLayout.tvSessionDescription.text = Html.fromHtml(sessionDetailResponse.agendaInfo?.description ?: "")

        sessionStreamLoadVYoutubeVideo(
            getVideoId(
                sessionDetailResponse.agendaInfo?.streamLink ?: ""
            ) ?: ""
        )

        binding.mainLayout.recyclerViewSpeakers.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.mainLayout.recyclerViewSpeakers.adapter =
            SessionSpeakersAdapter(
                this,
                sessionDetailResponse.speakers?: emptyList(),
            )
    }

    private fun sessionStreamLoadVYoutubeVideo(videoId: String?) {
        binding.mainLayout.relTop.visibility = View.VISIBLE
        binding.mainLayout.linearYoutubeView.visibility = View.VISIBLE
        binding.mainLayout.webviewProgress.visibility = View.VISIBLE

        sessionLoadYoutube(
            binding.mainLayout.wvVideo,
            videoId ?: "",
            binding.mainLayout.webviewProgress
        )
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun sessionLoadYoutube(
        webView: WebView,
        videoUrl: String,
        webviewProgress: ProgressBar
    ) {
        val frameVideo =
            IFrameHelper.loadWebViewYoutubeHTMLData(this@SessionStreamingActivity, videoUrl)
        val youTubeWebClient = YouTubeWebClient(webviewProgress, webView)
        webviewProgress.indeterminateDrawable.setColorFilter(
            Color.parseColor("#FFFFFF"), PorterDuff.Mode.SRC_IN
        )
        webView.webViewClient = youTubeWebClient
        webView.settings.mediaPlaybackRequiresUserGesture = false
        webView.settings.pluginState = WebSettings.PluginState.ON
        webView.setBackgroundColor(
            ContextCompat.getColor(
                this@SessionStreamingActivity,
                R.color.black
            )
        )
        webView.webChromeClient = YoutubeChromeClient(this@SessionStreamingActivity)
        webView.settings.setRenderPriority(WebSettings.RenderPriority.HIGH)
        webView.settings.javaScriptEnabled = true
        webView.settings.cacheMode = WebSettings.LOAD_NO_CACHE
        webView.settings.builtInZoomControls = false
        webView.settings.setSupportZoom(false)
        webView.settings.domStorageEnabled = true
        webView.settings.loadWithOverviewMode = true
        webView.settings.useWideViewPort = true
        webView.loadDataWithBaseURL(
            SessionLiveStreamURLs.YOUTUBE_BASE_URL,
            frameVideo,
            SessionMimeTypeValue.TEXT_HTML_MIME_TYPE,
            "utf-8",
            null
        )
    }

    inner class YouTubeWebClient(
        private var progressBarVideo: ProgressBar?,
        var webViewFeed: WebView?
    ) : WebViewClient() {
        override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
            if (url != null && url.isNotEmpty() && Uri.parse(url).scheme.equals(
                    "embed",
                    ignoreCase = true
                )
            ) {
                if (Uri.parse(url).getQueryParameter("duration") != null) {
                    val duration = Uri.parse(url).getQueryParameter("duration")
                    webViewYoutubeCallBacks?.totalDuration(duration)
                    if (Uri.parse(url).getQueryParameter("position") != null) {
                        if (tickPosition > 0) {
                            tickPosition = if (tickPosition > 1) {
                                tickPosition - 1
                            } else {
                                0
                            }
                        } else {
                            currentVideoDurationInSec =
                                Uri.parse(url).getQueryParameter("position")!!.toFloat()
                            webViewYoutubeCallBacks?.seekBarPosition(
                                duration,
                                Uri.parse(url).getQueryParameter("position")
                            )
                            convertSecondsToHoursAndMinutes(
                                changeNumberToTwoDigits(
                                    Uri.parse(url).getQueryParameter("position")!!
                                )!!.toInt(), findViewById(R.id.seekTime)
                            )
                        }
                    }
                }
                if (Uri.parse(url).getQueryParameter("playback") != null) {
                    checkIfBuffering(Uri.parse(url).getQueryParameter("playback")!!.toInt())
                }
                if (Uri.parse(url).getQueryParameter("failed") != null) {
                    if (Uri.parse(url).getQueryParameter("failed")
                            .equals("false", ignoreCase = true)
                    ) {
                        isVideoLoaded = true
                    }
                }
                if (Uri.parse(url).host != null && Uri.parse(url).host.equals(
                        "onReady",
                        ignoreCase = true
                    )
                ) {
                    isVideoLoaded = true
                    binding.mainLayout.play.performClick()

                }
                return true
            }
            return super.shouldOverrideUrlLoading(
                view,
                url
            ) //To change body of overridden methods use File | Settings | File Templates.
        }

        override fun onPageFinished(view: WebView, url: String) {
            if (progressBarVideo != null) progressBarVideo!!.visibility = View.GONE
            if (webViewFeed != null) webViewFeed!!.visibility = View.VISIBLE
            binding.mainLayout.play.performClick()
            super.onPageFinished(view, url)
        }

        override fun shouldInterceptRequest(
            view: WebView,
            request: WebResourceRequest
        ): WebResourceResponse? {
            if (request.url != null) {
                val url = request.url.toString()
                val extension = MimeTypeMap.getFileExtensionFromUrl(url)
                //I have some folders for files with the same extension
                if (extension == "css" && url.contains("www-player")) {

                    try {
                        return WebResourceResponse(
                            getMimeType(url),
                            "UTF-8",
                            assets.open("youtubeplayer-mode.css")
                        )
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                }
            }
            return super.shouldInterceptRequest(view, request)
        }

        //get mime type by url
        private fun getMimeType(url: String?): String? {
            var type: String? = null
            val extension = MimeTypeMap.getFileExtensionFromUrl(url)
            if (extension != null) {
                when (extension) {
                    SessionMimeTypeValue.JS_MIME_TYPE -> {
                        return "text/javascript"
                    }
                    SessionMimeTypeValue.WOFF_MIME_TYPE -> {
                        return "application/font-woff"
                    }
                    SessionMimeTypeValue.WOFF2_MIME_TYPE -> {
                        return "application/font-woff2"
                    }
                    SessionMimeTypeValue.TTF_MIME_TYPE -> {
                        return "application/x-font-ttf"
                    }
                    SessionMimeTypeValue.EOT_MIME_TYPE -> {
                        return "application/vnd.ms-fontobject"
                    }
                    SessionMimeTypeValue.SVG_MIME_TYPE -> {
                        return "image/svg+xml"
                    }
                    else -> type = MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension)
                }
            }
            return type
        }

        override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
            super.onPageStarted(view, url, favicon)

        }
    }

    object SessionLiveStreamURLs {
        const val YOUTUBE_BASE_URL = "https://youtube.com/"
    }


    object SessionMimeTypeValue {
        const val JS_MIME_TYPE = "js"
        const val WOFF_MIME_TYPE = "woff"
        const val WOFF2_MIME_TYPE = "woff2"
        const val TTF_MIME_TYPE = "ttf"
        const val EOT_MIME_TYPE = "eot"
        const val SVG_MIME_TYPE = "svg"
        const val TEXT_HTML_MIME_TYPE = "text/html"
    }

    override fun seekBarPosition(durationInSeconds: String?, seekSeconds: String) {
        val seekPosition = (changeNumberToTwoDigits(seekSeconds)?.toFloat()?.times(100))?.div(
            changeNumberToTwoDigits(durationInSeconds.toString())!!.toFloat()
        )
        binding.mainLayout.seekBar.progress = seekPosition!!.toInt()
    }

    override fun totalDuration(durationInSeconds: String?) {
        videoTotalDurationInSec = durationInSeconds!!.toInt()
        convertSecondsToHoursAndMinutes(
            durationInSeconds.toInt(),
            findViewById<View>(R.id.totalTime) as TextView
        )
    }

    override fun playback(isPlaying: Int) {
        when (isPlaying) {
            1 -> {
                if (binding.mainLayout.webviewProgress.visibility == View.VISIBLE)
                    binding.mainLayout.webviewProgress.visibility = View.GONE
                binding.mainLayout.play.setImageDrawable(
                    ResourcesCompat.getDrawable(
                        resources,
                        android.R.drawable.ic_media_pause,
                        theme
                    )
                )
            }
            3 -> {
                if (binding.mainLayout.webviewProgress.visibility == View.GONE)
                    binding.mainLayout.webviewProgress.visibility = View.VISIBLE
                binding.mainLayout.play.setImageDrawable(
                    ResourcesCompat.getDrawable(
                        resources,
                        android.R.drawable.ic_media_pause,
                        theme
                    )
                )
            }
            else -> {
                if (binding.mainLayout.webviewProgress.visibility == View.VISIBLE)
                    binding.mainLayout.webviewProgress.visibility = View.GONE
                binding.mainLayout.play.setImageDrawable(
                    ResourcesCompat.getDrawable(
                        resources,
                        android.R.drawable.ic_media_play,
                        theme
                    )
                )
            }
        }
    }

    fun checkIfBuffering(playback: Int) {
        when (playback) {
            0 -> {
                if (currentVideoDurationInSec > videoTotalDurationInSec - 1) {
                    currentVideoDurationInSec = 0f
                    webViewYoutubeCallBacks!!.seekBarPosition(
                        videoTotalDurationInSec.toString() + "",
                        currentVideoDurationInSec.toString() + ""
                    )
                    webViewYoutubeCallBacks!!.playback(2)
                } else {
                    if (currentVideoDurationInSec != 0f) {
                        webViewYoutubeCallBacks!!.playback(3)
                    }
                }
            }
            1 -> {
                webViewYoutubeCallBacks!!.playback(1)
            }
            2 -> {
                webViewYoutubeCallBacks!!.playback(2)
            }
            3 -> {
                if (currentVideoDurationInSec != 0f) {
                    webViewYoutubeCallBacks!!.playback(3)
                }
            }
            else -> {
                webViewYoutubeCallBacks!!.playback(3)
            }
        }
    }


    //convert seconds to time that can be shown
    public fun convertSecondsToHoursAndMinutes(seconds: Int, textView: TextView) {
        val hr = seconds / 3600
        val min = (seconds % 3600) / 60
        val sec = (seconds % 3600) % 60

        var time = ""

        if (hr > 0) {
            time = changeTimeToTwoDigit(hr.toString()) + ":"
        }

        time = if (min > 0) {
            if (sec > 0) {
                time + changeTimeToTwoDigit(min.toString()) + ":" + changeTimeToTwoDigit(sec.toString())
            } else {
                time + changeTimeToTwoDigit(min.toString()) + ":00"
            }
        } else {
            if (sec > 0) {
                time + "00" + ":" + changeTimeToTwoDigit(sec.toString())
            } else {
                time + "00" + ":00"
            }
        }
        runOnUiThread {
            textView.text = time
        }
    }

    private fun changeTimeToTwoDigit(number1: String): String? {
        return try {
            val nf = NumberFormat.getNumberInstance(Locale.US)
            val formatter = nf as DecimalFormat
            formatter.applyPattern("00")
            formatter.format(number1.toDouble())
        } catch (e: Exception) {
            number1
        }
    }


    //convert number to have max 2 fraction example 1.2333 = 1.23
    private fun changeNumberToTwoDigits(number1: String): String? {
        return try {
            val nf = NumberFormat.getNumberInstance(Locale.getDefault())
            val formatter = nf as DecimalFormat
            formatter.applyPattern("00")
            formatter.format(number1.toDouble())
        } catch (e: java.lang.Exception) {
            number1
        }
    }

    fun getVideoId(videoUrl: String): String? {
        var vId: String? = null
        val pattern = Pattern.compile(
            "(?<=watch\\?v=|/videos/|embed\\/|youtu.be\\/|\\/v\\/|watch\\?v%3D|%2Fvideos%2F|embed%2F|youtu.be%2F|%2Fv%2F)[^#\\&\\?\\n]*"
        )
        val matcher = pattern.matcher(videoUrl)
        if (matcher.find()) {
            vId = matcher.group()
        }
        return vId
    }

    private fun hideShowControls() {
        runnable?.let { handler?.removeCallbacks(it) }
        if (binding.mainLayout.relControls.visibility == View.VISIBLE) {
            binding.mainLayout.relControls.visibility = View.GONE
            binding.mainLayout.relControls.startAnimation(slideDownAnimation)
        } else if (binding.mainLayout.relControls.visibility == View.GONE) {
            binding.mainLayout.relControls.startAnimation(slideUpAnimation)
            binding.mainLayout.relControls.visibility = View.VISIBLE
            hideControls()
        }
    }

    private fun hideControls() {
        runnable?.let { handler?.postDelayed(it, 3000) }
    }

    var toggledFullScreen = 0
    private fun toggleVideoToFullScreen() {
        if (toggledFullScreen == 0) {
            toggledFullScreen = 1
            val displayMetrics = DisplayMetrics()
            windowManager.defaultDisplay.getMetrics(displayMetrics)
            binding.mainLayout.relTop.layoutParams.height = LinearLayout.LayoutParams.MATCH_PARENT
            binding.mainLayout.bottomSheetNestedScroll.visibility = View.GONE
            binding.mainLayout.appBarLayout.visibility = View.GONE
            binding.mainLayout.appBarDetailLayout.visibility = View.GONE
            binding.mainLayout.ivFullScreen.setImageDrawable(
                ContextCompat.getDrawable(
                    applicationContext,
                    R.drawable.ic_fullscreen_exit_24
                )
            )
            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
            mIsLandscape = true
        } else {
            toggledFullScreen = 0
            /*val displayMetrics = DisplayMetrics()
            windowManager.defaultDisplay.getMetrics(displayMetrics)
            binding.mainLayout.relTop.layoutParams.height = (displayMetrics.widthPixels * (1-0.5625)).toInt()*/
            binding.mainLayout.relTop.layoutParams.height = relTopHeightInPotrait
            binding.mainLayout.bottomSheetNestedScroll.visibility = View.VISIBLE
            binding.mainLayout.appBarLayout.visibility = View.GONE
            //binding.mainLayout.appBarDetailLayout.visibility = View.VISIBLE
            binding.mainLayout.ivFullScreen.setImageDrawable(
                ContextCompat.getDrawable(
                    applicationContext,
                    R.drawable.ic_fullscreen_24
                )
            )
            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
            mIsLandscape = false
        }
        //changeBottomSheetHeight()
        hideShowControls()
    }

    private fun initControls() {
        slideUpAnimation = AnimationUtils.loadAnimation(
            applicationContext,
            R.anim.slide_up_fast
        )

        slideDownAnimation = AnimationUtils.loadAnimation(
            applicationContext,
            R.anim.slide_down_fast
        )

        //to hide controlls
        handler = Handler()
        runnable = Runnable {
            if (binding.mainLayout.relControls.visibility == View.VISIBLE) {
                binding.mainLayout.relControls.visibility = View.GONE
                binding.mainLayout.relControls.startAnimation(slideDownAnimation)
            }
        }

        binding.mainLayout.seekBar.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (seekBar!!.isPressed) {
                    tickPosition = 2;
                    var seekTo =
                        (changeNumberToTwoDigits(videoTotalDurationInSec.toString())?.toFloat())?.times(
                            (changeNumberToTwoDigits(progress.toString())?.toFloat()?.div(100)!!)
                        );
                    if (seekTo != null) {
                        convertSecondsToHoursAndMinutes(seekTo.toInt(), findViewById(R.id.seekTime))
                    }
                    binding.mainLayout.wvVideo.loadUrl("javascript:seek($seekTo);")
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }
        })

        binding.mainLayout.overlayWebView.setOnClickListener {
            if (isVideoLoaded) {
                hideShowControls()
            }
        }

        binding.mainLayout.play.setOnClickListener {
            if (isVideoLoaded) {
                if (videoPlaying == 1) {
                    binding.mainLayout.wvVideo.loadUrl("javascript:pause();")
                    if (binding.mainLayout.relControls.visibility == View.GONE) {
                        binding.mainLayout.relControls.startAnimation(slideUpAnimation)
                        binding.mainLayout.relControls.visibility = View.VISIBLE
                    }
                } else {
                    binding.mainLayout.wvVideo.loadUrl("javascript:play();")
                    hideShowControls()
                }
            }
        }

        binding.mainLayout.ivFullScreen.setOnClickListener {
            toggleVideoToFullScreen()
        }
    }

    private fun sessionDetailApiCall() {
        val sessionId =
            SharedPreferenceUtil.getInstance(this)?.getData(PreferenceKeyConstants.AGENDA_ID, "")
                ?: ""
        if (sessionId.isNotEmpty()) {
            LoginHelper.sessionDetail(
                this,
                this,
                sessionId,
                object : SessionApiCallResponseCallBack {
                    override fun onError(error: String) {

                    }

                    override fun onSuccess(mainResponse: CommonResponse<SessionDetailResponse>) {
                        if (mainResponse.status == true) {
                            if (mainResponse.success?.data != null) {
                                if (mainResponse.success?.data is SessionDetailResponse) {
                                    val sessionResponse =
                                        mainResponse.success?.data as SessionDetailResponse
                                    fillSessionData(sessionResponse)
                                }
                            }
                        }
                    }
                })
        }
    }

    private fun getTimeFromInputFormat(milliseconds: Long): String {
        val locale = Locale.getDefault()
        Locale.setDefault(locale)
        val millis: Long = milliseconds
        val date = Date(millis)
        val formatter = SimpleDateFormat("HH:mm", locale)
        return formatter.format(date)
    }

    private fun getDateFromInputFormat(milliseconds: Long, timeFormat: String = "dd MMMM yyyy"): String {
        return try{
            val locale = Locale.getDefault()
            Locale.setDefault(locale)
            val millis: Long = milliseconds
            val date = Date(millis)
            val formatter = SimpleDateFormat(timeFormat, locale)
            formatter.format(date)
        }catch (e : Exception){
            ""
        }
    }
}
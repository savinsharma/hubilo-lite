package com.hubilo.lite

import android.content.Context
import android.util.Log
import android.webkit.WebView
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

object IFrameHelper {

    fun loadWebViewYoutubeHTMLData(context: Context, video_id: String): String {
        val htmlParams = "{" +
                "\"videoId\": \"" + video_id + "\"," +
                "\"width\": \"100%\"," +
                "\"height\": \"100%\"," +
                "\"events\": {" +
                "\"onReady\": \"onReady\"," +
                "\"onStateChange\": \"onStateChange\"," +
                "\"onPlaybackQualityChange\": \"onPlaybackQualityChange\"," +
                "\"onError\":\"onPlayerError\"" +
                "}," +
                "\"playerVars\": {" +
                "\"cc_load_policy\": 1," +
                "\"iv_load_policy\": 3," +
                "\"controls\": 0," +
                "\"playsinline\": 1," +
                "\"autohide\": 1," +
                "\"showinfo\": 0," +
                "\"rel\": 0," +
                "\"modestbranding\":1," +
                "\"start\":0" +
                "  }" +
                "}"
        val rawHtml: String = loadAssetTextAsString(context, "Youtube.html")
        return rawHtml.replace("%@".toRegex(), htmlParams)
    }

    private fun loadAssetTextAsString(context: Context, name: String): String {
        var `in`: BufferedReader? = null
        try {
            val buf = StringBuilder()
            val `is` = context.assets.open(name)
            `in` = BufferedReader(InputStreamReader(`is`))
            var str: String?
            var isFirst = true
            while (`in`.readLine().also { str = it } != null) {
                if (isFirst) isFirst = false else buf.append('\n')
                buf.append(str)
            }
            return buf.toString()
        } catch (e: IOException) {
            Log.e("Responsive", "Error opening asset $name")
        } finally {
            if (`in` != null) {
                try {
                    `in`.close()
                } catch (e: IOException) {
                    Log.e("Responsive", "Error closing asset $name")
                }
            }
        }
        return ""
    }

    fun loadVimeoIFrame(vimeoURL: String,isAutoPlay:Int=1): String {
        return "<html>" +
                "<head>" +
                "<meta http-equiv=\"X-UA-Compatible\" content=\"IE=Edge\"/>" +
                "<meta charset=\"utf-8\"/>" +
                "<meta name=\"viewport\"" +
                "content=\"user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, width=device-width\"/>" +
                "<style>" +
                "body { margin: 0; width:100%%; height:100%%; background-color:#000; }" +
                "html { width:100%%; height:100%%; background-color:#000; }" +
                ".container iframe, .container object, .container embed { position: absolute;top: 0; left: 0; width: 100%% !important; height: 100%% !important; }" +
                "</style>" +
                "</head>" +
                "<body>" +
                "<script src=\"https://player.vimeo.com/api/player.js\"></script>" +
                "<style>" +
                ".embed-container {" +
                "position: relative;" +
                "padding-bottom: 56.25%;" +
                "height: 0;" +
                "overflow: hidden;" +
                "max-width: 100%;" +
                "}" +
                ".embed-container iframe, .embed-container object, .embed-container embed {" +
                "position: absolute;" +
                "top: 0;" +
                "left: 0;" +
                "width: 100%;" +
                "height: 100%;" +
                "}" +
                "</style>" +
                "<div class=\"embed-container\">" +
                "<iframe title=\"vimeo\" width=\"100%\" height=\"100%\" src=\"" + vimeoURL+"?autoplay=$isAutoPlay"+ "\"" +
                "frameBorder=\"0\" allowfullscreen=\"allowfullscreen\" mozallowfullscreen=\"mozallowfullscreen\" msallowfullscreen=\"msallowfullscreen\" oallowfullscreen=\"oallowfullscreen\" webkitallowfullscreen=\"webkitallowfullscreen\"></iframe>" +
                "</iframe>" +
                "</div>" +
                "</body>" +
                "</html>"
    }

    fun loadYokuIframe(webView:WebView,videoID: String?): String {
        return  "<!doctype html>" +
                "<html class=\"no-js\" lang=\"\">" +
                "<head>" +
                "    <meta charset=\"utf-8\">" +
                "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">    " +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">" +
                "    <meta name=\"description\" content=\"\">" +
                "<style>" +
                "body { margin: 0; width:100%%; height:100%%; background-color:#000; }" +
                "html { width:100%%; height:100%%; background-color:#000; }" +
                ".container iframe, .container object, .container embed { position: absolute;top: 0; left: 0; width: 100%% !important; height: 100%% !important; }" +
                "</style>" +
                "<script src=\" https://player.youku.com/unifull/js/youku-player.umd.min.js?v=20190416\"></script>" +
                "</head>" +
                "<body>" +
                "<div class=\"video\">" +
                "<iframe id=\"test\" src=\"https://player.youku.com/embed/" + videoID + "\" frameborder=\"0\"" +
                "allowfullscreen=\"allowfullscreen\" style=\"position: absolute; top: 0; left: 0; width: 100%; height: 100%;\">" +
                "</iframe>" +
                "</div>" +
                "</body>" +
                "</html>"
    }

    fun loadEpitomeIFrame(videoId: String?): String {
        return "<html>" + "<head>" +
                "<meta http-equiv=\"X-UA-Compatible\" content=\"IE=Edge\"/>" +
                "<meta charset=\"utf-8\"/>" +
                "<meta name=\"viewport\"" +
                "content=\"user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, width=device-width\"/>" +
                "<style>" +
                "body { margin: 0; width:100%%; height:100%%; background-color:#000; }" +
                "html { width:100%%; height:100%%; background-color:#000; }" +
                ".container iframe, .container object, .container embed { position: absolute;top: 0; left: 0; width: 100%% !important; height: 100%% !important; }" +
                "</style>" +
                "</head>" +
                "<body>" +
                "<style>" +
                ".embed-container {" +
                "position: relative;" +
                "padding-bottom: 56.25%;" +
                "height: 0;" +
                "overflow: hidden;" +
                "max-width: 100%;" +
                "}" +
                ".embed-container iframe, .embed-container object, .embed-container embed {" +
                "position: absolute;" +
                "top: 0;" +
                "left: 0;" +
                "width: 100%;" +
                "height: 100%;" +
                "}" +
                "</style>" +
                "<div class='embed-container'>" +
                /*"<video id=\"video\" width=\"100%\" height=\"100%\" controls playsinline autoplay>" +
                "<source src=\"" + videoId + "\">" +
                "</video>"+*/
                "<iframe height='100%' width='100%' src=\"" + videoId + "\"" + " \" frameBorder=\"0\" allowfullscreen=\"allowfullscreen\" mozallowfullscreen=\"mozallowfullscreen\" msallowfullscreen=\"msallowfullscreen\" oallowfullscreen=\"oallowfullscreen\" webkitallowfullscreen=\"webkitallowfullscreen\" style='border:0' onerror=\"alert('Failed')\">" +
                "</iframe>" +
                "</div>" +
                "</body>" +
                "</html>"
    }

    fun loadEmbedAppHtml(embedCode: String) : String{
        return  "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "<style>\n" +
                "a,abbr,acronym,address,applet,article,aside,audio,b,big,blockquote," +
                "body,canvas,caption,center,cite,code,dd,del,details,dfn,div,dl,dt,em," +
                "embed,fieldset,figcaption,figure,footer,form,h1,h2,h3,h4,h5,h6,header,hgroup," +
                "html,i,iframe,img,ins,kbd,label,legend,li,mark,menu,nav,object,ol,output,pre,q," +
                "ruby,s,samp,section,small,span,strike,strong,sub,summary,sup,table,tbody," +
                "td,textarea,tfoot,th,thead,time,tr,tt,u,ul,var,video {\n" +
                "background-color : transparent; \n" +
                "width : 100%!important; \n" +
                "height : 100%!important;\n" +
                "margin : 0;\n" +
                "padding : 0;\n" +
                "outline : none;\n" +
                "vertical-align : baseline;\n" +
                "border : 0;" +
                "}\n" +
                "</style>\n" +
                "</head>\n" +
                "<body>\n" +
                embedCode +
                "</body>\n" +
                "</html>"
    }
}
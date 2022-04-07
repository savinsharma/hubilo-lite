
package com.hubilo.lite;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

public class YoutubeChromeClient extends WebChromeClient {
    protected FrameLayout mFullscreenContainer;
    private View mCustomView;
    private WebChromeClient.CustomViewCallback mCustomViewCallback;
    private int mOriginalOrientation;
    private int mOriginalSystemUiVisibility;
    private ProgressBar progressBar;
    private Activity activity;

    public YoutubeChromeClient(Activity activity) {
        this.activity = activity;
    }

    public Bitmap getDefaultVideoPoster() {
        if (this == null) {
            return null;
        }
        return BitmapFactory.decodeResource(activity.getResources(), 2130837573);
    }

    @Override
    public void onProgressChanged(WebView view, int progress) {
        super.onProgressChanged(view, progress);
    }

    public void onHideCustomView() {
        ((FrameLayout) activity.getWindow().getDecorView()).removeView(this.mCustomView);
        this.mCustomView = null;
        activity.getWindow().getDecorView().setSystemUiVisibility(this.mOriginalSystemUiVisibility);
        /*setRequestedOrientation(this.mOriginalOrientation);*/
        this.mCustomViewCallback.onCustomViewHidden();
        this.mCustomViewCallback = null;
        if (activity instanceof SessionStreamingActivity) {
            activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
    }

    public void onShowCustomView(View paramView, CustomViewCallback paramCustomViewCallback) {
        if (this.mCustomView != null) {
            onHideCustomView();
            return;
        }
        this.mCustomView = paramView;
        this.mOriginalSystemUiVisibility = activity.getWindow().getDecorView().getSystemUiVisibility();
        this.mOriginalOrientation = activity.getRequestedOrientation();
        this.mCustomViewCallback = paramCustomViewCallback;
        ((FrameLayout) activity.getWindow().getDecorView()).addView(this.mCustomView, new FrameLayout.LayoutParams(-1, -1));
        this.mCustomView.setBackgroundColor(Color.BLACK);
        activity.getWindow().getDecorView().setSystemUiVisibility(3846);
        if (activity instanceof SessionStreamingActivity) {
            activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_FULL_SENSOR);
        }
    }
}
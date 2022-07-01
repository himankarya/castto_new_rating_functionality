package com.soomapps.screenmirroring.activities;

import android.app.ProgressDialog;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import com.google.android.gms.ads.AdView;
import com.soomapps.screenmirroring.R;
import com.soomapps.screenmirroring.Util.AppConstants;

public class PrivacyPolicyActivity extends AppCompatActivity {
    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    WebView webView;
    AdView adView;
    ProgressDialog progressDialog;
    @RequiresApi(api = Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_policy);

        if (android.os.Build.VERSION.SDK_INT >= 21) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(getResources().getString(R.string.privacy_policy));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //setTitle(getResources().getString(R.string.privacy_policy));
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        webView = (WebView)findViewById(R.id.webView);

        adView=(AdView)findViewById(R.id.adView);
        AppConstants.codeForBannerAd(PrivacyPolicyActivity.this,adView);

        if(AppConstants.checkInternetConnection(PrivacyPolicyActivity.this)){
            adView.setVisibility(View.VISIBLE);
        }else{
            adView.setVisibility(View.GONE);
        }

        webView.getSettings().getJavaScriptEnabled();
        WebSettings webSetting = webView.getSettings();
        webSetting.setTextZoom(120);
        webSetting.setSupportZoom(true);
        webSetting.setBuiltInZoomControls(true);
        webSetting.setDisplayZoomControls(true);
        webView.setWebViewClient(new WebViewClient());

            webView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                return true;
            }
        });

        //webView.setWebViewClient(new HostsWebClient());
        webView.getSettings().setPluginState(WebSettings.PluginState.ON);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(false);
        //webView.getSettings().setPluginState(true);
        webView.getSettings().setSupportMultipleWindows(false);
        webView.getSettings().setSupportZoom(false);
        webView.setVerticalScrollBarEnabled(false);
        webView.setHorizontalScrollBarEnabled(false);

        webView.loadUrl("https://www.soomapps.com/castto-privacy-policy/");

        progressDialog = new ProgressDialog(PrivacyPolicyActivity.this);
        progressDialog.setMessage("Loadin45g");
        progressDialog.setCancelable(true);
        progressDialog.show();

        webView.setWebViewClient(new android.webkit.WebViewClient() {

            public void onPageFinished(WebView view, String url) {
                try {
                    progressDialog.dismiss();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    @Override
    protected void onPause() {
        super.onPause();
        if (adView != null) {
            adView.pause();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (adView != null) {
            adView.resume();
        }
    }
    @Override
    public void onDestroy() {
        if (adView != null) {
            adView.destroy();
        }
        super.onDestroy();
    }
    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }

}

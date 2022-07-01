package com.soomapps.screenmirroring.activities;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.icu.text.IDNA;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import com.google.ads.mediation.facebook.FacebookAdapter;
import com.google.ads.mediation.facebook.FacebookExtras;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.VideoController;
import com.google.android.gms.ads.VideoOptions;
import com.google.android.gms.ads.formats.NativeAdOptions;
import com.google.android.gms.ads.formats.UnifiedNativeAd;
import com.google.android.gms.ads.formats.UnifiedNativeAdView;
import com.soomapps.screenmirroring.R;
import com.soomapps.screenmirroring.Util.AppConstants;

public class InfoActivity extends AppCompatActivity {

    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    AdView adView;
    LinearLayout ll_ad, ll_arab, ll_common;
    SharedPreferences sharedpreferences;
    SharedPreferences.Editor editor;
    ImageView next;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_part_01);

        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);

        ll_arab = (LinearLayout) findViewById(R.id.ll_arab);
      //  ll_common = (LinearLayout) findViewById(R.id.ll_common);
        next= (ImageView) findViewById(R.id.next);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(InfoActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        sharedpreferences = getSharedPreferences("mypref",
                Context.MODE_PRIVATE);

        if (android.os.Build.VERSION.SDK_INT >= 21) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
        }

        ll_ad = (LinearLayout) findViewById(R.id.ll_ad);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(getResources().getString(R.string.instructions));
        //toolbar.setElevation(1);
        //setTitle(getResources().getString(R.string.instructions));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        refreshmainAd();

        if (!AppConstants.checkInternetConnection(InfoActivity.this)) {
            ll_ad.setVisibility(View.GONE);
        }

    }

    @SuppressLint("MissingPermission")
    private void refreshmainAd() {
        // refresh.setEnabled(false);
        AdLoader.Builder builder = new AdLoader.Builder(this, getString(R.string.native_ad));
        builder.forUnifiedNativeAd(new UnifiedNativeAd.OnUnifiedNativeAdLoadedListener() {
            // OnUnifiedNativeAdLoadedListener implementation.
            @Override
            public void onUnifiedNativeAdLoaded(UnifiedNativeAd unifiedNativeAd) {
                LinearLayout frameLayout =
                        findViewById(R.id.fl_adplaceholder);
                UnifiedNativeAdView adView = (UnifiedNativeAdView) getLayoutInflater()
                        .inflate(R.layout.ad_unified_main, null);

                // I add this code for Using native ads (Code Start)

                Bundle extras = unifiedNativeAd.getExtras();
                if (extras.containsKey(FacebookAdapter.KEY_SOCIAL_CONTEXT_ASSET)) {
                    String socialContext = (String) extras.get(FacebookAdapter.KEY_SOCIAL_CONTEXT_ASSET);
                    Log.d("social", "" + socialContext);
                }

                // I add this code for Using native ads (Code end)

                populateUnifiedNativeAdView(unifiedNativeAd, adView);
                frameLayout.removeAllViews();
                frameLayout.addView(adView);
            }

        });

        VideoOptions videoOptions = new VideoOptions.Builder()
                .setStartMuted(true)
                .build();

        NativeAdOptions adOptions = new NativeAdOptions.Builder()
                .setVideoOptions(videoOptions)
                .build();

        builder.withNativeAdOptions(adOptions);

        AdLoader adLoader = builder.withAdListener(new AdListener() {
            @Override
            public void onAdFailedToLoad(int errorCode) {
                // refresh.setEnabled(true);
               /* Toast.makeText(ScanResultActivity.this, "Failed to load native ad: "
                        + errorCode, Toast.LENGTH_SHORT).show();*/
            }

            @Override
            public void onAdLoaded() {
                ll_ad.setVisibility(View.VISIBLE);
                super.onAdLoaded();
            }
        }).build();


        // I add this code for Using Facebook Native Ads without a MediaView (Code Start)

        Bundle extras = new FacebookExtras()
                .setNativeBanner(true)
                .build();

        adLoader.loadAd(new AdRequest.Builder().addNetworkExtrasBundle(FacebookAdapter.class, extras).build());

        // I add this code for Using Facebook Native Ads without a MediaView (Code end)


        //videoStatus.setText("");
    }

    private void populateUnifiedNativeAdView(UnifiedNativeAd nativeAd, UnifiedNativeAdView adView) {
        // Set the media view. Media content will be automatically populated in the media view once
        // adView.setNativeAd() is called.
       /* MediaView mediaView = adView.findViewById(R.id.ad_media);
        adView.setMediaView(mediaView);
*/
        // Set other ad assets.
        adView.setHeadlineView(adView.findViewById(R.id.ad_headline));
        adView.setBodyView(adView.findViewById(R.id.ad_body));
        adView.setCallToActionView(adView.findViewById(R.id.ad_call_to_action));
        adView.setIconView(adView.findViewById(R.id.ad_app_icon));
        adView.setPriceView(adView.findViewById(R.id.ad_price));
        adView.setStarRatingView(adView.findViewById(R.id.ad_stars));
        adView.setStoreView(adView.findViewById(R.id.ad_store));
        adView.setAdvertiserView(adView.findViewById(R.id.ad_advertiser));

        // The headline is guaranteed to be in every UnifiedNativeAd.
        ((TextView) adView.getHeadlineView()).setText(nativeAd.getHeadline());
        // These assets aren't guaranteed to be in every UnifiedNativeAd, so it's important to
        // check before trying to display them.
        if (nativeAd.getBody() == null) {
            adView.getBodyView().setVisibility(View.INVISIBLE);
        } else {
            adView.getBodyView().setVisibility(View.VISIBLE);
            ((TextView) adView.getBodyView()).setText(nativeAd.getBody());
        }

        if (nativeAd.getCallToAction() == null) {
            adView.getCallToActionView().setVisibility(View.INVISIBLE);
        } else {
            adView.getCallToActionView().setVisibility(View.VISIBLE);
            ((Button) adView.getCallToActionView()).setText(nativeAd.getCallToAction());
        }

        if (nativeAd.getIcon() == null) {
            adView.getIconView().setVisibility(View.GONE);
        } else {
            ((ImageView) adView.getIconView()).setImageDrawable(
                    nativeAd.getIcon().getDrawable());
            adView.getIconView().setVisibility(View.VISIBLE);
        }

        if (nativeAd.getPrice() == null) {
            adView.getPriceView().setVisibility(View.GONE);
        } else {
            adView.getPriceView().setVisibility(View.GONE);
            ((TextView) adView.getPriceView()).setText(nativeAd.getPrice());
        }

        if (nativeAd.getStore() == null) {
            adView.getStoreView().setVisibility(View.GONE);
        } else {
            adView.getStoreView().setVisibility(View.GONE);
            ((TextView) adView.getStoreView()).setText(nativeAd.getStore());
        }

        if (nativeAd.getStarRating() == null) {
            adView.getStarRatingView().setVisibility(View.GONE);
        } else {
            ((RatingBar) adView.getStarRatingView()).setRating(nativeAd.getStarRating().floatValue());
            adView.getStarRatingView().setVisibility(View.GONE);
        }

        if (nativeAd.getAdvertiser() == null) {
            adView.getAdvertiserView().setVisibility(View.GONE);
        } else {
            ((TextView) adView.getAdvertiserView()).setText(nativeAd.getAdvertiser());
            adView.getAdvertiserView().setVisibility(View.GONE);
        }

        // This method tells the Google Mobile Ads SDK that you have finished populating your
        // native ad view with this native ad. The SDK will populate the adView's MediaView
        // with the media content from this native ad.
        adView.setNativeAd(nativeAd);
        // Get the video controller for the ad. One will always be provided, even if the ad doesn't
        // have a video asset.
        VideoController vc = nativeAd.getVideoController();

        // Updates the UI to say whether or not this ad has a video asset.
        if (vc.hasVideoContent()) {
           /* videoStatus.setText(String.format(Locale.getDefault(),
                    "Video status: Ad contains a %.2f:1 video asset.",
                    vc.getAspectRatio()));*/

            // Create a new VideoLifecycleCallbacks object and pass it to the VideoController. The
            // VideoController will call methods on this object when events occur in the video
            // lifecycle.
            vc.setVideoLifecycleCallbacks(new VideoController.VideoLifecycleCallbacks() {
                @Override
                public void onVideoEnd() {
                    // Publishers should allow native ads to complete video playback before
                    // refreshing or replacing them with another ad in the same UI location.
                   /* refresh.setEnabled(true);
                    videoStatus.setText("Video status: Video playback has ended.");*/
                    super.onVideoEnd();
                }
            });
        } else {
            /*videoStatus.setText("Video status: Ad does not contain a video asset.");
            refresh.setEnabled(true);*/
        }
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
      /*  if((Locale.getDefault().getLanguage().equalsIgnoreCase("ar")&&
                sharedpreferences.getString("arab",null).equalsIgnoreCase("yes"))||
                Locale.getDefault().getLanguage().equalsIgnoreCase("ar")){
            ll_arab.setVisibility(View.GONE);
            ll_common.setVisibility(View.VISIBLE);
            Toast.makeText(InfoActivity.this,"1 "+Locale.getDefault().getLanguage(),Toast.LENGTH_LONG).show();
        }else if(!Locale.getDefault().getLanguage().equalsIgnoreCase("ar")&&
                !sharedpreferences.getString("arab",null).equalsIgnoreCase("yes")){
            ll_arab.setVisibility(View.GONE);
            ll_common.setVisibility(View.VISIBLE);
            Toast.makeText(InfoActivity.this,"2 "+Locale.getDefault().getLanguage(),Toast.LENGTH_LONG).show();
        }else if(sharedpreferences.getString("arab",null).equalsIgnoreCase("yes")&&
                !Locale.getDefault().getLanguage().equalsIgnoreCase("ar")){
            ll_arab.setVisibility(View.GONE);
            ll_common.setVisibility(View.VISIBLE);
            Toast.makeText(InfoActivity.this,"3 "+Locale.getDefault().getLanguage(),Toast.LENGTH_LONG).show();
        }else if(sharedpreferences.getString("arab",null).equalsIgnoreCase("yes")){
            ll_arab.setVisibility(View.VISIBLE);
            ll_common.setVisibility(View.GONE);
            Toast.makeText(InfoActivity.this,"4 "+Locale.getDefault().getLanguage(),Toast.LENGTH_LONG).show();
        }else{
            ll_arab.setVisibility(View.GONE);
            ll_common.setVisibility(View.VISIBLE);
            Toast.makeText(InfoActivity.this,"4 "+Locale.getDefault().getLanguage(),Toast.LENGTH_LONG).show();
        }*/
    }

    @Override
    public void onDestroy() {
        if (adView != null) {
            adView.destroy();
        }
        super.onDestroy();
    }
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}

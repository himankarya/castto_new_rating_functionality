package com.soomapps.screenmirroring.activities;

//import static org.acra.ACRA.LOG_TAG;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.content.ContextCompat;
import com.agrawalsuneet.dotsloader.loaders.TashieLoader;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.soomapps.screenmirroring.MyApplication;
import com.soomapps.screenmirroring.R;
import com.soomapps.screenmirroring.Util.AppConstants;
import java.util.Locale;
import java.util.Random;

public class SplashActivity extends AppCompatActivity {
    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    InterstitialAd mInterstitialAd;
    private static int SPLASH_TIME_OUT = 12000;///12000 will be set
    Locale myLocale;
    String currentLanguage;
    SharedPreferences sharedpreferences;
    SharedPreferences.Editor editor;
    ProgressBar progressBar;
    private SharedPreferences pref;
    LinearLayout tashieLoader;
    RelativeLayout activity_splash;

    @SuppressLint({"NewApi", "MissingPermission"})
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.SplashTheme);
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
//       getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//       WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.splash);

        MobileAds.initialize(
                this,
                new OnInitializationCompleteListener() {
                    @Override
                    public void onInitializationComplete(
                            @NonNull InitializationStatus initializationStatus) {
                    }
                });


        Random r = new Random();
        int i1 = r.nextInt((12000 - 2000) + 1) + 2000;

//       progressBar=(ProgressBar)findViewById(R.id.progress_bar);
        tashieLoader = (LinearLayout) findViewById(R.id.tashieLoader);
        ImageView splash_image = (ImageView) findViewById(R.id.splash_image);
        TextView splash_text = (TextView) findViewById(R.id.splash_text);
        splash_text.setText("Castto © SoomApps");

//        Animation animation;
//        animation = AnimationUtils.loadAnimation(getApplicationContext(),
//                R.anim.fade_in);
//
//        splash_image.startAnimation(animation);

//        Handler handler1=new Handler();
//        handler1.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                 TextView splash_text = (TextView) findViewById(R.id.splash_text);
//                 splash_text.setText("Castto © SoomApps");
//
//                 Animation myAnim = AnimationUtils.loadAnimation(SplashActivity.this, R.anim.slide_up_splash);
//
//                splash_text.startAnimation(myAnim);
//
//            }
//        },2000);

//        Animation animation1;
//        animation1 = AnimationUtils.loadAnimation(getApplicationContext(),
//                R.anim.slide_up_splash);
//
//        splash_text.startAnimation(animation1);

        currentLanguage = Locale.getDefault().getLanguage();
        MobileAds.initialize(this, getResources().getString(R.string.app_id));
        sharedpreferences = getSharedPreferences("mypref", Context.MODE_PRIVATE);
        editor = sharedpreferences.edit();

        Log.e("My Language is ", "" + Locale.getDefault().getLanguage());

        if (Locale.getDefault().getLanguage().equalsIgnoreCase("ar")) {
            editor.putString("arab", "yes");
        } else {
            editor.putString("arab", "no");
        }
        editor.commit();
        if (AppConstants.checkInternetConnection(SplashActivity.this)) {

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    TashieLoader tashie = new TashieLoader(
                            getApplicationContext(), 10,
                            10, 10,
                            ContextCompat.getColor(getApplicationContext(), R.color.white));
                    tashie.setAnimDuration(500);
                    tashie.setAnimDelay(100);
                    tashie.setInterpolator(new LinearInterpolator());
                    tashieLoader.addView(tashie);
                    //showNextScreen();
                }

            }, 200);

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    showFullScreenAd();
                    //showNextScreen();
                }

            }, i1);
        } else {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    TashieLoader tashie = new TashieLoader(
                            getApplicationContext(), 10,
                            10, 10,
                            ContextCompat.getColor(getApplicationContext(), R.color.white));
                    tashie.setAnimDuration(500);
                    tashie.setAnimDelay(100);
                    tashie.setInterpolator(new LinearInterpolator());
                    tashieLoader.addView(tashie);
                    //showNextScreen();
                }

            }, 200);

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    showNextScreen();
                    //showNextScreen();
                }

            }, 4000);

        }
//        AppConstants.onWifi(SplashActivity.this);
        pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("star", "1");
        editor.commit();

    }

    @SuppressLint("MissingPermission")
    public void loadFullAd() {

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(getString(R.string.interstitial_full_screen));
        if (!mInterstitialAd.isLoading() && !mInterstitialAd.isLoaded()) {
            AdRequest.Builder builder = new AdRequest.Builder();
            /*.addTestDevice(AdRequest.DEVICE_ID_EMULATOR);*/
            mInterstitialAd.loadAd(builder.build());
        }
    }

    public void showFullScreenAd() {
//        Application application = getApplication();
//
//        ((MyApplication) application)
//                .showAdIfAvailable(
//                        SplashActivity.this,
//                        new MyApplication.OnShowAdCompleteListener() {
//                            @Override
//                            public void onShowAdComplete() {
//                                showNextScreen();
//                            }
//                        });
//        if (!(application instanceof MyApplication)) {
//            //  Log.e(LOG_TAG, "Failed to cast application to MyApplication.");
//            showNextScreen();
//
//            return;
//        } else if (!(application instanceof MyApplication)) {
//            new Handler().postDelayed(new Runnable() {
//                @Override
//                public void run() {
//
//                    showNextScreen();
//                    //showNextScreen();
//                }
//
//            }, SPLASH_TIME_OUT);
//
////            showNextScreen();
//        }
//    }

        if (mInterstitialAd != null && mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
            mInterstitialAd.setAdListener(new AdListener() {
                @Override
                public void onAdClosed() {
                    super.onAdClosed();
                    showNextScreen();

                }
            });
        } else {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {

                    showNextScreen();
                    //showNextScreen();
                }

            }, SPLASH_TIME_OUT);

//            showNextScreen();
        }
    }

    public void showNextScreen() {
        setLocale();
    }

    @Override
    protected void onResume() {
        super.onResume();
          loadFullAd();
    }

    public void setLocale() {
        // Log.e("Current Language is","  <><><>  "+currentLanguage);
        if (currentLanguage.equalsIgnoreCase("en")) {
            myLocale = new Locale("en");
            Resources res = getResources();
            DisplayMetrics dm = res.getDisplayMetrics();
            Configuration conf = res.getConfiguration();
            conf.locale = myLocale;
            res.updateConfiguration(conf, dm);
            Intent refresh = new Intent(this, MainActivity.class);
            startActivity(refresh);
           // finish();
        } else if (currentLanguage.equalsIgnoreCase("fr")) {
            myLocale = new Locale("fr");
            Resources res = getResources();
            DisplayMetrics dm = res.getDisplayMetrics();
            Configuration conf = res.getConfiguration();
            conf.locale = myLocale;
            res.updateConfiguration(conf, dm);
            Intent refresh = new Intent(this, MainActivity.class);
            startActivity(refresh);
           // finish();
        } else if (currentLanguage.equalsIgnoreCase("es")) {
            myLocale = new Locale("es");
            Resources res = getResources();
            DisplayMetrics dm = res.getDisplayMetrics();
            Configuration conf = res.getConfiguration();
            conf.locale = myLocale;
            res.updateConfiguration(conf, dm);
            Intent refresh = new Intent(this, MainActivity.class);
            startActivity(refresh);
           // finish();
        } else if (currentLanguage.equalsIgnoreCase("pt")) {
            myLocale = new Locale("pt");
            Resources res = getResources();
            DisplayMetrics dm = res.getDisplayMetrics();
            Configuration conf = res.getConfiguration();
            conf.locale = myLocale;
            res.updateConfiguration(conf, dm);
            Intent refresh = new Intent(this, MainActivity.class);
            startActivity(refresh);
           // finish();
        } else if (currentLanguage.equalsIgnoreCase("ar")) {
            myLocale = new Locale("ar");
            Resources res = getResources();
            DisplayMetrics dm = res.getDisplayMetrics();
            Configuration conf = res.getConfiguration();
            conf.locale = myLocale;
            res.updateConfiguration(conf, dm);
            Intent refresh = new Intent(this, MainActivity.class);
            startActivity(refresh);
          //  finish();
        } else if (currentLanguage.equalsIgnoreCase("cs")) {
            myLocale = new Locale("cs");
            Resources res = getResources();
            DisplayMetrics dm = res.getDisplayMetrics();
            Configuration conf = res.getConfiguration();
            conf.locale = myLocale;
            res.updateConfiguration(conf, dm);
            Intent refresh = new Intent(this, MainActivity.class);
            startActivity(refresh);
           // finish();
        } else if (currentLanguage.equalsIgnoreCase("fil")) {
            myLocale = new Locale("fil");
            Resources res = getResources();
            DisplayMetrics dm = res.getDisplayMetrics();
            Configuration conf = res.getConfiguration();
            conf.locale = myLocale;
            res.updateConfiguration(conf, dm);
            Intent refresh = new Intent(this, MainActivity.class);
            startActivity(refresh);
           // finish();
        } else if (currentLanguage.equalsIgnoreCase("de")) {
            myLocale = new Locale("de");
            Resources res = getResources();
            DisplayMetrics dm = res.getDisplayMetrics();
            Configuration conf = res.getConfiguration();
            conf.locale = myLocale;
            res.updateConfiguration(conf, dm);
            Intent refresh = new Intent(this, MainActivity.class);
            startActivity(refresh);
           //  finish();
        } else if (currentLanguage.equalsIgnoreCase("in")) {
            myLocale = new Locale("in");
            Resources res = getResources();
            DisplayMetrics dm = res.getDisplayMetrics();
            Configuration conf = res.getConfiguration();
            conf.locale = myLocale;
            res.updateConfiguration(conf, dm);
            Intent refresh = new Intent(this, MainActivity.class);
            startActivity(refresh);
           // finish();
        } else if (currentLanguage.equalsIgnoreCase("it")) {
            myLocale = new Locale("it");
            Resources res = getResources();
            DisplayMetrics dm = res.getDisplayMetrics();
            Configuration conf = res.getConfiguration();
            conf.locale = myLocale;
            res.updateConfiguration(conf, dm);
            Intent refresh = new Intent(this, MainActivity.class);
            startActivity(refresh);
           //  finish();
        } else if (currentLanguage.equalsIgnoreCase("ko")) {
            myLocale = new Locale("ko");
            Resources res = getResources();
            DisplayMetrics dm = res.getDisplayMetrics();
            Configuration conf = res.getConfiguration();
            conf.locale = myLocale;
            res.updateConfiguration(conf, dm);
            Intent refresh = new Intent(this, MainActivity.class);
            startActivity(refresh);
           //  finish();
        } else if (currentLanguage.equalsIgnoreCase("ms")) {
            myLocale = new Locale("ms");
            Resources res = getResources();
            DisplayMetrics dm = res.getDisplayMetrics();
            Configuration conf = res.getConfiguration();
            conf.locale = myLocale;
            res.updateConfiguration(conf, dm);
            Intent refresh = new Intent(this, MainActivity.class);
            startActivity(refresh);
           // finish();
        } else if (currentLanguage.equalsIgnoreCase("pl")) {
            myLocale = new Locale("pl");
            Resources res = getResources();
            DisplayMetrics dm = res.getDisplayMetrics();
            Configuration conf = res.getConfiguration();
            conf.locale = myLocale;
            res.updateConfiguration(conf, dm);
            Intent refresh = new Intent(this, MainActivity.class);
            startActivity(refresh);
           // finish();
        } else if (currentLanguage.equalsIgnoreCase("ru")) {
            myLocale = new Locale("ru");
            Resources res = getResources();
            DisplayMetrics dm = res.getDisplayMetrics();
            Configuration conf = res.getConfiguration();
            conf.locale = myLocale;
            res.updateConfiguration(conf, dm);
            Intent refresh = new Intent(this, MainActivity.class);
            startActivity(refresh);
           //   finish();
        } else if (currentLanguage.equalsIgnoreCase("sr")) {
            myLocale = new Locale("sr");
            Resources res = getResources();
            DisplayMetrics dm = res.getDisplayMetrics();
            Configuration conf = res.getConfiguration();
            conf.locale = myLocale;
            res.updateConfiguration(conf, dm);
            Intent refresh = new Intent(this, MainActivity.class);
            startActivity(refresh);
           // finish();
        } else if (currentLanguage.equalsIgnoreCase("sl")) {
            myLocale = new Locale("sl");
            Resources res = getResources();
            DisplayMetrics dm = res.getDisplayMetrics();
            Configuration conf = res.getConfiguration();
            conf.locale = myLocale;
            res.updateConfiguration(conf, dm);
            Intent refresh = new Intent(this, MainActivity.class);
            startActivity(refresh);
           // finish();
        } else if (currentLanguage.equalsIgnoreCase("sv")) {
            myLocale = new Locale("sv");
            Resources res = getResources();
            DisplayMetrics dm = res.getDisplayMetrics();
            Configuration conf = res.getConfiguration();
            conf.locale = myLocale;
            res.updateConfiguration(conf, dm);
            Intent refresh = new Intent(this, MainActivity.class);
            startActivity(refresh);
          //  finish();
        } else if (currentLanguage.equalsIgnoreCase("th")) {
            myLocale = new Locale("th");
            Resources res = getResources();
            DisplayMetrics dm = res.getDisplayMetrics();
            Configuration conf = res.getConfiguration();
            conf.locale = myLocale;
            res.updateConfiguration(conf, dm);
            Intent refresh = new Intent(this, MainActivity.class);
            startActivity(refresh);
           // finish();
        } else if (currentLanguage.equalsIgnoreCase("tr")) {
            myLocale = new Locale("tr");
            Resources res = getResources();
            DisplayMetrics dm = res.getDisplayMetrics();
            Configuration conf = res.getConfiguration();
            conf.locale = myLocale;
            res.updateConfiguration(conf, dm);
            Intent refresh = new Intent(this, MainActivity.class);
            startActivity(refresh);
          //  finish();
        } else if (currentLanguage.equalsIgnoreCase("vi")) {
            myLocale = new Locale("vi");
            Resources res = getResources();
            DisplayMetrics dm = res.getDisplayMetrics();
            Configuration conf = res.getConfiguration();
            conf.locale = myLocale;
            res.updateConfiguration(conf, dm);
            Intent refresh = new Intent(this, MainActivity.class);
            startActivity(refresh);
          //  finish();
        } else if (currentLanguage.equalsIgnoreCase("hr")) {
            myLocale = new Locale("hr");
            Resources res = getResources();
            DisplayMetrics dm = res.getDisplayMetrics();
            Configuration conf = res.getConfiguration();
            conf.locale = myLocale;
            res.updateConfiguration(conf, dm);
            Intent refresh = new Intent(this, MainActivity.class);
            startActivity(refresh);
          //  finish();
        } else if (currentLanguage.equalsIgnoreCase("nl")) {
            myLocale = new Locale("nl");
            Resources res = getResources();
            DisplayMetrics dm = res.getDisplayMetrics();
            Configuration conf = res.getConfiguration();
            conf.locale = myLocale;
            res.updateConfiguration(conf, dm);
            Intent refresh = new Intent(this, MainActivity.class);
            startActivity(refresh);
           // finish();
        } else {
            myLocale = new Locale("en");
            Resources res = getResources();
            DisplayMetrics dm = res.getDisplayMetrics();
            Configuration conf = res.getConfiguration();
            conf.locale = myLocale;
            res.updateConfiguration(conf, dm);
            Intent refresh = new Intent(this, MainActivity.class);
            startActivity(refresh);
          //  finish();
        }

    }
}

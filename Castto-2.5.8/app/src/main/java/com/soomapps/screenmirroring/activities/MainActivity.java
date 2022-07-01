package com.soomapps.screenmirroring.activities;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.ads.consent.ConsentForm;
import com.google.ads.consent.ConsentFormListener;
import com.google.ads.consent.ConsentInfoUpdateListener;
import com.google.ads.consent.ConsentInformation;
import com.google.ads.consent.ConsentStatus;
import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.ads.mediation.facebook.FacebookAdapter;
import com.google.ads.mediation.facebook.FacebookExtras;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.VideoController;
import com.google.android.gms.ads.VideoOptions;
import com.google.android.gms.ads.formats.NativeAdOptions;
import com.google.android.gms.ads.formats.UnifiedNativeAd;
import com.google.android.gms.ads.formats.UnifiedNativeAdView;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.navigation.NavigationView;
import com.soomapps.screenmirroring.R;
import com.soomapps.screenmirroring.Util.AppConstants;
import com.soomapps.screenmirroring.Util.SharedPreference;
import com.soomapps.screenmirroring.Util.Utility;
import com.soomapps.screenmirroring.fragments.BottomSheetFragment;

import java.net.MalformedURLException;
import java.net.URL;

import pl.droidsonroids.gif.GifImageView;

import static android.view.View.GONE;
import static androidx.fragment.app.DialogFragment.STYLE_NORMAL;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    Switch switch_button;
    InterstitialAd InterstitialAd;
    private InterstitialAd interstitial;
    AdView adView;
    InterstitialAd mInterstitialAd;
    LinearLayout ll_select, ll_info, ll_main;
    boolean doubleBackToExitPressedOnce = false;
    Button iv_info, iv_select;
    TextView tv_info, tv_select;
    DrawerLayout drawer;
    SharedPreferences sharedpreferences;
    SharedPreferences.Editor editor;
    int rating_value;
    LinearLayout ll_ad;
    String s;
    Dialog dialog;
    BottomSheetDialog bottomSheetDialog, bottomSheetDialograting;
    //thirdtimeADB
    SharedPreference sharedPreferenceisboolen;
    Boolean isboolean = true;
    private int count = 0;
    private ImageView iv_star_1;
    private ImageView iv_star_2;
    private ImageView iv_star_3;
    private ImageView iv_star_4;
    private ImageView iv_star_5;
    private LinearLayout ratinglayout;
    private GifImageView ratingGIF;
    private ImageView cancelIMG;
    private Handler handler;
    private ImageView imageView;
    public ImageView iv, wifi_on_off;
    View mViewToReveal;
    private SharedPreferences pref;
    //Ok,GOT IT popup
    public SharedPreference sharedPreferenceisboolennn;
    Boolean isbooleann = true;
    private int increment = 0;
    private TextView privacy_policy;
    private TextView learn;
    private LinearLayout ll_ok;
    private ConsentForm form;
    ActionBarDrawerToggle toggle;
    private ImageView rate_emoji;
    private TextView rate_text_1, rate_text_2;
    private  Button rate_submit;
    private WifiManager wifiManager;
    Thread thread;
    Runnable updater;
    Handler timerHandler;


    @SuppressLint({"RestrictedApi", "MissingPermission"})
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_final);

        try {
        } catch (Exception e) {
            if (e.getCause().getClass().equals(AssertionError.class)) {
                // handle your exception  1
            } else {
                // handle the rest of the world exception
            }
        }

        sharedPreferenceisboolennn = new SharedPreference(this);

        if (sharedPreferenceisboolennn.getValueBoolien("isbool", isbooleann) == true) {
            okgotit_dialog();

        } else {

        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        Drawable d = ResourcesCompat.getDrawable(getResources(), R.drawable.ic_baseline_menu_24, null);
        toolbar.setNavigationIcon(d);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(getResources().getString(R.string.app_title));
        MobileAds.initialize(this, getString(R.string.app_id));
        refreshAd();
        refreshmainAd();

        ll_ad = (LinearLayout) findViewById(R.id.ll_ad);
        ll_select = (LinearLayout) findViewById(R.id.ll_select);
        ll_info = (LinearLayout) findViewById(R.id.ll_info);
        tv_info = (TextView) findViewById(R.id.tv_info);
        tv_select = (TextView) findViewById(R.id.tv_select);
        iv_info = (Button) findViewById(R.id.iv_info);
        wifi_on_off = (ImageView) findViewById(R.id.wifi_on_off);

        ll_main = (LinearLayout) findViewById(R.id.ll_main);

        updateImage();

        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.KITKAT) {
            Drawable img = iv_info.getContext().getResources().getDrawable(R.drawable.filled_info_icon);
            iv_info.setCompoundDrawablesWithIntrinsicBounds(img, null, null, null);
        } else {
            Drawable img = iv_info.getContext().getResources().getDrawable(R.drawable.ic_info);
            iv_info.setCompoundDrawablesWithIntrinsicBounds(img, null, null, null);

        }

        iv_select = (Button) findViewById(R.id.iv_select);

        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.KITKAT) {
            Drawable img = iv_select.getContext().getResources().getDrawable(R.drawable.filled_tv_screen_updated);
            iv_select.setCompoundDrawablesWithIntrinsicBounds(img, null, null, null);
        } else {
            Drawable img = iv_select.getContext().getResources().getDrawable(R.drawable.ic_screen_updated);
            iv_select.setCompoundDrawablesWithIntrinsicBounds(img, null, null, null);

        }


        sharedpreferences = getSharedPreferences("mypref", Context.MODE_PRIVATE);

        if (sharedpreferences.getInt("repeat_value", 0) <= 4) {
            increaseValue();
        }

        if (!AppConstants.checkInternetConnection(MainActivity.this)) {
            ll_ad.setVisibility(GONE);
        }

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


        sharedPreferenceisboolen = new SharedPreference(this);

        if (sharedPreferenceisboolen.getValueBoolien("isbool", isboolean) == true) {
            thirdtimeADB();
            Log.e("sanjay  ", "" + sharedPreferenceisboolen.getValueBoolien("isbool", isboolean));

        } else {
            Log.e("sanjayFalse  ", "" + sharedPreferenceisboolen.getValueBoolien("isbool", isboolean));
        }

        drawer.bringToFront();
        drawer.requestLayout();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        iv_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (AppConstants.checkInternetConnection(MainActivity.this)) {
                    showFullScreenAd();
                } else {
                    showNextScreen();
                }
            }
        });


        iv_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (AppConstants.checkInternetConnection(MainActivity.this)) {
                    showAdd();
                } else {
                    showInfoScreen();
                }
            }
        });

        wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);

//        thread = new Thread() {
//            @Override
//            public void run() {
//                try {
//                    while (!thread.isInterrupted()) {
//                        Thread.sleep(50);
//                        runOnUiThread(new Runnable() {
//                            @Override
//                            public void run() {
//                                // update ImageView here!
//                                ConnectivityManager connManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
//                                NetworkInfo mWifi = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
//
//                                if (mWifi.isConnected()) {
//                                    wifi_on_off.setImageResource(R.drawable.wifi_on_new);
//                                    wifi_on_off.setOnClickListener(new View.OnClickListener() {
//                                        @Override
//                                        public void onClick(View v) {
//                                            wifi_on_off.setOnTouchListener(Utility.imgPress());
//                                            startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
//                                        }
//                                    });
//                                } else if (!mWifi.isConnected()) {
//                                    wifi_on_off.setImageResource(R.drawable.wifi_cross);
//
//                                    wifi_on_off.setOnClickListener(new View.OnClickListener() {
//                                        @Override
//                                        public void onClick(View v) {
//                                            wifi_on_off.setOnTouchListener(Utility.imgPress());
//
//                                            startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
//
//                                        }
//                                    });
//
//                                }
//                            }
//                        });
//                    }
//                } catch (InterruptedException e) {
//                }
//            }
//        };
//        thread.start();



//        iv_select.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View view, MotionEvent motionEvent) {
//                switch (motionEvent.getAction()) {
//                    case MotionEvent.ACTION_DOWN:
//                        iv_select.setPressed(true);
//                        // PRESSED
//                      //  tv_select.setTextColor(getResources().getColor(R.color.select_language_arrow_color));
//                        return true; // if you want to handle the touch event
//                    case MotionEvent.ACTION_UP:
//                        iv_select.setPressed(false);
//                        // RELEASED
//                      //  tv_select.setTextColor(getResources().getColor(R.color.white));
//                        if (AppConstants.checkInternetConnection(MainActivity.this)) {
//                            showFullScreenAd();
//                        } else {
//                            showNextScreen();
//                        }
//                        return true; // if you want to handle the touch event
//                    default:
//                      //  tv_select.setTextColor(getResources().getColor(R.color.white));
//                }
//                return false;
//            }
//        });

//        iv_info.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View view, MotionEvent motionEvent) {
//                switch (motionEvent.getAction()) {
//                    case MotionEvent.ACTION_DOWN:
//                        // PRESSED
//                        //tv_info.setTextColor(getResources().getColor(R.color.select_language_arrow_color));
//                       // iv_info.setBackgroundResource(R.drawable.info_dark);
//                        return true; // if you want to handle the touch event
//                    case MotionEvent.ACTION_UP:
//                        // RELEASED
//                      //  tv_info.setTextColor(getResources().getColor(R.color.white));
//                      //  iv_info.setBackgroundResource(R.drawable.info_dark);
//                        if (AppConstants.checkInternetConnection(MainActivity.this)) {
//                            showAdd();
//                        } else {
//                            showInfoScreen();
//                        }
//                        return true; // if you want to handle the touch event
//                    default:
//                        tv_info.setTextColor(getResources().getColor(R.color.white));
//                }
//                return false;
//            }
//        });
        pref = getApplicationContext().getSharedPreferences("MyPref", 0);// 0 - for private mode

    }

    void updateImage() {
        timerHandler = new Handler();

        updater = new Runnable() {
            @Override
            public void run() {
                ConnectivityManager connManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                @SuppressLint("MissingPermission") NetworkInfo mWifi = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
                if (mWifi.isConnected()) {
                    wifi_on_off.setImageResource(R.drawable.wifi_on_new);
                    wifi_on_off.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            wifi_on_off.setOnTouchListener(Utility.imgPress());
                            startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
                        }
                    });
                } else if (!mWifi.isConnected()) {
                    wifi_on_off.setImageResource(R.drawable.wifi_cross);

                    wifi_on_off.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            wifi_on_off.setOnTouchListener(Utility.imgPress());

                            startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));

                        }
                    });

                }
                timerHandler.postDelayed(updater,100);
            }
        };
        timerHandler.post(updater);
    }

    private void okgotit_dialog() {
        sharedpreferences = getSharedPreferences("PREFERENCE_NAME", Context.MODE_PRIVATE);
        editor = sharedpreferences.edit();
        increment = sharedpreferences.getInt("user", increment);
        Log.d("check", "" + increment);
        if (increment == 0) {
            ConsentInformation consentInformation = ConsentInformation.getInstance(getApplicationContext());
            String[] publisherIds = {"pub-3424164443057663"};
            consentInformation.requestConsentInfoUpdate(publisherIds, new ConsentInfoUpdateListener() {
                @Override
                public void onConsentInfoUpdated(ConsentStatus consentStatus) {
                    // User's consent status successfully updated.

                    boolean isEuropeanUser = ConsentInformation.getInstance(MainActivity.this).isRequestLocationInEeaOrUnknown();

                    if (isEuropeanUser) {
                        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
                        Menu menu = navigationView.getMenu();
                        MenuItem target = menu.findItem(R.id.nav_ad_settings);
                        target.setVisible(true);
                        dialog = new Dialog(MainActivity.this);
                        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                        dialog.setCancelable(false);
                        dialog.setContentView(R.layout.activity_got_it);
                        privacy_policy = (TextView) dialog.findViewById(R.id.privacy_policy);
                        learn = (TextView) dialog.findViewById(R.id.learn);

                        privacy_policy.setMovementMethod(LinkMovementMethod.getInstance());
                        privacy_policy.setMovementMethod(LinkMovementMethod.getInstance());

                        learn.setMovementMethod(LinkMovementMethod.getInstance());
                        learn.setMovementMethod(LinkMovementMethod.getInstance());

                        ll_ok = dialog.findViewById(R.id.ll_ok_got_it);

                        ll_ok.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                increment += 1;
                                editor.putInt("user", increment);
                                editor.commit();

                                dialog.dismiss();
                            }

                        });

                        dialog.show();
                    } else {

                        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);

                        Menu menu = navigationView.getMenu();

                        MenuItem target = menu.findItem(R.id.nav_ad_settings);

                        target.setVisible(false);
                    }

                }

                @Override
                public void onFailedToUpdateConsentInfo(String errorDescription) {
                    // User's consent status failed to update.
                }
            });

//            ConsentInformation.getInstance(this).addTestDevice("C6F4B0F407225F950F26883E25F1FB02");
//            ConsentInformation.getInstance(this).
//            setDebugGeography(DebugGeography.DEBUG_GEOGRAPHY_EEA);
        }

    }


    public void showBottomSheetDialogFragment() {
        BottomSheetFragment bottomSheetFragment = new BottomSheetFragment();
        bottomSheetFragment.show(getSupportFragmentManager(), bottomSheetFragment.getTag());

    }

    public void showFollowUsDialog() {
        final Dialog dialog = new Dialog(MainActivity.this);
        dialog.getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED,
                WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;

        dialog.setContentView(R.layout.item_grid);

        LinearLayout fbBTN = (LinearLayout) dialog.findViewById(R.id.fbBTN);
        fbBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://www.facebook.com/soomapps";
                Uri uri = Uri.parse(url);

                if (!url.startsWith("http://") && !url.startsWith("https://")) {
                    uri = Uri.parse("http://" + url);
                }
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                // Verify that the intent will resolve to an activity
                if (intent.resolveActivity(getPackageManager()) != null) {
                    // Here we use an intent without a Chooser unlike the next example
                    startActivity(intent);
                    getOpenFacebookIntent(MainActivity.this);

                }

//                Intent i = new Intent(Intent.ACTION_VIEW);
//                i.setData(Uri.parse("https://www.facebook.com/soomapps"));
//                startActivity(i);
//                getOpenFacebookIntent(MainActivity.this);

                // https://www.instagram.com/soomapps/
                // https://www.facebook.com/soomapps
            }
        });
        LinearLayout instaBTN = (LinearLayout) dialog.findViewById(R.id.instaBTN);
        instaBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String url = "https://www.instagram.com/soomapps/";
                Uri uri = Uri.parse(url);
                if (!url.startsWith("http://") && !url.startsWith("https://")) {
                    uri = Uri.parse("http://" + url);
                }

                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                // Verify that the intent will resolve to an activity
                if (intent.resolveActivity(getPackageManager()) != null) {
                    // Here we use an intent without a Chooser unlike the next example
                    startActivity(intent);

                }
//
//                Intent i = new Intent(Intent.ACTION_VIEW);
//                i.setData(Uri.parse("https://www.instagram.com/soomapps/"));
//                startActivity(i);

//

                // https://www.instagram.com/soomapps/
                // https://www.facebook.com/soomapps
            }
        });

        TextView cancelBTN = (TextView) dialog.findViewById(R.id.cancelBTN);
        cancelBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();

    }


    public static Intent getOpenFacebookIntent(Context context) {

        try {
            context.getPackageManager().getPackageInfo("com.facebook.katana", 0);
            return new Intent(Intent.ACTION_VIEW, Uri.parse("fb://soomapps"));

        } catch (AssertionError error) {
            // Workaround for a bug in Android that can cause crashes on Android 8.0 and 8.1

        } catch (Exception e) {
            Log.d("TTTTT", e.getMessage());
            return new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/soomapps"));
        }
        return null;
    }


    public static Intent newFacebookIntent(PackageManager pm, String url) {
        Uri uri = Uri.parse(url);
        try {
            ApplicationInfo applicationInfo = pm.getApplicationInfo("com.facebook.katana", 0);
            if (applicationInfo.enabled) {
                // http://stackoverflow.com/a/24547437/1048340
                uri = Uri.parse("fb://facewebmodal/f?href=" + url);
            }
        } catch (PackageManager.NameNotFoundException ignored) {
        }
        return new Intent(Intent.ACTION_VIEW, uri);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBackPressed() {
//        bottomSheetDialog.setCancelable(true);
//        bottomSheetDialograting.setCancelable(true);
        if (doubleBackToExitPressedOnce) {
            sharedPreferenceisboolen.clearSharedPreference();
            finishAffinity();
            System.exit(0);
            moveTaskToBack(true);
            return;
        } else {
            if (sharedpreferences.getInt("repeat_value", 0) == 3) {
                increaseValue();
                rating_value = 0;
                // ShowRatingDialog();
            } else {
                drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                if (drawer.isDrawerOpen(GravityCompat.START)) {
                    drawer.closeDrawer(GravityCompat.START);
                } else {
                    this.doubleBackToExitPressedOnce = true;
                    Toast.makeText(this, "Press Back again to exit", Toast.LENGTH_SHORT).show();
                    new Handler().postDelayed(new Runnable() {

                        @Override
                        public void run() {
                            doubleBackToExitPressedOnce = false;
                        }
                    }, 2000);

                }
            }
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.nav_rate_us) {
            rating_value = 0;
            ShowRatingDialog();
        } else if (id == R.id.nav_share_app) {
            try {
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("text/plain");
                i.putExtra(Intent.EXTRA_SUBJECT, getResources().getString(R.string.app_name));
                String sAux = getResources().getString(R.string.download_this) + "\n\n";
                sAux = sAux + "https://play.google.com/store/apps/details?id=" + getPackageName() + "\n\n";
                i.putExtra(Intent.EXTRA_TEXT, sAux);
                startActivity(Intent.createChooser(i, "Choose one"));
            } catch (Exception e) {
                //e.toString();
            }
        } else if (id == R.id.nav_more_apps) {
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(AppConstants.developer_id_link));
            startActivity(i);
        } else if (id == R.id.nav_privacy_policy) {
            startActivity(new Intent(MainActivity.this, PrivacyPolicyActivity.class));
        } else if (id == R.id.nav_multilanguage) {
            SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
            SharedPreferences.Editor editor = pref.edit();
            editor.putString("star", "2");
            editor.commit();

            if (sharedPreferenceisboolen.getValueBoolien("isbool", isboolean) == true) {
                sharedPreferenceisboolen.save("isbool", false);

            } else {

            }
            startActivity(new Intent(MainActivity.this, MultiLanguageActivity.class).putExtra("new_lang", "en"));


        } else if (id == R.id.nav_follow_us) {
            showFollowUsDialog();
        } else if (id == R.id.nav_ad_settings) {
            getConsentInfo();
            getConsentForm();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void getConsentInfo() {

        ConsentInformation consentInformation = ConsentInformation.getInstance(getApplicationContext());
        String[] publisherIds = {"pub-3424164443057663"};
        consentInformation.requestConsentInfoUpdate(publisherIds, new ConsentInfoUpdateListener() {
            @Override
            public void onConsentInfoUpdated(ConsentStatus consentStatus) {
                // User's consent status successfully updated.

                boolean isEuropeanUser = ConsentInformation.getInstance(MainActivity.this).isRequestLocationInEeaOrUnknown();
                if (isEuropeanUser) {
                    Log.d("logg", "" + consentStatus.toString());

                    switch (consentStatus) {
                        case PERSONALIZED:
                            personalizedAds(true);
                            break;
                        case NON_PERSONALIZED:
                            personalizedAds(false);
                            break;
                        case UNKNOWN:
                            getConsentForm();
                            break;
                    }
                }
            }

            @Override
            public void onFailedToUpdateConsentInfo(String errorDescription) {
                // User's consent status failed to update.
            }
        });

//        ConsentInformation.getInstance(this).addTestDevice("C6F4B0F407225F950F26883E25F1FB02");
//        ConsentInformation.getInstance(this).
//        setDebugGeography(DebugGeography.DEBUG_GEOGRAPHY_EEA);

    }

    private void personalizedAds(boolean isPersonalized) {

        if (isPersonalized) {
            AdRequest adRequest = new AdRequest.Builder().build();

        } else {
            //For Non Personalized Ads
            Bundle extras = new Bundle();
            extras.putString("npa", "1");
            AdRequest request = new AdRequest.Builder()
                    .addNetworkExtrasBundle(AdMobAdapter.class, extras)
                    .build();
        }
    }

    private void getConsentForm() {

        URL privacyUrl = null;
        try {
            // TODO: Replace with your app's privacy policy URL.
            privacyUrl = new URL("https://www.soomapps.com/castto-privacy-policy/");
        } catch (MalformedURLException e) {
            e.printStackTrace();
            // Handle error.
        }
        form = new ConsentForm.Builder(this, privacyUrl)
                .withListener(new ConsentFormListener() {
                    @Override
                    public void onConsentFormLoaded() {
                        // Consent form loaded successfully.
                        form.show();
                    }

                    @Override
                    public void onConsentFormOpened() {
                        // Consent form was displayed.
                    }

                    @Override
                    public void onConsentFormClosed(
                            ConsentStatus consentStatus, Boolean userPrefersAdFree) {
                        // Consent form was closed.

                        Log.d("hhhhhh", "" + consentStatus);

                        if (consentStatus.equals(ConsentStatus.PERSONALIZED)) {

                            personalizedAds(true);
                        } else {
                            personalizedAds(false);

                        }
                    }

                    @Override
                    public void onConsentFormError(String errorDescription) {
                        // Consent form error.
                    }
                })
                .withPersonalizedAdsOption()
                .withNonPersonalizedAdsOption()
                .withAdFreeOption()
                .build();

        form.load();

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
        loadFullAd();
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
        timerHandler.removeCallbacks(updater);
    }

    @SuppressLint("MissingPermission")
    private void refreshAd() {
        // refresh.setEnabled(false);

        AdLoader.Builder builder = new AdLoader.Builder(this, getString(R.string.native_ad));

        builder.forUnifiedNativeAd(new UnifiedNativeAd.OnUnifiedNativeAdLoadedListener() {
            // OnUnifiedNativeAdLoadedListener implementation.
            @Override
            public void onUnifiedNativeAdLoaded(UnifiedNativeAd unifiedNativeAd) {
                FrameLayout frameLayout =
                        findViewById(R.id.fl_adplaceholder_nav);
                UnifiedNativeAdView adView = (UnifiedNativeAdView) getLayoutInflater()
                        .inflate(R.layout.ad_unified, null);

                // I add this code for Using native ads (Code Start)

                Bundle extras = unifiedNativeAd.getExtras();
                if (extras.containsKey(FacebookAdapter.KEY_SOCIAL_CONTEXT_ASSET)) {
                    String socialContext = (String) extras.get(FacebookAdapter.KEY_SOCIAL_CONTEXT_ASSET);
                    Log.d("social", "" + socialContext);

                }

                // I add this code for Using native ads (Code end)

                populateUnifiedNativeAdView(unifiedNativeAd, adView);
                if (frameLayout != null) {
                    frameLayout.removeAllViews();
                    frameLayout.addView(adView);
                }
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
                if (frameLayout != null) {
                    frameLayout.removeAllViews();
                    frameLayout.addView(adView);
                }
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
            adView.getIconView().setVisibility(GONE);
        } else {
            ((ImageView) adView.getIconView()).setImageDrawable(
                    nativeAd.getIcon().getDrawable());
            adView.getIconView().setVisibility(View.VISIBLE);
        }

        if (nativeAd.getPrice() == null) {
            adView.getPriceView().setVisibility(GONE);
        } else {
            adView.getPriceView().setVisibility(GONE);
            ((TextView) adView.getPriceView()).setText(nativeAd.getPrice());
        }

        if (nativeAd.getStore() == null) {
            adView.getStoreView().setVisibility(GONE);
        } else {
            adView.getStoreView().setVisibility(GONE);
            ((TextView) adView.getStoreView()).setText(nativeAd.getStore());
        }

        if (nativeAd.getStarRating() == null) {
            adView.getStarRatingView().setVisibility(GONE);
        } else {
            ((RatingBar) adView.getStarRatingView())
                    .setRating(nativeAd.getStarRating().floatValue());
            adView.getStarRatingView().setVisibility(GONE);
        }

        if (nativeAd.getAdvertiser() == null) {
            adView.getAdvertiserView().setVisibility(GONE);
        } else {
            ((TextView) adView.getAdvertiserView()).setText(nativeAd.getAdvertiser());
            adView.getAdvertiserView().setVisibility(GONE);
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

    @SuppressLint("MissingPermission")
    public void loadFullAd() {
        mInterstitialAd = new InterstitialAd(this);

        // set the ad unit ID
        mInterstitialAd.setAdUnitId(getString(R.string.interstitial_full_screen));

        if (!mInterstitialAd.isLoading() && !mInterstitialAd.isLoaded()) {
            mInterstitialAd.loadAd(new AdRequest.Builder().build());
        }
    }

    public void showFullScreenAd() {
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
            showNextScreen();
        }
    }

    public void showNextScreen() {
/*
        if(isAvailable(MainActivity.this)) {
            try {
                startActivity(new Intent("android.settings.WIFI_DISPLAY_SETTINGS"));
            } catch (ActivityNotFoundException e) {
                e.printStackTrace();
                try {
                    startActivity(new Intent("com.samsung.wfd.LAUNCH_WFD_PICKER_DLG"));
                } catch (Exception e2) {
                    try {
                        startActivity(new Intent("android.settings.CAST_SETTINGS"));
                    } catch (Exception e3) {
                        Toast.makeText(getApplicationContext(), "Device not supported", Toast.LENGTH_LONG).show();
                    }
                }
            }
        }
*/
        try {
            startActivity(new Intent("android.settings.CAST_SETTINGS"));
        } catch (Exception e3) {
            Toast.makeText(getApplicationContext(), "Device not supported", Toast.LENGTH_LONG).show();
        }
    }

    public void showAdd() {
        if (mInterstitialAd != null && mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
            mInterstitialAd.setAdListener(new AdListener() {
                @Override
                public void onAdClosed() {
                    super.onAdClosed();
                    showInfoScreen();
                }
            });
        } else {
            showInfoScreen();
        }

//        AdRequest adRequest = new AdRequest.Builder().build();
//        // Prepare the Interstitial Ad
//        interstitial = new InterstitialAd(MainActivity.this);
//// Insert the Ad Unit ID
//        interstitial.setAdUnitId(getString(R.string.interstitial_full_screen));
//
//        interstitial.loadAd(adRequest);
//// Prepare an Interstitial Ad Listener
//        interstitial.setAdListener(new AdListener() {
//            public void onAdLoaded() {
//// Call displayInterstitial() function
//                displayInterstitial();
//            }
//        });
//
//        startActivity(new Intent(MainActivity.this, InfoActivity.class));

    }

    public void showInfoScreen() {

        startActivity(new Intent(MainActivity.this, InfoPart1Activity.class));


    }

//    public void displayInterstitial() {
//// If Ads are loaded, show Interstitial else show nothing.
//        if (interstitial.isLoaded()) {
//            interstitial.show();
//        }
//    }

    public void increaseValue() {
        editor = sharedpreferences.edit();
        editor.putInt("repeat_value",
                sharedpreferences.getInt("repeat_value", 0) + 1);
        editor.commit();
    }


    private void thirdtimeADB() {

        sharedpreferences = getSharedPreferences("PREFERENCE_NAME", Context.MODE_PRIVATE);
        editor = sharedpreferences.edit();
        count = sharedpreferences.getInt("username", count);
        if (count == 0) {
            count += 1;
            editor.putInt("username", count);
            editor.commit();

        } else {
            count += 1;
            editor.putInt("username", count);
            editor.commit();
            count = sharedpreferences.getInt("username", count);

            if (count == 3) {
                handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        showBottomSheetDialog();
                    }
                }, 1000);

//                dialog = new Dialog(MainActivity.this);
//                dialog.getWindow().setFlags(
//                        WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED,
//                        WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED);
//                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
//                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//                dialog.setCancelable(false);
//                dialog.setContentView(R.layout.pop_up);
//
//                //  LinearLayout ll_ok = (LinearLayout) dialog.findViewById(R.id.ll_ok);
//
//                iv_star_1 = (ImageView) dialog.findViewById(R.id.iv_star_1);
//                iv_star_2 = (ImageView) dialog.findViewById(R.id.iv_star_2);
//                iv_star_3 = (ImageView) dialog.findViewById(R.id.iv_star_3);
//                iv_star_4 = (ImageView) dialog.findViewById(R.id.iv_star_4);
//                iv_star_5 = (ImageView) dialog.findViewById(R.id.iv_star_5);
//
//                ratinglayout = (LinearLayout) dialog.findViewById(R.id.ratinglayout);
//                ratingGIF = (GifImageView) dialog.findViewById(R.id.ratingGIF);
//                //imageView=(ImageView)dialog.findViewById(R.id.gifImage);
////                Glide.with(getApplicationContext())
////                        .asGif()
////                        .load(R.drawable.stars_grey)
////                        .into(imageView);
//                cancelIMG = (ImageView) dialog.findViewById(R.id.cancelIMG);
//                cancelIMG.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        dialog.dismiss();
//                    }
//                });
//
//                ratinglayout.setVisibility(GONE);
//                handler = new Handler();
//                handler.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        ratinglayout.setVisibility(View.VISIBLE);
//                        ratingGIF.setVisibility(GONE);
//
//                        iv_star_1.setBackgroundResource(R.drawable.ic_star_icon_third);
//                        iv_star_2.setBackgroundResource(R.drawable.ic_star_icon_third);
//                        iv_star_3.setBackgroundResource(R.drawable.ic_star_icon_third);
//                        iv_star_4.setBackgroundResource(R.drawable.ic_star_icon_third);
//                        iv_star_5.setBackgroundResource(R.drawable.ic_star_new_three_lines);
//
//                    }
//                }, 3700);
//
//                // Animation rotation=AnimationUtils.loadAnimation(MainActivity.this,R.anim.blinkanimation);
//
//                iv_star_1.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        iv_star_1.setBackgroundResource(R.drawable.fill_rating_third_icon);
//                        iv_star_2.setBackgroundResource(R.drawable.ic_star_icon_third);
//                        iv_star_3.setBackgroundResource(R.drawable.ic_star_icon_third);
//                        iv_star_4.setBackgroundResource(R.drawable.ic_star_icon_third);
//                        iv_star_5.setBackgroundResource(R.drawable.ic_star_new_three_lines);
//                        rating_value = 1;
//
//                        handler = new Handler();
//                        handler.postDelayed(new Runnable() {
//                            @Override
//                            public void run() {
//                                afterratingAction();
//                            }
//                        }, 200);
//                    }
//                });
//                iv_star_2.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        iv_star_1.setBackgroundResource(R.drawable.fill_rating_third_icon);
//                        iv_star_2.setBackgroundResource(R.drawable.fill_rating_third_icon);
//                        iv_star_3.setBackgroundResource(R.drawable.ic_star_icon_third);
//                        iv_star_4.setBackgroundResource(R.drawable.ic_star_icon_third);
//                        iv_star_5.setBackgroundResource(R.drawable.ic_star_new_three_lines);
//                        rating_value = 2;
//
//                        handler = new Handler();
//                        handler.postDelayed(new Runnable() {
//                            @Override
//                            public void run() {
//                                afterratingAction();
//                            }
//                        }, 200);
//                    }
//                });
//                iv_star_3.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        iv_star_1.setBackgroundResource(R.drawable.fill_rating_third_icon);
//                        iv_star_2.setBackgroundResource(R.drawable.fill_rating_third_icon);
//                        iv_star_3.setBackgroundResource(R.drawable.fill_rating_third_icon);
//                        iv_star_4.setBackgroundResource(R.drawable.ic_star_icon_third);
//                        iv_star_5.setBackgroundResource(R.drawable.ic_star_new_three_lines);
//                        rating_value = 3;
//
//                        handler = new Handler();
//                        handler.postDelayed(new Runnable() {
//                            @Override
//                            public void run() {
//                                afterratingAction();
//                            }
//                        }, 200);
//                    }
//                });
//                iv_star_4.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        iv_star_1.setBackgroundResource(R.drawable.fill_rating_third_icon);
//                        iv_star_2.setBackgroundResource(R.drawable.fill_rating_third_icon);
//                        iv_star_3.setBackgroundResource(R.drawable.fill_rating_third_icon);
//                        iv_star_4.setBackgroundResource(R.drawable.fill_rating_third_icon);
//                        iv_star_5.setBackgroundResource(R.drawable.ic_star_new_three_lines);
//                        rating_value = 4;
//
//                        handler = new Handler();
//                        handler.postDelayed(new Runnable() {
//                            @Override
//                            public void run() {
//                                afterratingAction();
//                            }
//                        }, 200);
//                    }
//                });
//                iv_star_5.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        iv_star_1.setBackgroundResource(R.drawable.fill_rating_third_icon);
//                        iv_star_2.setBackgroundResource(R.drawable.fill_rating_third_icon);
//                        iv_star_3.setBackgroundResource(R.drawable.fill_rating_third_icon);
//                        iv_star_4.setBackgroundResource(R.drawable.fill_rating_third_icon);
//                        iv_star_5.setBackgroundResource(R.drawable.fill_bigger_icon);
//                        rating_value = 5;
//
//                        handler = new Handler();
//                        handler.postDelayed(new Runnable() {
//                            @Override
//                            public void run() {
//                                afterratingAction();
//                            }
//                        }, 200);
//                    }
//                });
//                dialog.show();
            }

        }
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);

        // The following line triggers the initialization of ACRA
        //        ACRA.init(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.mainmenu, menu);
        Menu menu1 = (Menu) findViewById(R.id.rate_us);
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        iv = (ImageView) inflater.inflate(R.layout.new_iv_refresh, null);

        ImageView img = iv.findViewById(R.id.rate_us1);

        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
        SharedPreferences.Editor editor = pref.edit();
        String value = pref.getString("key_name", null);
        editor.commit();

        Log.d("value", "" + value);

        if (value != null && value.equalsIgnoreCase("121")) {
            img.setImageResource(R.drawable.filled);
            img.setEnabled(false);
//            final Animation myAnim = AnimationUtils.loadAnimation(MainActivity.this, R.anim.bounce);
//            MyBounceInterpolator interpolator = new MyBounceInterpolator(0.2, 20);
//            myAnim.setInterpolator(interpolator);
//            iv.startAnimation(myAnim);
        }


        Handler handler1 = new Handler();
        handler1.postDelayed(new Runnable() {
            @Override
            public void run() {
                final Animation myAnim = AnimationUtils.loadAnimation(MainActivity.this, R.anim.bounce);

                // Use bounce interpolator with amplitude 0.2 and frequency 20
                MyBounceInterpolator interpolator = new MyBounceInterpolator(0.2, 20);
                myAnim.setInterpolator(interpolator);
                iv.startAnimation(myAnim);
            }
        }, 5000);


        // Animation rotation = AnimationUtils.loadAnimation(this, R.anim.blinkanimation);
        // rotation.setRepeatCount(3);
        // rotation.setRepeatCount(3);
        //  iv.startAnimation(rotation);
        menu.findItem(R.id.rate_us).setActionView(iv);
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rating_value = 0;
                ShowRatingDialog();
            }
        });

        return true;

    }

    private void showBottomSheetDialog() {

        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        bottomSheetDialog.setContentView(R.layout.new_rating_system);
        Button good_btn = bottomSheetDialog.findViewById(R.id.good_btn);
        Button not_really_btn = bottomSheetDialog.findViewById(R.id.not_really_btn);
        bottomSheetDialog.show();
        bottomSheetDialog.setCanceledOnTouchOutside(false);


        good_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomSheetDialog.dismiss();
                ShowRatingDialog();
            }
        });

        not_really_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, Not_Really_Activity.class);
                startActivity(i);
                finish();
            }
        });
    }



    private void ShowRatingDialog(){
        final BottomSheetDialog bottomSheetDialograting = new BottomSheetDialog(this);
        bottomSheetDialograting.setContentView(R.layout.pop_up);
        bottomSheetDialograting.setCanceledOnTouchOutside(false);

        iv_star_1 = (ImageView) bottomSheetDialograting.findViewById(R.id.iv_star_1);
        iv_star_2 = (ImageView) bottomSheetDialograting.findViewById(R.id.iv_star_2);
        iv_star_3 = (ImageView) bottomSheetDialograting.findViewById(R.id.iv_star_3);
        iv_star_4 = (ImageView) bottomSheetDialograting.findViewById(R.id.iv_star_4);
        iv_star_5 = (ImageView) bottomSheetDialograting.findViewById(R.id.iv_star_5);
        rate_emoji = (ImageView) bottomSheetDialograting.findViewById(R.id.rate_emoji);
        rate_text_1 =(TextView) bottomSheetDialograting.findViewById(R.id.rate_text1);
        rate_text_2 =(TextView) bottomSheetDialograting.findViewById(R.id.rate_text2);
        rate_submit = (Button) bottomSheetDialograting.findViewById(R.id.rate_submit);


        ratinglayout = (LinearLayout) bottomSheetDialograting.findViewById(R.id.ratinglayout);

        ratingGIF = (GifImageView) bottomSheetDialograting.findViewById(R.id.ratingGIF);
        cancelIMG = (ImageView) bottomSheetDialograting.findViewById(R.id.cancelIMG);
        cancelIMG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetDialograting.dismiss();

            }
        });
        ratinglayout.setVisibility(GONE);


        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                ratinglayout.setVisibility(View.VISIBLE);

//                imageView.setVisibility(View.GONE);
                ratingGIF.setVisibility(GONE);
//                Glide.with(getApplicationContext())
//                        .asBitmap()
//                        .load(R.drawable.stars_grey)
//                        .into(imageView);

//                imageView.setVisibility(View.GONE);

                iv_star_1.setBackgroundResource(R.drawable.ic_unfilled_rating_system_new_staricon);
                iv_star_2.setBackgroundResource(R.drawable.ic_unfilled_rating_system_new_staricon);
                iv_star_3.setBackgroundResource(R.drawable.ic_unfilled_rating_system_new_staricon);
                iv_star_4.setBackgroundResource(R.drawable.ic_unfilled_rating_system_new_staricon);
                iv_star_5.setBackgroundResource(R.drawable.ic_unfilled_three_line_star);

            }
        }, 4400);

        iv_star_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
                SharedPreferences.Editor editor = pref.edit();
                editor.putString("key_name", "121");
                editor.commit();
                iv.setVisibility(GONE);
                iv_star_1.setBackgroundResource(R.drawable.ic_filled_rating_system_new_staricon);
                iv_star_2.setBackgroundResource(R.drawable.ic_unfilled_rating_system_new_staricon);
                iv_star_3.setBackgroundResource(R.drawable.ic_unfilled_rating_system_new_staricon);
                iv_star_4.setBackgroundResource(R.drawable.ic_unfilled_rating_system_new_staricon);
                iv_star_5.setBackgroundResource(R.drawable.ic_unfilled_three_line_star);
                rating_value = 1;
                rate_emoji.setImageResource(R.drawable.emoji_star_one);
                rate_text_1.setText(getString(R.string.oh_no));
                rate_text_2.setText(getString(R.string.please_leave_us_feedback));
                rate_submit.setText(getString(R.string.rate));
                rate_submit.setBackgroundResource(R.drawable.rate_btn_enabled);

                rate_submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Intent i = new Intent(MainActivity.this,Not_Really_Activity.class);
                        startActivity(i);
                        finish();
//                        handler = new Handler();
//                        handler.postDelayed(new Runnable() {
//                            @Override
//                            public void run() {
//                                afterratingAction();
//                            }
//                        }, 200);
//                        bottomSheetDialog.dismiss();
                    }
                });

            }
        });

        iv_star_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
                SharedPreferences.Editor editor = pref.edit();
                editor.putString("key_name", "121");
                editor.commit();
                iv.setVisibility(GONE);
                iv_star_1.setBackgroundResource(R.drawable.ic_filled_rating_system_new_staricon);
                iv_star_2.setBackgroundResource(R.drawable.ic_filled_rating_system_new_staricon);
                iv_star_3.setBackgroundResource(R.drawable.ic_unfilled_rating_system_new_staricon);
                iv_star_4.setBackgroundResource(R.drawable.ic_unfilled_rating_system_new_staricon);
                iv_star_5.setBackgroundResource(R.drawable.ic_unfilled_three_line_star);
                rating_value = 2;
                rate_emoji.setImageResource(R.drawable.emoji_star_two);
                rate_text_1.setText(getString(R.string.oh_no));
                rate_text_2.setText(getString(R.string.please_leave_us_feedback));
                rate_submit.setText(getString(R.string.rate));
                rate_submit.setBackgroundResource(R.drawable.rate_btn_enabled);


                rate_submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent i = new Intent(MainActivity.this,Not_Really_Activity.class);
                        startActivity(i);
                        finish();
//                        handler = new Handler();
//                        handler.postDelayed(new Runnable() {
//                            @Override
//                            public void run() {
//                                afterratingAction();
//                            }
//                        }, 200);
                    }
                });

            }
        });
        iv_star_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
                SharedPreferences.Editor editor = pref.edit();
                editor.putString("key_name", "121");
                editor.commit();
                iv.setVisibility(GONE);
                iv_star_1.setBackgroundResource(R.drawable.ic_filled_rating_system_new_staricon);
                iv_star_2.setBackgroundResource(R.drawable.ic_filled_rating_system_new_staricon);
                iv_star_3.setBackgroundResource(R.drawable.ic_filled_rating_system_new_staricon);
                iv_star_4.setBackgroundResource(R.drawable.ic_unfilled_rating_system_new_staricon);
                iv_star_5.setBackgroundResource(R.drawable.ic_unfilled_three_line_star);
                rating_value = 3;
                rate_emoji.setImageResource(R.drawable.emoji_star_three);
                rate_text_1.setText(getString(R.string.oh_no));
                rate_text_2.setText(getString(R.string.please_leave_us_feedback));
                rate_submit.setText(getString(R.string.rate));
                rate_submit.setBackgroundResource(R.drawable.rate_btn_enabled);

                rate_submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent i = new Intent(MainActivity.this,Not_Really_Activity.class);
                        startActivity(i);
                        finish();
//                        handler = new Handler();
//                        handler.postDelayed(new Runnable() {
//                            @Override
//                            public void run() {
//                                afterratingAction();
//                            }
//                        }, 200);
                    }
                });

            }
        });
        iv_star_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
                SharedPreferences.Editor editor = pref.edit();
                editor.putString("key_name", "121");
                editor.commit();
                iv.setVisibility(GONE);
                iv_star_1.setBackgroundResource(R.drawable.ic_filled_rating_system_new_staricon);
                iv_star_2.setBackgroundResource(R.drawable.ic_filled_rating_system_new_staricon);
                iv_star_3.setBackgroundResource(R.drawable.ic_filled_rating_system_new_staricon);
                iv_star_4.setBackgroundResource(R.drawable.ic_filled_rating_system_new_staricon);
                iv_star_5.setBackgroundResource(R.drawable.ic_unfilled_three_line_star);
                rating_value = 4;
                rate_emoji.setImageResource(R.drawable.emoji_star_four);
                rate_text_1.setText(getString(R.string.we_like_you_too));
                rate_text_2.setText(getString(R.string.thanks_for_feedback));
                rate_submit.setText(getString(R.string.rate));
                rate_submit.setBackgroundResource(R.drawable.rate_btn_enabled);


                rate_submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent i = new Intent(MainActivity.this,Not_Really_Activity.class);
                        startActivity(i);
                        finish();
//                        handler = new Handler();
//                        handler.postDelayed(new Runnable() {
//                            @Override
//                            public void run() {
//                                afterratingAction();
//                            }
//                        }, 200);
                    }
                });

            }
        });
        iv_star_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
                SharedPreferences.Editor editor = pref.edit();
                editor.putString("key_name", "121");
                editor.commit();
                iv.setVisibility(GONE);
                iv_star_1.setBackgroundResource(R.drawable.ic_filled_rating_system_new_staricon);
                iv_star_2.setBackgroundResource(R.drawable.ic_filled_rating_system_new_staricon);
                iv_star_3.setBackgroundResource(R.drawable.ic_filled_rating_system_new_staricon);
                iv_star_4.setBackgroundResource(R.drawable.ic_filled_rating_system_new_staricon);
                iv_star_5.setBackgroundResource(R.drawable.biggest_star_icon);
                rating_value = 5;
                rate_emoji.setImageResource(R.drawable.emoji_star_five);
                rate_text_1.setText(getString(R.string.we_like_you_too));
                rate_text_2.setText(getString(R.string.thanks_for_feedback));
                rate_submit.setText(getString(R.string.rate_google_play));
                rate_submit.setBackgroundResource(R.drawable.rate_btn_enabled);

                rate_submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                afterratingAction();
                            }
                        }, 200);
                    }
                });

            }
        });
        bottomSheetDialograting.show();
    }

//    public void ShowRatingDialog() {
//        dialog = new Dialog(MainActivity.this);
//        dialog.getWindow().setFlags(
//                WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED,
//                WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED);
//        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
//        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        dialog.setCancelable(false);
//        dialog.setContentView(R.layout.pop_up);
//
//        //  LinearLayout ll_ok = (LinearLayout) dialog.findViewById(R.id.ll_ok);
//
//        iv_star_1 = (ImageView) dialog.findViewById(R.id.iv_star_1);
//        iv_star_2 = (ImageView) dialog.findViewById(R.id.iv_star_2);
//        iv_star_3 = (ImageView) dialog.findViewById(R.id.iv_star_3);
//        iv_star_4 = (ImageView) dialog.findViewById(R.id.iv_star_4);
//        iv_star_5 = (ImageView) dialog.findViewById(R.id.iv_star_5);
//
//        ratinglayout = (LinearLayout) dialog.findViewById(R.id.ratinglayout);
//
////         imageView=(ImageView)dialog.findViewById(R.id.gifImage);
//
////        Glide.with(getApplicationContext())
////                .asGif()
////
////                .load(R.drawable.stars_grey)
////                .into(imageView);
//        ratingGIF = (GifImageView) dialog.findViewById(R.id.ratingGIF);
//        cancelIMG = (ImageView) dialog.findViewById(R.id.cancelIMG);
//        cancelIMG.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dialog.dismiss();
//
//            }
//        });
//        ratinglayout.setVisibility(GONE);
//
//        handler = new Handler();
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                ratinglayout.setVisibility(View.VISIBLE);
//
////                imageView.setVisibility(View.GONE);
//                ratingGIF.setVisibility(GONE);
////                Glide.with(getApplicationContext())
////                        .asBitmap()
////                        .load(R.drawable.stars_grey)
////                        .into(imageView);
//
////                imageView.setVisibility(View.GONE);
//
//                iv_star_1.setBackgroundResource(R.drawable.ic_unfilled_rating_system_new_staricon);
//                iv_star_2.setBackgroundResource(R.drawable.ic_unfilled_rating_system_new_staricon);
//                iv_star_3.setBackgroundResource(R.drawable.ic_unfilled_rating_system_new_staricon);
//                iv_star_4.setBackgroundResource(R.drawable.ic_unfilled_rating_system_new_staricon);
//                iv_star_5.setBackgroundResource(R.drawable.ic_unfilled_three_line_star);
//
//            }
//        }, 4300);
//
//        //Animation rotation=AnimationUtils.loadAnimation(MainActivity.this,R.anim.blinkanimation);
//
//        iv_star_1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
//                SharedPreferences.Editor editor = pref.edit();
//                editor.putString("key_name", "121");
//                editor.commit();
//                iv.setVisibility(GONE);
//                iv_star_1.setBackgroundResource(R.drawable.ic_filled_rating_system_new_staricon);
//                iv_star_2.setBackgroundResource(R.drawable.ic_unfilled_rating_system_new_staricon);
//                iv_star_3.setBackgroundResource(R.drawable.ic_unfilled_rating_system_new_staricon);
//                iv_star_4.setBackgroundResource(R.drawable.ic_unfilled_rating_system_new_staricon);
//                iv_star_5.setBackgroundResource(R.drawable.ic_unfilled_three_line_star);
//                rating_value = 1;
//
//                handler = new Handler();
//                handler.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        afterratingAction();
//                    }
//                }, 200);
//            }
//        });
//        iv_star_2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
//                SharedPreferences.Editor editor = pref.edit();
//                editor.putString("key_name", "121");
//                editor.commit();
//                iv.setVisibility(GONE);
//                iv_star_1.setBackgroundResource(R.drawable.ic_filled_rating_system_new_staricon);
//                iv_star_2.setBackgroundResource(R.drawable.ic_filled_rating_system_new_staricon);
//                iv_star_3.setBackgroundResource(R.drawable.ic_unfilled_rating_system_new_staricon);
//                iv_star_4.setBackgroundResource(R.drawable.ic_unfilled_rating_system_new_staricon);
//                iv_star_5.setBackgroundResource(R.drawable.ic_unfilled_three_line_star);
//                rating_value = 2;
//
//                handler = new Handler();
//                handler.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        afterratingAction();
//                    }
//                }, 200);
//            }
//        });
//        iv_star_3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
//                SharedPreferences.Editor editor = pref.edit();
//                editor.putString("key_name", "121");
//                editor.commit();
//                iv.setVisibility(GONE);
//                iv_star_1.setBackgroundResource(R.drawable.ic_filled_rating_system_new_staricon);
//                iv_star_2.setBackgroundResource(R.drawable.ic_filled_rating_system_new_staricon);
//                iv_star_3.setBackgroundResource(R.drawable.ic_filled_rating_system_new_staricon);
//                iv_star_4.setBackgroundResource(R.drawable.ic_unfilled_rating_system_new_staricon);
//                iv_star_5.setBackgroundResource(R.drawable.ic_unfilled_three_line_star);
//                rating_value = 3;
//
//                handler = new Handler();
//                handler.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        afterratingAction();
//                    }
//                }, 200);
//            }
//        });
//        iv_star_4.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
//                SharedPreferences.Editor editor = pref.edit();
//                editor.putString("key_name", "121");
//                editor.commit();
//                iv.setVisibility(GONE);
//                iv_star_1.setBackgroundResource(R.drawable.ic_filled_rating_system_new_staricon);
//                iv_star_2.setBackgroundResource(R.drawable.ic_filled_rating_system_new_staricon);
//                iv_star_3.setBackgroundResource(R.drawable.ic_filled_rating_system_new_staricon);
//                iv_star_4.setBackgroundResource(R.drawable.ic_filled_rating_system_new_staricon);
//                iv_star_5.setBackgroundResource(R.drawable.ic_unfilled_three_line_star);
//                rating_value = 4;
//
//                handler = new Handler();
//                handler.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        afterratingAction();
//                    }
//                }, 200);
//            }
//        });
//        iv_star_5.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
//                SharedPreferences.Editor editor = pref.edit();
//                editor.putString("key_name", "121");
//                editor.commit();
//                iv.setVisibility(GONE);
//                iv_star_1.setBackgroundResource(R.drawable.ic_filled_rating_system_new_staricon);
//                iv_star_2.setBackgroundResource(R.drawable.ic_filled_rating_system_new_staricon);
//                iv_star_3.setBackgroundResource(R.drawable.ic_filled_rating_system_new_staricon);
//                iv_star_4.setBackgroundResource(R.drawable.ic_filled_rating_system_new_staricon);
//                iv_star_5.setBackgroundResource(R.drawable.fill_bigger_icon);
//                rating_value = 5;
//
//                handler = new Handler();
//                handler.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        afterratingAction();
//                    }
//                }, 200);
//            }
//        });
//        dialog.show();
//    }

    public void showDialog() {
        final Dialog dialog = new Dialog(MainActivity.this);
        dialog.getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED,
                WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.contact_popup_dialog);
        LinearLayout ll_ok = (LinearLayout) dialog.findViewById(R.id.l_ok);
        ll_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                sendEmail();
            }
        });
        dialog.show();

    }

    public void afterratingAction() {

        if (rating_value != 0) {
           // dialog.dismiss();
            if (rating_value < 5) {
                //open feedback screen
                //startActivity(new Intent(Main2Activity.this, FeedbackActivity.class));
                showDialog();
            } else {
                // showDialog();
                //rate on google play
                if (rating_value == 5) {
                    Toast.makeText(this, R.string.ratingMessage, Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://play.google.com/store/apps/details?id=" + getPackageName())));
                } else {
                    Uri uri = Uri.parse("market://details?id=" + getPackageName());
                    Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
                    // To count with Play market backstack, After pressing back button,
                    // to taken back to our application, we need to add following flags to intent.
                    goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY |
                            Intent.FLAG_ACTIVITY_NEW_DOCUMENT |
                            Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                    try {
                        startActivity(goToMarket);
                    } catch (ActivityNotFoundException e) {
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://play.google.com/store/apps/details?id=" + getPackageName())));
                    }
                }
            }
        }
    }


    protected void sendEmail() {
        String mailto = "mailto:contact@soomapps.com" +
                "?cc=" + "" +
                "&subject=" + Uri.encode("") +
                "&body=" + Uri.encode("");

        Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
        emailIntent.setData(Uri.parse(mailto));

        try {
            startActivity(emailIntent);
        } catch (ActivityNotFoundException e) {
            //TODO: Handle case where no email app is available
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.rate_us) {
            rating_value = 0;
            ShowRatingDialog();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


//    private void showDialogbottomsheet() {
//
//        dialog = new Dialog(MainActivity.this);
//        dialog.getWindow().setFlags(
//                WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED,
//                WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED);
//        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
//        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        dialog.setCancelable(false);
//        dialog.setContentView(R.layout.bottomsheetlayout);
//
//
//        dialog.getWindow().setGravity(Gravity.BOTTOM);
//
//        TextView ok_txt = (TextView) dialog.findViewById(R.id.ok_txt);
//        TextView seemore_txt = (TextView) dialog.findViewById(R.id.seemore_txt);
//        final TextView searching_ntwrk = (TextView) dialog.findViewById(R.id.searching_ntwrk);
//
//        switch_button = (Switch) dialog.findViewById(R.id.switch_button);
////        wifiList = findViewById(R.id.wifiList);
//
//        switch_button.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                if (isChecked) {
//                    wifiManager.setWifiEnabled(true);
//                    searching_ntwrk.setVisibility(View.VISIBLE);
//
//                } else {
//                    wifiManager.setWifiEnabled(false);
//                    searching_ntwrk.setVisibility(GONE);
//                }
//            }
//        });
//
//        seemore_txt.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(Settings.Panel.ACTION_INTERNET_CONNECTIVITY));
//
//            }
//        });
//
//
//        ok_txt.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                dialog.dismiss();
//            }
//        });
//        dialog.show();
//        dialog.setCanceledOnTouchOutside(true);
//
//    }

/*
    public static boolean isAvailable(Context context) {
        return context.getSystemService(Context.DISPLAY_SERVICE) != null
                && context.getSystemService(Context.WIFI_P2P_SERVICE) != null;
    }
*/

}

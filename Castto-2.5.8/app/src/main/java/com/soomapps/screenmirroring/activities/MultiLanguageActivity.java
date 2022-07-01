package com.soomapps.screenmirroring.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.gms.ads.AdView;
import com.soomapps.screenmirroring.R;
import com.soomapps.screenmirroring.Util.AppConstants;
import com.soomapps.screenmirroring.Util.RecyclerItemClickListener;
import com.yariksoffice.lingver.Lingver;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MultiLanguageActivity extends AppCompatActivity {
    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }
    Locale myLocale;
    String currentLanguage = "en", currentLang;
    AdView adView;
    RecyclerView recyclerView;
    LanguageAdapter mAdapter;
    List<String> list;
    SharedPreferences sharedpreferences;
    SharedPreferences.Editor editor;
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multilanguage);
        if (android.os.Build.VERSION.SDK_INT >= 21) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
        }
        recyclerView=findViewById(R.id.recycler_view);
        currentLanguage = Locale.getDefault().getLanguage();

        sharedpreferences = getSharedPreferences("mypref",
                Context.MODE_PRIVATE);
        editor = sharedpreferences.edit();

       // Log.e("Its current lang","   <><><>   "+currentLanguage);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(getResources().getString(R.string.multi_language));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));

        //setTitle(getResources().getString(R.string.multi_language));
        adView=(AdView)findViewById(R.id.adView);
        AppConstants.codeForBannerAd(MultiLanguageActivity.this,adView);

        if(AppConstants.checkInternetConnection(MultiLanguageActivity.this)){
            adView.setVisibility(View.VISIBLE);
        }else{
            adView.setVisibility(View.GONE);
        }
        addLanguage();

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(),1);
        gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL); // set Horizontal Orientation
        recyclerView.setLayoutManager(gridLayoutManager);

        mAdapter = new LanguageAdapter(MultiLanguageActivity.this,list,currentLanguage);
        recyclerView.setAdapter(mAdapter);

        recyclerView.addOnItemTouchListener(
                (RecyclerView.OnItemTouchListener) new RecyclerItemClickListener(MultiLanguageActivity.this, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        switch (position) {
                            case 0:
                                myLocale = new Locale("ar");
                                editor.putString("arab","yes");
                                editor.commit();
                                break;
                            case 1:
                                myLocale = new Locale("hr");
                                editor.putString("arab","no");
                                editor.commit();
                                break;
                            case 2:
                                myLocale = new Locale("cs");
                                editor.putString("arab","no");
                                editor.commit();
                                break;
                            case 3:
                                myLocale = new Locale("nl");
                                editor.putString("arab","no");
                                editor.commit();
                                break;
                            case 4:
                                myLocale = new Locale("en");
                                editor.putString("arab","no");
                                editor.commit();
                                break;
                            case 5:
                                myLocale = new Locale("fil");
                                editor.putString("arab","no");
                                editor.commit();
                                break;
                            case 6:
                                myLocale = new Locale("fr");
                                editor.putString("arab","no");
                                editor.commit();
                                break;
                            case 7:
                                myLocale = new Locale("de");
                                editor.putString("arab","no");
                                editor.commit();
                                break;
                            case 8:
                                myLocale = new Locale("in");
                                editor.putString("arab","no");
                                editor.commit();
                                break;
                            case 9:
                                myLocale = new Locale("it");
                                editor.putString("arab","no");
                                editor.commit();
                                break;
                            case 10:
                                myLocale = new Locale("ko");
                                editor.putString("arab","no");
                                editor.commit();
                                break;
                            case 11:
                                // Required empty public constructor
                                myLocale = new Locale("ms");
                                editor.putString("arab","no");
                                editor.commit();
                                break;
                            case 12:
                                myLocale = new Locale("pl");
                                editor.putString("arab","no");
                                editor.commit();
                                break;
                            case 13:
                                myLocale = new Locale("pt");
                                editor.putString("arab","no");
                                editor.commit();
                                break;
                            case 14:
                                myLocale = new Locale("ru");
                                editor.putString("arab","no");
                                editor.commit();
                                break;
                            case 15:
                                myLocale = new Locale("sr");
                                editor.putString("arab","no");
                                editor.commit();
                                break;
                            case 16:
                                myLocale = new Locale("sk");
                                editor.putString("arab","no");
                                editor.commit();
                                break;
                            case 17:
                                myLocale = new Locale("sl");
                                editor.putString("arab","no");
                                editor.commit();
                                break;
                            case 18:
                                myLocale = new Locale("es");
                                editor.putString("arab","no");
                                editor.commit();
                                break;
                            case 19:
                                myLocale = new Locale("sv");
                                editor.putString("arab","no");
                                editor.commit();
                                break;
                            case 20:
                                myLocale = new Locale("th");
                                editor.putString("arab","no");
                                editor.commit();
                                break;
                            case 21:
                                myLocale = new Locale("tr");
                                editor.putString("arab","no");
                                editor.commit();
                                break;
                            case 22:
                                myLocale = new Locale("vi");
                                editor.putString("arab","no");
                                editor.commit();
                                break;
                        }
                        setNewLocale(String.valueOf(myLocale),"English");
                        Resources res = getResources();
                        DisplayMetrics dm = res.getDisplayMetrics();
                        Configuration conf = res.getConfiguration();
                        conf.locale = myLocale;
                        res.updateConfiguration(conf, dm);
                        Intent refresh = new Intent(MultiLanguageActivity.this, MainActivity.class);
                        startActivity(refresh);
                        finish();

                    }
                })
        );
    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }

    private void setNewLocale(String language,  String country) {
        Lingver.getInstance().setLocale(this, language, country);
        restart();
    }

    private void followSystemLocale() {
        Lingver.getInstance().setFollowSystemLocale(this);
        restart();
    }

    private void restart() {
        Intent i = new Intent(MultiLanguageActivity.this, MainActivity.class);
        startActivity(i);
        finish();
        // startActivity(i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK))

        // Toast.makeText(this, "Activity restarted", Toast.LENGTH_SHORT).show()
    }

    public void addLanguage(){
        list = new ArrayList<String>();
        list.add("Arabic");
        list.add("Croatian");  /*hr_ [Croatian]*/
        list.add("Czech");
        list.add("Dutch");/*    nl_ [Dutch]*/
        list.add("English");
        list.add("Filipino");/*fil_*/
        list.add("French");
        list.add("German");/*de*/
        list.add("Indonesian");/*in*/
        list.add("Italian");/*it_ [Italian]*/
        list.add("Korean");/*ko_ [Korean]*/
        list.add("Malay");/*ms_ [Malay]*/
        list.add("Polish");/*pl_ [Polish]*/
        list.add("Portuguese");
        list.add("Russian");/*ru_ [Russian]*/
        list.add("Serbian");/*sr_ [Serbian]*/
        list.add("Slovak");/*    sk_ [Slovak]*/
        list.add("Slovenian");/*sl_ [Slovenian]*/
        list.add("Spanish");
        list.add("Swedish");/*sv_ [Swedish]*/
        list.add("Thai");/*th_ [Thai]*/
        list.add("Turkish");/*tr_ [Turkish]*/
        list.add("Vietnamese");/*vi_ [Vietnamese]*/
    }

}

package com.soomapps.screenmirroring.Util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.wifi.WifiManager;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

/**
 * Created by ntf-19 on 25/1/18.
 */

public class AppConstants {

    public static String developer_id_link="https://play.google.com/store/apps/dev?id=6622016126453358044";

    @SuppressLint("MissingPermission")
    public static boolean checkInternetConnection(Context context) {
        try
        {
        ConnectivityManager conMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (conMgr.getActiveNetworkInfo() != null && conMgr.getActiveNetworkInfo().isAvailable() && conMgr.getActiveNetworkInfo().isConnected()) {
            return true;
        } else {
            System.out.println("Internet Connection Not Present");
            return false;
        }
    }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return false;
    }

    @SuppressLint("MissingPermission")
    public static void codeForBannerAd(final Context mContext, AdView mAdView){
        AdRequest.Builder builder = new AdRequest.Builder();
                /*.addTestDevice(AdRequest.DEVICE_ID_EMULATOR);*/
     /*   GDPRChecker.Request request = GDPRChecker.getRequest();
        if (request == GDPRChecker.Request.NON_PERSONALIZED) {
            // load non Personalized ads
            Bundle extras = new Bundle();
            extras.putString("npa", "1");
            builder.addNetworkExtrasBundle(AdMobAdapter.class, extras);
        } // else do nothing , it will load PERSONALIZED ads*/
        mAdView.loadAd(builder.build());
    }

    public static void onWifi(Context context){
        WifiManager wifiManager = (WifiManager)context.getSystemService(Context.WIFI_SERVICE);
        wifiManager.setWifiEnabled(true);
    }

}


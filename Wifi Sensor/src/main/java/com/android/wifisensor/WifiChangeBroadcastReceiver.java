package com.android.wifisensor;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by aa on 14-2-11.
 */
public class WifiChangeBroadcastReceiver extends BroadcastReceiver {

    Context mContext;
    TextView textView;

    public WifiChangeBroadcastReceiver() {
        //textView = (TextView) mContext.findViewById(R.id.info);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        mContext = context;
        obtain();
        Log.i("\n\nchange ","sadsasfas");

    }


    public void obtain(){

        WifiManager wifiManager = (WifiManager) mContext.getSystemService(mContext.WIFI_SERVICE);
        WifiInfo winfo = wifiManager.getConnectionInfo();

        wifiManager.startScan();
        List<ScanResult> scanResultsList = wifiManager.getScanResults();
        StringBuffer sb = new StringBuffer();
        Log.i("scanResult:", "" + scanResultsList.size());

        for(ScanResult sr:scanResultsList){
            double strength = WifiManager.calculateSignalLevel(sr.level, 1001);
            sb.append("名称：").append(sr.SSID+"\nmac地址:"+sr.BSSID+"\n强度："+strength);
            sb.append("\n------------------------------------\n");
        }
        //info.setText("");
    }

}

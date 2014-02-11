package com.android.wifisensor;

import android.accounts.OperationCanceledException;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.telephony.*;

import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.text.format.Formatter;
import android.util.Log;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    TextView info;
    Button btn1;
    Button btn2;
    Spinner spinner;
    private ArrayAdapter<String> adapter = null;
    private static final String [] loca ={};
    List<String> ll = new ArrayList<String>();


    @TargetApi(Build.VERSION_CODES.GINGERBREAD)
    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_main);


        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        info = (TextView)this.findViewById(R.id.info);
        btn1 = (Button)this.findViewById(R.id.detect);
        btn2 = (Button)this.findViewById(R.id.upload);

        spinner = (Spinner)this.findViewById(R.id.location);

        loadData();

        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,(ArrayList)ll);
        //设置下拉列表风格
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //将适配器添加到spinner中去
        spinner.setAdapter(adapter);
        spinner.setBackgroundColor(android.R.color.white);
        spinner.setVisibility(View.VISIBLE);//设置默认显示

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        info.setMovementMethod(ScrollingMovementMethod.getInstance());

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Example action.", Toast.LENGTH_SHORT).show();
                obtain();
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), spinner.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
            }
        });

/*
        Thread t = new Thread(new Runnable()
        {
            public void run()
            {
                while(true)
                {
                    runOnUiThread(new Runnable()
                    {
                        public void run()
                        {
                            obtain();
                        }
                    });
                    try{
                        Thread.sleep(500);
                    }catch(InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }
        });
        t.start();
*/
/*
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
*/


        //String wifi = obtainwifi();
        //info.setText(wifi);
        obtain();

    }

    public void obtain(){

        WifiManager wifiManager = (WifiManager) getSystemService(WIFI_SERVICE);
        WifiInfo winfo = wifiManager.getConnectionInfo();

        wifiManager.startScan();
        List<ScanResult> scanResultsList = wifiManager.getScanResults();
        StringBuffer sb = new StringBuffer();
        Log.i("scanResult:", ""+ scanResultsList.size());

        for(ScanResult sr:scanResultsList){

            if(sr.SSID.compareTo("sMobileNet")==0){
                double strength = WifiManager.calculateSignalLevel(sr.level, 1001);
                sb.append("名称：").append(sr.SSID+"\nmac地址:"+sr.BSSID+"\n强度："+strength);
                sb.append("\n------------------------------------\n");
            }

        }
        //info.setText("");
        info.setText(sb);
/*
        if (winfo.getBSSID() != null) {
            // 链接信号强度
            double strength = WifiManager.calculateSignalLevel(winfo.getRssi(), 1001);
            StringBuilder sb = new StringBuilder();
            sb.append("名称：").append(winfo.getSSID()+"\nmac地址:"+winfo.getBSSID()+"\n强度："+strength+"\nid:"+winfo.getNetworkId());
            info.setText(sb);
        }
*/
        //Log.i("@@@@@@@@@@@@@@@@localIterator:",""+localIterator.hasNext());

    }

    public void loadData(){

        Log.i("log"," data");
        HTTPService hs = new HTTPService();
         //return serviceProvider.getService(getActivity()).getLocation(currentCategory);
        try {
            List<LocationCategory> lc = hs.getLocationCategory();
            Log.i("load"," "+lc.size());

            for(LocationCategory lca : lc){
                List<Location> l = hs.getLocation(lca);
                //Log.i("location num",""+l.size() + "   for " +lca.getName());
                    for(Location lo : l){
                        Log.i("@@@@@@@@@@@",lo.getCategory() +"  -  "+ lo.getName());
                        ll.add(lca.getName() +"  -  "+ lo.getName());
                    }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String obtainwifi(){

        WifiManager wifiManager = (WifiManager) getSystemService(WIFI_SERVICE);
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();

        //System.out.println("    " + wifiInfo.getNetworkId() + " " + wifiInfo.getBSSID() + " " + wifiInfo.getHidde;
        
        List<ScanResult> scanResultsList = wifiManager.getScanResults();
        StringBuffer sb = new StringBuffer();

        Log.i("scanResult:", ""+ scanResultsList.size());

        for(ScanResult sr:scanResultsList){
            sb.append("\n\n信号名称：" + sr.SSID);
            //sb.append(""+sr.toString());
            sb.append("\nMAC：" + sr.BSSID);
            sb.append("\n\ndescribeContents：" +  sr.describeContents());
            //sb.append("\n\n：" + wifiInfo.getLinkSpeed());
            sb.append("\n\nMac(手机)：" + wifiInfo.getMacAddress()==null?"No Wifi Device":wifiInfo.getMacAddress());
            sb.append("\n\n信号强度：" + sr.level);
            sb.append("\n\ncapabilities:" + sr.capabilities);
            sb.append("\n\nfrequency:" + sr.frequency);

            Log.i("@@@@@@@@@@@@NetworkReceiver", "连接wifi的信息：\n" + sb.toString());
        }

        return sb.toString();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }
    }

}

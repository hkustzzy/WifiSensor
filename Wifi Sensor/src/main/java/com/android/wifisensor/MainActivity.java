package com.android.wifisensor;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
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


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }

    public void onReceive(Context context, Intent intent) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo net = connectivityManager.getActiveNetworkInfo();
        if (net == null) {
            Log.i("@@@@@@@@@@@@NetworkReceiver", "无网络连接");
        } else {
            Log.i("@@@@@@@@@@@@NetworkReceiver", "网络连接类型为" + net.getTypeName());
        }
        // wifi连接状态
        State wifi = connectivityManager.getNetworkInfo(
                ConnectivityManager.TYPE_WIFI).getState();
        if (wifi == State.CONNECTED || wifi == State.CONNECTING) {
            Log.i("@@@@@@@@@@@@NetworkReceiver", "连接wifi");
            // wifi管理
            WifiManager wifiManager = (WifiManager) context
                    .getSystemService(Context.WIFI_SERVICE);
            WifiInfo wifiInfo = wifiManager.getConnectionInfo();
            StringBuffer sb = new StringBuffer();
            sb.append("\n获取BSSID属性（所连接的WIFI设备的MAC地址）：" + wifiInfo.getBSSID());
            // sb.append("getDetailedStateOf()  获取客户端的连通性：");
            sb.append("\n\n获取SSID 是否被隐藏：" + wifiInfo.getHiddenSSID());
            // IP地址转化为字符串格式
            sb.append("\n\n获取IP 地址：" +  Formatter.formatIpAddress(wifiInfo.getIpAddress()));
            sb.append("\n\n获取连接的速度：" + wifiInfo.getLinkSpeed());
            sb.append("\n\n获取Mac 地址（手机本身网卡的MAC地址）：" + wifiInfo.getMacAddress()==null?"No Wifi Device":wifiInfo.getMacAddress());
            sb.append("\n\n获取802.11n 网络的信号：" + wifiInfo.getRssi());
            sb.append("\n\n获取SSID（所连接的WIFI的网络名称）：" + wifiInfo.getSSID());
            sb.append("\n\n获取具体客户端状态的信息：" + wifiInfo.getSupplicantState());
            Log.i("@@@@@@@@@@@@NetworkReceiver", "连接wifi的信息：\n"+sb.toString());

        } else if (wifi == State.DISCONNECTED || wifi == State.DISCONNECTING) {
            Log.i("@@@@@@@@@@@@NetworkReceiver", "断开wifi");
        }

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

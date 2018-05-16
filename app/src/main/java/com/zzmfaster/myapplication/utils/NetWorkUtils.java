package com.zzmfaster.myapplication.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.telephony.TelephonyManager;
import android.util.Log;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

public class NetWorkUtils {


    /**
     * 获取GSM网络的IP地址
     */
    public static String getMobileIP() {
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements(); ) {
                NetworkInterface intf = (NetworkInterface) en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress()) {
                        String ipaddress = inetAddress.getHostAddress().toString();
                        return ipaddress;
                    }
                }
            }
        } catch (SocketException ex) {
            Log.e("getMobileIP", "Exception in Get IP Address: " + ex.toString());
        }
        return "";
    }

    /**
     * 获取WIFI网络的IP地址
     */
    public static String getIp(Context context) {
        WifiManager wifiManager = (WifiManager) context
                .getSystemService(Context.WIFI_SERVICE);
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
        int ipAddress = wifiInfo.getIpAddress();

        // 格式化IP address，例如：格式化前：1828825280，格式化后：192.168.1.109
        String ip = String.format("%d.%d.%d.%d", (ipAddress & 0xff),
                (ipAddress >> 8 & 0xff), (ipAddress >> 16 & 0xff),
                (ipAddress >> 24 & 0xff));
        return ip;
    }

    /**
     * 获取网络类型
     */
    public static String getNetTypeName(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            NetworkInfo activeNetworkInfo = connectivityManager
                    .getActiveNetworkInfo();
            if (activeNetworkInfo == null) {
                return "无网络";
            }
            if (activeNetworkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
                return activeNetworkInfo.getTypeName();
            } else {
                String typeName = activeNetworkInfo.getSubtypeName();
                //Log.i("网络名称:", typeName);
                if (typeName == null || typeName.length() == 0) {
                    return "未知网络";
                } else if (typeName.length() > 3) {
                    return activeNetworkInfo.getSubtypeName().substring(0, 4);
                } else {
                    return activeNetworkInfo.getSubtypeName().substring(0,
                            typeName.length());
                }
            }
        } else {
            return "无网络";
        }
    }

    /**
     *  判断网络状态
     */
    public static boolean isNetOk(Context context) {
        ConnectivityManager cm = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm == null)
            return false;
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo == null) {
            return false;
        }
        if (netInfo.isConnected()) {
            return true;
        }
        return false;
    }

    /**
     *  获取手机号码(双卡手机未测试)
     */
    public static String getTelNumber(Context context) {
        TelephonyManager tm = (TelephonyManager) context
                .getSystemService(Context.TELEPHONY_SERVICE);
        return tm.getLine1Number();
    }

}

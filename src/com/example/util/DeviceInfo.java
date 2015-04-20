package com.example.util;

import java.util.Locale;
import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.nfc.NfcAdapter;

public class DeviceInfo {
	/**
	 * Return MAC address of the wifi interface. If WIFI is not enabled, null is
	 * returned.
	 * 
	 * @param context
	 * @return MAC address string (12 digits, in upper-case) or null
	 */
	public static String getMacAddress(Context context) {
		WifiManager mgr = (WifiManager) context
				.getSystemService(Context.WIFI_SERVICE);
		if (mgr.isWifiEnabled()) {
			WifiInfo info = mgr.getConnectionInfo();
			String mac = info.getMacAddress();
			if (mac != null) {
				// format it to 12 digits/upper-case
				mac = mac.replace(":", "").toUpperCase(Locale.US);
			}
			return mac;
		}
		return null;

	}
	
	
	/**
	 * need permission
	 * uses-permission android:name="android.permission.ACCESS_WIFI_STATE"
	 * uses-permission android:name="android.permission.CHANGE_WIFI_STATE"
	 * uses-permission android:name="android.permission.UPDATE_DEVICE_STATS"
	 * @param context
	 * */
	
	public static boolean isWifiAvailable(Context context) {
		WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
		
		if (wifiManager.isWifiEnabled())
			return true;
		else
			return false;
	}
	
	
	/**
	 * uses-permission android:name="android.permission.BLUETOOTH_ADMIN"
	 * uses-permission android:name="android.permission.BLUETOOTH"
	 * 
	 * @param context
	 * */
	
	public static boolean isBlueToothAvailable(Context context) {
		boolean isBlueToothAvailable = context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_BLUETOOTH);
		
		if (isBlueToothAvailable)
			return true;
		else
			return false;
	}
	/**
	 * require at least API10
	 * @param context
	 * @return boolean isNFCAvailable
	 */
	@SuppressLint("NewApi")
	public static boolean isNFCAvailable(Context context) {
		NfcAdapter nfcAdapter = NfcAdapter.getDefaultAdapter(context);
		
		if (nfcAdapter!=null)
			return true;
		else
			return false;
	}

}

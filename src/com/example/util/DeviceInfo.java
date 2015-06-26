package com.example.util;

import java.util.Locale;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.hardware.Camera.CameraInfo;
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
	
<<<<<<< HEAD
	public float getBackCameraResolutionInMp()
	{
	    int noOfCameras = Camera.getNumberOfCameras();
	    float maxResolution = -1;
	    long pixelCount = -1;
	    for (int i = 0;i < noOfCameras;i++)
	    {
	        Camera.CameraInfo cameraInfo = new CameraInfo();
	        Camera.getCameraInfo(i, cameraInfo);

	        if (cameraInfo.facing == CameraInfo.CAMERA_FACING_BACK)
	        {
	            Camera camera = Camera.open(i);
	            Camera.Parameters cameraParams = camera.getParameters();
	            for (int j = 0;j < cameraParams.getSupportedPictureSizes().size();j++)
	            {
	                long pixelCountTemp = cameraParams.getSupportedPictureSizes().get(i).width * cameraParams.getSupportedPictureSizes().get(i).height;
	                if (pixelCountTemp > pixelCount)
	                {
	                    pixelCount = pixelCountTemp;
	                    maxResolution = ((float)pixelCountTemp) / (1024000.0f);
	                }
	            }

	            camera.release();
	        }
	    }

	    return maxResolution;
=======
	/**
	 * YOU MUST ADD uses permission ACCESS_NETWORK_STAT 
	 * 
	 * Indicates whether network connectivity exists and it is possible to
	 * establish connections and pass data.
	 * 
	 * networkType can be null if just check Network available or not
	 * 
	 * @param context
	 * @param networkType
	 *            ex: ConnectivityManager.TYPE_WIFI
	 *            ,ConnectivityManager.TYPE_ETHERNET...etc.;
	 * 
	 * @return true if network connectivity exists, false otherwise
	 * 
	 * 
	 */

	public static boolean isNetworkConnected(Context context, int networkType) {
		ConnectivityManager connManager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo info = connManager.getNetworkInfo(networkType);
		if (info != null)
			return info.isConnected();
		else
			return false;
	}
	
	/**
	 * YOU MUST ADD uses permission ACCESS_NETWORK_STAT 
	 * 
	 * if network is connect return true;
	 * 
	 * @param context
	 * @return
	 */

	public static boolean isNetworkConnected(Context context) {

		ConnectivityManager manager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networInfo = manager.getActiveNetworkInfo();
		if (networInfo == null || !networInfo.isAvailable())
			return false;
		else
			return true;
>>>>>>> origin/master
	}

}

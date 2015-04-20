package com.example.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetWorkState {
	
	

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
	}
}

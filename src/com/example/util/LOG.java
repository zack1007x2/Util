package com.example.util;

import android.util.Log;

public class LOG {
	
	/**
	 * appear Log if DEBUG is true
	 */
    public static boolean DEBUG = true;
    

    
    public static void v(String TAG, String Info) {
        if (DEBUG) Log.v(TAG,  Info);
    }

    public static void v(String TAG, String Info, Throwable tr) {
        if (DEBUG) Log.v( TAG,  Info, tr);
    }

    
    public static void w(String TAG, String Info) {
        if (DEBUG) Log.w(  TAG,   Info);
    }

    public static void w(String TAG, String Info, Throwable tr) {
        if (DEBUG) Log.w(  TAG,   Info, tr);
    }

    public static void w(String TAG, Throwable tr) {
        if (DEBUG) Log.w(  TAG, tr);
    }

    
    public static void i(String TAG, String Info) {
        if (DEBUG) Log.i(  TAG,   Info);
    }

    public static void i(String TAG, String Info, Throwable tr) {
        if (DEBUG) Log.i(  TAG,  Info, tr);
    }

    
    public static void d(String TAG, String Info) {
        if (DEBUG) Log.d(  TAG,   Info);
    }

    public static void d(String TAG, String Info, Throwable tr) {
        if (DEBUG) Log.d(  TAG,   Info, tr);
    }

    
    public static void e(String TAG, String Info) {
        Log.e(  TAG,   Info);
    }

    public static void e(String TAG, String Info, Throwable tr) {
        Log.e(  TAG,   Info, tr);
    }
    
    
}

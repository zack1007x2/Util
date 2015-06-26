package com.example.util;

import java.lang.reflect.Method;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.text.InputType;
import android.text.method.ReplacementTransformationMethod;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

public class SystemControl {
	
	
	private Context context;
	public SystemControl(Context context) {
		this.context = context;
	}
	/**
	 * 讓一個UI元件永久focus
	 * @param view  
	 */
	public void getFouces(View view){
		view.setFocusable(true);
		view.requestFocus();
		view.setFocusableInTouchMode(true);
	}
	/**
	 * 隱藏系統鍵盤
	 * @param view
	 */
	public void hideSoftInputMethod(TextView view){
		if (android.os.Build.VERSION.SDK_INT <= 10) {  
			view.setInputType(InputType.TYPE_NULL);  
        } else {  
        	((Activity)context).getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);  
            try {  
                Class<TextView> cls = TextView.class;   
                Method setShowSoftInputOnFocus = cls.getMethod("setShowSoftInputOnFocus",  
                        boolean.class);  
                setShowSoftInputOnFocus.setAccessible(true);  
                setShowSoftInputOnFocus.invoke(view, false);  
            } catch (Exception e) {  
                e.printStackTrace();
            }   
        }  
	}
	/**
	 * 轉換EditText中的英文字母自動大寫   只是看起來大寫了，但實際獲取的字符需要toUppCase()轉換一下大寫
	 * @param et
	 */
	public void autoToUppCase(EditText et){
		et.setTransformationMethod(new AllCapTransformationMethod());
	}
	private static class AllCapTransformationMethod extends ReplacementTransformationMethod {
		@Override
		protected char[] getOriginal() {
			char[] aa = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z' };
			return aa;
		}
		@Override
		protected char[] getReplacement() {
			char[] cc = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z' };
			return cc;
		}
	}
	/**
	 * 	禁止螢幕轉暗
	 * @param flag  true設置開啟全屏模式
	 */
	public void setKeepScreenOn(boolean flag) {
		if (flag) {
			((Activity) context).getWindow().setFlags(
					WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON,
					WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		}
	}
	
	/**
	 * Mute or unmute audio streams.
	 * 
	 * @param context
	 * @param state
	 *            The required mute state: true for mute ON, false for mute OFF
	 */
	public void setAllStreamsMute(boolean state) {

		final int[] streams = { AudioManager.STREAM_VOICE_CALL,
				AudioManager.STREAM_SYSTEM, AudioManager.STREAM_RING,
				AudioManager.STREAM_MUSIC, AudioManager.STREAM_ALARM };

		AudioManager audioManager = (AudioManager) ((Activity)context).getSystemService(Context.AUDIO_SERVICE);
		for (int stream : streams) {
			audioManager.setStreamMute(stream, state);
		}

	}
	
	
	

}

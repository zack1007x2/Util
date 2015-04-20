package com.example.util;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

public class SystemControl {
	
	public static void hideKeyboard(Activity activity){
		  // Check if no view has focus:
		View view = activity.getCurrentFocus();
	    if (view instanceof EditText) {
	        InputMethodManager inputManager = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
	        inputManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
	        }
	    }
	
	
	/**
	 * Mute or unmute audio streams.
	 * 
	 * @param context
	 * @param state
	 *            The required mute state: true for mute ON, false for mute OFF
	 */
	public static void setAllStreamsMute(Context context, boolean state) {

		final int[] streams = { AudioManager.STREAM_VOICE_CALL,
				AudioManager.STREAM_SYSTEM, AudioManager.STREAM_RING,
				AudioManager.STREAM_MUSIC, AudioManager.STREAM_ALARM };

		AudioManager audioManager = (AudioManager) context
				.getSystemService(Context.AUDIO_SERVICE);
		for (int stream : streams) {
			audioManager.setStreamMute(stream, state);
		}

	}

}

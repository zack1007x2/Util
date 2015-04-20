package com.example.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

public class Decoder {
	
	/**
	 * 
	 * @param base64img
	 * @return Bitmap
	 */
	public static Bitmap decodeBase64(String base64img) {
		byte[] decodedByte = Base64.decode(base64img, 0);
		return BitmapFactory
				.decodeByteArray(decodedByte, 0, decodedByte.length);
	}

}

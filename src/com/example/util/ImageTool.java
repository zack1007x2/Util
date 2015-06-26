package com.example.util;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class ImageTool {


	// TODO Image Convert

	public static Bitmap resizeBitmap(byte[] byteArray) {
		BitmapFactory.Options option = new BitmapFactory.Options();
		Bitmap resizedBitmap = null;
		resizedBitmap = BitmapFactory.decodeByteArray(byteArray, 0,
				byteArray.length, option);
		option.inJustDecodeBounds = true;
		option = new BitmapFactory.Options();

		option.inSampleSize = 2;
		resizedBitmap = BitmapFactory.decodeByteArray(byteArray, 0,
				byteArray.length, option);
		return resizedBitmap;
	}

	public static Bitmap resizeBitmap(Bitmap bitmap) {
		BitmapFactory.Options option = new BitmapFactory.Options();
		Bitmap resizedBitmap = bitmap;
		option.inJustDecodeBounds = true;
		option = new BitmapFactory.Options();

		option.inSampleSize = 2;
		return resizedBitmap;
	}

}

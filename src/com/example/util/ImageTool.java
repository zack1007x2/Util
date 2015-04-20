package com.example.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Bitmap.CompressFormat;
import android.os.Environment;
import android.util.Base64;
import android.util.Log;

public class ImageTool {

	private static final String TAG = ImageTool.class.getSimpleName();

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

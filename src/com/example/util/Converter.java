package com.example.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Bitmap.CompressFormat;
import android.util.DisplayMetrics;
import android.util.Log;

public class Converter {

	/**
	 * This method converts dp unit to equivalent pixels, depending on device
	 * density.
	 * 
	 * @param dp
	 *            A value in dp (density independent pixels) unit. Which we need
	 *            to convert into pixels
	 * @param context
	 *            Context to get resources and device specific display metrics
	 * @return A float value to represent px equivalent to dp depending on
	 *         device density
	 */
	public static int convertDpToPixel(float dp, Context context) {
		Resources resources = context.getResources();
		DisplayMetrics metrics = resources.getDisplayMetrics();
		float px = dp * (metrics.densityDpi / 160f);
		return (int) px;
	}

	/**
	 * This method converts device specific pixels to density independent
	 * pixels.
	 * 
	 * @param px
	 *            A value in px (pixels) unit. Which we need to convert into db
	 * @param context
	 *            Context to get resources and device specific display metrics
	 * @return A float value to represent dp equivalent to px value
	 */
	public static int convertPixelsToDp(float px, Context context) {
		Resources resources = context.getResources();
		DisplayMetrics metrics = resources.getDisplayMetrics();
		float dp = px / (metrics.densityDpi / 160f);
		return (int) dp;
	}

	public static int convertSpToPixel(float sp, Context context) {
		final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
		return (int) (sp * fontScale + 0.5f);
	}

	// Image

	/**
	 * Convert png file to jpg format
	 * 
	 * @param pngFile
	 *            input file path
	 * @param jpgFile
	 *            output file path
	 * @param quality_persent
	 *            XX% quality after transfer
	 * @throws IOException
	 */
	public static boolean convertPngToJpg(File pngFilePath, File jpgFilePath,
			int quality_persent) throws IOException {

		boolean result = false;
		InputStream in = new FileInputStream(pngFilePath);
		if (jpgFilePath.isDirectory()) {
			try {
				OutputStream out = new FileOutputStream(jpgFilePath);
				try {
					Bitmap bitmap = BitmapFactory.decodeStream(in);

					if (bitmap != null
							&& bitmap.compress(CompressFormat.JPEG,
									quality_persent, out)) {
						result = true;
					}
				} finally {
					out.close();
				}

			} finally {
				in.close();
			}
		}
		return result;
	}

}

package com.example.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

public class FileIO {

	// TODO get filePATH

	public static String getDCIMPath() {

		File f = Environment
				.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);
		if (!f.exists()) {
			// DCIM not exists,use sd path
			f = Environment.getExternalStorageDirectory();
			return f.getPath();
		}else {
			return "";
		}
		
	}

	public static String getSDPath() {

		File sdDir = null;
		boolean sdCardExist = Environment.getExternalStorageState().equals(
				android.os.Environment.MEDIA_MOUNTED);
		if (sdCardExist) {
			sdDir = Environment.getExternalStorageDirectory();
		}
		if (sdDir == null) {
			return "";
		}
		// sdDir = Environment.getDataDirectory();
		return sdDir.toString();

	}
	
	
	public static Bitmap getBitmapfromPath(String path) {

		Bitmap bitmap = null;
		File imgFile = new File(path);
		if (imgFile.exists()) {

			bitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
		}
		return bitmap;
	}
	
	

	/**
	 * 
	 * @param dirpath
	 * @param fileName
	 * @param savebitmap
	 * @return if successfully return image file path,else return null
	 */
	public static String saveBitmapToJPGFile(String dirpath, String fileName,
			Bitmap savebitmap) {
		FileIO.filemkdir(dirpath);

		File picFile = new File(dirpath, fileName + ".jpg");

		if (picFile.exists()) {
			picFile.delete();
		}

		try {
			picFile.createNewFile();
		} catch (IOException e2) {
			e2.printStackTrace();
		}

		FileOutputStream fos = null;
		boolean success = false;
		try {
			fos = new FileOutputStream(picFile);
		} catch (FileNotFoundException e2) {
			e2.printStackTrace();
		}
		success = savebitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
		if (fos != null) {
			try {
				fos.flush();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			try {
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (success) {
			return picFile.getAbsolutePath();
		} else {
			picFile.delete();
			return null;
		}

	}

	/**
	 * 
	 * @param dirpath
	 * @param fileName
	 * @param savebitmap
	 * @return if successfully return image file path,else return null
	 */
	public static String saveBitmapToPNGFile(String dirpath, String fileName,
			Bitmap savebitmap) {

		FileIO.filemkdir(dirpath);

		File picFile = new File(dirpath, fileName + ".png");

		if (picFile.exists()) {
			picFile.delete();
		}

		try {
			picFile.createNewFile();
		} catch (IOException e2) {
			e2.printStackTrace();
		}

		FileOutputStream fos = null;
		boolean success = false;
		try {
			fos = new FileOutputStream(picFile);
		} catch (FileNotFoundException e2) {
			e2.printStackTrace();
		}
		success = savebitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
		if (fos != null) {
			try {
				fos.flush();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			try {
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (success) {
			return picFile.getAbsolutePath();
		} else {
			picFile.delete();
			return null;
		}

	}

	// Create filePath
	public static boolean filemkdir(String Path) {
		File file = new File(Path);
		if (!file.isDirectory()) {
			file.mkdirs();
			return true;
		}
		return false;
	}

	/**
	 * delete hole file
	 * 
	 * @param fileOrDirectory
	 */
	public static void DeleteRecursive(File fileOrDirectory) {
		if (fileOrDirectory.isDirectory())
			for (File child : fileOrDirectory.listFiles())
				DeleteRecursive(child);

		fileOrDirectory.delete();
	}
	
	
	public static byte[] getFileToByteArray(File file){
		ByteArrayOutputStream bos = null;
		FileInputStream fis = null;
		byte[] buffer = null;
		try {
			bos = new ByteArrayOutputStream();
			fis = new FileInputStream(file);
			byte[] buf = new byte[1024];
			int len = 0;
			while((len = fis.read(buf)) != -1){
				bos.write(buf, 0, len);
			}
			buffer = bos.toByteArray();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				if(bos != null)
					bos.close();
			} catch (IOException e) {
				e.printStackTrace();
				bos = null;
				System.gc();
			}
			try {
				if(fis != null)
					fis.close();
			} catch (IOException e) {
				e.printStackTrace();
				fis = null;
				System.gc();
			}
		}
		return buffer;
	}
	
	public boolean isFileExists(String filePath){
		return new File(filePath).exists();
	}
	
	
	public boolean isDictionaryExists(String FolderPath){
		return new File(FolderPath).isDirectory();
	}
	
	


}

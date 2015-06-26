package com.example.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.Thread.UncaughtExceptionHandler;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

/**
 * 需創建Application class
 * 在OnCreate initial CrashHandler
 * 		CrashHandler crashHandler = CrashHandler.getInstance();  
 *   	crashHandler.init(this);  
 */
public class SaveLogHandler implements UncaughtExceptionHandler,Runnable {
	private static SaveLogHandler INSTANCE = new SaveLogHandler();
	private Context mContext;
	private Map<String, String> info = new HashMap<String, String>();// 用来存储设备信息和异常信息  
	/**系統預設的Handler，當自製Handler無法正常使用時，使用預設Handler*/private UncaughtExceptionHandler mDefaultHandler;
	private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss",Locale.getDefault());
	private Thread errThread;
	private Throwable errorInfoToFile;
	
	private SaveLogHandler(){// 保證只產生一個實體
	}
	
	/**
	 * 取得{@link SaveLogHandler}實體
	 * @return
	 */
	public static SaveLogHandler getInstance(){
		return INSTANCE;
	}
	
	public void init(Context context){
		this.mContext = context;
		mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
		Thread.setDefaultUncaughtExceptionHandler(this);
//        scanner = new MediaScannerConnection(mContext, this);
//        scanner.connect();
	}

	@Override
	public void uncaughtException(Thread thread, Throwable ex) {
		// TODO Auto-generated method stub
		this.errThread = thread;
		if(!handleException(ex) && mDefaultHandler!=null)
			mDefaultHandler.uncaughtException(thread, ex);
	}
	
    /** 
     * @hide
     * 收集设备参数信息 
     * @param context 
     */
    void collectDeviceInfo(Context context) {  
        try {  
            PackageManager pm = context.getPackageManager();// 获得包管理器  
            PackageInfo pi = pm.getPackageInfo(context.getPackageName(),  
                    PackageManager.GET_ACTIVITIES);// 得到该应用的信息，即主Activity  
            if (pi != null) {  
                String versionName = pi.versionName == null ? "null"
                        : pi.versionName;  
                String versionCode = pi.versionCode + "";  
                info.put("versionName", versionName);  
                info.put("versionCode", versionCode);  
            }  
        } catch (NameNotFoundException e) {  
            e.printStackTrace();  
        }  
                
        Field[] fields = Build.class.getDeclaredFields();// 反射机制  
        for (Field field : fields) {  
            try {  
                field.setAccessible(true);  
                info.put(field.getName(), field.get("").toString());  
                Log.d("TAG", field.getName() + ":" + field.get(""));  
            } catch (IllegalArgumentException e) {  
                e.printStackTrace();  
            } catch (IllegalAccessException e) {  
                e.printStackTrace();  
            }  
        }  
    }  
	
	/**
	 * 
	 * @param ex
	 * @return 是否成功處理錯誤訊息
	 */
	private boolean handleException(Throwable ex){
		if(ex==null)
			return false;
		
		Toast.makeText(mContext, "很抱歉,程序出现异常,即将退出", Toast.LENGTH_SHORT).show(); 
		this.errorInfoToFile = ex;
		new Thread(this).start(); 
		
		return true;
	}
	
	/**
	 * 保存訊息至檔案
	 * @param ex
	 */
	private void saveCrashInfoToFile(Thread thread,Throwable ex){
		StringBuffer sb = new StringBuffer();
		Writer writer = new StringWriter();  
        PrintWriter pw = new PrintWriter(writer);  
        ex.printStackTrace(pw);  
        Throwable cause = ex.getCause();  
        // 循环着把所有的异常信息写入writer中  
        while (cause != null) {  
            cause.printStackTrace(pw);  
            cause = cause.getCause();  
        }  
        pw.close();// 记得关闭  
        String result = writer.toString();  
        sb.append(result);  
        // 保存文件  
        long timetamp = System.currentTimeMillis();  
        String time = format.format(new Date());  
        String fileName = "crash-" + time + "-" + timetamp + ".txt";  
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
            try {  
                File dir = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "appcrash");  
                if (!dir.exists())
                	dir.mkdir();
                else
                	Log.d("TAG","folder exist");
                
                File file = new File(dir, fileName);
                
                if(!file.exists())
                	file.createNewFile();
                
                FileOutputStream fos = new FileOutputStream(file);  
                
                String str = sb.toString();
                
                fos.write(str.replaceAll("\t", "\r\n").getBytes());  
                fos.close();
                
                if(file.exists() && file.isFile())// 送出廣播讓系統處理，千萬別用TMD MediaScannerClient，因為接下來程式就要當掉了
                	mContext.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.fromFile(file)));
            } catch (FileNotFoundException e){  
            	Log.e("TAG",e.getMessage());  
            } catch (IOException e){ 
            	Log.e("TAG",e.getMessage());  
            }  
            mDefaultHandler.uncaughtException(thread, ex);
        } 
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		saveCrashInfoToFile(this.errThread,this.errorInfoToFile);
	}
}
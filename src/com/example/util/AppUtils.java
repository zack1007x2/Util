package com.example.util;

import java.io.File;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.Intent.ShortcutIconResource;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;

/**
 * 軟件工具類，獲取軟件的各種屬性
 * @author lee
 */
public class AppUtils {
	private Context context;
	public AppUtils(Context context) {
		this.context = context;
	}
	/**
	 * 取得當前版本號
	 * @return
	 */
	public String getVersionName() {
		try {
			PackageManager packageManager = context.getPackageManager();
			PackageInfo packInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
			String version = packInfo.versionName;
			return version;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 取得當前正在運行的Activity
	 * @return
	 * 		<uses-permission android:name="android.permission.GET_TASKS"/>
	 */
	public String getActivityName() {
		ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
		RunningTaskInfo info = manager.getRunningTasks(1).get(0);
		String shortClassName = info.topActivity.getShortClassName();
		System.out.println("shortClassName=" + shortClassName);
		return shortClassName;
	}

	/**
	 * 安裝指定文件路徑的apk文件
	 * @param path
	 */
	public void installApk(String path) {
		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		intent.setDataAndType(Uri.fromFile(new File(path)),
				"application/vnd.android.package-archive");
		context.startActivity(intent); // 安裝新版本
	}
	/**
	 * 建立桌面捷徑/上架至google play的應用程式不需使用此方法
	 * @param resId  捷徑圖片
	 * 	<uses-permission android:name="com.android.launcher.permission.INSTALL_SHORTCUT" />
	 */
	public void createShortcut(int resId) {
			Intent shortcut = new Intent(
					"com.android.launcher.action.INSTALL_SHORTCUT");
			shortcut.putExtra(Intent.EXTRA_SHORTCUT_NAME,
					context.getString(R.string.app_name));
			shortcut.putExtra("duplicate", false);
			ComponentName comp = new ComponentName(context.getPackageName(), "."
					+ ((Activity) context).getLocalClassName());
			shortcut.putExtra(Intent.EXTRA_SHORTCUT_INTENT, new Intent(
					Intent.ACTION_MAIN).setComponent(comp));
			ShortcutIconResource iconRes = Intent.ShortcutIconResource.fromContext(context, resId);
			shortcut.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE, iconRes);
			context.sendBroadcast(shortcut);
	}
}

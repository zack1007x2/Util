package com.example.util;

import java.util.Hashtable;
import android.util.Log;

public class LOG {
	/**
	 * @param logFlag 		appear Log if logFlag is true
	 * @param tag 			set up app name here
	 * @param logLevel 		only show tag >= logLevel
	 */
    private final static boolean logFlag = true;
    public static String tag = "AppName";
    private final static int logLevel = Log.VERBOSE;
	

	
	
	private static Hashtable<String, LOG> sLogTable = new Hashtable<String, LOG>();
	private String mClassName;
	private static LOG alog;
	private static LOG jlog;
	private static LOG tlog;
	private static LOG zlog;
	private static LOG log;
	
	private static final String ALEX = "@alex@ ";
	private static final String JACK = "@jack@ ";
	private static final String TINA = "@tina@ ";
	private static final String ZACK = "@zack@ ";

	private LOG(String name) {
		mClassName = name;
	}

	/**
	 * custom Log
	 * @param LogTitle  A identify name you want to show in log ,it can be null
	 * @param TagName	Tag showed in LogCat
	 * @return A initialed log object
	 */
	public static LOG Log(String LogTitle, String TagName) {
		if (log == null) {
			tag = TagName;
			log = new LOG(LogTitle);
		}
		return log;
	}
	
	/**
	 * Log for Alex
	 * @param TagName Tag showed in LogCat
	 * @return initialed log object
	 */
	public static LOG aLog(String TagName) {
		if (alog == null) {
			tag = TagName;
			alog = new LOG(ALEX);
		}
		return alog;
	}

	/**
	 * Log for Jack
	 * @param TagName Tag showed in LogCat
	 * @return initialed log object
	 */
	public static LOG jLog(String TagName) {
		if (jlog == null) {
			tag = TagName;
			jlog = new LOG(JACK);
		}
		return jlog;
	}
	/**
	 * Log for Tina
	 * @param TagName Tag showed in LogCat
	 * @return initialed log object
	 */
	public static LOG tLog(String TagName) {
		if (tlog == null) {
			tag = TagName;
			tlog = new LOG(TINA);
		}
		return tlog;
	}
	/**
	 * Log for Zack
	 * @param TagName Tag showed in LogCat
	 * @return initialed log object
	 */
	public static LOG zLog(String TagName) {
		if (zlog == null) {
			tag = TagName;
			zlog = new LOG(ZACK);
		}
		return zlog;
	}

	/**
	 * Get The Current Function Name
	 * 
	 * @return
	 */
	private String getFunctionName() {
		StackTraceElement[] sts = Thread.currentThread().getStackTrace();
		if (sts == null) {
			return null;
		}
		for (StackTraceElement st : sts) {
			if (st.isNativeMethod()) {
				continue;
			}
			if (st.getClassName().equals(Thread.class.getName())) {
				continue;
			}
			if (st.getClassName().equals(this.getClass().getName())) {
				continue;
			}
			return mClassName + "[ " + Thread.currentThread().getName() + ": "
					+ st.getFileName() + ":" + st.getLineNumber() + " "
					+ st.getMethodName() + " ]";
		}
		return null;
	}

	/**
	 * The Log Level:i
	 * 
	 * @param str  Text showed in LogCat
	 */
	public void i(Object str) {
		if (logFlag) {
			if (logLevel <= Log.INFO) {
				String name = getFunctionName();
				if (name != null) {
					Log.i(tag, name + " - " + str);
				} else {
					Log.i(tag, str.toString());
				}
			}
		}

	}

	/**
	 * The Log Level:d
	 * 
	 * @param str Text showed in LogCat
	 */
	public void d(Object str) {
		if (logFlag) {
			if (logLevel <= Log.DEBUG) {
				String name = getFunctionName();
				if (name != null) {
					Log.d(tag, name + " - " + str);
				} else {
					Log.d(tag, str.toString());
				}
			}
		}
	}

	/**
	 * The Log Level:V
	 * 
	 * @param str Text showed in LogCat
	 */
	public void v(Object str) {
		if (logFlag) {
			if (logLevel <= Log.VERBOSE) {
				String name = getFunctionName();
				if (name != null) {
					Log.v(tag, name + " - " + str);
				} else {
					Log.v(tag, str.toString());
				}
			}
		}
	}

	/**
	 * The Log Level:w
	 * 
	 * @param str Text showed in LogCat
	 */
	public void w(Object str) {
		if (logFlag) {
			if (logLevel <= Log.WARN) {
				String name = getFunctionName();
				if (name != null) {
					Log.w(tag, name + " - " + str);
				} else {
					Log.w(tag, str.toString());
				}
			}
		}
	}

	/**
	 * The Log Level:e
	 * 
	 * @param str Text showed in LogCat
	 */
	public void e(Object str) {
		if (logFlag) {
			if (logLevel <= Log.ERROR) {
				String name = getFunctionName();
				if (name != null) {
					Log.e(tag, name + " - " + str);
				} else {
					Log.e(tag, str.toString());
				}
			}
		}
	}

	/**
	 * The Log Level:e
	 * LOG for show Exception content
	 * 
	 * @param ex
	 */
	public void e(Exception ex) {
		if (logFlag) {
			if (logLevel <= Log.ERROR) {
				Log.e(tag, "error", ex);
			}
		}
	}

	/**
	 * The Log Level:e
	 * LOG for thread
	 * 
	 * @param log
	 * @param tr
	 */
	public void e(String log, Throwable tr) {
		if (logFlag) {
			String line = getFunctionName();
			Log.e(tag, "{Thread:" + Thread.currentThread().getName() + "}"
					+ "[" + mClassName + line + ":] " + log + "\n", tr);
		}
	}
    
}

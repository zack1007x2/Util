package com.examlpe.util.hardware;

public interface GPSListener {
	/**
	 * 當GPS資訊發生變化時調用，該方法可以更新手機的GPS資訊
	 * @param gpsParams GPS資訊
	 */
	void updateGPS(GPSParams gpsParams);

}

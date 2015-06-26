package com.examlpe.util.hardware;
import android.hardware.SensorEventListener;
public interface MySensor extends SensorEventListener{
	public void onCreate();
	public void onRaumse(int rate);
	public void onPause();
}
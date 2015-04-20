package com.examlpe.util.hardware;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorManager;
/**
 * 加速度感應器
 */
public class AccessSensorUtil implements MySensor {
	private Context context;
	private SensorManager manager;
	private float x,y,z;
	private Sensor sensor;
	public AccessSensorUtil(Context context) {
		this.context = context;
	}
	@Override
	public void onCreate() {
		manager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
		sensor = manager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
	}
	@Override
	public void onRaumse(int rate) {
		manager.registerListener(this, sensor, rate);
	}
	@Override
	public void onPause() {
		manager.unregisterListener(this);
	}
	@Override
	public void onSensorChanged(SensorEvent event) {
		float[] values = event.values;
		x = values[0];
		y = values[1];
		z = values[2];
	}
	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {

	}
	/**
	 * 取得X軸上的加速度值
	 */
	public float getX() {
		return x;
	}
	/**
	 * 取得Y軸上的加速度值
	 */
	public float getY() {
		return y;
	}
	/**
	 * 取得Z軸上的加速度值
	 */
	public float getZ() {
		return z;
	}
	
}

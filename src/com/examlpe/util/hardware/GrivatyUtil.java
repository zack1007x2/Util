package com.examlpe.util.hardware;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorManager;

/**
 *	重力感應器 需在對應的生命週期呼叫對應的方法
 */
public class GrivatyUtil implements MySensor {
	private Context context;
	private SensorManager manager;
	private Sensor sensor;
	private float x,y,z;
	public GrivatyUtil(Context context) {
		this.context = context;
	}

	public void onCreate(){
		manager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
		sensor = manager.getDefaultSensor(Sensor.TYPE_GRAVITY);
	}
	public void onRaumse(int rate){
		manager.registerListener(this, sensor, rate);
	}
	public void onPause(){
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
	 * 取得X軸上的重力
	 * @return
	 */
	public float getX() {
		return x;
	}
	/**
	 * 取得Y軸上的重力
	 * @return
	 */
	public float getY() {
		return y;
	}
	/**
	 * 取得Z軸上的重力
	 * @return
	 */
	public float getZ() {
		return z;
	}
}

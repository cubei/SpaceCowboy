package com.quchen.spacecowboy;
/**
 * Uses the orientationsensor to detect whether the device is tilted
 * @author lars
 */
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.view.Surface;

public class TiltSensor implements SensorEventListener {
	
	Tiltable tilter;
	private SensorManager sm = null;
	private float[] mValuesMagnet = new float[3];
	private float[] mValuesAccel = new float[3];
	private float[] mValuesOrientation = new float[3];
	private float[] mRotationMatrix = new float[9];
	
	TiltSensor(Tiltable tilter, SensorManager sm){
		this.tilter = tilter;
		this.sm = sm;
	}
	
	public void onResume(){
		sm.registerListener(this, sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
		sm.registerListener(this, sm.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD), SensorManager.SENSOR_DELAY_NORMAL);
	}
	
	public void onPause(){
		sm.unregisterListener(this);
	}

	public void onAccuracyChanged(Sensor sensor, int accuracy) {}

	public void onSensorChanged(SensorEvent event) {
		float x, y;

		switch (event.sensor.getType()) {
		case Sensor.TYPE_ACCELEROMETER:
			System.arraycopy(event.values, 0, mValuesAccel, 0, 3);
			break;

		case Sensor.TYPE_MAGNETIC_FIELD:
			System.arraycopy(event.values, 0, mValuesMagnet, 0, 3);
			break;
		}
		
		SensorManager.getRotationMatrix(mRotationMatrix, null, mValuesAccel, mValuesMagnet);
		SensorManager.getOrientation(mRotationMatrix, mValuesOrientation);
        
		if(Util.ORIENTATION == Surface.ROTATION_0){
			//Tablet
			x = (mValuesOrientation[2] * Util.TO_DEGREE);
			y = -(mValuesOrientation[1] * Util.TO_DEGREE);
		}else if(Util.ORIENTATION == Surface.ROTATION_90){
			// Handy
			x = -(mValuesOrientation[1] * Util.TO_DEGREE);
			y = -(mValuesOrientation[2] * Util.TO_DEGREE);
		}else if(Util.ORIENTATION == Surface.ROTATION_180){
			x = -(mValuesOrientation[1] * Util.TO_DEGREE);
			y = (mValuesOrientation[2] * Util.TO_DEGREE);
		}else{
			x = (mValuesOrientation[1] * Util.TO_DEGREE);
			y = (mValuesOrientation[2] * Util.TO_DEGREE);
		}
		
		((Tiltable)tilter).onTiltChange(x, y);
	}

}

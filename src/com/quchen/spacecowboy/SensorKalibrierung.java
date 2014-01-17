package com.quchen.spacecowboy;
/**
 * Uses the current orientation as default 0 orientation = no movement
 * @author lars
 */
import android.app.Activity;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.Toast;

public class SensorKalibrierung extends Activity implements Tiltable{
	
	private TiltSensor tiltSensor;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		tiltSensor = new TiltSensor(this, (SensorManager) getSystemService(SENSOR_SERVICE));
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		tiltSensor.onPause();
		if(Util.musicPlayer != null){
			Util.musicPlayer.pause();
		}
	}

	@Override
	protected void onResume() {
		super.onResume();
		tiltSensor.onResume();
		if(Util.musicPlayer != null){
			Util.musicPlayer.start();
		}
	}

	public void onTiltChange(float x, float y) {
		Util.DefaultXAngle = (short) x;
		Util.DefaultYAngle = (short) y;
		
		Toast.makeText(this.getApplicationContext(), R.string.ToastSensorCalibration, Toast.LENGTH_SHORT).show();
		
		finish();
	}
}

package com.quchen.spacecowboy;
/**
 * Activity for settings.
 * Volumelevels and sensorcalibration
 * @author lars
 */
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class Config extends Activity {
private Button backButton;
private Button sensorButton;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.config);
        
        ((TextView) findViewById(R.id.tvMusic)).setTextSize(Util.getTextSize());
        ((TextView) findViewById(R.id.tvSound)).setTextSize(Util.getTextSize());
        
        backButton = (Button)findViewById(R.id.backButton);
        backButton.setTextSize(Util.getTextSize());
        backButton.setOnClickListener(new View.OnClickListener() {	
			public void onClick(View v) {
				finish();
			}
		});
        sensorButton = (Button)findViewById(R.id.sensorButton);
        sensorButton.setTextSize(Util.getTextSize());
        sensorButton.setOnClickListener(new View.OnClickListener() {	
			public void onClick(View v) {
				startActivity(new Intent("com.quchen.spacecowboy.SensorKalibrierung"));
			}
		});
        
        SeekBar musicVolume = (SeekBar)findViewById(R.id.sbMusicVolume);
        musicVolume.setProgress((int)(Util.musicVolume*100));
        musicVolume.setMinimumHeight(Util.getTextSize());
        musicVolume.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			public void onStopTrackingTouch(SeekBar seekBar) {
			}
			public void onStartTrackingTouch(SeekBar seekBar) {
			}
			public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
				Util.musicVolume = (float)(progress/100.0);
				Util.updateMusicVolume();
			}
		});
        
        SeekBar soundVolume = (SeekBar)findViewById(R.id.sbSoundVolume);
        soundVolume.setProgress((int)(Util.soundVolume*100));
        soundVolume.setMinimumHeight(Util.getTextSize());
        soundVolume.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			public void onStopTrackingTouch(SeekBar seekBar) {
			}
			public void onStartTrackingTouch(SeekBar seekBar) {
			}
			public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
				Util.soundVolume = (float)(progress/100.0);
			}
		});
    }
	
	@Override
	protected void onResume() {
		super.onResume();
		if(Util.musicPlayer != null){
			Util.musicPlayer.start();
		}
	}

	@Override
	protected void onPause() {
		if(Util.musicPlayer != null){
			Util.musicPlayer.pause();
		}
		super.onPause();
		
		saveVolume(this);
	}
	
	
	/**
	 * Name of SharedPreferenc
	 */
	private static final String prefsName = "CONFIG";
	private static final String musicVolume = "musicVolume";
	private static final String soundVolume = "soundVolume";
	
	public static void saveVolume(Activity activity){
		SharedPreferences saves = activity.getSharedPreferences(prefsName, 0);
		SharedPreferences.Editor editor = saves.edit();
		editor.putFloat(musicVolume, Util.musicVolume);
		editor.putFloat(soundVolume, Util.soundVolume);
		editor.commit();
	}
	
	public static void readVolume(Activity activity){
		Util.musicVolume = activity.getSharedPreferences(prefsName, 0).getFloat(musicVolume, 0.5f);
		Util.soundVolume = activity.getSharedPreferences(prefsName, 0).getFloat(soundVolume, 0.5f);
	}
}

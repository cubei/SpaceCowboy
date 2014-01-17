package com.quchen.spacecowboy;
/**
 * About Dialog
 * @author lars
 */

import android.app.Activity;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class About extends Activity {
	
	private Button backButton;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about);
        
        backButton = (Button)findViewById(R.id.backButton);
		backButton.setTextSize(Util.getTextSize());
        backButton.setOnClickListener(new View.OnClickListener() {	
			public void onClick(View v) {
				finish();
			}
		});
		((TextView) findViewById(R.id.about_tv)).setTextSize((int)(Util.getTextSize()*1.5));
        
		try {((TextView) findViewById(R.id.version)).setText("Version: " + getPackageManager().getPackageInfo(getPackageName(), 0).versionName);
		} catch (NameNotFoundException e) {e.printStackTrace();}
		
		// text must be scaled for different screens
		((TextView) findViewById(R.id.version)).setTextSize(Util.getTextSize());
		((TextView) findViewById(R.id.developer)).setTextSize(Util.getTextSize());
		((TextView) findViewById(R.id.graphics)).setTextSize(Util.getTextSize());
		((TextView) findViewById(R.id.music)).setTextSize(Util.getTextSize());
		((TextView) findViewById(R.id.developer0)).setTextSize(Util.getTextSize());
		((TextView) findViewById(R.id.graphics0)).setTextSize(Util.getTextSize());
		((TextView) findViewById(R.id.music0)).setTextSize(Util.getTextSize());
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
	}

}

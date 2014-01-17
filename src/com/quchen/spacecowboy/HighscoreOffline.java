package com.quchen.spacecowboy;
/**
 * Displays the highest score.
 * Used when not connected to the google play services.
 * @author lars
 */
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HighscoreOffline extends Activity {

	private Button backButton;
	private TextView scoreView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.highscore_offline);

		backButton = (Button) findViewById(R.id.backButton);
		backButton.setTextSize(Util.getTextSize());
		backButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				finish();
			}
		});

		scoreView = (TextView) findViewById(R.id.scoreTextView);
		scoreView.setTextSize(Util.getTextSize());
		scoreView.setText(scoreView.getText() + " " + AccomplishmentsBox.getBestScore(this));
	}

	@Override
	protected void onResume() {
		super.onResume();
		if (Util.musicPlayer != null) {
			Util.musicPlayer.start();
		}
	}

	@Override
	protected void onPause() {
		if (Util.musicPlayer != null) {
			Util.musicPlayer.pause();
		}
		super.onPause();
	}
}

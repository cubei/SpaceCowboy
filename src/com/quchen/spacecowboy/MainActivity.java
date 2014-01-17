package com.quchen.spacecowboy;
/**
 * Displays the splashscreen at the start of the app.
 * Provides buttons to settings, highscore, gamestart, ...
 * @author lars
 */
import com.google.android.gms.common.SignInButton;
import com.google.example.games.basegameutils.GameActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;
import android.content.Intent;

public class MainActivity extends GameActivity {
	
	private ImageButton exitButton;
	private ImageButton helpButton;
	private ImageButton highscoreButton;
	private ImageButton highscoreButtonOffline;
	private ImageButton achievementButton;
	private ImageButton settingsButton;
	private ImageButton playButton;
	private ImageButton shopButton;
	private Button about;
	private SignInButton signInButton;
	private Button signOutButton;
	

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setDisplaySpecs();
		setContentView(R.layout.activity_main);
		setUp();
		Config.readVolume(this);
		Util.initMusicPlayer(this);
		Util.musicPlayer.start();
	}
	
	private void setDisplaySpecs(){
		Util.DENSITY = getResources().getDisplayMetrics().density;
		Util.DISPLAX_SIZE = getResources().getDisplayMetrics().heightPixels / getResources().getDisplayMetrics().densityDpi;
		Util.PIXEL_HEIGHT = getResources().getDisplayMetrics().heightPixels;
		Util.PIXEL_WIDTH = getResources().getDisplayMetrics().widthPixels;
		Util.ORIENTATION = getWindow().getWindowManager().getDefaultDisplay().getRotation();
	}
	
	private void setUp(){
		exitButton = (ImageButton)findViewById(R.id.exitButton);
		exitButton.setImageBitmap(Sprite.createBitmap(getResources().getDrawable(R.drawable.exit_button)));
		exitButton.setOnClickListener(new View.OnClickListener() {	
			public void onClick(View v) {
				finish();
			}
		});
		
		helpButton = (ImageButton)findViewById(R.id.helpButton);
		helpButton.setImageBitmap(Sprite.createBitmap(getResources().getDrawable(R.drawable.help_button)));
		helpButton.setOnClickListener(new View.OnClickListener() {	
			public void onClick(View v) {
				startActivity(new Intent("com.quchen.spacecowboy.Help"));
			}
		});
		
		highscoreButton = (ImageButton)findViewById(R.id.highscoreButton);
		highscoreButton.setImageBitmap(Sprite.createBitmap(getResources().getDrawable(R.drawable.highscore)));
		highscoreButton.setOnClickListener(new View.OnClickListener() {	
			public void onClick(View v) {
				startActivityForResult(MainActivity.this.mHelper.getGamesClient().getLeaderboardIntent(
						getResources().getString(R.string.leaderboard_space_cowboy)), 0);
				startActivity(MainActivity.this.mHelper.getGamesClient().getAllLeaderboardsIntent());
			}
		});
		
		highscoreButtonOffline = (ImageButton)findViewById(R.id.highscoreButtonOffline);
		highscoreButtonOffline.setImageBitmap(Sprite.createBitmap(getResources().getDrawable(R.drawable.highscore)));
		highscoreButtonOffline.setOnClickListener(new View.OnClickListener() {	
			public void onClick(View v) {
				startActivity(new Intent("com.quchen.spacecowboy.HighscoreOffline"));
			}
		});
		
		shopButton = (ImageButton)findViewById(R.id.shopButton);
		shopButton.setImageBitmap(Sprite.createBitmap(getResources().getDrawable(R.drawable.shop)));
		shopButton.setOnClickListener(new View.OnClickListener() {	
			public void onClick(View v) {
				startActivity(new Intent("com.quchen.spacecowboy.Shop"));
			}
		});
		
		achievementButton = (ImageButton)findViewById(R.id.achievementButton);
		achievementButton.setImageBitmap(Sprite.createBitmap(getResources().getDrawable(R.drawable.achievement_button)));
		achievementButton.setOnClickListener(new View.OnClickListener() {	
			public void onClick(View v) {
				startActivityForResult(MainActivity.this.mHelper.getGamesClient().getAchievementsIntent(),0);
			}
		});
		
		settingsButton = (ImageButton)findViewById(R.id.settingsButton);
		settingsButton.setImageBitmap(Sprite.createBitmap(getResources().getDrawable(R.drawable.config_button)));
		settingsButton.setOnClickListener(new View.OnClickListener() {	
			public void onClick(View v) {
				startActivity(new Intent("com.quchen.spacecowboy.Config"));
			}
		});
		
		playButton = (ImageButton)findViewById(R.id.playButton);
		playButton.setImageBitmap(Sprite.createBitmap(getResources().getDrawable(R.drawable.play_button)));
		playButton.setOnClickListener(new View.OnClickListener() {	
			public void onClick(View v) {
				startActivity(new Intent("com.quchen.spacecowboy.Game"));
			}
		});
		
		about = (Button)findViewById(R.id.about);
		about.setTextSize(Util.getTextSize());
		about.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				startActivity(new Intent("com.quchen.spacecowboy.About"));
			}
		});
		
		signInButton = (SignInButton)findViewById(R.id.sign_in_button);
		signInButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				beginUserInitiatedSignIn();
			}
		});
		
		signOutButton = (Button)findViewById(R.id.sign_out_button);
		signOutButton.setTextSize(Util.getTextSize());
		signOutButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				signOut();
				showOfflineButtons();
			}
		}); 
	}

	@Override
	protected void onDestroy() {
		if(Util.musicPlayer != null){
			Util.musicPlayer.stop();
		}
		super.onDestroy();
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

	@Override
	public void onSignInFailed() {
		showOfflineButtons();		
		Toast.makeText(getApplicationContext(), "Not signed in", Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onSignInSucceeded() {
		Toast.makeText(getApplicationContext(), "Signed in", Toast.LENGTH_SHORT).show();
		showOnlineButtons();
		if(!AccomplishmentsBox.isOnline(this)){
			AddScore.submitScore(this, AccomplishmentsBox.getLocal(this), this.getGamesClient());
		}
	}
	
	private void showOnlineButtons(){
		findViewById(R.id.achievementButton).setVisibility(View.VISIBLE);
		findViewById(R.id.highscoreButton).setVisibility(View.VISIBLE);
		findViewById(R.id.sign_in_button).setVisibility(View.GONE);
		findViewById(R.id.sign_out_button).setVisibility(View.VISIBLE);
		findViewById(R.id.sign_in_button_help_text).setVisibility(View.GONE);
		findViewById(R.id.highscoreButtonOffline).setVisibility(View.GONE);
	}
	
	private void showOfflineButtons(){
		findViewById(R.id.sign_in_button).setVisibility(View.VISIBLE);
		findViewById(R.id.sign_out_button).setVisibility(View.GONE);
		findViewById(R.id.achievementButton).setVisibility(View.GONE);
		findViewById(R.id.highscoreButton).setVisibility(View.GONE);
		findViewById(R.id.sign_in_button_help_text).setVisibility(View.VISIBLE);
		findViewById(R.id.highscoreButtonOffline).setVisibility(View.VISIBLE);
	}
}

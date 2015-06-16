package com.quchen.spacecowboy;
/**
 * Gameover Dialog
 * with points and the ability to upload accomblishments or save local
 * @author lars
 */
import com.google.android.gms.games.GamesClient;
import com.google.example.games.basegameutils.GameActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class AddScore extends GameActivity {
	
	private Button okButton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.addscore);
		
		TextView tvPoints = (TextView)findViewById(R.id.points);
		tvPoints.setText(getResources().getString(R.string.yourScoreIs) + ": " + this.getIntent().getExtras().getInt("points")
				+ "\nSee you Space Cowboy");
		tvPoints.setTextSize(Util.getTextSize());
		
		okButton = (Button)findViewById(R.id.bok);
		okButton.setTextSize(Util.getTextSize());
		okButton.setOnClickListener(new View.OnClickListener() {	
			public void onClick(View v) {
				AccomplishmentsBox.setLocal(Game.theGame.getAccomplishments(), AddScore.this);
				if(getGameHelper().isSignedIn()){
					submitScore(AddScore.this, Game.theGame.getAccomplishments(), AddScore.this.mHelper.getGamesClient());
				}else{
					AccomplishmentsBox.savesAreOffline(AddScore.this);
				}
				
				finish();
			}
		});
	}
	
	/**
	 * Publishes the accomplishments online with google play services
	 * @param activity
	 * @param box
	 * @param gamesClient
	 */
	public static void submitScore(Activity activity, AccomplishmentsBox box, GamesClient gamesClient){
		
		gamesClient.submitScore(activity.getResources().getString(R.string.leaderboard_space_cowboy), box.score);

		if(box.cows_100){
			gamesClient.unlockAchievement(activity.getResources().getString(R.string.achievement_cows_100));
		}
		if(box.cows_1000){
			gamesClient.unlockAchievement(activity.getResources().getString(R.string.achievement_cows_1000));
		}
		if(box.cows_75_run){
			gamesClient.unlockAchievement(activity.getResources().getString(R.string.achievement_cows_75_run));
		}
		if(box.cows_200_run){
			gamesClient.unlockAchievement(activity.getResources().getString(R.string.achievement_cows_200_run));
		}
		if(box.meteoroids_100){
			gamesClient.unlockAchievement(activity.getResources().getString(R.string.achievement_meteoroids_100));
		}
		if(box.meteoroids_1000){
			gamesClient.unlockAchievement(activity.getResources().getString(R.string.achievement_meteoroids_1000));
		}
		if(box.meteoroids_50_run){
			gamesClient.unlockAchievement(activity.getResources().getString(R.string.achievement_meteoroids_50_run));
		}
		if(box.meteoroids_200_run){
			gamesClient.unlockAchievement(activity.getResources().getString(R.string.achievement_meteoroids_200_run));
		}
		if(box.milkContainer_100){
			gamesClient.unlockAchievement(activity.getResources().getString(R.string.achievement_milkcontainer_100));
		}
		if(box.powerUps_100){
			gamesClient.unlockAchievement(activity.getResources().getString(R.string.achievement_powerups_100));
		}
		if(box.powerUps_30_run){
			gamesClient.unlockAchievement(activity.getResources().getString(R.string.achievement_powerups_30_run));
		}
		if(box.sleepy_cowboy){
			gamesClient.unlockAchievement(activity.getResources().getString(R.string.achievement_sleepy_cowboy));
		}
		if(box.survived_5){
			gamesClient.unlockAchievement(activity.getResources().getString(R.string.achievement_survived_5));
		}
		if(box.survived_15){
			gamesClient.unlockAchievement(activity.getResources().getString(R.string.achievement_survived_15));
		}
		if(box.undamaged_1){
			gamesClient.unlockAchievement(activity.getResources().getString(R.string.achievement_undamaged_1));
		}
		if(box.undamaged_5){
			gamesClient.unlockAchievement(activity.getResources().getString(R.string.achievement_undamaged_5));
		}
		if(box.catch_dancecow_frozen){
			gamesClient.unlockAchievement(activity.getResources().getString(R.string.achievement_catch_dancecow_frozen));
		}
		if(box.heal_poison){
			gamesClient.unlockAchievement(activity.getResources().getString(R.string.achievement_heal_poison));
		}
		if(box.low_milk_1_minutes){
			gamesClient.unlockAchievement(activity.getResources().getString(R.string.achievement_low_milk_1_minutes));
		}
		if(box.full_milk_2_minutes){
			gamesClient.unlockAchievement(activity.getResources().getString(R.string.achievement_full_milk_2_minutes));
		}
		if(box.meteoroids_shield_10){
			gamesClient.unlockAchievement(activity.getResources().getString(R.string.achievement_meteoroids_with_shield_10));
		}
		if(box.lvl_10_10_milkcontainer){
			gamesClient.unlockAchievement(activity.getResources().getString(R.string.achievement_lvl_10_with_10_milkcontainer));
		}
		if(box.red_coin){
			gamesClient.unlockAchievement(activity.getResources().getString(R.string.achievement_red_coin));
		}
		if(box.leet_king){
			gamesClient.unlockAchievement(activity.getResources().getString(R.string.achievement_leet_king));
		}
		if(box.catched_them_all_bool){
			gamesClient.unlockAchievement(activity.getResources().getString(R.string.achievement_catch_them_all));
		}
		
		AccomplishmentsBox.savesAreOnline(activity);
		
		Toast.makeText(activity.getApplicationContext(), "Uploaded", Toast.LENGTH_SHORT).show();
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
	public void onSignInFailed() {}

	@Override
	public void onSignInSucceeded() {}

}

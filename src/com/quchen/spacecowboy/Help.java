package com.quchen.spacecowboy;
/**
 * Help Dialog
 * @author lars
 */
import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Help extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.help);

		((TextView) findViewById(R.id.help0)).setTextSize(Util.getTextSize());
		((TextView) findViewById(R.id.help1)).setTextSize(Util.getTextSize());
		((TextView) findViewById(R.id.help2)).setTextSize(Util.getTextSize());
		((TextView) findViewById(R.id.help3)).setTextSize(Util.getTextSize());
		((TextView) findViewById(R.id.help4)).setTextSize(Util.getTextSize());

		((ImageView) findViewById(R.id.coin)).setImageBitmap(Sprite.createBitmap(getResources().getDrawable(R.drawable.coin_single)));
		((ImageView) findViewById(R.id.rock)).setImageBitmap(Sprite.createBitmap(getResources().getDrawable(R.drawable.rock_single)));
		((ImageView) findViewById(R.id.cow)).setImageBitmap(Sprite.createBitmap(getResources().getDrawable(R.drawable.cow)));

		Button backButton = (Button) findViewById(R.id.backButton);
		backButton.setTextSize(Util.getTextSize());
		backButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				finish();
			}
		});
		
		Button moreHelpButton = (Button) findViewById(R.id.moreHelpButton);
		moreHelpButton.setTextSize(Util.getTextSize());
		moreHelpButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Bitmap tmp;
				// More Help
				setContentView(R.layout.more_help);
						
				((ImageView) findViewById(R.id.moreHelpControlIV)).setImageBitmap(Sprite.createBitmap(getResources().getDrawable(R.drawable.tilt)));
				
				tmp = Sprite.createBitmap(getResources().getDrawable(R.drawable.rock1));
				((ImageView) findViewById(R.id.moreHelpRockIV)).setImageBitmap(Bitmap.createBitmap(tmp,0,0,tmp.getWidth()/Rock.NUMBER_OF_COLUMNS, tmp.getHeight()/Rock.NUMBER_OF_ROWS));
				((ImageView) findViewById(R.id.moreHelpRockFrozenIV)).setImageBitmap(Bitmap.createBitmap(tmp,0,0,tmp.getWidth()/Rock.NUMBER_OF_COLUMNS, tmp.getHeight()/Rock.NUMBER_OF_ROWS));
				((ImageView) findViewById(R.id.moreHelpRockFlashIV)).setImageBitmap(Bitmap.createBitmap(tmp,0,0,tmp.getWidth()/Rock.NUMBER_OF_COLUMNS, tmp.getHeight()/Rock.NUMBER_OF_ROWS));
				tmp = Sprite.createBitmap(getResources().getDrawable(R.drawable.status));
				((ImageView) findViewById(R.id.moreHelpRockFrozen2IV)).setImageBitmap(Bitmap.createBitmap(tmp,0,tmp.getHeight()/Status.NUMBER_OF_ROWS,tmp.getWidth()/Status.NUMBER_OF_COLUMNS, tmp.getHeight()/Status.NUMBER_OF_ROWS));
				((ImageView) findViewById(R.id.moreHelpRockFlash2IV)).setImageBitmap(Bitmap.createBitmap(tmp,0,tmp.getHeight()/Status.NUMBER_OF_ROWS*2,tmp.getWidth()/Status.NUMBER_OF_COLUMNS, tmp.getHeight()/Status.NUMBER_OF_ROWS));
				tmp = Sprite.createBitmap(getResources().getDrawable(R.drawable.guided_rock));
				((ImageView) findViewById(R.id.moreHelpRockGuidedIV)).setImageBitmap(Bitmap.createBitmap(tmp,0,0,tmp.getWidth()/RockGuided.NUMBER_OF_COLUMNS, tmp.getHeight()/RockGuided.NUMBER_OF_ROWS));
				tmp = Sprite.createBitmap(getResources().getDrawable(R.drawable.fat_rock));
				((ImageView) findViewById(R.id.moreHelpRockFatIV)).setImageBitmap(Bitmap.createBitmap(tmp,0,0,tmp.getWidth()/RockFat.NUMBER_OF_COLUMNS, tmp.getHeight()/RockFat.NUMBER_OF_ROWS));
				
				((ImageView) findViewById(R.id.moreHelpCowIV)).setImageBitmap(Sprite.createBitmap(getResources().getDrawable(R.drawable.cow)));
				((ImageView) findViewById(R.id.moreHelpCowOldIV)).setImageBitmap(Sprite.createBitmap(getResources().getDrawable(R.drawable.old_cow)));
				((ImageView) findViewById(R.id.moreHelpCowFatIV)).setImageBitmap(Sprite.createBitmap(getResources().getDrawable(R.drawable.fat_cow)));
				tmp = Sprite.createBitmap(getResources().getDrawable(R.drawable.ghost_cow));
				((ImageView) findViewById(R.id.moreHelpCowGhostIV)).setImageBitmap(Bitmap.createBitmap(tmp,0,0,tmp.getWidth()/CowGhost.NUMBER_OF_COLUMNS, tmp.getHeight()/CowGhost.NUMBER_OF_ROWS));
				tmp = Sprite.createBitmap(getResources().getDrawable(R.drawable.dance_cow));
				((ImageView) findViewById(R.id.moreHelpCowDanceIV)).setImageBitmap(Bitmap.createBitmap(tmp,0,0,tmp.getWidth()/CowDance.NUMBER_OF_COLUMNS, tmp.getHeight()/CowDance.NUMBER_OF_ROWS));
				tmp = Sprite.createBitmap(getResources().getDrawable(R.drawable.zombie_cow));
				((ImageView) findViewById(R.id.moreHelpCowZombieIV)).setImageBitmap(Bitmap.createBitmap(tmp,0,0,tmp.getWidth()/CowZombie.NUMBER_OF_COLUMNS, tmp.getHeight()/CowZombie.NUMBER_OF_ROWS));
				
				((ImageView) findViewById(R.id.moreHelpMilkContainerIV)).setImageBitmap(Sprite.createBitmap(getResources().getDrawable(R.drawable.milk)));
				tmp = Sprite.createBitmap(getResources().getDrawable(R.drawable.bell));
				((ImageView) findViewById(R.id.moreHelpBellIV)).setImageBitmap(Bitmap.createBitmap(tmp,0,0,tmp.getWidth()/PowerUpBell.NUMBER_OF_COLUMNS, tmp.getHeight()/PowerUpBell.NUMBER_OF_ROWS));
				((ImageView) findViewById(R.id.moreHelpNitroCheeseIV)).setImageBitmap(Sprite.createBitmap(getResources().getDrawable(R.drawable.nitrokaese)));
				((ImageView) findViewById(R.id.moreHelpCoinIV)).setImageBitmap(Sprite.createBitmap(getResources().getDrawable(R.drawable.coin_single)));

				
				((TextView) findViewById(R.id.moreHelpControlTV)).setTextSize(Util.getTextSize());
				((TextView) findViewById(R.id.moreHelpRockTV)).setTextSize(Util.getTextSize());
				((TextView) findViewById(R.id.moreHelpRockFrozenTV)).setTextSize(Util.getTextSize());
				((TextView) findViewById(R.id.moreHelpRockFlashTV)).setTextSize(Util.getTextSize());
				((TextView) findViewById(R.id.moreHelpRockGuidedTV)).setTextSize(Util.getTextSize());
				((TextView) findViewById(R.id.moreHelpRockFatTV)).setTextSize(Util.getTextSize());
				((TextView) findViewById(R.id.moreHelpCowTV)).setTextSize(Util.getTextSize());
				((TextView) findViewById(R.id.moreHelpCowOldTV)).setTextSize(Util.getTextSize());
				((TextView) findViewById(R.id.moreHelpCowGhostTV)).setTextSize(Util.getTextSize());
				((TextView) findViewById(R.id.moreHelpCowDanceTV)).setTextSize(Util.getTextSize());
				((TextView) findViewById(R.id.moreHelpCowFatTV)).setTextSize(Util.getTextSize());
				((TextView) findViewById(R.id.moreHelpCowZombieTV)).setTextSize(Util.getTextSize());
				((TextView) findViewById(R.id.moreHelpMilkContainerTV)).setTextSize(Util.getTextSize());
				((TextView) findViewById(R.id.moreHelpBellTV)).setTextSize(Util.getTextSize());
				((TextView) findViewById(R.id.moreHelpNitroCheeseTV)).setTextSize(Util.getTextSize());
				((TextView) findViewById(R.id.moreHelpCoinTV)).setTextSize(Util.getTextSize());
				
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
	}
}

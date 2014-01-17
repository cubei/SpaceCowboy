package com.quchen.spacecowboy;
/**
 * A bell that lures cows when collected
 * @author lars
 */
import android.content.Context;
import android.graphics.Bitmap;

public class PowerUpBell extends PowerUp {
	public static final int ANIMATION_TIME = 150;
	public static final byte NUMBER_OF_ROWS = 1;
	public static final byte NUMBER_OF_COLUMNS = 6;
	
	public static final byte MIN_COWSPAWNS = 3;
	public static final byte MAX_COWSPAWNS = 11 - MIN_COWSPAWNS;
	protected static Bitmap globalBitmap;
	protected static int sound = -1;
	
	public PowerUpBell(GameView view, Context context) {
		super(view, context);
		if(globalBitmap == null){	
			globalBitmap = createBitmap(context.getResources().getDrawable(R.drawable.bell));
		}
		if(sound == -1){
			sound = Util.soundPool.load(context, R.raw.bell, 1);
		}
		this.bitmap = globalBitmap;
		this.width = this.bitmap.getWidth()/NUMBER_OF_COLUMNS;
		this.height = this.bitmap.getHeight();
		this.frameTime = ANIMATION_TIME / Util.UPDATE_INTERVAL;
		this.colNr = 6;
	}

	@Override
	public void onCollision() {
		super.onCollision();
		view.spawnCows((int)(Math.random()*MAX_COWSPAWNS) + MIN_COWSPAWNS);
		view.attractCows();
	}
	@Override
	protected void playSound() {
		super.playSound();
		Util.soundPool.play(sound, Util.soundVolume, Util.soundVolume, 0, 0, 1);
	}
}

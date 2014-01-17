package com.quchen.spacecowboy;
/**
 * Maincow
 * @author lars
 */
import android.content.Context;
import android.graphics.Bitmap;

public class Cow extends Sprite {
	public static final int COW_TYPE = 0;
	public static final byte POWER_COW = 2;
	public static final byte NUMBER_OF_ROWS = 1;
	public static final byte NUMBER_OF_COLUMNS = 1;
	
	protected static Bitmap globalBitmap;
	protected static Bitmap globalBitmapMirror;
	protected static int sound = -1;

	public Cow(GameView view, Context context) {
		super(view, context);
		if(globalBitmap == null){
			globalBitmap = createBitmap(context.getResources().getDrawable(R.drawable.cow));
			globalBitmapMirror = getHorizontalMirroredBitmap(globalBitmap);
		}
		if(sound == -1){
			sound = Util.soundPool.load(context, R.raw.cow, 1);
		}
		this.bitmap = globalBitmap;
		this.width = this.bitmap.getWidth();
		this.height = this.bitmap.getHeight();
		this.power = POWER_COW;
		this.colNr = 1;
		
		// Activates mirror effects
		this.setSpeedX(speedX);
		this.setSpeedY(speedY);
	}
	
	@Override
	public void onCollision() {
		super.onCollision();
		this.view.getGame().increaseMilk(this.power);
		this.isTimedOut = true;
		this.view.getGame().milkedCow();
		this.view.getGame().getAccomplishments().catch_them_all |= (1<<COW_TYPE);
	}

	@Override
	protected void playSound() {
		super.playSound();
		Util.soundPool.play(sound, Util.soundVolume, Util.soundVolume, 0, 0, 1);
	}

	@Override
	public void setSpeedX(int speedX) {
		super.setSpeedX(speedX);
		if(speedX < 0){
			this.bitmap = globalBitmapMirror;
		}else{
			this.bitmap = globalBitmap;
		}
	}
	
	@Override
	public boolean isColliding(Sprite sprite) {
		return isColliding(sprite, Util.COW_COLLISION_FACTOR);
	}
}

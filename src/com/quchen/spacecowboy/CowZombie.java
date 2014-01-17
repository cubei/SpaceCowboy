package com.quchen.spacecowboy;
/**
 * Cow that infects the player with an 'damage over time' effect
 * @author lars
 */
import android.content.Context;
import android.graphics.Bitmap;

public class CowZombie extends Cow {
	public static final int COW_TYPE = 5;
	public static final byte POWER_ZOMBIE_COW = 1;
	public static final byte POWER_ZOMBIE_COW_INCREASE_EVERY_X_LEVEL = 5;
	public static final short ANIMATION_TIME = 150;
	public static final byte NUMBER_OF_ROWS = 1;
	public static final byte NUMBER_OF_COLUMNS = 4;
	
	protected static Bitmap globalBitmap;
	protected static Bitmap globalBitmapMirror;

	public CowZombie(GameView view, Context context) {
		super(view, context);
		if(globalBitmap == null){
			globalBitmap = createBitmap(context.getResources().getDrawable(R.drawable.zombie_cow));
			globalBitmapMirror = getHorizontalMirroredBitmap(globalBitmap);
		}
		this.bitmap = globalBitmap;
		this.width = this.bitmap.getWidth()/NUMBER_OF_COLUMNS;
		this.height = this.bitmap.getHeight();
		this.power = (short) (POWER_ZOMBIE_COW + Util.lvl / POWER_ZOMBIE_COW_INCREASE_EVERY_X_LEVEL);
		this.colNr = 4;
		this.frameTime = ANIMATION_TIME / Util.UPDATE_INTERVAL;
		
		// Activates mirror effects
		this.setSpeedX(speedX>>1);
		this.setSpeedY(speedY>>1);
	}

	@Override
	public void onCollision() {
		super.onCollision();
		this.view.getRocket().inflictPoison(this.power);
		this.view.getGame().getAccomplishments().catch_them_all |= (1<<COW_TYPE);
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
}

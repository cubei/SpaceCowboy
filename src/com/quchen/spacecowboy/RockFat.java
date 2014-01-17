package com.quchen.spacecowboy;
/**
 * A big meteorite that seperates into smaller rocks when destroyed
 * @author lars
 */
import android.content.Context;
import android.graphics.Bitmap;

public class RockFat extends Rock {
	public static final byte FAT_ROCK_LIFE = 5;
	public static final byte POWER_FAT_ROCK = 5;
	public static final short ANIMATION_TIME = 150;
	public static final byte NUMBER_OF_ROWS = 1;
	public static final byte NUMBER_OF_COLUMNS = 3;
	
	private static Bitmap globalBitmap;

	public RockFat(GameView view, Context context) {
		super(view, context);
		if(globalBitmap == null){
			globalBitmap = createBitmap(context.getResources().getDrawable(R.drawable.fat_rock));
		}
		this.bitmap = globalBitmap;
		this.width = this.bitmap.getWidth()/NUMBER_OF_COLUMNS;
		this.height = this.bitmap.getHeight();
		this.life = FAT_ROCK_LIFE;
		this.power *= POWER_FAT_ROCK;
		this.colNr = 3;
		this.frameTime = ANIMATION_TIME / Util.UPDATE_INTERVAL;
		this.speedX = this.speedX *2/3;
		this.speedY = this.speedY *2/3;
	}
	
	@Override
	protected void onKill() {
		super.onKill();
		this.view.createNewRock(this.x, this.y);
		this.view.createNewRock(this.x, this.y);
		this.view.createNewRock(this.x, this.y);
	}

	
}

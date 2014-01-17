package com.quchen.spacecowboy;
/**
 * Cow that varies in speed and direction
 * @author lars
 */
import android.content.Context;
import android.graphics.Bitmap;

public class CowOld extends Cow {
	public static final int COW_TYPE = 2;
	public static final byte POWER_OLD_COW = 1;
	public static final byte NUMBER_OF_ROWS = 1;
	public static final byte NUMBER_OF_COLUMNS = 1;
	
	protected static Bitmap globalBitmap;
	protected static Bitmap globalBitmapMirror;

	public CowOld(GameView view, Context context) {
		super(view, context);
		if(globalBitmap == null){
			globalBitmap = createBitmap(context.getResources().getDrawable(R.drawable.old_cow));
			globalBitmapMirror = getHorizontalMirroredBitmap(globalBitmap);
		}
		this.bitmap = globalBitmap;
		this.width = this.bitmap.getWidth();
		this.height = this.bitmap.getHeight();
		this.power = POWER_OLD_COW;
		
		// Activates mirror effects
		this.setSpeedX(speedX);
		this.setSpeedY(speedY);
	}
	
	@Override
	public void move(float speedModifier) {
		super.move(speedModifier);
		if((int) (Math.random()*100) < 5){
			this.speedX = -this.speedX;
			if((int)(Math.random()*2) == 0){
				this.speedX /= 2;
				this.speedX++;
			}else{
				this.speedX = (this.speedX * 3) / 2;
			}
		}
		if((int) (Math.random()*100) < 5){
			this.speedY = -this.speedY;
			if((int)(Math.random()*2) == 0){
				this.speedY /= 2;
				this.speedY++;
			}else{
				this.speedY = (this.speedY * 3) / 2;
			}
		}
		
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
	public void onCollision() {
		super.onCollision();
		this.view.getGame().getAccomplishments().catch_them_all |= (1<<COW_TYPE);
	}
}

package com.quchen.spacecowboy;
/**
 * A meteorite that follows the player
 * @author lars
 */
import android.content.Context;
import android.graphics.Bitmap;

public class RockGuided extends Rock {
	public static final byte POWER_GUIDED_ROCK = 1;
	public static final short MAX_SPEED = (short) (4 * Util.getSpeedFactor() * Sprite.SPEED_DEFAULT);
	public static final byte NUMBER_OF_ROWS = 1;
	public static final byte NUMBER_OF_COLUMNS = 8;
	
	private static Bitmap globalBitmap;

	public RockGuided(GameView view, Context context) {
		super(view, context);
		if(globalBitmap == null){
			globalBitmap = createBitmap(context.getResources().getDrawable(R.drawable.guided_rock));
		}
		this.bitmap = globalBitmap;
		this.width = this.bitmap.getWidth()/NUMBER_OF_COLUMNS;
		this.height = this.bitmap.getHeight();
		this.colNr = 8;
		this.power *= POWER_GUIDED_ROCK;
	}

	@Override
	public void move(float speedModifier) {
		if(this.view.getRocket().getX() < this.x){
			this.speedX --;
			if(this.speedX < - MAX_SPEED * Util.GUIDED_ROCK_SPEED_FACTOR){
				this.speedX = (int) (- MAX_SPEED * Util.GUIDED_ROCK_SPEED_FACTOR);
			}
		}else{
			this.speedX ++;
			if(this.speedX > MAX_SPEED * Util.GUIDED_ROCK_SPEED_FACTOR){
				this.speedX = (int) (MAX_SPEED * Util.GUIDED_ROCK_SPEED_FACTOR);
			}
		}
		
		
		if(this.view.getRocket().getY() < this.y){
			this.speedY --;
			if(this.speedY < - MAX_SPEED * Util.GUIDED_ROCK_SPEED_FACTOR){
				this.speedY = (int) (- MAX_SPEED * Util.GUIDED_ROCK_SPEED_FACTOR);
			}
		}else{
			this.speedY ++;
			if(this.speedY > MAX_SPEED * Util.GUIDED_ROCK_SPEED_FACTOR){
				this.speedY = (int) (MAX_SPEED * Util.GUIDED_ROCK_SPEED_FACTOR);
			}
		}
		super.move(speedModifier);
	}
}

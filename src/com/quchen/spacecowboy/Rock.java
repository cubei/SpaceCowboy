package com.quchen.spacecowboy;
/**
 * A Meteorite that damages the player
 * @author lars
 */
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Rock extends Sprite {
	public static final byte POWER_ROCK = 1;
	public static final byte POWER_ROCK_INCREASE_EVERY_X_LEVEL = 2;
	public static final byte NUMBER_OF_ROWS = 1;
	public static final byte NUMBER_OF_COLUMNS = 10;
	public static final short ANIMATION_TIME = 100;

	private static Bitmap globalBitmap;
	protected static int sound = -1;
	
	protected int life;

	public Rock(GameView view, Context context) {
		super(view, context);
		if(globalBitmap == null){
			globalBitmap = createBitmap(context.getResources().getDrawable(R.drawable.rock1));
		}
		if(sound == -1){
			sound = Util.soundPool.load(context, R.raw.crash, 1);
		}
		this.bitmap = globalBitmap;
		this.width = this.bitmap.getWidth()/NUMBER_OF_COLUMNS;
		this.height = this.bitmap.getHeight();
		this.life = 1;
		this.power = (short) (POWER_ROCK + Util.lvl / POWER_ROCK_INCREASE_EVERY_X_LEVEL);
		this.colNr = 10;
		this.frameTime = ANIMATION_TIME / Util.UPDATE_INTERVAL;
		this.speedX = this.speedX *5/3;
		this.speedY = this.speedY *5/3;
	}
	
	public Rock(GameView view, Context context, int posX, int posY, int speedX, int speedY) {
		this(view, context);
		this.x = posX;
		this.y = posY;
		this.speedX = speedX;
		this.speedY = speedY;
	}

	@Override
	public void move(float speedModifier) {
		super.move(speedModifier);
	}
	
	
	/**
	 * Inflicts 1 Damage
	 * Returns true if life is down to 0
	 * @return
	 */
	public void inflictDamage(){
		life--;
		if(life <= 0){
			onKill();
		}
	}
	
	protected void onKill(){
		this.isTimedOut = true;
		if((int) (Math.random()*10) < 1){
			this.view.createNewPowerUp(this.x, this.y);
		}
		
		this.view.getGame().killedMeteorid();
	}

	@Override
	public void draw(Canvas canvas) {
		super.draw(canvas);
	}

	@Override
	public void onCollision() {
		super.onCollision();
		this.view.getRocket().inflictDamage(this.power);
		this.isTimedOut = true;
	}
	
	@Override
	protected void playSound() {
		super.playSound();
		Util.soundPool.play(sound, Util.soundVolume, Util.soundVolume, 0, 0, 1);
	}

	@Override
	public boolean isColliding(Sprite sprite) {
		return isColliding(sprite, Util.ROCK_COLLISION_FACTOR);
	}
}

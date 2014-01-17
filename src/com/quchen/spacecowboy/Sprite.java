package com.quchen.spacecowboy;
/**
 * The class all gameobjects inherit from
 * @author lars
 */
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

public abstract class Sprite {
	public static final short SPEED_DEFAULT = 5;
	public static final short ANIMATION_TIME = 50;
	public static final byte NUMBER_OF_ROWS = 1;
	public static final byte NUMBER_OF_COLUMNS = 1;

	protected Bitmap bitmap;
	protected int height, width;
	protected int x;
	protected int y;
	protected int speedX;
	protected int speedY;
	protected Rect src;
	protected Rect dst;
	protected byte col, row;	// Spritesheet kords
	protected byte colNr = 1;
	protected short power;
	protected short frameTime;
	protected short frameTimeCounter;
	protected boolean isTimedOut = false;
	
	protected GameView view;
	protected Context context;
	
	public Sprite(GameView view, Context context){
		this.view = view;
		this.context = context;
		this.width = 200;
		this.height = 200;
		frameTime = ANIMATION_TIME / Util.UPDATE_INTERVAL;
		initPos();
		initSpeed();
	}
	
	private void initPos(){
		if((int)(Math.random()*2) == 0){
			// von oben oder unten
			x = (int) (Math.random()*(view.getWidth()<<1)) - (view.getWidth()>>1);
			y = (int) (Math.random()*view.getHeight());
			if((int)(Math.random()*2) == 0){
				// von oben
				y = -y - height - view.getHeight()/2;
			}else{
				// von unten
				y = view.getHeight() + height + view.getHeight()/2;
			}
		}else{
			// von links oder rechts
			y = (int) (Math.random()*(view.getHeight()<<1)) - (view.getHeight()>>1);
			x = (int) (Math.random()*view.getWidth());
			if(((int)(Math.random()*2))%2 == 0){
				// von links
				x = -x - width - view.getWidth()/2;
			}else{
				// von rechts
				x = view.getWidth() + width + view.getWidth()/2;
			}
		}
	}
	
	void initSpeed(){
		speedX = (int) (Util.getSpeedFactor() * Math.random() * (Util.lvl*2 + SPEED_DEFAULT));
		speedX = speedX - ((int)((Util.getSpeedFactor() * Math.random() * (Util.lvl*2 + SPEED_DEFAULT)))>>1);
		speedY = (int) (Util.getSpeedFactor() * Math.random() * (Util.lvl*2 + SPEED_DEFAULT));
		speedY = speedY - ((int)((Util.getSpeedFactor() * Math.random() * (Util.lvl*2 + SPEED_DEFAULT)))>>1);
		
		
		//Richtung manipulieren
		if(x < 0){
			//kommt von links
			speedX += Math.abs(speedX)>>1;
		}
		if(x > this.view.getWidth()){
			//kommt von rechts
			speedX -= Math.abs(speedX)>>1;
		}
		if(y < 0){
			//kommt von oben
			speedY += Math.abs(speedY)>>1;
		}
		if(y > this.view.getHeight()){
			// kommt von unten
			speedY -= Math.abs(speedY)>>1;
		}
		
		//Verhindert stehende Sprites
		if(speedY < 1 && speedY > -1){
			speedY = 1;
		}
		if(speedX < 1 && speedX > -1){
			speedX = 1;
		}
	}

	public void draw(Canvas canvas){
		// Sprite außerhalb des sichtbaren Bereichs
		if(this.x > Util.PIXEL_WIDTH || this.y > Util.PIXEL_HEIGHT
				|| this.x + this.width < 0 || this.y + this.height < 0){
			return;
		}
		src = new Rect(col*width, row*height, (col+1)*width, (row+1)*height);
		dst = new Rect(x, y, x+width, y+height);
		canvas.drawBitmap(bitmap, src, dst, null);
	}
	
	public void move(float speedModifier){
		this.frameTimeCounter++;
		if(this.frameTimeCounter >= this.frameTime){
			this.col = (byte) ((this.col+1) % this.colNr);
			this.frameTimeCounter = 0;
		}
		x+= (speedX * speedModifier) - this.view.getBGxSpeed();
		y+= (speedY * speedModifier) - this.view.getBGySpeed();
	}
	
	public boolean isOutOfRange(){
		if(	this.x > this.view.getWidth()*3
			|| this.x < -view.getWidth()*2
			|| this.y > this.view.getHeight()*3
			|| this.y < -view.getHeight()*2){
			return true;
		}
		return false;
	}
	
	/**
	 * checks whether the sprite is touching this.
	 * with the distance of the 2 centers.
	 * @param sprite
	 * @return
	 */
	public boolean isColliding(Sprite sprite){
		return isColliding(sprite, Util.DISTANCE_COLLISION_FACTOR);
	}
	
	public boolean isColliding(Sprite sprite, float factor){
		int m1x = this.x+(this.width>>1);
		int m1y = this.y+(this.height>>1);
		int m2x = sprite.x+(sprite.width>>1);
		int m2y = sprite.y+(sprite.height>>1);
		int dx = m1x - m2x;
		int dy = m1y - m2y;
		int d = (int) Math.sqrt(dy*dy + dx*dx);
		
		if(d < (this.width + sprite.width) * factor
			|| d < (this.height + sprite.height) * factor){
			return true;
		}else{
			return false;
		}
	}
	
	
	
	public boolean isTouching(int x, int y){
		if(	x + Util.ATTACK_AREA_EFFECT > this.x && x - Util.ATTACK_AREA_EFFECT < this.x + this.width
			&& y + Util.ATTACK_AREA_EFFECT > this.y && y - Util.ATTACK_AREA_EFFECT < this.y + this.height){
			return true;
		}
		return false;
	}
	
	public void onCollision(){
		playSound();
	}
	
	public boolean isTimedOut(){
		return this.isTimedOut;
	}
	
	public int getPower(){
		return power;
	}
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getSpeedX() {
		return speedX;
	}

	public void setSpeedX(int speedX) {
		this.speedX = speedX;
	}

	public int getSpeedY() {
		return speedY;
	}

	public void setSpeedY(int speedY) {
		this.speedY = speedY;
	}
	
	protected void playSound(){
		
	}
	
	public void moveTo(int x, int y){
		this.speedY = (int)(((y - this.y) >> 5) * Math.random());
		this.speedX = (int)(((x - this.x) >> 5) * Math.random());
	}
	
	public static Bitmap createBitmap(Drawable drawable){
		BitmapDrawable bd = (BitmapDrawable) drawable;
		Bitmap bm = bd.getBitmap();
		return Bitmap.createScaledBitmap(bm,
				(int)(bm.getWidth()*Util.getScaleFactor()),
				(int)(bm.getHeight()*Util.getScaleFactor()),
				false);
	}
	
	public static Bitmap getHorizontalMirroredBitmap(Bitmap bm){
		Matrix m = new Matrix();
		m.preScale(-1, 1);
		return Bitmap.createBitmap(bm, 0, 0, bm.getWidth(), bm.getHeight(), m, false);
	}

}

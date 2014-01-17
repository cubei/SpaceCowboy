package com.quchen.spacecowboy;
/**
 * Most important class of the game.
 * Hold the game loop which checks all objects and draws them.
 * Gets the tiltevents.
 * @author lars
 */
import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GameView extends SurfaceView implements Runnable, Tiltable{
	public static final byte POWER_ICE = 6; // speed / power
	public static final byte MAX_METEOR_SHOWER = 15;
	
	private Game game;
	private Thread t;
	private SurfaceHolder holder;
	volatile private boolean shouldRun = true;
	volatile private int touchX, touchY;
	volatile private boolean isTouched = false;
	private float NPCspeedModifier = 1;
	
	volatile boolean punishment = false;	// Creates a meteor shower when the player sleeps for a certain amount of time
	
	private Rocket rocket;
	private BackGround bg;
	private List<Rock> rocks;
	private List<Cow> cows;
	private List<PowerUp> powerUps;
	
	/** Not usable */
	private GameView(Context context){super(context);}
	/** Not usable */
	private GameView(Context context, AttributeSet attrs){super(context, attrs);}
	/** Not usable */
	private GameView(Context context, AttributeSet attrs, int defStyle){super(context, attrs, defStyle);}
	
	/**
	 * @param activity the game, class:Game
	 */
	public GameView(Activity activity) {
		super(activity);
		this.game = (Game) activity;
		this.shouldRun = true;
		holder = getHolder();		
		rocket = new Rocket(this, activity);
		bg = new BackGround(this, activity);
		rocks = new ArrayList<Rock>();
		cows = new ArrayList<Cow>();
		powerUps = new ArrayList<PowerUp>();
	}

	@SuppressLint("WrongCall")
	public void run() {
		while(shouldRun){
			
			if(!holder.getSurface().isValid()){
				continue;
			}

			// checks
			if(this.game.getMilk() <= 0){
				gameOver();
			}
			if(this.isTouched){
				checkTouchedRocks();
				this.isTouched = false;
			}
					
			checkTimeOut();

			checkOutOfRange();
		
			checkCollision();

			createNew();
		
			move();

			// draw
			Canvas c = holder.lockCanvas();
			onDraw(c);

			holder.unlockCanvasAndPost(c);
			game.updateStatsText();

			// sleep
			try {
				Thread.sleep(Util.UPDATE_INTERVAL);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public boolean isRunning(){
		return shouldRun;
	}
	
	public void pause(){
		for(TimerExec timer: TimerExec.allTimers){
			timer.pause();
		}
		
		shouldRun = false;
		while(t != null){
			try {
				t.join();
				break;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		t = null;
	}
	
	public void resume(){
		shouldRun = false;
		while(t != null){
			try {
				t.join();
				break;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		shouldRun = true;
		t = new Thread(this);
		t.start();
		
		for(TimerExec timer: TimerExec.allTimers){
			timer.resume();
		}
	}
	
	/**
	 * Draws the background and all sprites
	 */
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		
		bg.draw(canvas);
		
		for(Rock r : this.rocks){
			r.draw(canvas);
		}
		for(Cow c : this.cows){
			c.draw(canvas);
		}
		for(PowerUp p : this.powerUps){
			p.draw(canvas);
		}
		rocket.draw(canvas);
	}
	
	/**
	 * Checks whether a gameobject is timed out
	 * isTimedOut means collided and stuff
	 */
	private void checkTimeOut(){
		for(int i=0; i<this.rocks.size(); i++){
			if(this.rocks.get(i).isTimedOut()){
				this.rocks.remove(i);
				i--;
			}
		}
		for(int i=0; i<this.cows.size(); i++){
			if(this.cows.get(i).isTimedOut()){
				this.cows.remove(i);
				i--;
			}
		}
		for(int i=0; i<this.powerUps.size(); i++){
			if(this.powerUps.get(i).isTimedOut()){
				this.powerUps.remove(i);
				i--;
			}
		}
	}
	
	/**
	 * Checks whether sprites are out of range and deletes them
	 */
	private void checkOutOfRange(){
		for(int i=0; i<this.rocks.size(); i++){
			if(this.rocks.get(i).isOutOfRange()){
				this.rocks.remove(i);
				i--;
			}
		}
		for(int i=0; i<this.cows.size(); i++){
			if(this.cows.get(i).isOutOfRange()){
				this.cows.remove(i);
				i--;
			}
		}
		for(int i=0; i<this.powerUps.size(); i++){
			if(this.powerUps.get(i).isOutOfRange()){
				this.powerUps.remove(i);
				i--;
			}
		}
	}
	
	/**
	 * Checks collisions and performs the action (dmg, heal)
	 */
	private void checkCollision(){
		for(Rock r : this.rocks){
			if(r.isColliding(this.rocket)){
				r.onCollision();
			}
		}
		for(Cow c : this.cows){
			if(c.isColliding(this.rocket)){
				c.onCollision();
			}
		}
		
		for(PowerUp p : this.powerUps){
			if(p.isColliding(this.rocket)){
				p.onCollision();
			}
		}
	}
	
	private void gameOver(){
		if(this.game.getAccomplishments().score == 1337){
			this.game.getAccomplishments().leet_king = true;
		}
		Intent intent = new Intent("com.quchen.spacecowboy.AddScore");
		intent.putExtra("points", this.game.getAccomplishments().score);
		this.game.startActivity(intent);
		this.game.finish();
		this.shouldRun = false;
	}
	
	/**
	 * Can create new Gameobjects
	 */
	private void createNew(){
		if(this.rocks.size() < (Util.lvl*2 + Util.AMOUNT_OF_ROCKS)){
			this.createNewRock();
		}
		
		if(this.cows.size() < (Util.lvl/2 + Util.AMOUNT_OF_COWS)){
			this.createNewCow();
		}
		
		if(this.powerUps.size() < (Util.lvl/4 + Util.AMOUNT_OF_POWER_UPS)){
			this.createNewPowerUp();
		}
		
		if(punishment){
			punishment = false;
			createMeteorshower((short)100);
		}
	}
	
	private void createNewRock(){
		int choice = (int) (Math.random() * 100);
		if((choice -= Util.CHANCE_FROZEN_ROCK) < 0 && Util.lvl >= Util.START_LVL_FROZEN_ROCK){
			rocks.add(new RockFrozen(this, this.getContext()));
		}else if((choice -= Util.CHANCE_FLASH_ROCK) < 0 && Util.lvl >= Util.START_LVL_FLASH_ROCK){
			rocks.add(new RockFlash(this, this.getContext()));
		}else if((choice -= Util.CHANCE_GUIDED_ROCK) < 0  && Util.lvl >= Util.START_LVL_GUIDED_ROCK){
			rocks.add(new RockGuided(this, this.getContext()));
		}else if((choice -= Util.CHANCE_FAT_ROCK) < 0  && Util.lvl >= Util.START_LVL_FAT_ROCK){
			rocks.add(new RockFat(this, this.getContext()));
		}else if((choice -= Util.CHANCE_METEOR_SHOWER) < 0  && Util.lvl >= Util.START_LVL_METEOR_SHOWER){
			createMeteorshower((short) (Math.random() * MAX_METEOR_SHOWER));
		}else{
			rocks.add(new Rock(this, this.getContext()));
		}
	}
	
	public void createNewRock(int x, int y){
		createNewRock();
		this.rocks.get(this.rocks.size()-1).setX(x);
		this.rocks.get(this.rocks.size()-1).setY(y);
	}
	
	private void createNewCow(){
		int choice = (int) (Math.random() * 100);
		if ((choice -= Util.CHANCE_FAT_COW) < 0  && Util.lvl >= Util.START_LVL_FAT_COW) {
			cows.add(new CowFat(this, this.getContext()));
		} else if ((choice -= Util.CHANCE_DANCE_COW) < 0  && Util.lvl >= Util.START_LVL_DANCE_COW) {
			cows.add(new CowDance(this, this.getContext()));
		} else if ((choice -= Util.CHANCE_OLD_COW) < 0  && Util.lvl >= Util.START_LVL_OLD_COW) {
			cows.add(new CowOld(this, this.getContext()));
		} else if ((choice -= Util.CHANCE_GHOST_COW) < 0  && Util.lvl >= Util.START_LVL_GHOST_COW) {
			cows.add(new CowGhost(this, this.getContext()));
		} else if ((choice -= Util.CHANCE_ZOMBIE_COW) < 0  && Util.lvl >= Util.START_LVL_ZOMBIE_COW) {
			cows.add(new CowZombie(this, this.getContext()));
		} else {
			cows.add(new Cow(this, this.getContext()));
		}
	}
	
	private void createNewPowerUp(){
		int choice = (int) (Math.random() * 100);
		if ((choice -= Util.CHANCE_MILKCONTAINER) < 0 && Util.lvl >= Util.START_LVL_MILKCONTAINER) {
			powerUps.add(new PowerUpMilkContainer(this, this.getContext()));
		}else if ((choice -= Util.CHANCE_NITROKAESE) < 0 && Util.lvl >= Util.START_LVL_NITROKAESE) {
			powerUps.add(new PowerUpNitrokaese(this, this.getContext()));
		}else if ((choice -= Util.CHANCE_BELL) < 0 && Util.lvl >= Util.START_LVL_BELL) {
			powerUps.add(new PowerUpBell(this, this.getContext()));
		}else if ((choice -= Util.CHANCE_COIN5 ) < 0 && Util.lvl >= Util.START_LVL_COIN5) {
			powerUps.add(new Coin5(this, this.getContext()));
		}else{
			powerUps.add(new Coin(this, this.getContext()));
		}
	}
	
	public void createNewPowerUp(int x, int y){
		createNewPowerUp();
		this.powerUps.get(this.powerUps.size()-1).setX(x);
		this.powerUps.get(this.powerUps.size()-1).setY(y);
	}
	
	/**
	 * Update sprite movements
	 */
	private void move(){
		bg.move(1);
		rocket.move(1);
		for (Rock r : this.rocks) {
			r.move(NPCspeedModifier);
		}
		for (Cow c : this.cows) {
			c.move(NPCspeedModifier);
		}
		for (PowerUp p : this.powerUps) {
			p.move(NPCspeedModifier);
		}
	}
	
	public void Touched(int x, int y){
		this.touchX = x;
		this.touchY = y;
		this.isTouched = true;
	}
	
	private void checkTouchedRocks(){
		for(int i=0; i<this.rocks.size(); i++){
			if(this.rocks.get(i).isTouching(touchX, touchY)){
				this.rocks.get(i).inflictDamage();
			}
		}
	}
	
	public int getBGxSpeed(){
		return this.bg.speedX;
	}
	public int getBGySpeed(){
		return this.bg.speedY;
	}
	
	public Rocket getRocket(){
		return this.rocket;
	}
	
	public Game getGame(){
		return this.game;
	}

	public void onTiltChange(float x, float y) {
		if(this.rocket.isStunned()){
			x = y = 0;
		}else{
			x -= Util.DefaultXAngle;
			y -= Util.DefaultYAngle;
			x *= Util.getSpeedFactor();
			y *= Util.getSpeedFactor();
			
			if(x < 4 && x > -4){
				x = 0;
			}
			if(y < 4 && y > -4){
				y = 0;
			}
			
			if(x >= 0){
				x = (int) (0.5 * Math.pow(x, 1.3));
			}else{
				x *= -1;
				x = (int) (0.5 * Math.pow(x, 1.3));
				x *= -1;
			}
			if(y >= 0){
				y = (int) (0.5 * Math.pow(y, 1.3));
			}else{
				y *= -1;
				y = (int) (0.5 * Math.pow(y, 1.3));
				y *= -1;
			}
			
			if(this.rocket.isFrozen()){
				x /= POWER_ICE;
				y /= POWER_ICE;
			}
		}
		
		rocket.setSpeedX((int) x);
		rocket.setSpeedY((int) y);
		
		bg.setSpeedX((int) x);
		bg.setSpeedY((int) y);
	}
	
	public void setNPCSpeedModifier(float val){
		this.NPCspeedModifier = val;
	}
	
	public void resetNPCSpeedModifier(){
		this.NPCspeedModifier = 1;
	}
	
	public void spawnCows(int amount){
		for(int i=0; i<amount; i++){
			this.createNewCow();
		}
	}
	
	public void attractCows(){
		for(Cow c : this.cows){
			c.moveTo(this.rocket.getX(), this.rocket.getY());
		}
	}
	
	private void createMeteorshower(short amount){
		this.createNewRock();	//Random Rock
		int posX = this.rocks.get(this.rocks.size()-1).getX();
		int posY = this.rocks.get(this.rocks.size()-1).getY();
		double random = Math.random();
		int speedY = (int)(((this.rocket.y - posY)>>5) * random);
		int speedX = (int)(((this.rocket.x - posX)>>5) * random);
		this.rocks.get(this.rocks.size()-1).setSpeedX(speedX);
		this.rocks.get(this.rocks.size()-1).setSpeedY(speedY);
		
		for(int i=1; i<amount; i++){
			double speedMod = Math.random()+0.5;
			if(speedMod > 1){
				speedMod = 1;
			}
			this.rocks.add(new Rock(this, this.getContext(),
					posX + (int) (Math.random()*Util.PIXEL_HEIGHT/2 - Util.PIXEL_HEIGHT/4),
					posY + (int) (Math.random()*Util.PIXEL_HEIGHT/2 - Util.PIXEL_HEIGHT/4),
					(int) (speedX * speedMod), 
					(int) (speedY * speedMod)));
		}
		
		this.game.showToast(game.getResources().getString(R.string.ToastMeteorShower));
	}
	
}

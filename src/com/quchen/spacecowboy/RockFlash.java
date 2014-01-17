package com.quchen.spacecowboy;
/**
 * A meteorite that stuns the player
 * @author lars
 */
import android.content.Context;
import android.graphics.Canvas;

public class RockFlash extends Rock {
	public static final byte POWER_FLASH_ROCK = 1;
	
	private Status status;

	public RockFlash(GameView view, Context context) {
		super(view, context);
		this.status = new Status(view, context);
		this.status.row = 2;
		this.power *= POWER_FLASH_ROCK;
	}

	@Override
	public void move(float speedModifier) {
		super.move(speedModifier);
		this.status.move(speedModifier);
	}

	@Override
	public void draw(Canvas canvas) {
		super.draw(canvas);
		this.status.setX(this.x);
		this.status.setY(this.y);
		this.status.draw(canvas);
	}
	
	@Override
	public void onCollision() {
		super.onCollision();
		this.view.getRocket().inflictStun();
	}
}

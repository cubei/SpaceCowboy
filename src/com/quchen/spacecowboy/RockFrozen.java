package com.quchen.spacecowboy;
/**
 * A meteorite that freezes the player
 * @author lars
 */
import android.content.Context;
import android.graphics.Canvas;

public class RockFrozen extends Rock {
	public static final byte POWER_FROZEN_ROCK = 1;
	
	private Status status;

	public RockFrozen(GameView view, Context context) {
		super(view, context);
		this.status = new Status(view, context);
		this.status.row = 1;
		this.power *= POWER_FROZEN_ROCK;
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
		this.view.getRocket().inflictIce();
	}

}

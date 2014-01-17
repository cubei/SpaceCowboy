package com.quchen.spacecowboy;
/**
 * A Milkcontainer that raises the max milk amount
 * @author lars
 */
import android.content.Context;
import android.graphics.Bitmap;

public class PowerUpMilkContainer extends PowerUp {
	public static final byte MILK_CONTAINER_MAX_INCREASE = 5;
	public static final byte MILK_IN_CONTAINER = 2;
	protected static Bitmap globalBitmap;
	public static final byte NUMBER_OF_ROWS = 1;
	public static final byte NUMBER_OF_COLUMNS = 1;

	public PowerUpMilkContainer(GameView view, Context context) {
		super(view, context);
		if(globalBitmap == null){
			globalBitmap = createBitmap(context.getResources().getDrawable(R.drawable.milk));
		}
		this.bitmap = globalBitmap;
		this.width = this.bitmap.getWidth();
		this.height = this.bitmap.getHeight();
		this.speedX = this.speedX>>2;
		this.speedY = this.speedY>>2;
	}

	@Override
	public void onCollision() {
		super.onCollision();
		this.view.getGame().increaseMilkMax(MILK_CONTAINER_MAX_INCREASE);
		this.view.getGame().increaseMilk(MILK_IN_CONTAINER);
	}
}

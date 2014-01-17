package com.quchen.spacecowboy;
/**
 * Coin that give 5 coins
 * @author lars
 */
import android.content.Context;
import android.graphics.Bitmap;

public class Coin5 extends Coin{
	protected static Bitmap globalBitmap;
	public static final byte NUMBER_OF_ROWS = 1;
	public static final byte NUMBER_OF_COLUMNS = 12;

	public Coin5(GameView view, Context context) {
		super(view, context);
		if(globalBitmap == null){
			globalBitmap = createBitmap(context.getResources().getDrawable(R.drawable.coin5));
		}
		this.bitmap = globalBitmap;
		this.width = this.bitmap.getWidth()/NUMBER_OF_COLUMNS;
		this.height = this.bitmap.getHeight();
	}

	@Override
	public void onCollision() {
		super.onCollision();
		this.view.getGame().increasePoints(5 - 1);
		this.view.getGame().redCoinCollected();
	}
}

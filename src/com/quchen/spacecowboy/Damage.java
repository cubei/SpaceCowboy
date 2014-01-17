package com.quchen.spacecowboy;
/**
 * Provides a milksplash effect when hit
 * @author lars
 */
import android.content.Context;
import android.graphics.Bitmap;

public class Damage extends Sprite {
	public static final byte NUMBER_OF_ROWS = 2;
	public static final byte NUMBER_OF_COLUMNS = 4;
	
	protected static Bitmap globalBitmap;
	private byte TimeOutCounter;
	
	public Damage(GameView view, Context context) {
		super(view, context);
		
		if(globalBitmap == null){
			globalBitmap = createBitmap(context.getResources().getDrawable(R.drawable.damage));
		}
		this.bitmap = globalBitmap;
		
		this.width = this.bitmap.getWidth()/NUMBER_OF_COLUMNS;
		this.height = this.bitmap.getHeight()/NUMBER_OF_ROWS;
		this.colNr = 4;
	}

	@Override
	public boolean isTimedOut() {
		this.TimeOutCounter++;
		return this.TimeOutCounter >= this.colNr;
	}
}

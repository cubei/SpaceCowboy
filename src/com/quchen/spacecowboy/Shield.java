package com.quchen.spacecowboy;
/**
 * The shield that the ghost cow provides
 * @author lars
 */
import android.content.Context;

public class Shield extends Sprite {
	public static final byte NUMBER_OF_ROWS = 1;
	public static final byte NUMBER_OF_COLUMNS = 1;

	public Shield(GameView view, Context context) {
		super(view, context);
		this.bitmap = createBitmap(context.getResources().getDrawable(R.drawable.shield));
		this.width = this.bitmap.getWidth();
		this.height = this.bitmap.getHeight();
	}

}

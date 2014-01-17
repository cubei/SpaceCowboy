package com.quchen.spacecowboy;
/**
 * A cheese that slows down everything except the player himself
 * Cheese = Kaese (german)
 * @author lars
 */
import android.content.Context;
import android.graphics.Bitmap;

public class PowerUpNitrokaese extends PowerUp {
	public static final int TIME_NITRO = 5000;
	public static final byte NUMBER_OF_ROWS = 1;
	public static final byte NUMBER_OF_COLUMNS = 1;
	protected static Bitmap globalBitmap;
	static private TimerExec nitroTimer;

	public PowerUpNitrokaese(GameView view, Context context) {
		super(view, context);
		if(globalBitmap == null){
			globalBitmap = createBitmap(context.getResources().getDrawable(R.drawable.nitrokaese));
		}
		this.bitmap = globalBitmap;
		this.width = this.bitmap.getWidth();
		this.height = this.bitmap.getHeight();
		nitroTimer = new TimerExec();
	}

	@Override
	public void onCollision() {
		super.onCollision();
		view.setNPCSpeedModifier(0.1f);
		view.getGame().showToast(view.getResources().getString(R.string.ToastNitroOn));
		
		nitroTimer.cancel();
		nitroTimer.setTimer(new TimerExecTask() {
			@Override
			public void onFinish() {
				view.resetNPCSpeedModifier();
				view.getGame().showToast(view.getResources().getString(R.string.ToastNitroOff));
			}
			@Override
			public void onTick() {}
		}, Util.TIME_DEFAULT_TICK, TIME_NITRO);
		nitroTimer.start();
	}

}

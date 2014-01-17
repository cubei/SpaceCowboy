package com.quchen.spacecowboy;
/**
 * This interface needs to be implemented to use the tiltsensor
 * @author lars
 */
public interface Tiltable {
	/**
	 * This method will be called on sensorchange
	 * @param x angle around x axis
	 * @param y angle around y axis
	 * 0 <= x || y < 360
	 */
	void onTiltChange(float x, float y);
}

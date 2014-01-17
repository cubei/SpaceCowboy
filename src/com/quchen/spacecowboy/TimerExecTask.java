package com.quchen.spacecowboy;
/**
 * The task of an TimerExec
 */
public interface TimerExecTask {

	/**
	 * Is called every tick
	 */
	void onTick();
	/**
	 * Is called after the duration is elapsed
	 */
	void onFinish();
}

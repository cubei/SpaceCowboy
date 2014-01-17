package com.quchen.spacecowboy;
/**
 * A Timer that can pause and resume
 * All timers are stored in a list so they can be paused and resume at the ~same time
 * https://github.com/c05mic/pause-resume-timer
 */

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TimerExec {
	static List<TimerExec> allTimers = new ArrayList<TimerExec>();
	private volatile boolean isRunning = false;
	private volatile boolean isPaused = false;
	private volatile long interval;
	private long elapsedTime;
	private volatile long duration;
	private ScheduledExecutorService execService = Executors.newSingleThreadScheduledExecutor();
	private Future<?> future = null;
	private TimerExecTask task;
	
	public TimerExec(){
		this(1000, -1, new TimerExecTask() {
			@Override
			public void onTick() {}
			@Override
			public void onFinish() {}
		});
	}
	
	/**
	 * @param interval
	 * @param duration -1 = infinity
	 * @param task
	 */
	public TimerExec(long interval, long duration, TimerExecTask task){
		this.interval = interval;
		this.duration = duration;
		this.task = task;
		this.elapsedTime = 0;
		
		allTimers.add(this);
	}
	

	public static void stopAllTimers() {
		for(TimerExec t: allTimers){
			t.cancel();
		}
		allTimers.clear();
	}
	
	public void start(){
		if(isRunning){
			return;
		}
		isRunning = true;
		isPaused = false;
		future = execService.scheduleWithFixedDelay(new Runnable(){
			@Override
			public void run() {
				task.onTick();
				elapsedTime += interval;
				if(duration > 0){	// -1 = infinity
					if(elapsedTime >= duration){
						future.cancel(false);
						task.onFinish();
					}
				}
			}
			
		}, interval, interval, TimeUnit.MILLISECONDS);
	}
	
	public void pause(){
		if(!isRunning){
			return;
		}
		future.cancel(false);
		isRunning = false;
		isPaused = true;
	}
	
	public void resume(){
		if(!isPaused){
			return;
		}
		start();
	}
	
	public void cancel(){
		pause();
		elapsedTime = 0;
		isPaused = false;
	}
	
	public void setTimer(TimerExecTask task, long interval, long duration){
		this.interval = interval;
		this.duration = duration;
		this.task = task;
		this.elapsedTime = 0;
	}
	
	public long getElapsedTime(){
		return elapsedTime;
	}

	@Override
	protected void finalize() throws Throwable {
		super.finalize();
		allTimers.remove(this);
	}

}


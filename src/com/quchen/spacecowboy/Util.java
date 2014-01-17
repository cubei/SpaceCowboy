package com.quchen.spacecowboy;
/**
 * Holds same variables and constants
 * Holds the player for the background music
 * @author Lars
 */
import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;

public class Util {
	//---------------------------------------------
	//----------------constants--------------------
	//---------------------------------------------
	
	public static final String VERSION = "1.01";
	// Devicesettings
	public static float DENSITY = 1;
	public static float DISPLAX_SIZE = 5;
	public static int PIXEL_WIDTH = 800;
	public static int PIXEL_HEIGHT = 480;
	public static int ORIENTATION = 1;
	// Systemsettings
	public static short DEFAULT_FONT_SIZE = 20;		// Motorola Milestone 2
	public static final short UPDATE_INTERVAL = 50;
	public static final byte TO_DEGREE = 90;
	public static final float DISTANCE_COLLISION_FACTOR = 0.4f;
	public static final int ATTACK_AREA_EFFECT_INCREASE = 80;
	
	// Gameconfig
	public static final byte COINS_FOR_LEVELUP = 10;
	public static final short MOVEMENTS_FOR_DISPLAY_HEIGHT_FOR_10_DEGREE = 10;
	public static final byte AMOUNT_OF_ROCKS = 20;
	public static final byte AMOUNT_OF_COWS = 15;
	public static final byte AMOUNT_OF_POWER_UPS = 10;
	
	public static final byte START_MILK = 4;
	public static final byte START_CONTAINER = 10;
	
	// Times
	public static final int TIME_DEFAULT_TICK = 1000; 
	
	//Chances rocks
	public static final byte CHANCE_GUIDED_ROCK = 10;
	public static final byte CHANCE_FROZEN_ROCK = 10;
	public static final byte CHANCE_FLASH_ROCK = 10;
	public static final byte CHANCE_FAT_ROCK = 10;
	public static final byte CHANCE_METEOR_SHOWER = 5;
	
	public static final byte START_LVL_GUIDED_ROCK = 5;
	public static final byte START_LVL_FAT_ROCK = 7;
	public static final byte START_LVL_FLASH_ROCK = 3;
	public static final byte START_LVL_FROZEN_ROCK = 2;
	public static final byte START_LVL_METEOR_SHOWER = 10;
	
	//Chances cows
	public static final byte CHANCE_FAT_COW = 10;
	public static final byte CHANCE_DANCE_COW = 20;
	public static final byte CHANCE_ZOMBIE_COW = 10;
	public static final byte CHANCE_OLD_COW = 10;
	public static final byte CHANCE_GHOST_COW = 10;
	
	public static final byte START_LVL_ZOMBIE_COW = 6;
	public static final byte START_LVL_FAT_COW = 4;
	public static final byte START_LVL_DANCE_COW = 3;
	public static final byte START_LVL_OLD_COW = 2;
	public static final byte START_LVL_GHOST_COW = 5;
	
	//Chances Powerups
	public static final byte CHANCE_MILKCONTAINER = 15;
	public static final byte CHANCE_NITROKAESE = 7;
	public static final byte CHANCE_BELL = 7;
	public static final byte CHANCE_COIN5 = 1;
	
	public static final byte START_LVL_MILKCONTAINER = 2;
	public static final byte START_LVL_NITROKAESE = 6;
	public static final byte START_LVL_BELL = 7;
	public static final byte START_LVL_COIN5 = 5;
	
	//---------------------------------------------
	//------------------variables------------------
	//---------------------------------------------
	public static short lvl = 1;
	public static short DefaultXAngle = 0;
	public static short DefaultYAngle = 0;
	public static float musicVolume = 0.5f;
	public static float soundVolume = 0.5f;
	public static float COW_COLLISION_FACTOR = DISTANCE_COLLISION_FACTOR;
	public static float COIN_COLLISION_FACTOR = DISTANCE_COLLISION_FACTOR;
	public static float ROCK_COLLISION_FACTOR = DISTANCE_COLLISION_FACTOR;
	public static float GUIDED_ROCK_SPEED_FACTOR = 1f;
	public static float STATUS_EFFECT_FACTOR = 1f;
	public static int ATTACK_AREA_EFFECT = 0;
	
	public static MediaPlayer musicPlayer;
	public static void initMusicPlayer(Context context){
		musicPlayer = MediaPlayer.create(context, R.raw.theme);
		musicPlayer.setLooping(true);
		musicPlayer.setVolume(musicVolume, musicVolume);
	}
	public static void updateMusicVolume(){
		if(musicPlayer != null){
			musicPlayer.setVolume(musicVolume, musicVolume);
		}
	}
	
	
	public static SoundPool soundPool = new SoundPool(10, AudioManager.STREAM_MUSIC,0);
	
	public static boolean isTablet(Activity res) {
	    boolean xlarge = ((res.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == 4);
	    boolean large = ((res.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_LARGE);
	    return (xlarge || large);
	}
	
	public static float getScaleFactor(){
		return PIXEL_HEIGHT / 128f / 3.75f;
	}
	
	public static short getTextSize(){
		return (short) (17f/320f * PIXEL_HEIGHT - 10); 
	}
	
	public static float getSpeedFactor(){
		return 0.1f * PIXEL_HEIGHT / MOVEMENTS_FOR_DISPLAY_HEIGHT_FOR_10_DEGREE / 10;
	}

}

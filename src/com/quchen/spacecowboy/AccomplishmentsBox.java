package com.quchen.spacecowboy;
/**
 * Class to store all accomplishments.
 * @author lars
 */

import android.app.Activity;
import android.content.SharedPreferences;

public class AccomplishmentsBox {
	public static final String online_save = "online";
	
	public static final String survived_5_save = "survived_5";
	public static final String survived_15_save = "survived_15";
	public static final String undamaged_1_save = "undamaged_1";
	public static final String undamaged_5_save = "undamaged_5";
	public static final String cows_100_save = "cows_100";
	public static final String cows_1000_save = "cows_1000";
	public static final String cows_75_run_save = "cows_75_run";
	public static final String cows_200_run_save = "cows_200_run";
	public static final String meteoroids_100_save = "meteorids_100";
	public static final String meteoroids_1000_save = "meteorids_1000";
	public static final String meteoroids_50_run_save = "meteorids_50_run";
	public static final String meteoroids_200_run_save = "meteorids_200_run";
	public static final String powerUps_100_save = "powerUps_100";
	public static final String powerUps_30_run_save = "powerUps_30_run";
	public static final String milkContainer_100_save = "milkContainer_100";
	public static final String sleepy_cowboy_save = "sleepy_cowboy";
	public static final String catch_dancecow_frozen_save = "catch_dancecow_frozen";
	public static final String heal_poison_save = "heal_poison";
	public static final String low_milk_1_minutes_save = "low_milk_1_minutes";
	public static final String full_milk_2_minutes_save = "full_milk_2_minutes";
	public static final String meteoroids_shield_10_save = "meteoroids_shield_10";
	public static final String lvl_10_10_milkcontainer_save = "lvl_10_10_milkcontainer";
	public static final String red_coin_save = "red_coin";
	public static final String leet_king_save = "leet_king";
	public static final String catch_them_all_save = "catch_them_all";
	public static final String catch_them_all_bool_save = "catch_them_all_bool";
	
	public static final String cows_save = "cows";
	public static final String meteoroids_save = "meteoroids";
	public static final String powerups_save = "powerups";
	public static final String coins_total_save = "coins_total";
	public static final String score_save = "score";
	public static final String best_score_save = "best_score";
	
	volatile boolean survived_5 = false;
	volatile boolean survived_15 = false;
	volatile boolean undamaged_1 = false;
	volatile boolean undamaged_5 = false;
	volatile boolean cows_100 = false;
	volatile boolean cows_1000 = false;
	volatile boolean cows_75_run = false;
	volatile boolean cows_200_run = false;
	volatile boolean meteoroids_100 = false;
	volatile boolean meteoroids_1000 = false;
	volatile boolean meteoroids_50_run = false;
	volatile boolean meteoroids_200_run = false;
	volatile boolean powerUps_100 = false;
	volatile boolean powerUps_30_run = false;
	volatile boolean milkContainer_100 = false;
	volatile boolean sleepy_cowboy = false;
	volatile boolean catch_dancecow_frozen = false;
	volatile boolean heal_poison = false;
	volatile boolean low_milk_1_minutes = false;
	volatile boolean full_milk_2_minutes = false;
	volatile boolean meteoroids_shield_10 = false;
	volatile boolean lvl_10_10_milkcontainer = false;
	volatile boolean red_coin = false;
	volatile boolean leet_king = false;
	volatile boolean catched_them_all_bool = false;
	volatile int  catch_them_all = 0;
	static final int catched_them_all = 63;
	
	volatile int score = 0;
	volatile int cows = 0;
	volatile int meteoroids = 0;
	volatile int powerups = 0;
	
	/**
	 * Name of SharedPreferenc
	 */
	private static final String name = "ACCOMBLISHMENTS";
	
	/**
	 * Stores the score and achievements locally.
	 * 
	 * The accomblishments will be saved local via SharedPreferences.
	 * This makes it very easy to cheat.
	 * 
	 * @param outbox Data that should be saved
	 * @param activity activity that is needed for shared preferences
	 */
	public static void setLocal(AccomplishmentsBox outbox, Activity activity){
		SharedPreferences saves = activity.getSharedPreferences(name, 0);
		SharedPreferences.Editor editor = saves.edit();
		if(outbox.survived_5){
			editor.putBoolean(survived_5_save, true);
		}
		if(outbox.survived_15){
			editor.putBoolean(survived_15_save, true);
		}
		if(outbox.undamaged_1){
			editor.putBoolean(undamaged_1_save, true);
		}
		if(outbox.undamaged_5){
			editor.putBoolean(undamaged_5_save, true);
		}
		if(outbox.cows_100){
			editor.putBoolean(cows_100_save, true);
		}
		if(outbox.cows_1000){
			editor.putBoolean(cows_1000_save, true);
		}
		if(outbox.cows_75_run){
			editor.putBoolean(cows_75_run_save, true);
		}
		if(outbox.cows_200_run){
			editor.putBoolean(cows_200_run_save, true);
		}
		if(outbox.meteoroids_100){
			editor.putBoolean(meteoroids_100_save, true);
		}
		if(outbox.meteoroids_1000){
			editor.putBoolean(meteoroids_1000_save, true);
		}
		if(outbox.meteoroids_50_run){
			editor.putBoolean(meteoroids_50_run_save, true);
		}
		if(outbox.meteoroids_200_run){
			editor.putBoolean(meteoroids_200_run_save, true);
		}
		if(outbox.powerUps_100){
			editor.putBoolean(powerUps_100_save, true);
		}
		if(outbox.powerUps_30_run){
			editor.putBoolean(powerUps_30_run_save, true);
		}
		if(outbox.milkContainer_100){
			editor.putBoolean(milkContainer_100_save, true);
		}
		if(outbox.sleepy_cowboy){
			editor.putBoolean(sleepy_cowboy_save, true);
		}
		if(outbox.catch_dancecow_frozen){
			editor.putBoolean(catch_dancecow_frozen_save, true);
		}
		if(outbox.heal_poison){
			editor.putBoolean(heal_poison_save, true);
		}
		if(outbox.low_milk_1_minutes){
			editor.putBoolean(low_milk_1_minutes_save, true);
		}
		if(outbox.full_milk_2_minutes){
			editor.putBoolean(full_milk_2_minutes_save, true);
		}
		if(outbox.meteoroids_shield_10){
			editor.putBoolean(meteoroids_shield_10_save, true);
		}
		if(outbox.lvl_10_10_milkcontainer){
			editor.putBoolean(lvl_10_10_milkcontainer_save, true);
		}
		if(outbox.red_coin){
			editor.putBoolean(red_coin_save, true);
		}
		if(outbox.leet_king){
			editor.putBoolean(leet_king_save, true);
		}
		if(outbox.catched_them_all_bool){
			editor.putBoolean(catch_them_all_bool_save, true);
		}

		editor.putInt(catch_them_all_save, outbox.catch_them_all | saves.getInt(catch_them_all_save, 0));
		editor.putInt(cows_save, outbox.cows + saves.getInt(cows_save, 0));
		editor.putInt(meteoroids_save, outbox.meteoroids + saves.getInt(meteoroids_save, 0));
		editor.putInt(powerups_save, outbox.powerups + saves.getInt(powerups_save, 0));
		editor.putInt(coins_total_save, outbox.score + saves.getInt(coins_total_save, 0));
		
		if(outbox.score > saves.getInt(score_save, 0)){
			editor.putInt(score_save, outbox.score);
		}
		if(outbox.score > saves.getInt(best_score_save, 0)){
			editor.putInt(best_score_save, outbox.score);
		}

		editor.commit();
	}
	
	/**
	 * reads the local stored data
	 * @param activity activity that is needed for shared preferences
	 * @return local stored score and achievements
	 */
	public static AccomplishmentsBox getLocal(Activity activity){
		AccomplishmentsBox box = new AccomplishmentsBox();
		SharedPreferences saves = activity.getSharedPreferences(name, 0);
		
		box.survived_5 = saves.getBoolean(survived_5_save, false);
		box.survived_15 = saves.getBoolean(survived_15_save, false);
		box.undamaged_1 = saves.getBoolean(undamaged_1_save, false);
		box.undamaged_5 = saves.getBoolean(undamaged_5_save, false);
		box.cows_100 = saves.getBoolean(cows_100_save, false);
		box.cows_1000 = saves.getBoolean(cows_1000_save, false);
		box.cows_75_run = saves.getBoolean(cows_75_run_save, false);
		box.cows_200_run = saves.getBoolean(cows_200_run_save, false);
		box.meteoroids_100 = saves.getBoolean(meteoroids_100_save, false);
		box.meteoroids_1000 = saves.getBoolean(meteoroids_1000_save, false);
		box.meteoroids_50_run = saves.getBoolean(meteoroids_50_run_save, false);
		box.meteoroids_200_run = saves.getBoolean(meteoroids_200_run_save, false);
		box.powerUps_100 = saves.getBoolean(powerUps_100_save, false);
		box.powerUps_30_run = saves.getBoolean(powerUps_30_run_save, false);
		box.milkContainer_100 = saves.getBoolean(milkContainer_100_save, false);
		box.sleepy_cowboy = saves.getBoolean(sleepy_cowboy_save, false);
		box.catch_dancecow_frozen = saves.getBoolean(catch_dancecow_frozen_save, false);
		box.heal_poison = saves.getBoolean(heal_poison_save, false);
		box.low_milk_1_minutes = saves.getBoolean(low_milk_1_minutes_save, false);
		box.full_milk_2_minutes = saves.getBoolean(full_milk_2_minutes_save, false);
		box.meteoroids_shield_10 = saves.getBoolean(meteoroids_shield_10_save, false);
		box.lvl_10_10_milkcontainer = saves.getBoolean(lvl_10_10_milkcontainer_save, false);
		box.red_coin = saves.getBoolean(red_coin_save, false);
		box.leet_king = saves.getBoolean(leet_king_save, false);
		box.catched_them_all_bool = saves.getBoolean(catch_them_all_bool_save, false);
		
		box.catch_them_all = saves.getInt(catch_them_all_save, 0);
		box.cows = saves.getInt(cows_save, 0);
		box.meteoroids = saves.getInt(meteoroids_save, 0);
		box.powerups = saves.getInt(powerups_save, 0);
		box.score = saves.getInt(score_save, 0);
		
		return box;
	}
	
	public static int getCoins(Activity activity){
		return activity.getSharedPreferences(name, 0).getInt("coins_total", 0);
	}
	
	/**
	 * Decreases the coins after an purchase in the upgrade store
	 * @param activity
	 * @param value
	 */
	public static void decreaseCoins(Activity activity, int value){
		SharedPreferences saves = activity.getSharedPreferences(name, 0);
		SharedPreferences.Editor editor = saves.edit();
		editor.putInt("coins_total", getCoins(activity) - value);
		editor.commit();
	}
	
	/**
	 * marks the data as online
	 * and resets the local score to 0
	 * @param activity activity that is needed for shared preferences
	 */
	public static void savesAreOnline(Activity activity){
		SharedPreferences saves = activity.getSharedPreferences(name, 0);
		SharedPreferences.Editor editor = saves.edit();
		editor.putBoolean("online", true);
		editor.putInt("score_save", 0);
		editor.commit();
	}
	
	/**
	 * marks the data as offline
	 * @param activity activity that is needed for shared preferences
	 */
	public static void savesAreOffline(Activity activity){
		SharedPreferences saves = activity.getSharedPreferences(name, 0);
		SharedPreferences.Editor editor = saves.edit();
		editor.putBoolean(online_save, false);
		editor.commit();
	}
	
	/**
	 * checks if the last data is already uploaded
	 * @param activity activity that is needed for shared preferences
	 * @return wheater the last data is already uploaded
	 */
	public static boolean isOnline(Activity activity){
		return activity.getSharedPreferences(name, 0).getBoolean(online_save, true);
	}
	
	public static int getBestScore(Activity activity){
		return activity.getSharedPreferences(name, 0).getInt(best_score_save, 0);
	}

}

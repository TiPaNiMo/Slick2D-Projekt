package de.game.engine;

import org.newdawn.slick.Music;
import org.newdawn.slick.Sound;

public class SoundController {

	/** Sound off / on */
	public static boolean PLAYSOUND = true;
	
	/** Background Music */
	public static Music BACKGROUND;
	
	/** Hit Sound */
	public static Sound HITSOUND;
	
	public SoundController() {
		
		/** 
		 * Loading Music files 
		 */
		try {

			SoundController.BACKGROUND = new Music("resource/sounds/background.ogg");
			SoundController.HITSOUND = new Sound("resource/sounds/hitsound.ogg");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}

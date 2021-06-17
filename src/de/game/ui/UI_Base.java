package de.game.ui;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public interface UI_Base {

	/**
	 * Update the game logic here. No rendering should take place in this method
	 * though it won't do any harm. 
	 * 
	 * @param container The container holing this game
	 * @param delta The amount of time thats passed since last update in milliseconds
	 * @throws SlickException Throw to indicate an internal error
	 */
	public void update(GameContainer container, int delta) throws SlickException;
	
	/**
	 * Render the game's screen here. 
	 * 
	 * @param container The container holing this game
	 * @param g The graphics context that can be used to render. However, normal rendering
	 * routines can also be used.
 	 * @throws SlickException Throw to indicate a internal error
	 */
	public void render(GameContainer container, Graphics g) throws SlickException;
}

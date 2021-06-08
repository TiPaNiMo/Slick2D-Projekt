package de.game.levels;

import de.game.engine.Main;
import de.game.objects.*;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public class Level{

	private String name;
	private Image background;
	
	private final Ball player;
	private ArrayList<LevelObject> staticObjects = new ArrayList<LevelObject>();
	private ArrayList<LevelObject> dynamicObjects = new ArrayList<LevelObject>();
	private ArrayList<TextObject> textObjects = new ArrayList<TextObject>();
	
	private int hitCounter = 0;
	
	public Level(String name, Ball player, Image background) {
		this.name = name;
		this.player = player;
		this.background = background;
	}
	
	/**
	 * Slick2D RENDER
	 * @param container
	 * @param g
	 */
	public void render(GameContainer container, Graphics g) {
		
		this.background.draw(0, 0, container.getWidth(), container.getHeight());
		
		for (LevelObject object : this.staticObjects) {
			object.render(g);
		}
		for (LevelObject object : this.dynamicObjects) {
			if (!object.isHit()) object.render(g);
		}
		for (TextObject object : this.textObjects) {
			object.render(g);
		}
		
		player.render(g, container);
		
	}
	
	/**
	 * Slick2D UPDATE
	 * @param container
	 * @param delta
	 */
	public void update(GameContainer container, int delta) {
		for (LevelObject object : this.staticObjects) {
			object.update(delta);
		}
		
		for (LevelObject object : this.dynamicObjects) {
			if (object.isHit()) {
				this.hitCounter++;
			} else {
				object.update(delta);
			}
		}
		
		if (this.hitCounter >= this.dynamicObjects.size()) {
			
			/** Resets counter */
			this.hitCounter = 0;
			
			/** Change Level */
			Main.PLAYER.changeLevel();
			
		} else {
			this.hitCounter = 0;
		}
		
		player.update(delta, container);
	}
	
	/**
	 * Reset Level
	 */
	public void reset() {
		
		/** Resets counter */
		this.hitCounter = 0;
		
		/** Resets object */
		for (LevelObject object : this.dynamicObjects) {
			object.setHit(false);
		}
	}
	
	/**
	 * Static LevelObject functions
	 */
	public void setStaticObjects(ArrayList<LevelObject> levelObjects) {
		this.staticObjects = levelObjects;
	}
	public void addStaticObject(LevelObject object) {
		this.staticObjects.add(object);
	}
	public void removeStaticObject(int index) {
		this.staticObjects.remove(index);
	}
	
	/**
	 * Dynamic LevelObject functions
	 */
	public void setDynamicObjects(ArrayList<LevelObject> levelObjects) {
		this.dynamicObjects = levelObjects;
	}
	public void addDynamicObject(LevelObject object) {
		this.dynamicObjects.add(object);
	}
	public void removeDynamicObject(int index) {
		this.dynamicObjects.remove(index);
	}
	
	/**
	 * Text functions
	 */
	public void setTextObjects(ArrayList<TextObject> textObjects) {
		this.textObjects = textObjects;
	}
	public void addTextObject(TextObject textObject) {
		this.textObjects.add(textObject);
	}
	public void removeTextObject(int index) {
		this.textObjects.remove(index);
	}

	/**
	 * Get name
	 * @return
	 */
	public String getName() {
		return this.name;
	}
	/**
	 * Set name
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	public Image getBackground() {
		return this.background;
	}

	public void setBackground(Image background) {
		this.background = background;
	}

	/**
	 * @return the staticObjects
	 */
	public ArrayList<LevelObject> getStaticObjects() {
		return this.staticObjects;
	}

	/**
	 * @return the dynamicObjects
	 */
	public ArrayList<LevelObject> getDynamicObjects() {
		return this.dynamicObjects;
	}
	
	/**
	 * @return the textObjects
	 */
	public ArrayList<TextObject> getTextObjects() {
		return textObjects;
	}
}

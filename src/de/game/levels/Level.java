package de.game.levels;

import de.game.objects.*;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Circle;

public class Level{

	private String name;
	private Image background;
	
	private final Ball player;
	private ArrayList<LevelObject> staticObjects = new ArrayList<LevelObject>();
	private ArrayList<LevelObject> dynamicObjects = new ArrayList<LevelObject>();
	
	public Level(String name, Ball player, Image background) {
		this.setName(name);
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
		
		for (LevelObject object : staticObjects) {
			object.render(g);
		}
		for (LevelObject object : dynamicObjects) {
			object.render(g);
		}
		player.render(g);
		
	}
	
	/**
	 * Slick2D UPDATE
	 * @param container
	 * @param delta
	 */
	public void update(GameContainer container, int delta) {
		for (LevelObject object : staticObjects) {
			object.update(delta);
		}
		for (LevelObject object : dynamicObjects) {
			object.update(delta);
		}
		player.update(delta);
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
	 * Get name
	 * @return
	 */
	public String getName() {
		return name;
	}
	/**
	 * Set name
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	public Image getBackground() {
		return background;
	}

	public void setBackground(Image background) {
		this.background = background;
	}

}

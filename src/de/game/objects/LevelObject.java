package de.game.objects;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Sound;
import org.newdawn.slick.geom.Shape;

import de.game.engine.Main;
import de.game.engine.SoundController;

public class LevelObject extends GameObject {

	/** state of object if its hit */
	private boolean hit = false;
	
	/** state of object if its first rotate */
	private boolean firstRotate = true;
	/** actual rotation of object */
	private float rotation = 0;
	
	/* UNSUED
	private boolean rotation = true;
	private float rotationSpeed = 0.9f;
	*/
	
	/** sound effect if object is hit */
	private Sound soundEffect;
	
	
	/**
	 * Creates a level object
	 * @param name Name of the object
	 * @param shape Shape of the object
	 * @param x Position X of object
	 * @param y Position Y of object
	 */
	public LevelObject(String name, Shape shape, float x, float y) {
		super(name, shape, x, y);
	}
	
	/**
	 * Creates a level object
	 * @param name Name of the object
	 * @param shape Shape of the object
	 * @param x Position X of object
	 * @param y Position Y of object
	 * @param color Color of object when its drawn
	 */
	public LevelObject(String name, Shape shape, float x, float y, Color color) {
		super(name, shape, x, y);
		this.color = color;
	}
	
	/**
	 * Creates a level object
	 * @param name Name of the object
	 * @param shape Shape of the object
	 * @param x Position X of object
	 * @param y Position Y of object
	 * @param startRotation First rotation when its drawn
	 */
	public LevelObject(String name, Shape shape, float x, float y, float startRotation) {
		super(name, shape, x, y);
		this.rotation += startRotation;
	}
	
	/**
	 * Creates a level object
	 * @param name Name of the object
	 * @param shape Shape of the object
	 * @param x Position X of object
	 * @param y Position Y of object
	 * @param startRotation First rotation when its drawn
	 * @param color Color of object when its drawn
	 */
	public LevelObject(String name, Shape shape, float x, float y, int startRotation, Color color) {
		super(name, shape, x, y);
		this.rotation += startRotation;
		this.color = color;
	}

	/**
	 * Update function
	 * @param delta deltaTime
	 */
	public void update(int delta) {
		super.update(delta);
		
		if (this.soundEffect == null) this.soundEffect = SoundController.HITSOUND;
		
		if (firstRotate) {
			this.rotate(this.rotation);
			this.firstRotate = false;
		}
		
		/**
		 * TODO Rotation
		if (rotation) {
			this.actualRotation = actualRotation % 360;
			this.rotate(this.actualRotation);
		}
		*/
		
		/** Check if object was hit by player */
		this.checkHit(Main.PLAYER.getHitbox());
		
		try {
			if (this.hit && this.soundEffect != null) {
				this.soundEffect.play(1f, 0.5f);
			}
		} catch(Exception e) {
			System.out.println(e);
		}
		
	}
	
	/**
	 * Render function
	 * @param g Slick2D Graphics
	 */
	public void render(Graphics g) {
		
		if (this.color == null) {
			g.setColor(this.color);
		} else {
			g.setColor(Color.white);
		}
		
		super.render(g);
	
	}
	
	/**
	 * Checks if object is hit by an object
	 * @param shape
	 */
	private boolean checkHit(Shape shape) {
		if (this.intersects(shape) && !Main.PLAYER.isCollidesOnStart()) {
			this.hit = true;
			return true;
		}
		this.hit = false;
		return false;
	}
	
	/**
	 * Sets sound effect
	 * @param sound
	 */
	public void setSoundEffect(Sound sound) {
		this.soundEffect = sound;
	}
	/**
	 * Gets sound effect
	 * @return
	 */
	public Sound getSoundEffect() {
		return this.soundEffect;
	}
	
	/**
	 * Sets color
	 */
	public void setColor(Color color) {
		this.color = color;
	}
	/**
	 * Gets color
	 */
	public Color getColor() {
		return this.getColor();
	}

	/**
	 * @return the hit
	 */
	public boolean isHit() {
		return hit;
	}
	
	/**
	 * sets hit
	 */
	public void setHit(boolean hit) {
		this.hit = hit;
	}
}

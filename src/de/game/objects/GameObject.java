package de.game.objects;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Transform;

/**
 * Represents a graphical object in the game with a shape, a texture and a hit box.
 * 
 * @author Maurice Sonntag
 */
public abstract class GameObject {
	
	/** Name of the game object */
	protected String name = null;
	
	/** The shape for the object to recognize collision and simple graphics */
	protected Shape shape = null;
	/** The main graphic of the game object */
	protected Image texture = null;
	/** Simple hit box */
	protected Shape hitbox = null;
	
	/** Color */
	protected Color color = Color.white;
	
	/** Position on x-axis */
	protected float x = 0;
	/** Position on y-axis */
	protected float y = 0;
	
	/** GameObject render method */
	public void render(Graphics g) {
		if (this.shape != null) {
			g.setColor(this.color);
			g.fill(this.shape);
		}
		if (this.texture != null) this.texture.draw(this.x, this.y, this.texture.getWidth(), this.texture.getHeight());
	}
	
	/** GameObject update method */
	public void update(int delta) {
		if (this.shape != null) {
			this.hitbox = this.shape;
			this.shape.setCenterX(this.x);
			this.shape.setCenterY(this.y);
		}
		if (this.hitbox != null) {
			this.shape.setCenterX(this.x);
			this.shape.setCenterY(this.y);
		}
	}
	
	/**
	 * Creates a new game object with given name and a simple shape
	 * @param name Name of the game object
	 * @param shape Simple shape for simple graphics
	 */
	public GameObject(String name, Shape shape) {
		this.name = name;
		this.shape = shape;
		this.hitbox = shape;
		this.x = shape.getX();
		this.y = shape.getY();
	}
	
	/**
	 * Creates a new game object with given name, a simple shape and a position
	 * @param name Name of the game object
	 * @param shape Simple shape for simple graphics
	 */
	public GameObject(String name, Shape shape, float x, float y) {
		this.name = name;
		this.shape = shape;
		this.hitbox = shape;
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Creates a new game object with given name, texture and simple generated hit box with the sizes of given texture
	 * @param name Name of the game object
	 * @param texture Main texture of the game object
	 * @param x Position on x-axis
	 * @param y Position on y-axis
	 */
	public GameObject(String name, Image texture, float x, float y) {
		this.name = name;
		this.texture = texture;
		this.x = x;
		this.y = y;
		
		Rectangle hitbox = new Rectangle(x, y, texture.getWidth(), texture.getHeight());
		this.hitbox = hitbox;
	}
	
	/**
	 * Creates a new game object with given name, texture and hit box
	 * @param name Name of the game object
	 * @param texture Main texture of the game object
	 * @param hitbox Shape
	 */
	public GameObject(String name, Image texture, Shape hitbox) {
		this.name = name;
		this.texture = texture;
		this.hitbox = hitbox;
	}
	
	/**
	 * Check if given shape intersects with the shape provided.
	 * @param shape The shape to check if it intersects with this one.
	 * @return The shape to check if it intersects with this one.
	 */
	public boolean intersects(Shape shape) {
		if (this.hitbox.intersects(shape)) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Check if the shape passed is entirely contained within this shape.
	 * @param shape The other shape to test against game object
	 * @return True if the other shape supplied is entirely contained within this one.
	 */
	public boolean contains(Shape shape) {
		if (this.hitbox.contains(shape)) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Sets position of game object
	 * @param x Position on x-axis
	 * @param y Position on y-axis
	 */
	public void setPosition(float x, float y) {
		this.x = x;
		this.y = y;
	}
	/**
	 * Gets position of game object as array
	 * @return x and y position as array
	 */
	public float[] getPosition() {
		float position[] = {this.x, this.y};
		return position;
	}

	/**
	 * Rotates the Shape and the hit box
	 * @param rotate
	 */
	public void rotate(float rotate) {
		Transform transform = Transform.createRotateTransform(rotate, this.x, this.y);
		this.shape = this.shape.transform(transform);
		this.hitbox = this.hitbox.transform(transform);
	}
	
	/**
	 * Returns position on x-axis
	 * @return x Position on x-axis
	 */
	public float getX() {
		return this.x;
	}
	/**
	 * Sets position on x-axis
	 * @param x Position on x-axis
	 */
	public void setX(float x) {
		this.x = x;
	}
	
	/**
	 * Returns position on x-axis
	 * @return x Position on x-axis
	 */
	public float getY() {
		return this.y;
	}
	/**
	 * Sets position on y-axis
	 * @param x Position on y-axis
	 */
	public void setY(float y) {
		this.y = y;
	}
	
	/**
	 * Returns name of game object
	 * @return Name of game object
	 */
	public String getName() {
		return this.name;
	}
	/**
	 * Sets name of game object
	 * @param name Name for game object
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Returns shape of game object
	 * @return simple shape
	 */
	public Shape getShape() {
		return this.shape;
	}
	/**
	 * Sets shape of game object
	 * @param shape simple shape
	 */
	public void setShape(Shape shape) {
		this.shape = shape;
	}
	
	/**
	 * Returns texture of game object
	 * @return Texture as image
	 */
	public Image getTexture() {
		return this.texture;
	}
	/**
	 * Sets texture of game object
	 * @param texture Texture as image
	 */
	public void setTexture(Image texture) {
		this.texture = texture;
	}
	
	/**
	 * Returns hit box of game object
	 * @return Texture as image
	 */
	public Shape getHitbox() {
		return this.hitbox;
	}
	/**
	 * Sets hit box of game object
	 * @param hitbox Texture as image
	 */
	public void setHitbox(Shape hitbox) {
		this.hitbox = hitbox;
	}
	
	/**
	 * Returns color of game object
	 * @return Color
	 */
	public Color getColor() {
		return this.color;
	}
	/**
	 * Sets color of game object
	 * @param color
	 */
	public void setColor(Color color) {
		this.color = color;
	}
}

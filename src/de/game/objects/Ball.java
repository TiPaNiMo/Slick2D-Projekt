package de.game.objects;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Vector2f;

import de.game.levels.LevelController;

/**
 * Ball Class
 * @author Maurice Sonntag
 * @author Pawel Czudy
 *
 */
public class Ball extends GameObject {

	/**
	 * Needed for rendering
	 */
	private static boolean render = false;
	private static boolean renderBalls = false;
	
	/** Input */
	private final Input input;
	
	/** Moving speed of the ball */
	private float acceleration = 0.4f;
	
	/** Position of the ball as vector (DON'T EDIT FOR CHANGING POSITION: USE this.setPosition or this.x, this.y) */
	private Vector2f position = new Vector2f(x, y);
	
	/** First position of the mouse when the left mouse button is pressed as vector */
	private Vector2f pressedMousePosition = new Vector2f(0, 0);
	
	/** Last mouse position of the mouse when the left mouse button is released as vector */
	private Vector2f shootMousePosition = new Vector2f(0, 0);
	
	/** Value added to ball's position for moving */
	private Vector2f directionVector = new Vector2f(0, 0);
	
	/** Angle of the directionVector */
	private float angle = 0;
	
	/** Saving small balls of the shooting UI */
	ArrayList<Circle> balls = new ArrayList<Circle>();
	
	/**
	 * Constructor of the player ball
	 * @param name
	 * @param input
	 * @param shape
	 */
	public Ball(String name, Input input, Shape shape) {
		super(name, shape);
		this.input = input;
		this.shape = shape;
		
		for (int i = 0; i < 9; i++) {
			balls.add(new Circle(0, 0, 4));
		}
		
	}
	
	/**
	 * Update method
	 * @param delta
	 * @param container
	 */
	public void update(int delta, GameContainer container) {
		super.update(delta);
		
		position.set(this.x, this.y);
		
		/**
		 * On first mouse press
		 */
		if (input.isMousePressed(0)) {
			
			/**
			 * Save mouse position on first click for placing the ball before release
			 */
			this.pressedMousePosition.set(input.getMouseX(), input.getMouseY());
			this.setPosition(this.pressedMousePosition.getX(), this.pressedMousePosition.getY());
		}
		
		/**
		 * On mouse is pressed
		 */
		if (input.isMouseButtonDown(0)) {

			/**
			 * Drawing shooting UI
			 */
			this.calculateShootingUI();
			
			/**
			 * Save shooting position to calculate direction
			 */
			this.shootMousePosition.set(input.getMouseX(), input.getMouseY());
			
			/**
			 * Calculating the direction vector to set first direction
			 */
			this.calculateDirectionVector();
			

			System.out.println(this.angle);
			
			/**
			 * Sets rendering of balls and shooting UI to true
			 */
			render = true;
			renderBalls = true;
		} else {

			for (LevelObject object : LevelController.LEVELS.get(LevelController.LEVELINDEX).getDynamicObjects()) {
				this.intersects(object);
			}
			for (LevelObject object : LevelController.LEVELS.get(LevelController.LEVELINDEX).getStaticObjects()) {
				this.intersects(object);
			}
			
			/**
			 * Moving the ball after mouse button 0 (left) released
			 */
			this.x += acceleration * delta * this.directionVector.getX();
			this.y += acceleration * delta * this.directionVector.getY();
			
			/**
			 * Sets rendering of shooting UI to false because its unnecessary
			 */
			renderBalls = false;
		}
		
	}
	
	/**
	 * Render method
	 * @param g
	 * @param container
	 */
	public void render(Graphics g, GameContainer container) {

		/**
		 * Render ball
		 */
		if (render) {
			super.render(g);
		}
		
		/**
		 * Render shooting UI
		 */
		if (renderBalls) {
			for (Circle ball: balls) {
				g.fill(ball);
			}
			
		}
	}
	
	public void intersects(GameObject object) {
		
		/**
		 * Check if the ball touches an game object and reflects the direction vector
		 */
		if (object.intersects(this.shape)) {
			
			Vector2f normal = new Vector2f(this.getX(), this.getY()).negate().normalise();
			
			float dotProduct = this.directionVector.dot(normal);
			
			this.directionVector.set(
					this.directionVector.getX() - 2 * dotProduct * normal.getX(),
					this.directionVector.getY() - 2 * dotProduct * normal.getY()
			);
		}
	}
	
	/**
	 * Calculating the direction vector that is required to move the ball
	 */
	private void calculateDirectionVector() {
		
		/**
		 * Calculate shooting angle in degrees (360°)
		 */
		float angle = (float) Math.toDegrees(
				Math.atan2(
						shootMousePosition.getY() - position.getY(), 
						shootMousePosition.getX() - position.getX()
				)
		);
		
		/**
		 * Sets angle
		 */
		this.angle = angle;
		
		/**
		 * Sets the direction vector as a unit vector
		 */
		this.directionVector.set(
				(float) Math.cos(Math.toRadians(angle)), 
				(float) Math.sin(Math.toRadians(angle))
		);
	}
	
	/**
	 * Calculating the shooting UI
	 * @param position
	 */
	private void calculateShootingUI() {
		
		/**
		 * Calculating parts between starting point and mouse point
		 */
		float x = 1f / balls.size();
		
		for (Circle ball : balls) {
			
			/**
			 * Using vector straight line equation ( d = p-> + x * v-> )
			 */
			Vector2f ballPosition = new Vector2f(
					(float) (this.pressedMousePosition.getX() + x * (this.shootMousePosition.getX() - this.pressedMousePosition.getX())), 
					(float) (this.pressedMousePosition.getY() + x * (this.shootMousePosition.getY() - this.pressedMousePosition.getY()))
			);
			
			/**
			 * Setting position of the ball
			 */
			ball.setCenterX(ballPosition.getX());
			ball.setCenterY(ballPosition.getY());
			
			/**
			 * Adding x by x each time to get closer to mouse position
			 */
			x += 1f / balls.size();
		}
	}
}

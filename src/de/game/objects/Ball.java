package de.game.objects;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Line;
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
	
	/** Last Position */
	private Vector2f lastPosition = new Vector2f(0, 0);
	
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
		
		this.hitbox = new Circle(this.shape.getX(), this.shape.getY(), this.shape.getBoundingCircleRadius() * 1.5f);
		
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
			this.lastPosition.set(this.shootMousePosition);
			
			/**
			 * Calculating the direction vector to set first direction
			 */
			this.calculateDirectionVector();
			
			/**
			 * Sets rendering of balls and shooting UI to true
			 */
			render = true;
			renderBalls = true;
		} else {

			/**
			 * Check for reflect
			 */
			for (LevelObject object : LevelController.LEVELS.get(LevelController.LEVELINDEX).getDynamicObjects()) {
				this.reflect(object);
			}
			for (LevelObject object : LevelController.LEVELS.get(LevelController.LEVELINDEX).getStaticObjects()) {
				this.reflect(object);
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
	
	/**
	 * Checks if the player collides with a given game object and reflects it if it's the case
	 * @param object
	 */
	public void reflect(GameObject object) {
		
		/** Check if object collides with player */
		if (object.intersects(this.shape)) {
			
			/** Collection of lines that form the object */
			ArrayList<Line> shapeLines = new ArrayList<Line>();
			
			/** Making lines of the shape */
			float[] linePoints = object.getHitbox().getPoints();
			
			for (int i = 0; i < linePoints.length; i += 2) {
				if (i + 3 > linePoints.length) {
					shapeLines.add(new Line(linePoints[i], linePoints[i + 1], linePoints[0], linePoints[1]));
				} else {
					shapeLines.add(new Line(linePoints[i], linePoints[i + 1], linePoints[i + 2], linePoints[ i + 3]));
				}
			}
			
			/** Check colliding with line */
			for (int i = 0; i < shapeLines.size(); i++) {
				
				/** Gets line from ArrayList */
				Line collidingLine = shapeLines.get(i);
				
				/** Checks if player collides with line */
				if (this.hitbox.intersects(collidingLine)) {
					
					/** Vector k1 for calculating reflection */
					Vector2f k1 = new Vector2f(collidingLine.getDX(), collidingLine.getDY());
					
					/** Vector k1 normal */
					Vector2f normalk1 = k1.getNormal().getPerpendicular();
					
					/** scalar product */
					float scalar = this.directionVector.dot(normalk1);
					
					/**
					 * Check if scalar is negative or positive, if its positive negate normal k1
					 */
					if (scalar > 0) {
						normalk1 = normalk1.negate();
					}
					
					/** Negate direction vector */
					this.directionVector = this.directionVector.negate();
					
					/** Project direction vector onto normalk1 */
					Vector2f p1 = new Vector2f();
					this.directionVector.projectOntoUnit(normalk1, p1);
					
					/** Calculate vector from v1 to p1 */
					Vector2f vToP = p1.sub(this.directionVector);
					
					/** Calculate r1 reflected v1 */
					this.directionVector = this.directionVector.add(vToP.scale(2));
					
					/** Save last Position */
					this.lastPosition.set(this.position);
				}
			}
		}
	}
	
	/**
	 * Calculating the direction vector that is required to move the ball
	 */
	private void calculateDirectionVector() {
		
		/**
		 * Calculate shooting angle in degrees (360°)
		 */
		this.angle = (float) Math.toDegrees(
				Math.atan2(
						shootMousePosition.getY() - position.getY(), 
						shootMousePosition.getX() - position.getX()
				)
		);
		
		/**
		 * Sets the direction vector as a unit vector
		 */
		this.directionVector.set(
				(float) Math.cos(Math.toRadians(this.angle)), 
				(float) Math.sin(Math.toRadians(this.angle))
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

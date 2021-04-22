package de.game.objects;

import java.util.ArrayList;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Vector2f;

/**
 * Ball Class
 * @author Maurice Sonntag
 * @author Pawel Czudy
 *
 */
public class Ball extends GameObject {

	private static boolean render = false;
	private final Input input;
	
	private Vector2f pressedMousePosition = new Vector2f(0, 0);
	
	ArrayList<Circle> balls = new ArrayList<Circle>();
	
	public Ball(String name, Input input, Shape shape) {
		super(name, shape);
		this.input = input;
		this.shape = shape;
		
		for (int i = 0; i < 9; i++) {
			balls.add(new Circle(0, 0, 4));
		}
		
	}
	
	public void update(int delta) {
		super.update(delta);
		
		if (input.isMousePressed(0)) {
			this.pressedMousePosition.set(input.getMouseX(), input.getMouseY());
			
			this.setPosition(this.pressedMousePosition.getX(), this.pressedMousePosition.getY());
		}
		
		if (input.isMouseButtonDown(0)) {
			
			Vector2f mousePosition = new Vector2f(input.getMouseX(), input.getMouseY());
			
			float x = 1f / balls.size();
			
			for (Circle ball : balls) {
				
				Vector2f ballPosition = new Vector2f(
						(float) (this.pressedMousePosition.getX() + x * (mousePosition.getX() - this.pressedMousePosition.getX())), 
						(float) (this.pressedMousePosition.getY() + x * (mousePosition.getY() - this.pressedMousePosition.getY()))
				);
				
				ball.setCenterX(ballPosition.getX());
				ball.setCenterY(ballPosition.getY());
				
				x += 1f / balls.size();
			}
			
			render = true;
		} else {
			render = false;
		}
		
	}
	
	public void render(Graphics g) {
		if (render) {
			super.render(g);
			for (Circle ball: balls) {
				g.fill(ball);
			}
			
		}
	}
}

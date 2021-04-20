package de.game.objects;

import java.util.ArrayList;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Vector2f;

public class Ball extends GameObject {

	private static boolean render = false;
	private final Input input;
	
	private int lastX, lastY;
	
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
			this.lastX = input.getMouseX();
			this.lastY = input.getMouseY();
			this.setPosition(lastX, lastY);
		}
		
		if (input.isMouseButtonDown(0)) {
			
			double distance = 0.0f;
			
			float mouseX = input.getMouseX();
			float mouseY = input.getMouseY();
			
			Vector2f lastBallPosition = new Vector2f(lastX, lastY);
			Vector2f mousePosition = new Vector2f(mouseX, mouseY);
			
			distance = lastBallPosition.distance(mousePosition);
			
			double lastBallX = lastX, lastBallY = lastY;
			
			for (Circle ball : balls) {
				double ballX = 0, ballY = 0;
				
				ballX = lastBallX + (mouseX - lastBallX)/ balls.size();
				ballY = lastBallY + (mouseY - lastBallY)/ balls.size();
				
				ball.setCenterX((float) ballX);
				ball.setCenterY((float) ballY);
				
				lastBallX = ballX;
				lastBallY = ballY;
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

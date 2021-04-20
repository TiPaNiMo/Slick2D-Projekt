package de.game.objects;

import java.util.ArrayList;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Shape;

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
			
			double mouseX = input.getMouseX();
			double mouseY = input.getMouseY();
			
			double[] vektor = {(lastX - mouseX), (lastY - mouseY)};
			distance = (float) Math.sqrt(Math.pow(vektor[0], 2) + Math.pow(vektor[1], 2));
			
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

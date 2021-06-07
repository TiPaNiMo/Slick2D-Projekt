package de.game.engine;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.Circle;

import de.game.levels.LevelController;
import de.game.objects.Ball;

public class Main extends BasicGame {
	
	/**
	 * Window Settings
	 */
	
	private static String windowName = "TiPaNiMo";
	private static int windowWidth = 600;
	private static int windowHeight = 1000;
	private static boolean fullscreen = false;
	private Input input;
	private static Ball PLAYER;
	
	private LevelController levelController;
	
	public enum State{
		START, GAME, GAME_OVER;
	}

	private static State state = State.GAME;

	public Main() {
		super(windowName);
	}
	
	public static void main(String[] args) throws SlickException {
		AppGameContainer container = new AppGameContainer(new Main());
		container.setDisplayMode(windowWidth, windowHeight, fullscreen);
		container.start();
	}
	
	@Override
	public void init(GameContainer container) throws SlickException {
		input = container.getInput();
		
		PLAYER = new Ball("Player", input, new Circle(0f, 0f, 10f));
		
		levelController = new LevelController(Main.PLAYER);
	}

	@Override
	public void render(GameContainer container, Graphics g) throws SlickException {
		
		switch(state) {
		case START:
			
			g.drawString("TiPaNiMo!", (container.getWidth() / 2) - 32, container.getHeight() / 2);
			
			break;
		case GAME:
			
			try {
				LevelController.LEVELS.get(LevelController.LEVELINDEX).render(container, g);
			} catch(Exception e) {
				System.out.println("[ERROR] Level nicht gefunden!");
				e.printStackTrace();
				state = State.START;
			}
			
			break;
		case GAME_OVER:
			
			break;
		default:
			
			break;
		
		}
		
	}

	@Override
	public void update(GameContainer container, int delta) throws SlickException {
		
		switch(state) {
		case START:
			
			break;
		case GAME:
			
			try {
				LevelController.LEVELS.get(LevelController.LEVELINDEX).update(container, delta);
			} catch(Exception e) {
				System.out.println("[ERROR] Level nicht gefunden!");
				e.printStackTrace();
				state = State.START;
			}
			
			break;
		case GAME_OVER:
			
			break;
		default:
			
			break;
		
		}
		
		//ESCAPE
		if (input.isKeyPressed(Input.KEY_ESCAPE)) container.exit();
	}
}

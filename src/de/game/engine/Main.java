package de.game.engine;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.Circle;

import de.game.levels.LevelController;
import de.game.objects.Ball;
import de.game.ui.UI_Button_Volume;

public class Main extends BasicGame {
	
	/**
	 * Window Settings
	 */
	
	private static String windowName = "TiPaNiMo";
	private static int windowWidth = 600;
	private static int windowHeight = 1000;
	private static boolean fullscreen = false;
	private Input input;
	public static Ball PLAYER;
	
	private UI_Button_Volume ui_volume;
	
	@SuppressWarnings("unused")
	private LevelController levelController;
	@SuppressWarnings("unused")
	private SoundController soundController;
	
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
		soundController = new SoundController();
		
		/**
		 * Play background song
		 */
		SoundController.BACKGROUND.loop(1f, 0.25f);
		
		/**
		 * Create UI
		 */
		ui_volume = new UI_Button_Volume(container.getWidth() - 80, 10, container.getInput());
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
			

			this.ui_volume.render(container, g);
			
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

			this.ui_volume.update(container, delta);
			
			if(input.isMousePressed(0)) {
				PLAYER.firstMousePress();
				
				if (this.ui_volume.getUi_button().contains(input.getMouseX(), input.getMouseY())) this.ui_volume.clicked = true;
			} else {
				this.ui_volume.clicked = false;
			}
			
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

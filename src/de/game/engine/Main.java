package de.game.engine;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Rectangle;
import de.game.levels.LevelController;
import de.game.objects.Ball;
import de.game.ui.UI_Button_Volume;
import java.awt.Font;

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
	
	private static Rectangle UI_BUTTON = new Rectangle(50, 50, 250, 100);
	private static Font BUTTON_FONT = new Font("Calibri", Font.BOLD, 50);
	private static TrueTypeFont BUTTON_TEXT;
	private static Image STARTBACKGROUND;
	
	@SuppressWarnings("unused")
	private LevelController levelController;
	@SuppressWarnings("unused")
	private SoundController soundController;
	
	public enum State{
		START, GAME, GAME_OVER;
	}

	private static State state = State.START;

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
		container.setShowFPS(false);
		
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
		BUTTON_TEXT = new TrueTypeFont(BUTTON_FONT, false);
		UI_BUTTON.setCenterX(container.getWidth() / 2);
		UI_BUTTON.setCenterY(container.getHeight() / 2 + 50);
		STARTBACKGROUND = new Image("resource/textures/level/noNam3.jpg");
	}

	@Override
	public void render(GameContainer container, Graphics g) throws SlickException {
		
		
		switch(state) {
		case START:
			
			g.drawImage(STARTBACKGROUND, 0, 0);
			g.setColor(new Color(13, 34, 39));
			g.fill(UI_BUTTON);
			BUTTON_TEXT.drawString(container.getWidth() / 2 - 42, container.getHeight() / 2 + 28, "Play", Color.white);
			
			break;
		case GAME:
			
			try {
				LevelController.LEVELS.get(LevelController.LEVELINDEX).render(container, g);
			} catch(Exception e) {
				//System.out.println("[ERROR] Level nicht gefunden!");
				//e.printStackTrace();
				state = State.START;
				levelController.LEVELINDEX = 0;
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
			
			if (input.isMousePressed(0)) {
				if (UI_BUTTON.contains(input.getMouseX(), input.getMouseY())) {
					Main.state = State.GAME;
				}
			}
			
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
				//System.out.println("[ERROR] Level nicht gefunden!");
				//e.printStackTrace();
				state = State.START;
				levelController.LEVELINDEX = 0;
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

package de.game.ui;

import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;

public class UI_Button_Volume extends UI_Button implements UI_Base {

	private static float WIDTH = 50f;
	private static float HEIGHT = 50f;
	
	private Image volume0;
	private Image volume1;
	private Image volume2;
	private Image volume3;
	
	private int volume = 2;

	private static UI_Text UI_TEXT = new UI_Text("", 0, 0);
	
	public UI_Button_Volume(float x, float y, Input input) {
		super(x, y, input, UI_TEXT, new Rectangle(x, y, WIDTH, HEIGHT));
		
		UI_TEXT.setShowMessage(false);
		this.ui_show = false;
		
		try {
			this.volume0 = new Image("resource/textures/ui/ui_volume_0.png");
			this.volume1 = new Image("resource/textures/ui/ui_volume_1.png");
			this.volume2 = new Image("resource/textures/ui/ui_volume_2.png");
			this.volume3 = new Image("resource/textures/ui/ui_volume_3.png");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void update(GameContainer container, int delta) throws SlickException {
		super.update(container, delta);
		
		if (this.clicked) {
			this.volume = (this.volume + 1) % 4;
		}
		
		if (this.volume == 0) {
			container.setMusicVolume(0f);
			container.setSoundVolume(0f);
		}
		if (this.volume == 1) {
			container.setMusicVolume(0.3f);
			container.setSoundVolume(0.3f);
		}
		if (this.volume == 2) {
			container.setMusicVolume(0.6f);
			container.setSoundVolume(0.6f);
		}
		if (this.volume == 3) {
			container.setMusicVolume(1f);
			container.setSoundVolume(1f);
		}
	}
	
	public void render(GameContainer container, Graphics g) throws SlickException {
		super.render(container, g);
		
		if (this.volume == 0) g.drawImage(volume0, this.x, this.y);
		if (this.volume == 1) g.drawImage(volume1, this.x, this.y);
		if (this.volume == 2) g.drawImage(volume2, this.x, this.y);
		if (this.volume == 3) g.drawImage(volume3, this.x, this.y);
	}
}

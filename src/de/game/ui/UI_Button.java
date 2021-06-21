package de.game.ui;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

public abstract class UI_Button extends UI_Element {

	/** Text Element */
	protected UI_Text text;
	
	/** Button Shape */
	protected Rectangle ui_button;
	
	/** Button Color */
	protected Color ui_color = Color.white;
	
	/** Clicked */
	public boolean clicked = false;
	
	/** Rectangle show */
	protected boolean ui_show = true;

	public UI_Button(float x, float y, Input input, UI_Text text, Rectangle rectangle) {
		super(x, y, input);
		this.setText(text);
		this.text = text;
		this.ui_button = rectangle;
	}

	@Override
	public void update(GameContainer container, int delta) throws SlickException {
	}

	@Override
	public void render(GameContainer container, Graphics g) throws SlickException {
		
		this.text.render(container, g);
		
		g.setColor(this.ui_color);
		if (ui_show) g.fill(this.ui_button);
	}

	public Rectangle getUi_button() {
		return ui_button;
	}

	protected void setUi_button(Rectangle ui_button) {
		this.ui_button = ui_button;
	}
	
	protected UI_Text getText() {
		return text;
	}

	protected void setText(UI_Text text) {
		this.text = text;
	}
	
	protected boolean isUi_show() {
		return ui_show;
	}

	protected void setUi_show(boolean ui_show) {
		this.ui_show = ui_show;
	}
}

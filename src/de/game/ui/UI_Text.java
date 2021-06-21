package de.game.ui;

import java.awt.Font;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.geom.Vector2f;

@SuppressWarnings("deprecation")
public class UI_Text implements UI_Base {

	/** Text to show */
	private String text = "";
	
	/** Position */
	private float x = 0f;
	private float y = 0f;
	
	/** Color */
	private Color color = null;
	
	/** Show text */
	private boolean showText = true;
	
	/** Font size */
	private Font font = new Font("Calibri", Font.BOLD, 20);
	private TrueTypeFont ttf = new TrueTypeFont(this.font, true);
	
	/**
	 * Creates an UI_Text
	 * @param text Text that you want to be displayed
	 * @param position Position given by a Slick2D Vector
	 */
	public UI_Text(String text, Vector2f position) {
		this.setText(text);
		this.setX(position.getX());
		this.setY(position.getY());
	}
	
	/**
	 * Creates an UI_Text
	 * @param text Text that you want to be displayed
	 * @param x x axe of the text
	 * @param y y axe of the text
	 */
	public UI_Text(String text, float x, float y) {
		this.setText(text);
		this.setX(x);
		this.setY(y);
	}
	
	/**
	 * Creates an UI_Text
	 * @param text Text that you want to be displayed
	 * @param x x axe of the text
	 * @param y y axe of the text
	 * @param color Slick2D Color
	 */
	public UI_Text(String text, float x, float y, Color color) {
		this.setText(text);
		this.setX(x);
		this.setY(y);
		this.setColor(color);
	}
	
	/**
	 * Creates an UI_Text
	 * @param text Text that you want to be displayed
	 * @param position Position given by a Slick2D Vector
	 * @param color Slick2D Color
	 */
	public UI_Text(String text, Vector2f position, Color color) {
		this.setText(text);
		this.setX(position.getX());
		this.setY(position.getY());
		this.setColor(color);
	}
	
	@Override
	public void render(GameContainer container, Graphics g) {
		if (this.showText) {
			if (this.color != null) {
				g.setColor(this.color);
			} else {
				g.setColor(Color.white);
			}
			this.ttf.drawString(this.x, this.y, this.text);
		}
	}
	
	@Override
	public void update(GameContainer container, int delta) {
		ttf = new TrueTypeFont(this.font, true);
	}

	/**
	 * @return the color
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * @param color the color to set
	 */
	public void setColor(Color color) {
		this.color = color;
	}

	/**
	 * @return the y
	 */
	public float getY() {
		return y;
	}

	/**
	 * @param y the y to set
	 */
	public void setY(float y) {
		this.y = y;
	}

	/**
	 * @return the x
	 */
	public float getX() {
		return x;
	}

	/**
	 * @param x the x to set
	 */
	public void setX(float x) {
		this.x = x;
	}
	
	/**
	 * @return position as float[]
	 */
	public float[] getPosition() {
		float[] position = {this.x, this.y};
		return position;
	}
	
	/**
	 * @return position as vector
	 */
	public Vector2f getVecPosition() {
		return new Vector2f(this.x, this.y);
	}

	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}

	/**
	 * @param text the text to set
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * @return the showMessage
	 */
	public boolean isShowMessage() {
		return showText;
	}

	/**
	 * @param showMessage the showMessage to set
	 */
	public void setShowMessage(boolean showMessage) {
		this.showText = showMessage;
	}

	/**
	 * @return the fontSize
	 */
	public Font getFont() {
		return this.font;
	}

	/**
	 * @param fontSize the fontSize to set
	 */
	public void setFont(Font font) {
		this.font = font;
	}

	/**
	 * @return the ttf
	 */
	public TrueTypeFont getTtf() {
		return ttf;
	}

	/**
	 * @param ttf the ttf to set
	 */
	public void setTtf(TrueTypeFont ttf) {
		this.ttf = ttf;
	}
}

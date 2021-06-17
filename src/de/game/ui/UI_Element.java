package de.game.ui;

import org.newdawn.slick.Input;

public abstract class UI_Element implements UI_Base {

	/** X-Position */
	protected float x = 0f;
	/** Y-Position */
	protected float y = 0f;
	
	/** Show UI Element */
	protected boolean showUI = true;
	
	/** Input */
	protected Input input;
	
	/**
	 * 
	 * @param x X-Achse position
	 * @param y Y-Achse position
	 */
	protected UI_Element(float x, float y, Input input) {
		this.x = x;
		this.y = y;
		this.input = input;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public boolean isShowUI() {
		return showUI;
	}

	public void setShowUI(boolean showUI) {
		this.showUI = showUI;
	}
}

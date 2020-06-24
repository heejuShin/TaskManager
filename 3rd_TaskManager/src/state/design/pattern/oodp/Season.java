package state.design.pattern.oodp;

import java.awt.Color;

public class Season {
	private Background background;
	
	public Season() {
		this.background=new Spring();
	}
	
	public Color setBackground(Background background) {
		this.background=background;
		return background.changeBackground();
	}
	
	public void changeBackground() {
		background.changeBackground();
	}
}

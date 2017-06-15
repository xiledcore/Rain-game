package rain.main;

import rain.display.Display;

public class Handler {

	private Game game;
	private Display display;
	
	public Handler(Game game) {
		this.game = game;
		display = game.getDisplay();
	}
	
	public Display getDisplay() {
		return display;
	}
	
	public int getWidth() {
		return display.getWidth();
	}
	
	public int getHeight() {
		return display.getHeight();
	}
	
}

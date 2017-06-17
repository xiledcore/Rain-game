package rain.states;

import java.awt.Graphics;

import rain.main.Handler;

public class StateManager {

	public static final int MENU_STATE = 0;
	public static final int GAME_STATE = 1;
	
	private State currentState = null;
	
	private Handler handler;
	
	public StateManager(Handler handler, int state) {
		this.handler = handler;
		setState(state);
	}
	
	public StateManager(Handler handler) {
		this.handler = handler;
	}
	
	public void setState(int state) {
		switch(state) {
			case MENU_STATE:
				currentState = new MenuState(handler);
				break;
			case GAME_STATE:
				currentState = new GameState(handler);
				break;				
		}
	}
	
	public State getState() {
		return currentState;
	}
	
	public boolean isStateActive() {
		return currentState != null;
	}
	
	public void update() {
		currentState.update();
	}
	
	public void render(Graphics g) {
		currentState.render(g);
	}
	
}

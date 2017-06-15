package rain.states;

import java.awt.Graphics;

import rain.main.Handler;

public abstract class State {

	public static State currentState = null;
	
	public static void setState(State state) {
		currentState = state;
	}
	
	public static State getState() {
		return currentState;
	}
	
	public static boolean isActive() {
		return currentState != null;
	}
	
	protected Handler handler;
	
	public State(Handler handler) {
		this.handler = handler;
	}
	
	public abstract void update();
	public abstract void render(Graphics g);
	
}

package rain.states;

import java.awt.Graphics;

import rain.levels.LevelManager;
import rain.main.Handler;
import rain.task.TaskManager;

public class GameState implements State {
	
	private Handler handler;
	private LevelManager lm;
	public GameState(Handler handler) {
		this.handler = handler;
		lm = new LevelManager(handler);
		this.handler.setLevelManager(lm);
	}

	@Override
	public void update() {
		lm.update();
		//handler.getCamera().move(64, 64);
	}

	@Override
	public void render(Graphics g) {
		lm.render(g);
	}

}

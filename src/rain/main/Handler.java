package rain.main;

import rain.camera.Camera;
import rain.display.Display;
import rain.entities.EntityManager;
import rain.input.KeyManager;
import rain.levels.LevelManager;
import rain.task.TaskManager;

public class Handler {

	private Game game;
	private Display display;
	private LevelManager lm;
	
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
	
	public Camera getCamera() {
		return game.getCamera();
	}
	
	public TaskManager getTaskManager() {
		return game.getTaskManager();
	}
	
	public KeyManager getKeyManager() {
		return game.getKeyManager();
	}
	
	public EntityManager getEntityManager() {
		return lm.getEntityManager();
	}
	
	public void setLevelManager(LevelManager lm) {
		this.lm = lm;
	}
	
	public LevelManager getLevelManager() {
		return lm;
	}
}

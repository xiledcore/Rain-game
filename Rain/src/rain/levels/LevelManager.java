package rain.levels;

import java.awt.Color;
import java.awt.Graphics;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import rain.camera.Camera;
import rain.entities.EntityManager;
import rain.entities.living.Player;
import rain.entities.living.enemies.Enemy;
import rain.entities.objects.PortalObject;
import rain.graphics.Assets;
import rain.main.Handler;
import rain.task.AnimationTask;
import rain.task.TaskManager;
import rain.tiles.Tile;
import rain.utils.Utils;

public class LevelManager {

	private Handler handler;
	
	private List<Level> levels = new ArrayList<>();
	private int currentLevelIndex = 0;
	private Level currentLevel;
	
	private int[][] currentGrid;
	
	private Player player;
	private EntityManager entityManager;
	//private ItemManager itemManager;
	private TaskManager taskManager;
	
	private Camera camera;
	
	public LevelManager(Handler handler) {
		this.handler = handler;
		loadLevels();
		currentLevel = levels.get(currentLevelIndex);
		loadLevel();
		taskManager = handler.getTaskManager();
	}
	
	private void loadLevels() {
		File[] levelFiles = Utils.getFilesInFolder("res/levels/");
		for(File f : levelFiles) {
			List<String> contents = Utils.getLevelContents(f);
			levels.add(new Level(f.getName(), contents));
		}
	}
	
	public void loadLevel() {
		currentLevel = levels.get(currentLevelIndex);
		currentGrid = currentLevel.getMapGrid();
		player = new Player(handler, null, currentLevel.getPlayerSpawnX(), currentLevel.getPlayerSpawnY());
		entityManager = new EntityManager(handler, player);
		camera = handler.getCamera();
		camera.setCenterEntity(player);
		placeEnemies();
	}
	
	public void update() {
		entityManager.update();
		camera.update();
	}
	
	public void render(Graphics g) {
		for(int y = camera.getYStart(); y < camera.getYEnd(); y++) {
			for(int x = camera.getXStart(); x < camera.getXEnd(); x++) {
				currentLevel.getTile(x, y).render(g, (int) (x * Tile.TILE_WIDTH - camera.getXOffset()), 
						(int) (y * Tile.TILE_HEIGHT - camera.getYOffset()));
			}
		}
		entityManager.render(g);
	}
	
	private void placeEnemies() {
		Map<Integer, String[]> enemies = currentLevel.getEnemies();
		for(String[] enemy : enemies.values()) {
			int x = Utils.parseInt(enemy[1]);
			int y = Utils.parseInt(enemy[2]);
			entityManager.addEntity(Enemy.createEnemy(handler, Utils.parseInt(enemy[0]), x, y, 3));
			//TODO create enemy system with enemies static list with respective id - like tiles ids static list
		}
	}
	
	private void placeItems() {
	}
	
	private void placeObjects() {
		
	}
	
	public void loadNextLevel() {
		currentLevelIndex++;
		loadLevel();
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public EntityManager getEntityManager() {
		return entityManager;
	}
	
	public Level getCurrentLevel() {
		return currentLevel;
	}
	
	public List<Level> getLevels() {
		return levels;
	}
	
}

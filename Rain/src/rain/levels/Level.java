package rain.levels;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import rain.tiles.Tile;
import rain.utils.Utils;

public class Level {

	private String name = "";
	private int number;
	
	private List<String> contents;
	
	private int levelWidth;
	private int levelHeight;
	private int levelWidthTileCount;
	private int levelHeightTileCount;
	
	private String[] player;
	private String[] endPoint;
	private Map<Integer, String[]> enemies = new LinkedHashMap<>();
	private Map<Integer, String[]> items = new LinkedHashMap<>();
	private Map<Integer, String[]> obstacles = new LinkedHashMap<>();
	private String[] map;
	
	int[][] mapGrid;
	
	public Level(String name, List<String> contents) {
		this.name = name.substring(0, name.length() - 4);
		number = Utils.parseInt(this.name.substring(this.name.length() - 1));
		this.contents = contents;
		
		organizeContents();
	}
	
	private void organizeContents() {
		player = contents.get(0).split(",");
		endPoint = contents.get(1).split(",");
		String[] tempEnemies = contents.get(2).split(";");
		int count = 0;
		for(String e : tempEnemies) {
			String[] tempEnemy = e.split(":");
			String[] position = tempEnemy[1].split(",");
			String[] enemy = new String[3];
			enemy[0] = tempEnemy[0];
			enemy[1] = position[0];
			enemy[2] = position[1];
			enemies.put(count, enemy);
			count++;
			//enemies.put(Utils.parseInt(tempEnemy[0]), tempEnemy[1].split(","));
		}
		System.out.println(enemies.keySet().size());
		String[] tempItems = contents.get(3).split(";");
		for(String e : tempItems) {
			String[] tempItem = e.split(":");
			items.put(Utils.parseInt(tempItem[0]), tempItem[1].split(","));
		}
		contents.remove(3);
		contents.remove(2);
		contents.remove(1);
		contents.remove(0);
		map = contents.toArray(new String[0]);
		levelWidthTileCount = map[0].length();
		levelHeightTileCount = map.length;
		levelWidth = levelWidthTileCount * Tile.TILE_WIDTH;
		levelHeight = levelHeightTileCount * Tile.TILE_HEIGHT;
		
		setUpGrid();
	}
	
	private void setUpGrid() {
		mapGrid = new int[levelHeightTileCount][levelWidthTileCount];
		
		for(int y = 0; y < levelHeightTileCount; y++) {
			String line = map[y];
			for(int x = 0; x < levelWidthTileCount; x++) {
				mapGrid[y][x] = Utils.parseInt(String.valueOf(line.charAt(x)));
			}
		}
	}
	
	public int getNumber() {
		return number;
	}
	
	public String getName() {
		return name;
	}
	
	public String[] getMap() {
		return map;
	}
	
	public int[][] getMapGrid() {
		return mapGrid;
	}
	
	public Tile getTile(int x, int y) {
		return Tile.TILES[mapGrid[y][x]];
	}
	
	public int getLevelWidthTileCount() {
		return levelWidthTileCount;
	}
	
	public int getLevelHeightTileCount() {
		return levelHeightTileCount;
	}
	
	public int getLevelWidth() {
		return levelWidth;
	}
	
	public int getLevelHeight() {
		return levelHeight;
	}
	
	public Map<Integer, String[]> getEnemies() {
		return enemies;
	}
	
	public Map<Integer, String[]> getItems() {
		return items;
	}
	
	public String[] getPlayerDetails() {
		return player;
	}
	
	public int getPlayerSpawnX() {
		return Utils.parseInt(player[0]);
	}
	
	public int getPlayerSpawnY() {
		return Utils.parseInt(player[1]);
	}
	
	public String[] getEndPoint() {
		return endPoint;
	}
	
	public int getEndPointX() {
		return Utils.parseInt(endPoint[0]);
	}
	
	public int getEndPointY() {
		return Utils.parseInt(endPoint[1]);
	}
	
	public int[] getEnemiesIds() {
		int[] ids = new int[enemies.keySet().size()];
		int count = 0;
		for(int id : enemies.keySet()) {
			System.out.println(id);
			ids[count] = id;
			count++;
		}
		return ids;
	}
	
	public int[] getItemsIds() {
		int[] ids = new int[items.keySet().size()];
		int count = 0;
		for(int id : items.keySet()) {
			ids[count] = id;
			count++;
		}
		return ids;
	}
	
	public String[] getEnemyDetails(int id) {
		return enemies.get(id);
	}
	
	public String[] getItemsDetails(int id) {
		return items.get(id);
	}
	
	public int getEnemyX(int id) {
		return Utils.parseInt(enemies.get(id)[0]);
	}
	
	public int getEnemyY(int id) {
		return Utils.parseInt(enemies.get(id)[1]);
	}
	
	public int getItemX(int id) {
		return Utils.parseInt(items.get(id)[0]);
	}
	
	public int getItemY(int id) {
		return Utils.parseInt(items.get(id)[1]);
	}
}

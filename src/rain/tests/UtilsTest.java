package rain.tests;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import rain.levels.Level;
import rain.utils.Utils;

public class UtilsTest {

	public static List<Level> levels = new ArrayList<>();
	
	public static void main(String[] args) {
		File[] folder = Utils.getFilesInFolder("res/levels/");
		for(File f : folder) {
			List<String> level = Utils.getLevelContents(f);
			levels.add(new Level(f.getName(), level));
		}
		for(Level l : levels) {
			System.out.println("Name: " + l.getName() + " - Number: " + l.getNumber());
			System.out.println("Player - Details: (" + l.getPlayerSpawnX() + ", " + l.getPlayerSpawnY() + ")");
			System.out.println("End Point - Details: (" + l.getEndPointX() + ", " + l.getEndPointY() + ")");
			
			System.out.println("Enemies:");
			for(int eid : l.getEnemiesIds()) {
				System.out.println("\tEnemy ID: " + eid + " - Details: (" + l.getEnemyX(eid) + ", " + l.getEnemyY(eid) + ")");
			}
			
			System.out.println("Items:");
			for(int iid : l.getItemsIds()) {
				System.out.println("\tItem ID: " + iid + " - Details: (" + l.getItemX(iid) + ", " + l.getItemY(iid) + ")");
			}
			
			System.out.println("Map:");
			System.out.println("\tDetails - Width: " + l.getLevelWidth() + ", Height: " + l.getLevelHeight());
			/*for(String mline : l.getMap()) {
				System.out.println("\t" + mline);
			}*/
			int[][] grid = l.getMapGrid();
			for(int y = 0; y < grid.length; y++) {
				for(int x = 0; x < grid[y].length; x++) {
					System.out.print(grid[y][x]);
				}
				System.out.println();
			}
		}
	}
	
	public static void organizeLevelContents() {
		
	}
	
}

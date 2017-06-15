package rain.tiles;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Tile {
	
	public static Tile[] TILES = new Tile[256];
	public static Tile dirt = new DirtTile(0);
	public static Tile grass = new GrassTile(1);
	public static Tile brick = new BrickTile(2);

	protected BufferedImage texture;
	protected int id;
	
	public static final int TILE_WIDTH = 64,
							TILE_HEIGHT = 64;
	
	public Tile(BufferedImage texture, int id) {
		this.texture = texture;
		this.id = id;
		TILES[id] = this;
	}
	
	public void update() {}
	
	public void render(Graphics g, int x, int y) {
		g.drawImage(texture, x, y, TILE_WIDTH, TILE_HEIGHT, null);
	}
	
	public boolean isSolid() {
		return false;
	}
	
	public BufferedImage getTexture() {
		return texture;
	}
	
	public int getID() {
		return id;
	}
	
}

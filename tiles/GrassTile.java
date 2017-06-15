package rain.tiles;

import java.awt.image.BufferedImage;

import rain.graphics.Assets;

public class GrassTile extends Tile {

	public GrassTile(int id) {
		super(Assets.grass, id);
	}
	
	public boolean isSolid() {
		return false;
	}

}

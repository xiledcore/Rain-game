package rain.tiles;

import java.awt.image.BufferedImage;

import rain.graphics.Assets;

public class BrickTile extends Tile {

	public BrickTile(int id) {
		super(Assets.brick, id);
	}

	public boolean isSolid() {
		return true;
	}
	
}

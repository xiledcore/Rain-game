package rain.tiles;

import java.awt.image.BufferedImage;

import rain.graphics.Assets;

public class DirtTile extends Tile {

	public DirtTile(int id) {
		super(Assets.dirt, id);
	}
	
	public boolean isSolid() {
		return false;
	}

}

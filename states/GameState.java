package rain.states;

import java.awt.Graphics;

import rain.graphics.Assets;
import rain.main.Handler;
import rain.tiles.Tile;

public class GameState extends State {
	
	public GameState(Handler handler) {
		super(handler);
	}

	@Override
	public void update() {
		
	}

	@Override
	public void render(Graphics g) {
		for(int i = 0; i < 3; i++) {
			Tile tile = Tile.TILES[i];
			tile.render(g, i * 64, 0);
		}
	}

}

package rain.hud;

import java.awt.Graphics;

import rain.entities.Entity;
import rain.graphics.Assets;

public class HUD {

	public static final int HEART_WIDTH = 32,
							HEART_HEIGHT = 32;
	
	private Entity e;
	
	private int x, y;
	private int spacing = 37;
	
	public HUD(Entity e) {
		this.e = e;
		x = 10;
		y = 10;
	}
	
	public void update() {
		
	}
	
	public void draw(Graphics g) {
		for(int i = 0; i < e.getHealth(); i++) {
			g.drawImage(Assets.heart, x + (i > 0 ? (spacing * i) : 0), y, HEART_WIDTH, HEART_HEIGHT, null);
		}
	}
	
}

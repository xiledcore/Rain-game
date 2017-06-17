package rain.graphics;

import java.awt.image.BufferedImage;

public class Assets {

	public static SpriteSheet ss, player_walk, slugger_walk;
	
	public static BufferedImage dirt, grass, brick, player_idle, heart;
	public static BufferedImage[] player_up, player_down, player_left, player_right, slugger;
	
	public static void init() {
		//sprite sheets
		ss = new SpriteSheet(ImageLoader.loadImage("/textures/final_sheet.png"));
		player_walk = new SpriteSheet(ImageLoader.loadImage("/textures/player_walk.png"));
		slugger_walk = new SpriteSheet(ImageLoader.loadImage("/textures/slugger.gif"));
		
		//tiles
		dirt = ss.crop(32, 0, 32, 32);
		grass = ss.crop(32 * 2, 0, 32, 32);
		brick = ss.crop(32 * 3, 0, 32, 32);
		player_idle = player_walk.crop(0, 0, 64, 64);
		
		//items
		heart = ss.crop(0, 32 * 2, 32, 32);
		
		//sprites for animations
		player_up = new BufferedImage[4];
		player_down = new BufferedImage[4];
		player_left = new BufferedImage[4];
		player_right = new BufferedImage[4];
		
		player_down[0] = player_idle;
		player_down[1] = player_walk.crop(64, 0, 64, 64);
		player_down[2] = player_walk.crop(64 * 2, 0, 64, 64);
		player_down[3] = player_walk.crop(64 * 3, 0, 64, 64);
		
		player_left[0] = player_walk.crop(0, 64, 64, 64);
		player_left[1] = player_walk.crop(64, 64, 64, 64);
		player_left[2] = player_walk.crop(64 * 2, 64, 64, 64);
		player_left[3] = player_walk.crop(64 * 3, 64, 64, 64);
		
		player_right[0] = player_walk.crop(0, 64 * 2, 64, 64);
		player_right[1] = player_walk.crop(64, 64 * 2, 64, 64);
		player_right[2] = player_walk.crop(64 * 2, 64 * 2, 64, 64);
		player_right[3] = player_walk.crop(64 * 3, 64 * 2, 64, 64);
		
		player_down[0] = player_walk.crop(0, 64 * 3, 64, 64);
		player_down[1] = player_walk.crop(64, 64 * 3, 64, 64);
		player_down[2] = player_walk.crop(64 * 2, 64 * 3, 64, 64);
		player_down[3] = player_walk.crop(64 * 3, 64 * 3, 64, 64);
		
		slugger = new BufferedImage[3];
		slugger[0] = slugger_walk.crop(0, 0, 30, 30);
		slugger[1] = slugger_walk.crop(30, 0, 30, 30);
		slugger[2] = slugger_walk.crop(60, 0, 30, 30);
		
	}
	
}

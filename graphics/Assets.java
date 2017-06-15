package rain.graphics;

import java.awt.image.BufferedImage;

public class Assets {

	public static SpriteSheet ss;
	
	public static BufferedImage dirt, grass, brick;
	
	public static void init() {
		ss = new SpriteSheet(ImageLoader.loadImage("/textures/final_sheet.png"));
		
		dirt = ss.crop(32, 0, 32, 32);
		grass = ss.crop(32 * 2, 0, 32, 32);
		brick = ss.crop(32 * 3, 0, 32, 32);
	}
	
}

package rain.entities.objects;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import rain.graphics.Animation;
import rain.graphics.Assets;
import rain.main.Handler;

public class PortalObject extends GameObject {
	
	private Animation anim;
	
	public PortalObject(Handler handler, BufferedImage texture, int x, int y,
			int width, int height) {
		super(handler, texture, x, y, width, height);
		init();
	}
	
	private void init() {
		BufferedImage[] frames = {texture, Assets.dirt};
		anim = new Animation(2, frames);
	}
	
	public Animation getAnimation() {
		return anim;
	}
	
	@Override
	public void update() {
		
	}

	@Override
	public void render(Graphics g) {
		
	}

}

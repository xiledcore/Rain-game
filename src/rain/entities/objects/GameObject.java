package rain.entities.objects;

import java.awt.image.BufferedImage;

import rain.entities.Entity;
import rain.graphics.Animation;
import rain.main.Handler;

public abstract class GameObject extends Entity {
	
	protected int id;

	public GameObject(Handler handler, BufferedImage texture, int x, int y,
			int width, int height) {
		super(handler, texture, x, y, width, height);
	}
	
	public abstract Animation getAnimation();
	
}

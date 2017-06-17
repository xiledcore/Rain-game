package rain.task;

import java.awt.Graphics;

import rain.entities.objects.GameObject;
import rain.graphics.Animation;


public class AnimationTask implements Runnable {
	
	private GameObject object;
	private Graphics g;
	
	public AnimationTask(GameObject object, Graphics g) {
		this.object = object;
		this.g = g;
	}
	
	@Override
	public void run() {
		Animation animation = object.getAnimation();
		animation.play(g, 20, 20, 32, 32, 500);
	}

}

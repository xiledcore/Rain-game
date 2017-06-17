package rain.entities.living.enemies;

import java.awt.image.BufferedImage;

import rain.entities.living.LivingEntity;
import rain.main.Handler;

public abstract class Enemy extends LivingEntity {
	
	public static final int SLUGGER = 0;

	protected int wanderingRange;
	
	public Enemy(Handler handler, BufferedImage texture, int x, int y, int width, int height) {
		super(handler, texture, x, y, width, height);
	}
	
	public Enemy(Handler handler, BufferedImage texture, int x, int y, int width, int height, int wanderingRange) {
		super(handler, texture, x, y, width, height);
		this.wanderingRange = wanderingRange;
	}
	
	public static Enemy createEnemy(Handler handler, int id, int x, int y, int wanderingRange) {
		switch(id) {
			case SLUGGER:
				return new Slugger(handler, x, y, wanderingRange);
		}
		return null;
	}
	
	public int getWanderingRange() {
		return wanderingRange;
	}
	
	public void setWanderingRange(int wanderingRange) {
		this.wanderingRange = wanderingRange;
	}
	
}

package rain.entities;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import rain.main.Handler;

public abstract class Entity {

	public static final int DEFAULT_HEALTH = 10;
	
	protected int width, height;
	protected int x, y;
	protected int health;
	protected BufferedImage texture;
	
	protected Rectangle bounds;
	
	protected boolean active = true;
	protected int lastHitDmg = 0;
	
	protected Handler handler;
	
	public Entity(Handler handler, BufferedImage texture, int x, int y, int width, int height) {
		this.handler = handler;
		this.texture = texture;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		health = DEFAULT_HEALTH;
		bounds = new Rectangle(x ,y , width, height);
	}
	
	public abstract void update();
	public abstract void render(Graphics g);

	public boolean checkEntityCollisions() {
		for(Entity e : handler.getEntityManager().getEntities()) {
			if(e.equals(this)) continue;
			if(e.getCollisionBounds().intersects(getCollisionBounds())) {
				return true;
			}
		}
		return false;
	}
	
	public boolean checkCollisionWith(Entity e) {
		if(e.equals(this)) return false;
		return e.getCollisionBounds().intersects(getCollisionBounds());
	}
	
	public Rectangle getCollisionBounds() {
		return new Rectangle(x + bounds.x, y + bounds.y, bounds.width, bounds.height);
	}
	
	public boolean isActive() {
		return active;
	}
	
	public void setActive(boolean active) {
		this.active = active;
	}
	
	public Rectangle getBounds() {
		return bounds;
	}
	
	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public BufferedImage getTexture() {
		return texture;
	}

	public void setTexture(BufferedImage texture) {
		this.texture = texture;
	}
	
}

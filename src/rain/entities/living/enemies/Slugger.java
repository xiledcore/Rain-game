package rain.entities.living.enemies;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

import rain.entities.living.Player;
import rain.graphics.Animation;
import rain.graphics.Assets;
import rain.main.Handler;
import rain.tiles.Tile;

public class Slugger extends Enemy {
	
	public static final int SLUGGER_WIDTH = 64,
							SLUGGER_HEIGHT = 64;

	private int delay, xTarget = 0;
	private long last, timer;
	private boolean move = false;
	private int dir = 0;
	private Random random;
	private Animation slugger_walk;
	
	private Player player = null;
	
	public Slugger(Handler handler, int x, int y, int wanderingRange) {
		super(handler, null, x, y, SLUGGER_WIDTH, SLUGGER_HEIGHT, wanderingRange);
		slugger_walk = new Animation(250, Assets.slugger);
		last = System.currentTimeMillis();
		delay = 2 * 1000;
		random = new Random();
		speed = 1;
		bounds.x = 10;
		bounds.y = 23;
		bounds.width = 43;
		bounds.height = 40;
	}

	@Override
	public void update() {
		wander();
		move();
		slugger_walk.update();
		if(checkPlayerTopCollision()) {
			if(checkCollisionWith(player)) {
				System.out.println("player hit");
				player.hurt(1);
			}
			System.out.println(player.getHealth());
			hurt(10);
		}
	}
	
	public boolean checkPlayerTopCollision() {
		if(player == null) player = handler.getLevelManager().getPlayer();
		if(player.getY() <= y + height && player.getY() + player.getHeight() >= y + height && player.getX() + player.getWidth() >= x && player.getX() <= x + width) {
			return true;
		}
		return false;
	}
	
	private int checkTileDirCollision() {
		if(collisionWithTile(x / Tile.TILE_WIDTH, y / Tile.TILE_HEIGHT) || 
				collisionWithTile(x / Tile.TILE_WIDTH, (y + height) / Tile.TILE_HEIGHT)) {
			//left collision
			return 0;
		} else if(collisionWithTile((x + width) / Tile.TILE_WIDTH, y / Tile.TILE_HEIGHT) || 
					collisionWithTile((x + width) / Tile.TILE_WIDTH, (y + height) / Tile.TILE_HEIGHT)) {
			//right collision
			return 1;
		}
		return -1;
	}
	
	private void wander() {
		xVelocity = 0;
		if(Math.abs(xTarget) - Math.abs(x) <= 0) {
			move = false;
		}
		if(move) {
			switch(checkTileDirCollision()) {
				case -1:
					break;
				case 0:
					dir = 0;
					break;
				case 1:
					dir = 1;
					break;
			}
			xVelocity = (dir == 0 ? speed : -speed);
		}
		if(!isMoving()) {
			timer += System.currentTimeMillis() - last;
			last = System.currentTimeMillis();
			if(timer >= delay) {
				//TODO set x target, perform movement - if colliding change direction(right>left, left>right)
				xTarget = x + random.nextInt(wanderingRange * Tile.TILE_WIDTH);
				System.out.println(xTarget + ", " + x);
				move = true;
				timer = 0;
			}
		}
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(slugger_walk.getCurrentFrame(), x - handler.getCamera().getXOffset(), y - handler.getCamera().getYOffset(), width, height, null);
		//g.fillRect((x + bounds.x) - handler.getCamera().getXOffset(), (y + bounds.y) - handler.getCamera().getYOffset(), bounds.width, bounds.height);
	}
	
	public BufferedImage getCurrentFrame() {
		if(!isMoving()) {
			System.out.println("T");
			return slugger_walk.getFrame(0);
		}
		return slugger_walk.getCurrentFrame();
	}

	@Override
	public void die() {
		System.out.println("Dead");
	}

}

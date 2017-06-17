package rain.entities.living;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import rain.graphics.Animation;
import rain.graphics.Assets;
import rain.hud.HUD;
import rain.input.KeyManager;
import rain.main.Handler;

public class Player extends LivingEntity {

	public static final int PLAYER_WIDTH = 64;
	public static final int PLAYER_HEIGHT = 64;
	private Animation walkingRight, walkingLeft;
	
	private int maxHP = 3;
	
	private HUD hud;
	
	public Player(Handler handler, BufferedImage texture, int x, int y) {
		super(handler, texture, x, y, PLAYER_WIDTH, PLAYER_HEIGHT);
		walkingRight = new Animation(250, Assets.player_right);
		walkingLeft = new Animation(250, Assets.player_left);
		health = maxHP;
		hud = new HUD(this);
		bounds.x = 22;
		bounds.y = 44;
		bounds.width = 19;
		bounds.height = 16;
	}

	@Override
	public void update() {
		hud.update();
		handleInput();
		move();
		fall();
		checkFinish();
		walkingRight.update();
		walkingLeft.update();
	}
	
	private void checkFinish() {
		//player reached end point(possibly tile for tool choosing tile)
	}
	
	private void handleInput() {
		xVelocity = 0;
		yVelocity = 0;
		
		KeyManager km = handler.getKeyManager();
		
		if(km.left) {
			xVelocity = -speed;
		}
		if(km.right) {
			xVelocity = speed;
		}
		if(km.down) {
			health--;
		}
		if(km.up) {
			health++;
		}
		if(km.space) {
			jump();
		}
	}
	
	public void addHP(int hp) {
		if(health == 3) return;
		if(hp + health > maxHP) {
			hp = maxHP - health;
		}
		health += hp;
	}

	@Override
	public void render(Graphics g) {
		hud.draw(g);
		g.drawImage(getCurrentAnimationFrame(), (int) (x - handler.getCamera().getXOffset()), (int) (y - handler.getCamera().getYOffset()), width, height, null);
		//g.fillRect((x + bounds.x) - handler.getCamera().getXOffset(), (y + bounds.y) - handler.getCamera().getYOffset(), bounds.width, bounds.height);
	}
	
	public BufferedImage getCurrentAnimationFrame() {
		if(xVelocity > 0) {
			return walkingRight.getCurrentFrame();
		} else if(xVelocity < 0) {
			return walkingLeft.getCurrentFrame();
		} else {
			return Assets.player_idle;
		}
	}

	@Override
	public void die() {
		
	}

}

package rain.entities.living;

import java.awt.image.BufferedImage;

import rain.entities.Entity;
import rain.main.Handler;
import rain.tiles.Tile;

public abstract class LivingEntity extends Entity {
	
	public static final int DEFAULT_SPEED = 3;
	public static final int DEFAULT_GRAVITY = 3;
	
	
	protected int speed;
	protected int xVelocity, yVelocity;
	protected int maxJumpingYOffset;
	protected int maxJumpingY;
	protected int gravity;
	protected boolean falling = true;
	protected boolean canJump = false;
	protected boolean jumping = false;
	protected boolean colliding = false;
	
	public LivingEntity(Handler handler, BufferedImage texture, int x, int y, int width, int height) {
		super(handler, texture, x, y, width, height);
		this.speed = DEFAULT_SPEED;
		this.gravity = DEFAULT_GRAVITY;
		maxJumpingYOffset = 100;
	}
	
	public abstract void die();
	
	public void move() {
		moveX();
		moveY();
	}
	
	public void moveX() {
		boolean movingRight = xVelocity > 0;
		
		for(int i = 0; i < Math.abs(xVelocity); i++) {
			if(movingRight) {
				if(collisionWithTile((int) ((x + bounds.x + bounds.width) / Tile.TILE_WIDTH), (int) ((y + bounds.y) / Tile.TILE_HEIGHT)) || 
						collisionWithTile((int) ((x + bounds.x + bounds.width) / Tile.TILE_WIDTH), (int) ((y + bounds.y + bounds.height) / Tile.TILE_HEIGHT))) {
					colliding = true;
					x -= 1;
					return;
				}
			} else {
				if(collisionWithTile((int) ((x + bounds.x) / Tile.TILE_WIDTH), (int) ((y + bounds.y) / Tile.TILE_HEIGHT)) || 
						collisionWithTile((int) ((x + bounds.x) / Tile.TILE_WIDTH), (int) ((y + bounds.y + bounds.height) / Tile.TILE_HEIGHT))) {
					colliding = true;
					x += 1;
					return;
				}
			}
			colliding = false;
			x += (movingRight ? 1 : -1);
		}
	}
	
	public void moveY() {
		if(this instanceof Player) {
			if(jumping) {
				yVelocity = -5;
				if(y <= maxJumpingY) {
					jumping = false;
					falling = true;
				}
			}
			fall();
		}
		boolean movingDown = yVelocity > 0;
		for(int i = 0; i < Math.abs(yVelocity); i++) {
			if(movingDown) {
				if(collisionWithTile((int) ((x + bounds.x) / Tile.TILE_WIDTH), (int) ((y + bounds.y + bounds.height) / Tile.TILE_HEIGHT)) ||
						collisionWithTile((int) ((x + bounds.x + bounds.width) / Tile.TILE_WIDTH),(int) ((y + bounds.y + bounds.height) / Tile.TILE_HEIGHT))) {
					canJump = true;
					falling = false;
					colliding = true;
					y -= 1;
					if(!jumping) {
						falling = true;
					}
					return;
				}
			} else {
				if(collisionWithTile((int) ((x + bounds.x) / Tile.TILE_WIDTH), (int) ((y + bounds.y) / Tile.TILE_WIDTH)) ||
						collisionWithTile((int) ((x + bounds.x + bounds.width) / Tile.TILE_WIDTH), (int) ((y + bounds.y) / Tile.TILE_HEIGHT))) {
					y += 1;
					colliding = true;
					falling = true;
					jumping = false;
					return;
				}
			}
			y += (movingDown ? 1 : -1);
		}
	}
	
	public void fall() {
		if(falling) {
			yVelocity = gravity;
		}
	}
	
	public void jump() {
		if(canJump) {
			canJump = false;
			maxJumpingY =  y - maxJumpingYOffset;
			jumping = true;
		}
	}
	
	public boolean collisionWithTile(int x, int y) {
		return handler.getLevelManager().getCurrentLevel().getTile(x, y).isSolid();
	}
	
	public boolean isMoving() {
		return xVelocity != 0 || yVelocity != 0;
	}
	
	public void hurt(int dmg) {
		health -= dmg;
		lastHitDmg = dmg;
		if(health <= 0) {
			active = false;
			die();
		}
	}
	
	public boolean isFalling() {
		return falling;
	}
	
	public boolean isJumping() {
		return jumping;
	}
	
	public double getGravity() {
		return gravity;
	}
	
	public void setGravity(int gravity) {
		this.gravity = gravity;
	}
	
	public int getSpeed() {
		return speed;
	}
	
	public void setSpeed(int speed) {
		this.speed = speed;
	}

}



/*public void moveX() {
	boolean movingRight = xVelocity > 0;
	
	for(int i = 0; i < Math.abs(xVelocity); i++) {
		if(movingRight) {
			if(collisionWithTile((int) ((x + width) / Tile.TILE_WIDTH), (int) (y / Tile.TILE_HEIGHT)) || 
					collisionWithTile((int) ((x + width) / Tile.TILE_WIDTH), (int) ((y + height) / Tile.TILE_HEIGHT))) {
				colliding = true;
				x -= 1;
				return;
			}
		} else {
			if(collisionWithTile((int) (x / Tile.TILE_WIDTH), (int) (y / Tile.TILE_HEIGHT)) || 
					collisionWithTile((int) (x / Tile.TILE_WIDTH), (int) ((y + height) / Tile.TILE_HEIGHT))) {
				colliding = true;
				x += 1;
				return;
			}
		}
		colliding = false;
		x += (movingRight ? 1 : -1);
	}
}*/

/*public void moveY() {
		if(this instanceof Player) {
			if(jumping) {
				yVelocity = -5;
				if(y <= maxJumpingY) {
					jumping = false;
					falling = true;
				}
			}
			fall();
		}
		boolean movingDown = yVelocity > 0;
		for(int i = 0; i < Math.abs(yVelocity); i++) {
			if(movingDown) {
				if(collisionWithTile((int) (x / Tile.TILE_WIDTH), (int) ((y + height) / Tile.TILE_HEIGHT)) ||
						collisionWithTile((int) ((x + width) / Tile.TILE_WIDTH),(int) ((y + height) / Tile.TILE_HEIGHT))) {
					canJump = true;
					falling = false;
					colliding = true;
					y -= 1;
					if(!jumping) {
						falling = true;
					}
					return;
				}
			} else {
				if(collisionWithTile((int) (x / Tile.TILE_WIDTH), (int) (y / Tile.TILE_WIDTH)) ||
						collisionWithTile((int) ((x + width) / Tile.TILE_WIDTH), (int) (y / Tile.TILE_HEIGHT))) {
					y += 1;
					colliding = true;
					falling = true;
					jumping = false;
					return;
				}
			}
			y += (movingDown ? 1 : -1);
		}
	}*/

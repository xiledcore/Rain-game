package rain.camera;

import rain.entities.Entity;
import rain.main.Handler;
import rain.tiles.Tile;

public class Camera {

	private Handler handler;
	private Entity centerEntity;
	private int xOffset = 0, yOffset = 0;
	private int xStart = 0, xEnd = 0, yStart = 0, yEnd = 0;
	
	public Camera(Handler handler) {
		this.handler = handler;
	}
	
	public Camera(Handler handler, Entity e) {
		this.handler = handler;
		centerEntity = e;
	}
	
	public void checkBlankSpace() {
		int widthLimit = handler.getLevelManager().getCurrentLevel().getLevelWidth() - handler.getWidth();
		int heightLimit = handler.getLevelManager().getCurrentLevel().getLevelHeight() - handler.getHeight();
		if(xOffset < 0) {
			xOffset = 0;
		} else if(xOffset > widthLimit) {
			xOffset = widthLimit;
		}
		
		if(yOffset < 0) {
			yOffset = 0;
		} else if(yOffset > heightLimit) {
			yOffset = heightLimit;
		}
	}
	
	public void update() {
		xOffset = centerEntity.getX() - (handler.getWidth() / 2) + (centerEntity.getWidth() / 2);
		yOffset = centerEntity.getY() - (handler.getHeight() / 2) + (centerEntity.getHeight() / 2);
		checkBlankSpace();
		xStart = (int) Math.max(0, xOffset / Tile.TILE_WIDTH);
		xEnd = (int) Math.min(handler.getLevelManager().getCurrentLevel().getLevelWidthTileCount(), (xOffset + handler.getWidth()) / Tile.TILE_WIDTH + 2);
		yStart = (int) Math.max(0, yOffset / Tile.TILE_HEIGHT);
		yEnd = (int) Math.min(handler.getLevelManager().getCurrentLevel().getLevelHeightTileCount(), (yOffset + handler.getHeight()) / Tile.TILE_HEIGHT + 2);
	}
	
	public void setCenterEntity(Entity e) {
		centerEntity = e;
	}
	
	public void move(int x, int y) {
		xOffset += x;
		yOffset += y;
		checkBlankSpace();
	}
	
	public int getXOffset() {
		return xOffset;
	}
	
	public int getYOffset() {
		return yOffset;
	}
	
	public int getXStart() {
		return xStart;
	}
	
	public int getXEnd() {
		return xEnd;
	}
	
	public int getYStart() {
		return yStart;
	}
	
	public int getYEnd() {
		return yEnd;
	}
	
}

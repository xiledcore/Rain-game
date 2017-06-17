package rain.graphics;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Animation {

	public static final int INFINITE = -1;
	
	private int delay, index;
	private long timer, lastTime;
	private BufferedImage[] frames;
	
	public Animation(int delay, BufferedImage[] frames) {
		this.delay = delay;
		this.frames = frames;
		index = 0;
		lastTime = System.currentTimeMillis();
	}
	
	public void update() {
		timer += System.currentTimeMillis() - lastTime;
		lastTime = System.currentTimeMillis();
		
		if(timer >= delay) {
			index++;
			timer = 0;
			if(index >= frames.length) index = 0;
		}
	}
	
	public BufferedImage getFrame(int index) {
		if(frames[index] != null) {
			return frames[index];
		}
		return null;
	}
	
	public BufferedImage getCurrentFrame() {
		return frames[index];
	}
	
	public void play(Graphics g, int x, int y, int w, int h, int times) {
		boolean play = true;
		int played = 0;
		while(play) {
			update();
			
			g.drawImage(getCurrentFrame(), x, y, w, h, null);
			if(index == frames.length - 1) {
				played++;
			}
			if(played == times) play = false;
		}
	}
	
}

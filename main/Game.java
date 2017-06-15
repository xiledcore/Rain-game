package rain.main;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import rain.display.Display;
import rain.graphics.Assets;
import rain.states.GameState;
import rain.states.State;

public class Game implements Runnable {
	
	public static final int FPS = 60;
	public static final double INTERVAL = 1000000000 / FPS;

	private Display display;
	private Thread thread = null;
	
	private boolean running = false;
	
	private BufferStrategy bs;
	private Graphics g;
	
	private Handler handler;
	
	private GameState gameState;

	public Game() {}
	
	public void init() {
		display = new Display();
		display.getCanvas().createBufferStrategy(3);
		bs = display.getCanvas().getBufferStrategy();
		
		Assets.init();
		
		handler = new Handler(this);
		
		gameState = new GameState(handler);
		State.setState(gameState);
	}
	
	public void update() {
		if(State.isActive()) {
			State.getState().update();
		}
	}
	
	public void render() {
		g = bs.getDrawGraphics();
		g.clearRect(0, 0, display.getWidth(), display.getHeight());

		if(State.isActive()) {
			State.getState().render(g);
		}
		
		bs.show();
		g.dispose();
	}
	
	@Override
	public void run() {
		init();
		
		long last = System.nanoTime();
		double delta = 0;
		long timer = 0;
		int updates = 0;
		
		while(running) {
			long now = System.nanoTime();
			delta += (now - last) / INTERVAL;
			timer += now - last;
			last = now;
			
			if(delta >= 1) {
				update();
				delta--;
				updates++;
			}
			
			render();
			
			if(timer >= 1000000000) {
				System.out.println("FPS: " + updates);
				updates = 0;
				timer = 0;
			}
		}
	}
	
	public void start() {
		if(running)
			return;
		if(thread == null)
			thread = new Thread(this);
		running = true;
		thread.start();
	}
	
	public void stop() {
		if(!running)
			return;
		running = false;
		try {
			thread.join();
		} catch(InterruptedException ie) {
			ie.printStackTrace();
		}
	}
	
	public Display getDisplay() {
		return display;
	}
	
	public static void main(String[] args) {
		Game game = new Game();
		game.start();
	}

}

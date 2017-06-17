package rain.main;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import rain.camera.Camera;
import rain.display.Display;
import rain.graphics.Assets;
import rain.input.KeyManager;
import rain.states.StateManager;
import rain.task.TaskManager;

public class Game implements Runnable {
	
	public static final int FPS = 60;
	public static final double INTERVAL = 1000000000 / FPS;
	public static final int numberOfThreads = Runtime.getRuntime().availableProcessors();

	private Display display;
	private Thread thread = null;
	
	private boolean running = false;
	
	private BufferStrategy bs;
	private Graphics g;
	
	private Handler handler;
	
	private StateManager sm;
	
	private KeyManager km;

	private Camera camera;
	
	private final TaskManager taskManager = new TaskManager(numberOfThreads);
	
	public Game() {}
	
	/**
	 * Initializes everything needed for the game to start and work
	 * @return void
	 */
	public void init() {
		display = new Display();
		display.getCanvas().createBufferStrategy(3);
		bs = display.getCanvas().getBufferStrategy();
		km = new KeyManager();
		display.getJFrame().addKeyListener(km);
		
		Assets.init();
		
		handler = new Handler(this);
		camera = new Camera(handler);
		sm = new StateManager(handler, StateManager.GAME_STATE);
	}
	
	/**
	 * Called 60 times per second, updates every entity, item, state, managers and so on
	 * @return void
	 */
	public void update() {
		km.update();
		if(sm.isStateActive()) {
			sm.update();
		}
	}
	
	/**
	 * Called maximum times the individual computer can, draws all graphics to the window
	 * @return void
	 */
	public void render() {
		g = bs.getDrawGraphics();
		g.clearRect(0, 0, display.getWidth(), display.getHeight());

		if(sm.isStateActive()) {
			sm.render(g);
		}
		
		bs.show();
		g.dispose();
	}
	
	/**
	 * Starts with the main game thread, holds the game loop, fps counter
	 * @return void
	 */
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
	
	/**
	 * returns {@code KeyManager}
	 * @return KeyManager km
	 */
	public KeyManager getKeyManager() {
		return km;
	}
	
	/**
	 * returns ({@code Camera}
	 * @return Camera camera
	 */
	public Camera getCamera() {
		return camera;
	}
	
	/**
	 * returns {@code Display}
	 * @return Display display
	 */
	public Display getDisplay() {
		return display;
	}
	
	public TaskManager getTaskManager() {
		return taskManager;
	}
	
	public static void main(String[] args) {
		Game game = new Game();
		game.start();
	}

}

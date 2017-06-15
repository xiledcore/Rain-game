package rain.display;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Display extends JFrame {

	public static final int WIDTH = 400;
	public static final int HEIGHT = WIDTH / 12 * 9;
	public static final int SCALE = 2;
	public static final String TITLE = "Rain";
	
	private Canvas canvas;
	
	public Display() {
		setTitle(TITLE);
		setSize(WIDTH * SCALE, HEIGHT * SCALE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		createCanvas();
		add(canvas);
		
		setResizable(false);
		setVisible(true);
		pack();
	}
	
	public void createCanvas() {
		canvas = new Canvas();
		canvas.setMinimumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		canvas.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		canvas.setMaximumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		canvas.setFocusable(false);
	}
	
	public JFrame getJFrame() {
		return this;
	}
	
	public Canvas getCanvas() {
		return canvas;
	}
	
	public int getWidth() {
		return WIDTH * SCALE;
	}
	
	public int getHeight() {
		return HEIGHT * SCALE;
	}
	
}

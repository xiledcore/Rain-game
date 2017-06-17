package tools;

import static tools.MapCreator.TILES_IN_HEIGHT;
import static tools.MapCreator.TILES_IN_WIDTH;

import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class Tile extends StackPane {
	
	public static Tile[][] TILES = new Tile[TILES_IN_HEIGHT][TILES_IN_WIDTH]; 
	
	private int x, y, w, h, i, j;
	private Text indexText = new Text();
	private Rectangle border;
	private WritableImage texture = null;
	private int textureID;
	private ImageView iv;
	private ImageView flag = null;
	
	public Tile(int x, int y, int w, int h, int i, int j) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.i = i;
		this.j = j;
		
		border = new Rectangle(w, h);
		border.setFill(null);
		border.setStroke(Color.BLACK);
		
		indexText.setText("(" + i + ", " + j + ")");
		
		iv = new ImageView();
		
		getChildren().addAll(border, iv);
		
		setTranslateX(x);
		setTranslateY(y);
		
		TILES[i][j] = this;
	}
	
	public void resetTexture(WritableImage wi, int id) {
		texture = wi;
		textureID = id;
		getChildren().remove(flag);
		flag = null;
		iv.setImage(texture);
	}
	
	public void setTexture(WritableImage wi, int id) {
		if(id == 3) {
			flag = new ImageView(wi);
			getChildren().add(flag);
		} else {
			texture = wi;
			textureID = id;
			iv.setImage(texture);
		}
	}
	
	public boolean hasFlag() {
		return flag != null;
	}
	
	public int getTextureId() {
		return textureID;
	}
	
	public void addIndexText() {
		getChildren().add(indexText);
	}
	
	public void removeIndexText() {
		getChildren().remove(indexText);
	}
	
	public void showBorders() {
		getChildren().add(border);
	}
	
	public void hideBorders() {
		getChildren().remove(border);
	}
	
}

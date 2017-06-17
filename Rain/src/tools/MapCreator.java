package tools;

import static tools.Tile.TILES;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import rain.graphics.ImageLoader;
import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class MapCreator extends Application {
	
	public static final int TILES_IN_WIDTH = 102;
	public static final int TILES_IN_HEIGHT = 12;
	public static final int TILE_WIDTH = 60;
	public static final int TILE_HEIGHT = 60;
	public static final int LEVEL_WIDTH = TILES_IN_WIDTH * TILE_WIDTH;
	public static final int LEVEL_HEIGHT = TILES_IN_HEIGHT * TILE_HEIGHT;

	private Stage window;
	private Scene scene;
	private Pane root;
	private HBox layout;
	private Pane tileArea;
	private ScrollPane tileAreaSP;
	private Pane tileAreaContents;
	private VBox controlsArea;
	private CheckBox tileToggleIndexText, tileToggleBorders;
	private HBox textureSlider;
	private ImageView iv;
	private FileChooser fc;
	
	//TODO create slider of tiles available, new\load\save buttons and check button to switch tile indexes in tiles array display on tile
	private Button newBtn, loadBtn, saveBtn, nextBtn, prevBtn;
	
	private int width = 1280, height = 720;
	
	private WritableImage[] textures;
	private int currentTexture = 0;
	private File loadedFile = null;
	
	private Parent createContent() {
		root = new Pane();
		root.setPrefSize(width, height);
		
		layout = new HBox(5);
		
		loadTextures();
		
		controlsArea = new VBox(20);
		controlsArea.setPrefSize(280, height);
		controlsArea.setAlignment(Pos.TOP_CENTER);
		
		tileToggleIndexText = new CheckBox("Toggle Tiles Index");
		tileToggleIndexText.setOnAction(e -> {
			boolean selected = tileToggleIndexText.isSelected();
			if(selected) {
				doActionOnAllTiles("addindex");
			} else {
				doActionOnAllTiles("removeindex");
			}
		});
		
		tileToggleBorders = new CheckBox("Toggle Tile Borders");
		tileToggleBorders.setSelected(true);
		tileToggleBorders.setOnAction(e -> {
			boolean selected = tileToggleBorders.isSelected();
			if(selected) {
				doActionOnAllTiles("showborders");				
			} else {
				doActionOnAllTiles("hideborders");				
			}
		});
		
		textureSlider = new HBox(10);
		textureSlider.setAlignment(Pos.CENTER);
		iv = new ImageView(textures[currentTexture]);
		nextBtn = new Button(">");
		prevBtn = new Button("<");
		
		nextBtn.setOnAction(e -> {
			if(currentTexture == textures.length - 1) return;
			
			iv.setImage(textures[++currentTexture]);
		});
		
		prevBtn.setOnAction(e -> {
			if(currentTexture == 0) return;
			
			iv.setImage(textures[--currentTexture]);
		});
		
		textureSlider.getChildren().addAll(prevBtn, iv, nextBtn);
		
		newBtn = new Button("New");
		loadBtn = new Button("Load");
		saveBtn = new Button("Save");
		
		newBtn.setOnAction(e -> createNewLevel());
		
		loadBtn.setOnAction(e -> loadLevel());
		
		saveBtn.setOnAction(e -> saveLevel());
		
		controlsArea.getChildren().addAll(tileToggleIndexText, tileToggleBorders, textureSlider, newBtn, loadBtn, saveBtn);
		
		tileArea = new Pane();
		tileArea.setPrefSize(1000, height);
		
		tileAreaSP = new ScrollPane();
		tileAreaSP.setPrefSize(1000, height);
		tileArea.getChildren().add(tileAreaSP);
		tileAreaContents = new Pane();
		tileAreaContents.setPrefSize(LEVEL_WIDTH, LEVEL_HEIGHT);
		createTiles();
		tileAreaSP.setContent(tileAreaContents);
		
		layout.getChildren().addAll(controlsArea, tileArea);
		root.getChildren().add(layout);
		
		fc = new FileChooser();
		
		return root;
	}
	
	private void fillImages() {
		for(WritableImage wi : textures) {
			ImageView iv = new ImageView(wi);
			controlsArea.getChildren().add(iv);
		}
	}
	
	private void createTiles() {
		tileAreaContents.getChildren().clear();
		for(int i = 0; i < TILES_IN_HEIGHT; i++) {
			for(int j = 0; j < TILES_IN_WIDTH; j++) {
				Tile tile = new Tile(j * TILE_WIDTH, i * TILE_HEIGHT, TILE_WIDTH, TILE_HEIGHT, i, j);
				tile.setTexture(textures[0], 0);
				
				tile.setOnMouseClicked(e -> {
					if(e.getButton() == MouseButton.PRIMARY) {
						if(currentTexture == 3) {
							if(mapHasFlag()) {
								return;
							}
						}
						tile.setTexture(textures[currentTexture], currentTexture);
					} else if(e.getButton() == MouseButton.SECONDARY) {
						tile.resetTexture(textures[0], currentTexture);
					}
				});
				
				tileAreaContents.getChildren().add(tile);
			}
		}
	}
	
	private void loadTextures() {
		File folder = new File("res/textures/");
		File[] files = folder.listFiles();
		
		textures = new WritableImage[files.length];
		
		for(int i = 0; i < files.length; i++) {
			String path = "/textures/" + files[i].getName();
			textures[i] = SwingFXUtils.toFXImage(ImageLoader.loadImage(path), new WritableImage(TILE_WIDTH, TILE_HEIGHT));
		}
	}
	
	private void doActionOnAllTiles(String action) {
		for(int i = 0; i < TILES_IN_HEIGHT; i++) {
			for(int j = 0; j < TILES_IN_WIDTH; j++) {
				Tile tile = TILES[i][j];
				switch(action.toLowerCase()) {
					case "addindex":
						tile.addIndexText();
						break;
					case "removeindex":
						tile.removeIndexText();
						break;
					case "showborders":
						tile.showBorders();
						break;
					case "hideborders":
						tile.hideBorders();
						break;						
				}
			}
		}
	}
	
	public boolean mapHasFlag() {
		for(int i = 0; i < TILES_IN_HEIGHT; i++) {
			for(int j = 0; j < TILES_IN_WIDTH; j++) {
				if(TILES[i][j].hasFlag()) {
					return true;
				}
			}
		}
		return false;
	}
	
	private void createNewLevel() {
		boolean answer = YesNoBox.display("Making Sure", "Are you sure you want to create a new level(all progress on current level will be loss)");
		if(answer) {
			TILES = new Tile[TILES_IN_HEIGHT][TILES_IN_WIDTH];
			createTiles();
		}
	}
	
	private void loadLevel() {
		int count = 0;
		String[] level = new String[TILES_IN_HEIGHT];		
		File file = fc.showOpenDialog(window);
		loadedFile = file;
		
		FileReader fr = null;
		BufferedReader br = null;
		
		try {
			fr = new FileReader(file);
			br = new BufferedReader(fr);
			String line = null;
			while((line = br.readLine()) != null) {
				level[count] = line.trim();
				count++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(fr != null)
						fr.close();
				if(br != null) 
					br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		loadLevelTiles(level);
	}
	
	private void loadLevelTiles(String[] level) {
		TILES = new Tile[TILES_IN_HEIGHT][TILES_IN_WIDTH];
		tileAreaContents.getChildren().clear();
		for(int i = 0; i < level.length; i++) {
			char[] ids = level[i].toCharArray();
			for(int j = 0; j < ids.length; j++) {
				Tile tile = new Tile(j * TILE_WIDTH, i * TILE_HEIGHT, TILE_WIDTH, TILE_HEIGHT, i, j);
				int texture = Integer.parseInt(String.valueOf(ids[j]));
				tile.setTexture(textures[texture], texture);
				
				tile.setOnMouseClicked(e -> {
					if(e.getButton() == MouseButton.PRIMARY) {
						if(currentTexture == 3) {
							if(mapHasFlag()) {
								return;
							}
						}
						tile.setTexture(textures[currentTexture], currentTexture);
					} else if(e.getButton() == MouseButton.SECONDARY) {
						tile.resetTexture(textures[0], currentTexture);
					}
				});
				
				tileAreaContents.getChildren().add(tile);
			}
		}
	}

	private boolean saveLevel() {
		String path = "res/levels/";
		File[] files = new File(path).listFiles();
		int levelNumber = Integer.parseInt(String.valueOf(files[files.length - 1].getName().charAt(5)));
		levelNumber++;
		String[] levelContents = createLevelArray();
		
		FileWriter fw = null;
		BufferedWriter bw = null;
		
		try {
			if(loadedFile == null) {
				fw = new FileWriter(path + "level" + levelNumber + ".txt");
			} else {
				boolean answer = YesNoBox.display("Making Sure", "Are you sure you want to overwrite the existing level?");
				if(!answer) return false;
				fw = new FileWriter(loadedFile);
			}
			bw = new BufferedWriter(fw);
			for(String s : levelContents) {
				bw.write(s + "\n");
			}
			System.out.println("Saved");
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		} finally { 
			try {
				if(bw != null) {
					bw.close();
				}
				if(fw != null) {
					fw.close();
				}
			} catch(IOException ioe) {
				ioe.printStackTrace();
			}
		}
	}
	
	public String[] createLevelArray() {
		String[] level = new String[TILES_IN_HEIGHT];
		for(int i = 0; i < TILES_IN_HEIGHT; i++) {
			String line = "";
			for(int j = 0; j < TILES_IN_WIDTH; j++) {
				Tile tile = TILES[i][j];
				line += tile.getTextureId();
			}
			level[i] = line;
		}
		return level;
	}
	
	@Override
	public void start(Stage stage) {
		window = stage;
		scene = new Scene(createContent());
		
		window.setScene(scene);
		window.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
}

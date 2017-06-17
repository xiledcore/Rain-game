package tools;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class YesNoBox {

	static boolean answer = false;
	
	public static boolean display(String title, String msg) {
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle(title);
		window.setMinWidth(150);
		window.setMinHeight(100);
		
		VBox layout = new VBox(10);
		layout.setAlignment(Pos.CENTER);
		HBox btnsLayout = new HBox(10);
		btnsLayout.setAlignment(Pos.CENTER);
		
		Label label = new Label(msg);
		Button yesBtn = new Button("Yes");
		Button noBtn = new Button("No");
		
		yesBtn.setOnAction(e -> {
			answer = true;
			window.close();
		});
		
		noBtn.setOnAction(e -> {
			answer = false;
			window.close();
		});
		
		btnsLayout.getChildren().addAll(yesBtn, noBtn);
		layout.getChildren().addAll(label, btnsLayout);
		
		Scene scene = new Scene(layout);
		window.setScene(scene);
		window.showAndWait();
		
		return answer;
	}
	
}

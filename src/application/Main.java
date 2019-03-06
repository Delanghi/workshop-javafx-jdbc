package application;	

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;

public class Main extends Application {
	
 // ATRIBUTO
	private static Scene mainScene; 								// guardando a refer�ncia neste ATRIBUTO	
	
 // 
	@Override 
	public void start(Stage primaryStage) {  
		try {   
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/MainView.fxml"));   
			ScrollPane scrollPane = loader.load(); 
			
			scrollPane.setFitToHeight(true);
			scrollPane.setFitToWidth(true); 
	 
			mainScene = new Scene(scrollPane);   					// referenciando o ATRIBUTO acima
			primaryStage.setScene(mainScene);   
			primaryStage.setTitle("Sample JavaFX application");   
			primaryStage.show();  
		} 
		catch (IOException e) {   
			e.printStackTrace();  
		} 
	}
	
 // M�TODOS
	public static Scene getMainScene() {			// M�TODO criado pra pegar esta refer�ncia
		return mainScene;
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}

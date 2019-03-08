package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import gui.util.Alerts;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import model.services.DepartmentService;

public class MainViewController implements Initializable {

 // ATRIBUTOS
	@FXML
	private MenuItem menuItemSeller;	
	
	@FXML
	private MenuItem menuItemDepartment;	
	
	@FXML
	private MenuItem menuItemAbout;										// referente ao comando Help 
	
 // MÉTODOS
	@FXML
	public void onMenuItemSellerAction() {
		System.out.println("onMenuItemSellerAction");
	}	
	
	@FXML
	public void onMenuItemDepartmentAction() {
		loadView2("/gui/DepartmentList.fxml");							// provisóriamente estamos chamando de "loadView2"
	}	

	@FXML
	public void onMenuItemAboutAction() {
		loadView("/gui/About.fxml");
	}	
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
	}
	
 // FUNÇÃO
	private synchronized void loadView(String absoluteName) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));	// INSTANCIAR pra abrir tela
			VBox newVBox = loader.load(); 												// Objeto Tipo VBox recebe "loader"
			
 // VARIÁVEIS			
			Scene mainScene = Main.getMainScene(); 										// VARIÁVEL ligação com CLASSE Main
			VBox mainVBox = (VBox) ((ScrollPane) mainScene.getRoot()).getContent();		// getRoot vai pegar 1º elemento da View = "ScrollPane"
				
			Node mainMenu = mainVBox.getChildren().get(0);
			mainVBox.getChildren().clear();
			mainVBox.getChildren().add(mainMenu);
			mainVBox.getChildren().addAll(newVBox.getChildren());
		}
		catch (IOException e) {
			Alerts.showAlert("IO Exceotion", "Error loading view", e.getMessage(), AlertType.ERROR);
		}
	}
		
	private synchronized void loadView2(String absoluteName) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));	// INSTANCIAR pra abrir tela
			VBox newVBox = loader.load(); 												// Objeto Tipo VBox recebe "loader"
			
 // VARIÁVEIS			
			Scene mainScene = Main.getMainScene(); 										// VARIÁVEL ligação com CLASSE Main
			VBox mainVBox = (VBox) ((ScrollPane) mainScene.getRoot()).getContent();		// getRoot vai pegar 1º elemento da View = "ScrollPane"
				
			Node mainMenu = mainVBox.getChildren().get(0);
			mainVBox.getChildren().clear();
			mainVBox.getChildren().add(mainMenu);
			mainVBox.getChildren().addAll(newVBox.getChildren());
			
			DepartmentListController controller = loader.getController();				// "loader" é o Objeto criado p/ carregar a "View". Agora tb pode acessar o "controller"
			controller.setDepartmentService(new DepartmentService());					// pra injetar "dependência" do SERVICE lá no CONTROLLER
			controller.updateTableView();												// aqui chama pra atualizar dados na tela da tableview
		}
		catch (IOException e) {
			Alerts.showAlert("IO Exceotion", "Error loading view", e.getMessage(), AlertType.ERROR);
		}
	}	
}

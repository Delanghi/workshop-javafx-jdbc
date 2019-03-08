package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;

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
	
 // M�TODOS
	@FXML
	public void onMenuItemSellerAction() {
		System.out.println("onMenuItemSellerAction");
	}	
	
	@FXML
	public void onMenuItemDepartmentAction() {														// inclu�do 2� par�metro pra iniciar o "controller"
		loadView("/gui/DepartmentList.fxml", (DepartmentListController controller) -> {
			controller.setDepartmentService(new DepartmentService());
			controller.updateTableView();
		});
	} 

	@FXML
	public void onMenuItemAboutAction() {
		loadView("/gui/About.fxml", x -> {});							// express�o lambda indicando "nada/vazio"
	}	
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
	}
	
 // FUN��O
	private synchronized <T> void loadView(String absoluteName, Consumer<T> initializingAction) {			// fun��o do Tipo "<T>" - cap�tulo Genirics
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));	// INSTANCIAR pra abrir tela
			VBox newVBox = loader.load(); 												// Objeto Tipo VBox recebe "loader"
			
 // VARI�VEIS			
			Scene mainScene = Main.getMainScene(); 										// VARI�VEL liga��o com CLASSE Main
			VBox mainVBox = (VBox) ((ScrollPane) mainScene.getRoot()).getContent();		// getRoot vai pegar 1� elemento da View = "ScrollPane"
				
			Node mainMenu = mainVBox.getChildren().get(0);
			mainVBox.getChildren().clear();
			mainVBox.getChildren().add(mainMenu);
			mainVBox.getChildren().addAll(newVBox.getChildren());
			
			T controller = loader.getController();						// "getController" retornar� o controller do Tipo chamado; aqui do "DepartmentListController"
			initializingAction.accept(controller);						// vai executar a fun��o passada como argumento: linha 42
		}
		catch (IOException e) {
			Alerts.showAlert("IO Exceotion", "Error loading view", e.getMessage(), AlertType.ERROR);
		}
	}
		
}

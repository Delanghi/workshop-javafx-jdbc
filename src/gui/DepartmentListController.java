package gui;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import application.Main;
import gui.util.Alerts;
import gui.util.Utils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.entities.Department;
import model.services.DepartmentService;

public class DepartmentListController implements Initializable {
	
	private DepartmentService service;
	
	@FXML
	private TableView<Department> tableViewDepartment;
	
	@FXML
	private TableColumn<Department, Integer> tableColumnId;
	
	@FXML
	private TableColumn<Department, String> tableColumnName;
	
	@FXML
	private Button btNew;
	
	private ObservableList<Department> obsList;							// declara��o
	
	@FXML
	public void onBtNewAction(ActionEvent event) {						// para ter refer�ncia p/ Controller que recebeu o evento
		Stage parentStage = Utils.currentStage(event);
		Department obj = new Department();								// como podemos criar um depto novo, temos que "criar um novo"
		createDialogForm(obj, "/gui/DepartmentForm.fxml", parentStage);
	}
	
 // M�TODOS
	public void setDepartmentService(DepartmentService service) {		// invers�o de controlle ao inv�s de INSTANCIAR no ATRIBUTO
		this.service = service;
	}
	
	
 // M�TODOS DA TABELA
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		initializeNodes();
	}  
	
	private void initializeNodes() {
		tableColumnId.setCellValueFactory(new PropertyValueFactory<>("id")); 
		tableColumnName.setCellValueFactory(new PropertyValueFactory<>("name"));  
		
		Stage stage = (Stage) Main.getMainScene().getWindow();					// macete para table view acompanhar a altura da janela
		tableViewDepartment.prefHeightProperty().bind(stage.heightProperty());
	}
	
	public void updateTableView() {											// Vamos acessar depto e jogar na "ObservableList"
		if (service == null) {
			throw new IllegalStateException("Service was null");
		}
		List<Department> list = service.findAll();							// vai buscar os "departamentos" mocados na outra CLASSE
		obsList = FXCollections.observableArrayList(list);					// INSTANCIAMOS o "obsList" com os dados de "list"
		tableViewDepartment.setItems(obsList);								// para mostrar os dados na tela
	}
	
	private void createDialogForm(Department obj, String absoluteName, Stage parentStage) {		// foi referenciado o Stage da janela de di�logo
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));			// INSTANCIAR pra abrir tela
			Pane pane = loader.load();
			
			DepartmentFormController controller = loader.getController();	// referenciando p/ injetar "Department obj" na tela de usu�rio
			controller.setDepartment(obj);									// p/ injetar "Department obj" no controller da tela de usu�rio
			controller.updateFormData();									// p/ carregar os dados na tela
			
			Stage dialogStage = new Stage();					// temos que INSTANCIAR novo Stage; uma janela na frente da outra
			dialogStage.setTitle("Enter department data");		// para configurar o t�tulo da janela
			dialogStage.setScene(new Scene(pane));				// novo cen�rio
			dialogStage.setResizable(false);					// a janela n�o poder� ser redimensionada
			dialogStage.initOwner(parentStage);					// esta � importante: que � o pai desta Stage
			dialogStage.initModality(Modality.WINDOW_MODAL);	// a janela vai travar; s� quando fechar vc ter� acesso na outra
			dialogStage.showAndWait();
		} 
		catch (IOException e) {
			Alerts.showAlert("IO Exception", "Error loading view", e.getMessage(), AlertType.ERROR);
		}
	}	
	
}

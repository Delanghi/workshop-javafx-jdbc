package gui;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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
	
	private ObservableList<Department> obsList;								// declaração
	
	@FXML
	public void onBtNewAction() {											// tratando o botão
		System.out.println("onBtNewAction");
	}
	
 // MÉTODOS
	public void setDepartmentService(DepartmentService service) {			// inversão de controlle ao invés de INSTANCIAR no ATRIBUTO
		this.service = service;
	}
	
	
 // MÉTODOS DA TABELA
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
	
}
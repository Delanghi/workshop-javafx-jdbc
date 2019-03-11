package gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import db.DbException;
import gui.listeners.DataChangeListener;
import gui.util.Alerts;
import gui.util.Constraints;
import gui.util.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.entities.Department;
import model.services.DepartmentService;

public class DepartmentFormController implements Initializable {
	
	private Department entity;
	
	private DepartmentService service;						// criamos uma depend�ncia
	
	private List<DataChangeListener> dataChangeListeners = new ArrayList<>();

 // ATRIBUTOS - DECLARA��O DOS COMPONENTES DA TELA
	@FXML
	private TextField txtId;
	
	@FXML
	private TextField txtName;
	
	@FXML
	private Label labelErrorName;
	
	@FXML
	private Button btSave;
	
	@FXML
	private Button btCancel;
	
 // M�TODO
	public void setDepartment(Department entity) {
		this.entity = entity;
	}
	
	public void setDepartmentService(DepartmentService service) {
		this.service = service;
	}

	public void subscribeDataChangeListener(DataChangeListener listener) {			// outros Objetos, desde que implementem esta interface "DataChangeListener", podem se inscrever na lista
		dataChangeListeners.add(listener);											// adicionar Objeto � lista
	}
	
	
 // DECLARA��O DOS M�TODOS PARA TRATAR OS BOT�ES	
	@FXML
	public void onBtSaveAction(ActionEvent event) {									// M�TODO do bot�o "save"
		if(entity == null) {														// preven��o
			throw new IllegalStateException("Entity was null");
		}															// estes avisos s�o importantes p/ avisar programador p/ injetar depend�ncia
		if(service == null) {
			throw new IllegalStateException("Service was null");
		}
		try {
			entity = getFormData();									// vai pegar dados das cxs.de texto e INSTANCIAR no depto. p/ n�s
			service.saveOrUpdate(entity); 							// assim salvamos a info no banco de dados
			notifyDataChangeListeners();							// ap�s salvamento com sucesso, os "listeners" devem ser notificados
			Utils.currentStage(event).close(); 						// ap�s clicar bot�o "save", a janela de fechar
		}
		catch (DbException e) {										// como se trata de banco de dados, pode dar ERRO
			Alerts.showAlert("Error saving object", null, e.getMessage(), AlertType.ERROR);
		}					
	}

	private void notifyDataChangeListeners() {	
		for(DataChangeListener listener : dataChangeListeners) {
			listener.onDataChanged();								// p/ executar o M�TODO "onDataChanged" em cada um dos listeners
		}
	}

	private Department getFormData() {
		Department obj = new Department();
		
		obj.setId(Utils.tryParseToInt(txtId.getText()));	// vamos buscar info na cx.de texto. Como � String, usamos "Utils.tryParseToInt" p/ converter
		obj.setName(txtName.getText());						// vamos buscar info na cx.de texto. este � String mesmo
		
		return obj;											// vai retornar um novo Objeto 
	}

	@FXML
	public void onBtCancelAction(ActionEvent event) {
		Utils.currentStage(event).close(); 					// ap�s clicar bot�o "cancel", a janela de fechar
	}
	
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		initializeNodes();
	}
	
 // FUN��ES - RESTRI��ES 
	private void initializeNodes() {
		Constraints.setTextFieldInteger(txtId);						// aqui informamos que o ID s� ter� n�meros inteiros
		Constraints.setTextFieldMaxLength(txtName, 30); 			// aqui teremos nome com no m�ximo 30 letras
	}
	
 // M�TODO
public void updateFormData() {									// p/ jogar info das cxs.de texto ID e Name que est�o no "Department entity"
		if(entity == null) {										// teste preventivo
			throw new IllegalStateException("Entity was null");
		}
		txtId.setText(String.valueOf(entity.getId()));		// "valueOf" a cx.de texto l� String; por isso temos que transformar Integer em String
		txtName.setText(entity.getName());
	}

}
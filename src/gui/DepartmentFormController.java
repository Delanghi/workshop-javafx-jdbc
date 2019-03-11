package gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

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
import model.exceptions.ValidationException;
import model.services.DepartmentService;

public class DepartmentFormController implements Initializable {
	
	private Department entity;
	
	private DepartmentService service;						// criamos uma dependência
	
	private List<DataChangeListener> dataChangeListeners = new ArrayList<>();

 // ATRIBUTOS - DECLARAÇÃO DOS COMPONENTES DA TELA
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
	
 // MÉTODO
	public void setDepartment(Department entity) {
		this.entity = entity;
	}
	
	public void setDepartmentService(DepartmentService service) {
		this.service = service;
	}

	public void subscribeDataChangeListener(DataChangeListener listener) {			// outros Objetos, desde que implementem esta interface "DataChangeListener", podem se inscrever na lista
		dataChangeListeners.add(listener);											// adicionar Objeto à lista
	}
	
	
 // DECLARAÇÃO DOS MÉTODOS PARA TRATAR OS BOTÕES	
	@FXML
	public void onBtSaveAction(ActionEvent event) {									// MÉTODO do botão "save"
		if(entity == null) {														// prevenção
			throw new IllegalStateException("Entity was null");
		}															// estes avisos são importantes p/ avisar programador p/ injetar dependência
		if(service == null) {
			throw new IllegalStateException("Service was null");
		}
		try {
			entity = getFormData();									// vai pegar dados das cxs.de texto e INSTANCIAR no depto. p/ nós
			service.saveOrUpdate(entity); 							// assim salvamos a info no banco de dados
			notifyDataChangeListeners();							// após salvamento com sucesso, os "listeners" devem ser notificados
			Utils.currentStage(event).close(); 						// após clicar botão "save", a janela de fechar
		}
		catch (ValidationException e) {								// caso ocorrá um ERRO na validação...
			setErrorMessages(e.getErrors());						
		}
		catch (DbException e) {										// como se trata de banco de dados, pode dar ERRO
			Alerts.showAlert("Error saving object", null, e.getMessage(), AlertType.ERROR);
		}					
	}

	private void notifyDataChangeListeners() {	
		for(DataChangeListener listener : dataChangeListeners) {
			listener.onDataChanged();								// p/ executar o MÉTODO "onDataChanged" em cada um dos listeners
		}
	}

	private Department getFormData() {
		Department obj = new Department();
		
		ValidationException exception = new ValidationException("Validation error"); 		// INSTANCIANDO a exceção
		
		obj.setId(Utils.tryParseToInt(txtId.getText()));	// vamos buscar info na cx.de texto. Como é String, usamos "Utils.tryParseToInt" p/ converter
		
		if(txtName.getText() == null || txtName.getText().trim().equals("")) {				// se campo for "nulo" ou "vazio"...
			exception.addErrors("name",  "Field can't be empty");
						
		}
		obj.setName(txtName.getText());						// vamos buscar info na cx.de texto. este é String mesmo
		
		if(exception.getErrors().size() > 0) {				// aqui estamos testando se a coleção de ERROS tem pelo menos um ERRO
			throw exception;								// se for verdade, lançará a exceção
		}
		
		return obj;											// vai retornar um novo Objeto 
	}

	@FXML
	public void onBtCancelAction(ActionEvent event) {
		Utils.currentStage(event).close(); 					// após clicar botão "cancel", a janela de fechar
	}
	
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		initializeNodes();
	}
	
 // FUNÇÕES - RESTRIÇÕES 
	private void initializeNodes() {
		Constraints.setTextFieldInteger(txtId);						// aqui informamos que o ID só terá números inteiros
		Constraints.setTextFieldMaxLength(txtName, 30); 			// aqui teremos nome com no máximo 30 letras
	}
	
 // MÉTODO
	public void updateFormData() {									// p/ jogar info das cxs.de texto ID e Name que estão no "Department entity"
		if(entity == null) {										// teste preventivo
			throw new IllegalStateException("Entity was null");
		}
		txtId.setText(String.valueOf(entity.getId()));		// "valueOf" a cx.de texto lê String; por isso temos que transformar Integer em String
		txtName.setText(entity.getName());
	}
	
	private void setErrorMessages (Map<String, String> errors) {	// colocar a mensagem de ERRO referente ao campo "vazio" da janela
		Set<String> fields = errors.keySet();
		
		if(fields.contains("name")) {								// verificar se existe a chave "name" (linha 98) 
			labelErrorName.setText(errors.get("name"));				// pega a mensagem referente a "name" e seta no "labelErrorName" (label vazia na janela)
		}
	}
}
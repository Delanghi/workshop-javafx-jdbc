package gui;

import java.net.URL;
import java.util.ResourceBundle;

import gui.util.Constraints;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.entities.Department;

public class DepartmentFormController implements Initializable {
	
	private Department entity;

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
	
	public void setDepartment(Department entity) {
		this.entity = entity;
	}


 // DECLARA��O DOS M�TODOS PARA TRATAR OS BOT�ES	
	@FXML
	public void onBtSaveAction() {
		System.out.println("onBtSaveAction");
	}

	@FXML
	public void onBtCancelAction() {
		System.out.println("onBtCancelAction");
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
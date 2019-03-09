package gui.util;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;

public class Utils {
	
	public static Stage currentStage(ActionEvent event) {						// cenário atual recebe ação de acionar botão
		return (Stage) ((Node) event.getSource()).getScene().getWindow();	

	}
}





//"ActionEvent event": quando clicamos no botão, pegamos o "stage" deste botão

// queremos o "getSource" Tipo "Node" por isso downcast

// "getWindow" é SuperCLASSE de "Stage". P/ isso downcast tb 
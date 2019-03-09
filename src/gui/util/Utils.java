package gui.util;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;

public class Utils {
	
	public static Stage currentStage(ActionEvent event) {						// cen�rio atual recebe a��o de acionar bot�o
		return (Stage) ((Node) event.getSource()).getScene().getWindow();	

	}
}





//"ActionEvent event": quando clicamos no bot�o, pegamos o "stage" deste bot�o

// queremos o "getSource" Tipo "Node" por isso downcast

// "getWindow" � SuperCLASSE de "Stage". P/ isso downcast tb 
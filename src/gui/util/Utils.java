package gui.util;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;

public class Utils {
	
	public static Stage currentStage(ActionEvent event) {						// cen�rio atual recebe a��o de acionar bot�o
		return (Stage) ((Node) event.getSource()).getScene().getWindow();	

	}
	
 // M�TODO
	public static Integer tryParseToInt(String str) {				// para a convers�o do n�mero (valor na caixa de sele��o)
		try {
			return Integer.parseInt(str);							// ou converte-se o n�mero corretamente...
		}
		catch (NumberFormatException e) {
			return null;											// ...ou retorna o valor nulo. Anulamos o risco de acontecer exce��o, caso tenhamos, p.ex., letras ao inv�s de n�meros
		}
		
	}
	
	
}
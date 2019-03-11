package gui.util;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;

public class Utils {
	
	public static Stage currentStage(ActionEvent event) {						// cenário atual recebe ação de acionar botão
		return (Stage) ((Node) event.getSource()).getScene().getWindow();	

	}
	
 // MÉTODO
	public static Integer tryParseToInt(String str) {				// para a conversão do número (valor na caixa de seleção)
		try {
			return Integer.parseInt(str);							// ou converte-se o número corretamente...
		}
		catch (NumberFormatException e) {
			return null;											// ...ou retorna o valor nulo. Anulamos o risco de acontecer exceção, caso tenhamos, p.ex., letras ao invés de números
		}
		
	}
	
	
}
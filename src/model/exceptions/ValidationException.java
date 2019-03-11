package model.exceptions;

import java.util.HashMap;
import java.util.Map;

public class ValidationException extends RuntimeException {

	private static final long serialVersionUID = 1L;					// n�mero serial padr�o; n�mero 1
	
	private Map<String, String> errors = new HashMap<>();				// 1� String define o campo de origem do ERRO; ex. Name. O 2� String � referente ao pr�prio ERRO
	
	public ValidationException(String msg) {							// for�ar a INSTANCIA��O da exce��o com String
		super(msg);
	}
	
	public Map<String, String> getErrors() {							// o M�TODO para capturar os ERROS
		return errors;
	}
	
	public void addErrors(String fieldName, String errorMessage) {		// fizemos uma exce��o personalizada que carrega p/ n�s todos os tipos de ERRO poss�veis
		errors.put(fieldName, errorMessage); 
	}

}
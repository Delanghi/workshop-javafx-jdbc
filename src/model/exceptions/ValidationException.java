package model.exceptions;

import java.util.HashMap;
import java.util.Map;

public class ValidationException extends RuntimeException {

	private static final long serialVersionUID = 1L;					// número serial padrão; número 1
	
	private Map<String, String> errors = new HashMap<>();				// 1º String define o campo de origem do ERRO; ex. Name. O 2º String é referente ao próprio ERRO
	
	public ValidationException(String msg) {							// forçar a INSTANCIAÇÃO da exceção com String
		super(msg);
	}
	
	public Map<String, String> getErrors() {							// o MÉTODO para capturar os ERROS
		return errors;
	}
	
	public void addErrors(String fieldName, String errorMessage) {		// fizemos uma exceção personalizada que carrega p/ nós todos os tipos de ERRO possíveis
		errors.put(fieldName, errorMessage); 
	}

}
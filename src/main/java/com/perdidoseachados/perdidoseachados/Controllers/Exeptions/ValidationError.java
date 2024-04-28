package com.perdidoseachados.perdidoseachados.Controllers.Exeptions;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandartError{
	
	private List<FieldMessage> errors = new ArrayList<FieldMessage>();

	public List<FieldMessage> getErrors() {
		return errors;
	}

	public void addError(String name, String message) {
		errors.add(new FieldMessage(name, message));
		
	}
	
	
	

}

package com.perdidoseachados.perdidoseachados.Servicies.exeptions;

public class ResourceNotFoundException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;

	public ResourceNotFoundException (String msg) {
		super(msg);
	}

}

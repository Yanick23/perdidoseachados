package com.perdidoseachados.perdidoseachados.Servicies.exeptions;


public class DuplicateStockException extends RuntimeException{
	
	public DuplicateStockException (String msg) {
		super(msg);
	}

}

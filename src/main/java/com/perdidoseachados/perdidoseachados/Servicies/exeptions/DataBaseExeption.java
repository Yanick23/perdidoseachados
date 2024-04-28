package com.perdidoseachados.perdidoseachados.Servicies.exeptions;

import org.springframework.dao.DataAccessException;

public class DataBaseExeption extends DataAccessException{

	private static final long serialVersionUID = 1L;

	public DataBaseExeption(String msg) {
		super(msg);
		
	}

	
}

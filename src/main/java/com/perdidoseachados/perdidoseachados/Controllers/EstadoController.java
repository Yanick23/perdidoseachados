package com.perdidoseachados.perdidoseachados.Controllers;

import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.perdidoseachados.perdidoseachados.DTOs.EstadoDTO;
import com.perdidoseachados.perdidoseachados.Servicies.EstadoService;

@RestController
@RequestMapping(value = "/estados")
public class EstadoController {
	
	@Autowired
	EstadoService estadoService;
	
	
	@GetMapping
	public  ResponseEntity <List<EstadoDTO>> findAll(){
		
		return  ResponseEntity.ok(estadoService.findAll());
		
	}

}

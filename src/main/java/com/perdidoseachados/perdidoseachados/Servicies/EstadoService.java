package com.perdidoseachados.perdidoseachados.Servicies;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.perdidoseachados.perdidoseachados.DTOs.CategoriaDTO;
import com.perdidoseachados.perdidoseachados.DTOs.EstadoDTO;
import com.perdidoseachados.perdidoseachados.Repository.EstadoRepository;

@Service
public class EstadoService {
	
	
	@Autowired
	EstadoRepository estadoRepository;
	
	public List<EstadoDTO> findAll(){
		 return  estadoRepository.findAll().stream().map(estado -> new EstadoDTO(estado)).collect(Collectors.toList());
	}
	
	

}

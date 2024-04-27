package com.perdidoseachados.perdidoseachados.Servicies;

import com.perdidoseachados.perdidoseachados.DTOs.CategoriaDTO;
import com.perdidoseachados.perdidoseachados.Entidades.Categoria;

import com.perdidoseachados.perdidoseachados.Repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class CategoriaService {
    @Autowired
    CategoriaRepository categoriaRepository;

    public List <CategoriaDTO> findAll(){
        List <Categoria> categorias = categoriaRepository.findAll();
        return  categorias.stream().map(categoria -> new CategoriaDTO(categoria)).collect(Collectors.toList());
    }


}

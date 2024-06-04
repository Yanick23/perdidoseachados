package com.perdidoseachados.perdidoseachados.Servicies;

import com.perdidoseachados.perdidoseachados.DTOs.CategoriaDTO;
import com.perdidoseachados.perdidoseachados.DTOs.ItemDTO;
import com.perdidoseachados.perdidoseachados.Entidades.Categoria;

import com.perdidoseachados.perdidoseachados.Entidades.Item;
import com.perdidoseachados.perdidoseachados.Entidades.Usuario;
import com.perdidoseachados.perdidoseachados.Repository.CategoriaRepository;
import com.perdidoseachados.perdidoseachados.constantes.EstadoDeDevolucao;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
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
    @Transactional
    public CategoriaDTO insert (CategoriaDTO categoriaDTO){
        Categoria entity = new Categoria();
        entity.setNome(categoriaDTO.getNome());
        entity = categoriaRepository.save(entity);
        return new CategoriaDTO(entity);
    }

}

package com.perdidoseachados.perdidoseachados.Controllers;

import com.perdidoseachados.perdidoseachados.DTOs.CategoriaDTO;
import com.perdidoseachados.perdidoseachados.DTOs.ItemDTO;
import com.perdidoseachados.perdidoseachados.DTOs.CategoriaDTO;
import com.perdidoseachados.perdidoseachados.DTOs.UsuarioInsertDTO;
import com.perdidoseachados.perdidoseachados.Entidades.Categoria;
import com.perdidoseachados.perdidoseachados.Servicies.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaController {

    @Autowired
    CategoriaService categoriaService;

    @GetMapping
    public ResponseEntity<List<CategoriaDTO>> findAll(){
        return  ResponseEntity.ok(categoriaService.findAll()) ;
    }
    @PostMapping(value = "/registar")
    public ResponseEntity <CategoriaDTO> insert(@RequestBody CategoriaDTO categoriaDTO){
        CategoriaDTO userDTO = categoriaService.insert(categoriaDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").
                buildAndExpand(userDTO.getId()).toUri();
        return ResponseEntity.created(uri).body(userDTO);
    }
    
    
}

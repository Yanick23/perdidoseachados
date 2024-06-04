package com.perdidoseachados.perdidoseachados.Controllers;

import com.perdidoseachados.perdidoseachados.DTOs.CategoriaDTO;
import com.perdidoseachados.perdidoseachados.DTOs.LocalizacaoDTO;
import com.perdidoseachados.perdidoseachados.DTOs.UsuarioDTO;
import com.perdidoseachados.perdidoseachados.DTOs.UsuarioInsertDTO;
import com.perdidoseachados.perdidoseachados.Servicies.CategoriaService;
import com.perdidoseachados.perdidoseachados.Servicies.LocalizacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/localizacoes")
public class LocalizacaoController {

    @Autowired
    LocalizacaoService localizacaoService;

    @GetMapping
    public ResponseEntity<List<LocalizacaoDTO>> findAll(){
        return  ResponseEntity.ok(localizacaoService.findAll()) ;
    }
    @PostMapping(value = "/registar")
    public ResponseEntity <LocalizacaoDTO> insert(@RequestBody LocalizacaoDTO localizacaoDTO){
        LocalizacaoDTO userDTO = localizacaoService.insert(localizacaoDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").
                buildAndExpand(userDTO.getId()).toUri();
        return ResponseEntity.created(uri).body(userDTO);
    }
    
    
}

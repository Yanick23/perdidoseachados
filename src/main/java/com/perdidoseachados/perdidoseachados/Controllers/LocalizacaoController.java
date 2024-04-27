package com.perdidoseachados.perdidoseachados.Controllers;

import com.perdidoseachados.perdidoseachados.DTOs.CategoriaDTO;
import com.perdidoseachados.perdidoseachados.DTOs.LocalizacaoDTO;
import com.perdidoseachados.perdidoseachados.Servicies.CategoriaService;
import com.perdidoseachados.perdidoseachados.Servicies.LocalizacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    
    
    
}

package com.perdidoseachados.perdidoseachados.Controllers;

import com.perdidoseachados.perdidoseachados.DTOs.ItemDTO;
import com.perdidoseachados.perdidoseachados.Servicies.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;


@RestController
@RequestMapping(value = "/itens")
public class ItemController {

    @Autowired
    ItemService itemService;

    @GetMapping
    public ResponseEntity <List<ItemDTO>> findAll(){
        return  ResponseEntity.ok(itemService.findAll()) ;
    }

    @PostMapping
    public ResponseEntity <ItemDTO> insert( @RequestBody ItemDTO itemDTO){
        itemDTO = itemService.Insert(itemDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").
                buildAndExpand(itemDTO.getId()).toUri();
        return ResponseEntity.created(uri).body(itemDTO);
    }






}

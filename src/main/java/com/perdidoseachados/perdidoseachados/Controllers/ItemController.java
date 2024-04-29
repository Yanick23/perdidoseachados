package com.perdidoseachados.perdidoseachados.Controllers;

import com.perdidoseachados.perdidoseachados.DTOs.ItemDTO;
import com.perdidoseachados.perdidoseachados.Servicies.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;


@RestController
@RequestMapping(value = "/itens")
public class ItemController<itemDTO> {

    @Autowired
    ItemService itemService;

    @GetMapping
    public ResponseEntity <List<ItemDTO>> findAll(){
        return  ResponseEntity.ok(itemService.findAll()) ;
    }

    @PostMapping("/registar")
    public ResponseEntity <ItemDTO> insert( @RequestBody ItemDTO itemDTO){
        itemDTO = itemService.Insert(itemDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").
                buildAndExpand(itemDTO.getId()).toUri();
        return ResponseEntity.created(uri).body(itemDTO);
    }

    @PutMapping(value ="/update/{id}")
    public ResponseEntity <ItemDTO> update (@PathVariable Long id,@RequestBody ItemDTO itemDTO){
        itemDTO = itemService.Update(id,itemDTO);
        return  ResponseEntity.ok(itemDTO);
    }

    @GetMapping("/meusitens")
    public ResponseEntity<List<ItemDTO>> findByUsuario()  {
   List <ItemDTO> list = itemService.itensDeUsuarioCorrente();
        return ResponseEntity.ok( list);

    }

    @GetMapping(value = "/{id}")
    public ResponseEntity <ItemDTO> findById(@PathVariable Long id)  {
        ItemDTO ItemDTO = itemService.findById(id);
        return ResponseEntity.ok(ItemDTO);

    }

    @DeleteMapping(value = "/delete/{id}")
    public  ResponseEntity delete(@PathVariable Long id){
        itemService.delete(id);
        return   ResponseEntity.noContent().build();
    }

}

package com.perdidoseachados.perdidoseachados.Controllers;

import com.perdidoseachados.perdidoseachados.DTOs.UsuarioInsertDTO;
import com.perdidoseachados.perdidoseachados.DTOs.UsuarioDTO;
import com.perdidoseachados.perdidoseachados.Servicies.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/usuarios")
public class UsuarioController
{


    @Autowired
    UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> findAll(){
        return  ResponseEntity.ok(usuarioService.findAll()) ;
    }

    @PostMapping("/registar")
    public ResponseEntity <UsuarioDTO> insert(@RequestBody UsuarioInsertDTO usuarioDTO){
        UsuarioDTO suarioDTO = usuarioService.insert(usuarioDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").
                buildAndExpand(suarioDTO.getId()).toUri();
        return ResponseEntity.created(uri).body(usuarioDTO);
    }

    @PutMapping(value ="/update/{id}")
    public ResponseEntity <UsuarioDTO> update (@PathVariable Long id,@RequestBody UsuarioDTO usuarioDTO){
        usuarioDTO = usuarioService.update(id,usuarioDTO);
        return  ResponseEntity.ok(usuarioDTO);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity <UsuarioDTO> findById(@PathVariable Long id)  {
        UsuarioDTO usuarioDTO = usuarioService.findById(id);
        return ResponseEntity.ok(usuarioDTO);

    }

    @DeleteMapping(value = "/delete/{id}")
    public  ResponseEntity delete(@PathVariable Long id){

        return   ResponseEntity.ok(usuarioService.delete(id));
    }

    @PostMapping(value = "/login")
    public ResponseEntity<String> authenticateUser(@RequestBody UsuarioInsertDTO loginUserDto) {
        String token = usuarioService.authenticateUser(loginUserDto);

        return new ResponseEntity<>(token, HttpStatus.OK);
    }



}

package com.perdidoseachados.perdidoseachados.Controllers;

import com.perdidoseachados.perdidoseachados.DTOs.*;
import com.perdidoseachados.perdidoseachados.Entidades.Item;
import com.perdidoseachados.perdidoseachados.Entidades.Usuario;
import com.perdidoseachados.perdidoseachados.Servicies.AuthService;
import com.perdidoseachados.perdidoseachados.Servicies.UsuarioService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/usuarios")
public class UsuarioController{
    @Autowired
    UsuarioService usuarioService;

    @Autowired
    AuthService authService;

    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> findAll(){
        return  ResponseEntity.ok(usuarioService.findAll()) ;
    }

    @PostMapping(value = "/registar")
    public ResponseEntity <UsuarioDTO> insert(@RequestBody UsuarioInsertDTO usuarioDTO){
        UsuarioDTO userDTO = usuarioService.insert(usuarioDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").
                buildAndExpand(userDTO.getId()).toUri();
        return ResponseEntity.created(uri).body(userDTO);
    }

    @PostMapping(value = "/reset")
    public ResponseEntity<GenerateResponse> resetPassword(HttpServletRequest request, @RequestBody UsuarioDTO userEmail) {
        GenerateResponse generateResponse = usuarioService.resetPassword(request,userEmail.getEmail());

        return  ResponseEntity.ok(generateResponse);
    }

    @GetMapping(value = "/usuario-logado")
    public UsuarioDTO usuarioLogado(){
        Usuario usuario = authService.authenticateed();

        return new UsuarioDTO(usuario);

    }


    @PutMapping(value ="/update/{id}")
    public ResponseEntity <UsuarioDTO> update (@PathVariable Long id,@RequestBody UsuarioDTO usuarioDTO){
        usuarioDTO = usuarioService.update(id,usuarioDTO);
        return  ResponseEntity.ok(usuarioDTO);
    }

    @PutMapping(value ="/block/{id}")
    public ResponseEntity  blockUser (@PathVariable Long id){
         usuarioService.blockUser(id);
        return  ResponseEntity.ok("Usuario bloqueado");
    }
    
    @PutMapping(value ="/unblockUser/{id}")
    public ResponseEntity  unblockUser (@PathVariable Long id){
         usuarioService.unblockUser(id);
        return  ResponseEntity.ok(" Usuario desbloqueado");
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
    public ResponseEntity<ResponseTokenDTO> authenticateUser(@RequestBody UsuarioInsertDTO loginUserDto) {
        String token = usuarioService.authenticateUser(loginUserDto);
        HttpStatus httpStatus;
        if(token == "Usuário está bloqueado, entre em contacto com o helpdesk"){
             httpStatus = HttpStatus.CREATED;
        }else{
            httpStatus = HttpStatus.OK;
        }



        return new ResponseEntity<>( new ResponseTokenDTO(token), httpStatus);
    }



}

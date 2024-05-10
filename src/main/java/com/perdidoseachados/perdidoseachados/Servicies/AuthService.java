package com.perdidoseachados.perdidoseachados.Servicies;

import com.perdidoseachados.perdidoseachados.Entidades.Usuario;
import com.perdidoseachados.perdidoseachados.Repository.UsuarioRepository;
import com.perdidoseachados.perdidoseachados.Servicies.exeptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


@Service
public class AuthService {


    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario authenticateed(){

        try {
            String username = SecurityContextHolder.getContext().getAuthentication().getName();

            return  usuarioRepository.findByEmail(username).orElseThrow(() -> new ResourceNotFoundException("Ususrio nao encontrado"));

        }
        catch (Exception e){
            throw  new ResourceNotFoundException("Ususrio nao encontrado");
        }

    };

    public  void valideSelfOrAdmin( Long id){
        Usuario usuario = authenticateed();

        if (!usuario.getId().equals(id)  && !usuario.hasRole("ROLE_ADMIN")){

            throw  new RuntimeException("Acces denied");

        }

    }
    


}

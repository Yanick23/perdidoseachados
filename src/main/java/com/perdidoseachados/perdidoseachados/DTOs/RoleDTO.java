package com.perdidoseachados.perdidoseachados.DTOs;

import com.perdidoseachados.perdidoseachados.Entidades.Item;
import com.perdidoseachados.perdidoseachados.Entidades.Role;
import com.perdidoseachados.perdidoseachados.Entidades.Usuario;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class RoleDTO {

    private Long id;
    private String authority;

    public RoleDTO(Role role){
        this.setId(role.getId());
        this.setAuthority(role.getAuthority());
    }

    private List<UsuarioDTO> usuariosDTOs;

    public RoleDTO(Role role, List<Usuario> usuarios){
        this(role);
        usuarios.forEach( usuario -> usuariosDTOs.add(new UsuarioDTO(usuario)));
    }

}

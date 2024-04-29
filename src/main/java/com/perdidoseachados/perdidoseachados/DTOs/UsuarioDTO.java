package com.perdidoseachados.perdidoseachados.DTOs;

import com.perdidoseachados.perdidoseachados.Entidades.Item;
import com.perdidoseachados.perdidoseachados.Entidades.Role;
import com.perdidoseachados.perdidoseachados.Entidades.Usuario;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@NoArgsConstructor
@Data
public class
UsuarioDTO {
    private Long  id;
    private String primeiroNome;
    private  String email;
    private String segundoNome;
    private String password;
    private String  telefone;
    @ManyToOne
    @JoinColumn(name = "role_id")
    private RoleDTO roleDTO;
    private String foto;
    private boolean estado_da_conta = false;
    private List<ItemDTO> itensDTOs;

    public UsuarioDTO(Usuario usuario){
        this.setId(usuario.getId());
        this.setPrimeiroNome(usuario.getPrimeiro_nome());
        this.setSegundoNome(usuario.getSegundo_nome());
        this.setPassword(usuario.getPassword());
        this.setTelefone(usuario.getTelefone());
        this.setFoto(usuario.getFoto());
        this.setEmail(usuario.getEmail());
        this.setRoleDTO(new RoleDTO(usuario.getRole()));
        this.setEstado_da_conta(usuario.isEstadoDaConta());
    }

    public UsuarioDTO(Usuario usuario, List <Item> items){
        this(usuario);
        items.forEach(item -> itensDTOs.add(new ItemDTO(item)));
    }

}

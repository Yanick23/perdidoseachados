package com.perdidoseachados.perdidoseachados.Entidades;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Usuario implements UserDetails, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long  id;
    private String primeiro_nome;
    private String email;
    private String segundo_nome;
    @Column(length = 60)
    private String password;
    private String  telefone;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id")
    private Role role;
    @Column(length = 255)
    private String foto;
    @OneToMany(mappedBy = "usuario")
    private List<Item> itens;
    private boolean estadoDaConta = false;

    @OneToMany(mappedBy = "usuario")
    private List <Notificacao> notificacoes;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.getAuthority()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public boolean hasRole(String roleNme){
        if (this.role.getAuthority().equals(roleNme)){
            return  true;

        }
        return  false;
    }


}



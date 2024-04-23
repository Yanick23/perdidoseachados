package com.perdidoseachados.perdidoseachados.Entidades;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long  id;
    private String primeiroNome;
    private String segundoNome;
    private String password;
    private String  telefone;
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;
    private String foto;
    @OneToMany(mappedBy = "usuario")
    private List<Item> itens;

}



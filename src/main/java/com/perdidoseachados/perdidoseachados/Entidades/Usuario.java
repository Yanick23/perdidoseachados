package com.perdidoseachados.perdidoseachados.Entidades;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Usuario  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long  id;
    private String primeiro_nome;
    private String segundo_nome;
    @Column(length = 60)
    private String password;
    private String  telefone;
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;
    private String foto;
    @OneToMany(mappedBy = "usuario")
    private List<Item> itens;
    private boolean estado_da_conta = false;

}



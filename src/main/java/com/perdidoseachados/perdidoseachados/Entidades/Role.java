package com.perdidoseachados.perdidoseachados.Entidades;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class Role implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String authority;
    @OneToMany(mappedBy = "role")
    private List<Usuario> usuarios;
}

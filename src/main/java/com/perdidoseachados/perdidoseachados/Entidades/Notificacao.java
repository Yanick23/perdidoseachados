package com.perdidoseachados.perdidoseachados.Entidades;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
public class Notificacao {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String texto;
    private boolean Lida;
    private  String  route;
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
}

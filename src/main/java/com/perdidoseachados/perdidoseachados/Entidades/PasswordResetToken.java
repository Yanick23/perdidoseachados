package com.perdidoseachados.perdidoseachados.Entidades;

import com.perdidoseachados.perdidoseachados.Entidades.Usuario;
import jakarta.persistence.*;

import java.util.Date;


@Entity
public class PasswordResetToken {

    private static final int EXPIRATION = 60 * 24;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String token;

    @OneToOne(targetEntity = Usuario.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private Usuario user;

    private Date expiryDate;


    public PasswordResetToken(Usuario usuario, String token){
        this.user = usuario;
        this.token = token;
    }
}

package com.perdidoseachados.perdidoseachados.DTOs;


import com.perdidoseachados.perdidoseachados.Entidades.Notificacao;
import com.perdidoseachados.perdidoseachados.Entidades.Usuario;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class NotificacaoDTO {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String texto;
    private boolean Lida;
    private  String  route;
    private UsuarioDTO usuarioDTO;

    public  NotificacaoDTO(Notificacao notificacao){
        this.setId(notificacao.getId());
        this.setLida(notificacao.isLida());
        this.setUsuarioDTO(new UsuarioDTO(notificacao.getUsuario()));
        this.setTexto(notificacao.getTexto());
        this.setRoute(notificacao.getRoute());

    }

    public  NotificacaoDTO(Notificacao notificacao, Usuario usuario){
        this(notificacao);
        this.setUsuarioDTO(new UsuarioDTO(usuario));
    }
}

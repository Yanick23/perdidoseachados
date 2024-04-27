package com.perdidoseachados.perdidoseachados.DTOs;


import com.perdidoseachados.perdidoseachados.Entidades.*;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class ItemDTO {


    private Long id;
    private String nome;
    private Boolean estadoDeDevolucao;
    private CategoriaDTO categoriaDTO;
    private LocalizacaoDTO localizacaoDTO;
    private String Descricao;
    private LocalDate dataEhoraEncontradoOuPerdido;
    private LocalDate expriracaoNoFeed;
    private EstadoDTO estadoDTO;
    private String foto;
    private UsuarioDTO usuarioDTO;

    public ItemDTO(Item item){
        this.setId(item.getId());
        this.setEstadoDTO(new EstadoDTO(item.getEstado()));
        this.setEstadoDeDevolucao(item.getEstadoDeDevolucao());
        this.setCategoriaDTO(new CategoriaDTO(item.getCategoria()));
        this.setNome(item.getNome());
        this.setLocalizacaoDTO(new LocalizacaoDTO(item.getLocalizacao()));
        this.setFoto(item.getFoto());
        this.setExpriracaoNoFeed(item.getExpriracaoNoFeed());
        this.setDataEhoraEncontradoOuPerdido(item.getDataEhoraEncontradoOuPerdido());
        this.setEstadoDeDevolucao(item.getEstadoDeDevolucao());
    }

    public ItemDTO(Item item, Usuario usuario){
        this.setUsuarioDTO(new UsuarioDTO(usuario));
    }

}
package com.perdidoseachados.perdidoseachados.DTOs;


import com.perdidoseachados.perdidoseachados.Entidades.*;
import com.perdidoseachados.perdidoseachados.constantes.EstadoDeDevolucao;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
public class ItemDTO {


    private Long id;
    private String nome;
    private EstadoDeDevolucao estadoDeDevolucao;
    private CategoriaDTO categoriaDTO;
    private LocalizacaoDTO localizacaoDTO;
    private String Descricao;
    private Instant dataEhoraEncontradoOuPerdido;
    private Instant expriracaoNoFeed;
    private EstadoDTO estadoDTO;
    private String foto;
    private UsuarioDTO usuarioDTO;
    private Instant datapublicacao;


    public ItemDTO(Item item){
        this.setId(item.getId());
        this.setEstadoDTO(new EstadoDTO(item.getEstado()));
        this.setEstadoDeDevolucao(item.getEstadoDeDevolucao());
        this.setCategoriaDTO(new CategoriaDTO(item.getCategoria()));
        this.setNome(item.getNome());
        this.setLocalizacaoDTO(new LocalizacaoDTO(item.getLocalizacao()));
        this.setFoto(item.getFoto());
        this.setExpriracaoNoFeed(item.getExpiracaoNoFeed());
        this.setDataEhoraEncontradoOuPerdido(item.getDataEhoraEncontradoOuPerdido());
        this.setDescricao(item.getDescricao());
        this.setDatapublicacao(item.getDatapublicacao());

    }

    public ItemDTO(Item item, Usuario usuario){
        this(item);
        this.setUsuarioDTO(new UsuarioDTO(usuario));
    }

}

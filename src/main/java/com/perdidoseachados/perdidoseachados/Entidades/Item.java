package com.perdidoseachados.perdidoseachados.Entidades;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@Data
@Entity
public class Item {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private Boolean estadoDeDevolucao;
    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;
    @ManyToOne
    @JoinColumn(name = "localizacao_id")
    private Localizacao localizacao;
    private String Descricao;
    private LocalDate dataEhoraEncontradoOuPerdido;
    private LocalDate expriracaoNoFeed;
    @ManyToOne
    @JoinColumn(name = "estado_id")
    private Estado estado;
    private String foto;
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;


}

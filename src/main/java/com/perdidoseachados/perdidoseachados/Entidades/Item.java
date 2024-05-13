package com.perdidoseachados.perdidoseachados.Entidades;

import com.perdidoseachados.perdidoseachados.constantes.EstadoDeDevolucao;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
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
    private EstadoDeDevolucao estadoDeDevolucao;
    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;
    @ManyToOne
    @JoinColumn(name = "localizacao_id")
    private Localizacao localizacao;
    private String Descricao;
    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Instant dataEhoraEncontradoOuPerdido;

    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Instant expiracaoNoFeed;
    @ManyToOne
    @JoinColumn(name = "estado_id")
    private Estado estado;
    private String foto;
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;




}

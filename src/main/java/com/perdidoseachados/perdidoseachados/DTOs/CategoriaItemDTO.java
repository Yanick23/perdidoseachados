package com.perdidoseachados.perdidoseachados.DTOs;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class CategoriaItemDTO {
    private String nomeCategoria;
    private Long totalItensRegistrados;
}

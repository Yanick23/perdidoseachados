package com.perdidoseachados.perdidoseachados.DTOs;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class LocalizacaoItensDTO {
    private String nomeLocalizacao;
    private Long totalItensRegistrados;
}

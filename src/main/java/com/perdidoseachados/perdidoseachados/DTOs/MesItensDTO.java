package com.perdidoseachados.perdidoseachados.DTOs;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MesItensDTO {
    int mes;
    Long totalItensRegistrados;

}

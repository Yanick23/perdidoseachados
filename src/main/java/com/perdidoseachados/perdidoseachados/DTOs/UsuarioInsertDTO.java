package com.perdidoseachados.perdidoseachados.DTOs;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UsuarioInsertDTO extends  UsuarioDTO{
    private  String password;
}

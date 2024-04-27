package com.perdidoseachados.perdidoseachados.DTOs;

import com.perdidoseachados.perdidoseachados.Entidades.Item;
import com.perdidoseachados.perdidoseachados.Entidades.Localizacao;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class LocalizacaoDTO {

    private Long id;
    private String nome;
    private List<ItemDTO> itensDTOS;

    public  LocalizacaoDTO(Localizacao localizacao){
        this.setId(localizacao.getId());
        this.setNome(localizacao.getNome());
    }

    public  LocalizacaoDTO (Localizacao localizacao, List <Item> items){
        this(localizacao);
        items.forEach( item -> itensDTOS.add(new ItemDTO(item)));
    }

}

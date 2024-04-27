package com.perdidoseachados.perdidoseachados.DTOs;

import com.perdidoseachados.perdidoseachados.Entidades.Item;
import com.perdidoseachados.perdidoseachados.Entidades.Estado;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@NoArgsConstructor
@Data
public class EstadoDTO {

    private Long id;
    private String nome;
    private List<ItemDTO> itensDTOS;

    public  EstadoDTO(Estado estado){
        this.setId(estado.getId());
        this.setNome(estado.getNome());
    }

    public  EstadoDTO (Estado Estado, List <Item> items){
        this(Estado);
        items.forEach( item -> itensDTOS.add(new ItemDTO(item)));
    }
}

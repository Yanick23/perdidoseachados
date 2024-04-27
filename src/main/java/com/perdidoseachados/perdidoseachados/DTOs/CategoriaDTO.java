package com.perdidoseachados.perdidoseachados.DTOs;

import com.perdidoseachados.perdidoseachados.Entidades.Categoria;
import com.perdidoseachados.perdidoseachados.Entidades.Item;
import com.perdidoseachados.perdidoseachados.Entidades.Localizacao;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@NoArgsConstructor
public class CategoriaDTO {



    private Long id;
    private String nome;
    private List<ItemDTO> itensDTOs;

    public  CategoriaDTO(Categoria categoria){
        this.setId(categoria.getId());
        this.setNome(categoria.getNome());
    }

    public  CategoriaDTO (Categoria categoria, List <Item> items){
        this(categoria);
        items.forEach( item -> itensDTOs.add(new ItemDTO(item)));
    }
}

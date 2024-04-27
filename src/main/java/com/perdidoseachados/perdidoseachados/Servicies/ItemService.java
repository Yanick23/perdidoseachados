package com.perdidoseachados.perdidoseachados.Servicies;
import com.perdidoseachados.perdidoseachados.DTOs.CategoriaDTO;
import com.perdidoseachados.perdidoseachados.DTOs.ItemDTO;
import com.perdidoseachados.perdidoseachados.Entidades.*;
import com.perdidoseachados.perdidoseachados.Repository.*;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ItemService {

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    LocalizacaoRepository localizacaoRepository;

    @Autowired
    CategoriaRepository categoriaRepository;

    @Autowired
    EstadoRepository estadoRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Transactional
    public List <ItemDTO>  findAll(){
        List<Item> donativoOtorgas = itemRepository.findAll();
        return donativoOtorgas.stream().map(x -> new ItemDTO(x)).collect(Collectors.toList());
    }

    @Transactional
    public ItemDTO findById(Long id){
        Optional <Item> optional = itemRepository.findById(id);
        Item entity = optional.orElseThrow(() -> new EntityNotFoundException("Item nao encontrado"));
        return new ItemDTO(entity) ;
    }

    @Transactional
    public ItemDTO Insert (ItemDTO itemDTO){
        Item entity = new Item();
        mapDTOTOItem(entity,itemDTO);
        entity = itemRepository.save(entity);
        return new ItemDTO(entity);
    }

    public void mapDTOTOItem(Item entity, ItemDTO itemDTO){

        if (itemDTO.getLocalizacaoDTO() != null){
            Localizacao localizacao = localizacaoRepository.getReferenceById(itemDTO.getLocalizacaoDTO().getId());
            entity.setLocalizacao(localizacao);
        }
        if(itemDTO.getCategoriaDTO() != null){
            Categoria categoria = categoriaRepository.getReferenceById(itemDTO.getCategoriaDTO().getId());
            entity.setCategoria(categoria);
        }

        if(itemDTO.getUsuarioDTO() != null ){
            Usuario usuario = usuarioRepository.getReferenceById(itemDTO.getCategoriaDTO().getId());
            entity.setUsuario(usuario);
        }

        if(itemDTO.getEstadoDTO() != null ){
            Estado estado = estadoRepository.getReferenceById(itemDTO.getEstadoDTO().getId());
            entity.setEstado(estado);
        }

        entity.setDescricao(itemDTO.getDescricao());
        entity.setFoto(itemDTO.getFoto());
        entity.setEstadoDeDevolucao(itemDTO.getEstadoDeDevolucao());
        entity.setDataEhoraEncontradoOuPerdido(itemDTO.getDataEhoraEncontradoOuPerdido());
        entity.setNome(itemDTO.getNome());
        entity.setExpriracaoNoFeed(itemDTO.getExpriracaoNoFeed());












    }


}

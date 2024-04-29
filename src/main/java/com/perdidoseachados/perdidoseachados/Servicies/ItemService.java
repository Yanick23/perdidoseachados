package com.perdidoseachados.perdidoseachados.Servicies;
import com.perdidoseachados.perdidoseachados.DTOs.ItemDTO;
import com.perdidoseachados.perdidoseachados.Entidades.*;
import com.perdidoseachados.perdidoseachados.Repository.*;
import com.perdidoseachados.perdidoseachados.Servicies.exeptions.DataBaseExeption;
import com.perdidoseachados.perdidoseachados.Servicies.exeptions.ResourceNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

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
        List<Item> itens = itemRepository.findAll();
        return itens.stream().map(x -> new ItemDTO(x,x.getUsuario())).collect(Collectors.toList());
    }

    @Transactional
    public ItemDTO findById(Long id){
        Optional <Item> optional = itemRepository.findById(id);
        Item entity = optional.orElseThrow(() -> new ResourceNotFoundException("Item "+id +" noo encontrado"));
        return new ItemDTO(entity,entity.getUsuario()) ;
    }

    @Transactional
    public ItemDTO Insert (ItemDTO itemDTO){
        Item entity = new Item();
        mapDTOTOItem(entity,itemDTO);
        entity = itemRepository.save(entity);
        return new ItemDTO(entity);
    }

    @Transactional
    public ItemDTO Update (Long id,ItemDTO itemDTO){

        Item entity = itemRepository.findById(id).orElseThrow( () -> new ResourceNotFoundException("Item nao encontrado, falha ao fazer update do item"));
        mapDTOTOItem(entity,itemDTO);
        entity = itemRepository.save(entity);
        return new ItemDTO(entity);

    }

    @Transactional
    public void delete(Long id) {
        try {
            itemRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Id not found: " + id);
        } catch (DataIntegrityViolationException ee) {
            throw new DataBaseExeption("Integrity violation");
        }
    }

    public void mapDTOTOItem(Item entity, ItemDTO itemDTO){

        if (itemDTO.getLocalizacaoDTO() != null){
            Localizacao localizacao = localizacaoRepository.findById(itemDTO.getLocalizacaoDTO().getId())
            .orElseThrow(() -> new ResourceNotFoundException(" Localicao nao encontrado (ItemService -> mapDTOTOItem() )"));
            entity.setLocalizacao(localizacao);
        }
        if(itemDTO.getCategoriaDTO() != null){
            Categoria categoria = categoriaRepository.findById(itemDTO.getCategoriaDTO().getId())
            .orElseThrow(() -> new ResourceNotFoundException(" Categoria nao encontrado (ItemService -> mapDTOTOItem() )"));
            entity.setCategoria(categoria);
        }

        if(itemDTO.getUsuarioDTO() != null ){
            Usuario usuario = usuarioRepository.findById(itemDTO.getCategoriaDTO().getId())
            .orElseThrow(() -> new ResourceNotFoundException(" Usuario nao encontrado (ItemService -> mapDTOTOItem() )"));
            entity.setUsuario(usuario);
        }

        if(itemDTO.getEstadoDTO() != null ){
            Estado estado = estadoRepository.findById(itemDTO.getEstadoDTO().getId())
            .orElseThrow(() -> new ResourceNotFoundException(" Estado nao encontrado (ItemService -> mapDTOTOItem() )"));
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

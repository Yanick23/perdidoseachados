package com.perdidoseachados.perdidoseachados.Servicies;
import com.perdidoseachados.perdidoseachados.DTOs.CategoriaItemDTO;
import com.perdidoseachados.perdidoseachados.DTOs.ItemDTO;
import com.perdidoseachados.perdidoseachados.DTOs.LocalizacaoItensDTO;
import com.perdidoseachados.perdidoseachados.DTOs.MesItensDTO;
import com.perdidoseachados.perdidoseachados.Entidades.*;
import com.perdidoseachados.perdidoseachados.Repository.*;
import com.perdidoseachados.perdidoseachados.Servicies.exeptions.DataBaseExeption;
import com.perdidoseachados.perdidoseachados.Servicies.exeptions.ResourceNotFoundException;
import com.perdidoseachados.perdidoseachados.constantes.EstadoDeDevolucao;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
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

   @Autowired
   AuthService authService;

    @Transactional
    public List <ItemDTO>  findAll(){
        List<Item> itens = itemRepository.findAllOrderedByDatapublicacao();
        return itens.stream().map(x -> new ItemDTO(x,x.getUsuario())).collect(Collectors.toList());
    }

    public List<ItemDTO> findItemsByFilters(EstadoDeDevolucao estado, String nomeCategoria,
                                         String nomeLocalizacao, String nomeEstado) {
        return  itemRepository.findItemsByFilters(estado, Instant.now(), nomeCategoria, nomeLocalizacao, nomeEstado)
                .stream().map(item -> new ItemDTO(item)).collect(Collectors.toList());
    }
   //l

    @Transactional
    public List <ItemDTO>  findItemsForFeed(){
        List<Item> itens = itemRepository.findItemsForFeed(EstadoDeDevolucao.NAO_DEVOLVIDO, Instant.now());
        return itens.stream().map(x -> new ItemDTO(x,x.getUsuario())).collect(Collectors.toList());
    }

    public List<ItemDTO> itensDeUsuarioCorrente(){
        Usuario usuario = authService.authenticateed();
        List<Item> itens = itemRepository.findByUsuario(usuario);

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
        Usuario usuario = authService.authenticateed();


        mapDTOTOItem(entity,itemDTO);
        entity.setEstadoDeDevolucao(EstadoDeDevolucao.NAO_DEVOLVIDO);
         entity.setDatapublicacao(Instant.now());

        entity.setUsuario(usuario);
        entity = itemRepository.save(entity);
        return new ItemDTO(entity);
    }


    @Transactional
    public long countTotalItemsRegistered(){
        long quantidade = itemRepository.countTotalItemsRegistered();
        return quantidade;
    }

    @Transactional
    public ItemDTO Update (Long id,ItemDTO itemDTO){

        Item entity = itemRepository.findById(id).orElseThrow( () -> new ResourceNotFoundException("Item nao encontrado, falha ao fazer update do item"));

        if (authService.authenticateed().getId() == entity.getUsuario().getId() || authService.authenticateed().hasRole("ADMIN")){
            mapDTOTOItem(entity,itemDTO)
            ;entity.setEstadoDeDevolucao(EstadoDeDevolucao.NAO_DEVOLVIDO);
            entity.setDatapublicacao(Instant.now());
            entity = itemRepository.save(entity);
            return new ItemDTO(entity);
        }

        return  null;



    }

    @Transactional
    public void delete(Long id) {
        try {
            Item entity = itemRepository.findById(id).orElseThrow( () -> new ResourceNotFoundException("Item nao encontrado, falha ao fazer update do item"));
            if (authService.authenticateed().getId() == entity.getUsuario().getId() || authService.authenticateed().hasRole("ADMIN")){
                itemRepository.deleteById(id);
            }

        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Id not found: " + id);
        } catch (DataIntegrityViolationException ee) {
            throw new DataBaseExeption("Integrity violation");
        }
    }

    public List<CategoriaItemDTO> getItemsRegisteredByCategory() {
        List<Object[]> results = itemRepository.countItemsRegisteredByCategory();
        List<CategoriaItemDTO> itemList = new ArrayList<>();
        for (Object[] result : results) {
            String nomeCategoria = (String) result[0];
            Long totalItensRegistrados = (Long) result[1];
            itemList.add(new CategoriaItemDTO(nomeCategoria, totalItensRegistrados));
        }
        return itemList;
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
            if(itemDTO.getExpriracaoNoFeed()  != null) {
                entity.setExpiracaoNoFeed(itemDTO.getExpriracaoNoFeed());

            }





    }


    public List<MesItensDTO> getItemsRegisteredByMonth(int year) {
        List<Object[]> results = itemRepository.countItemsRegisteredByMonth(year);
        List<MesItensDTO> itemList = new ArrayList<>();
        for (Object[] result : results) {
            Integer mes = (Integer) result[0];
            Long totalItensRegistrados = (Long) result[1];
            itemList.add(new MesItensDTO(mes, totalItensRegistrados));
        }
        return itemList;
    }

    public List<LocalizacaoItensDTO> getItemsRegisteredByLocation() {
        List<Object[]> results = itemRepository.countItemsRegisteredByLocation();
        List<LocalizacaoItensDTO> itemList = new ArrayList<>();
        for (Object[] result : results) {
            String nomeLocalizacao = (String) result[0];
            Long totalItensRegistrados = (Long) result[1];
            itemList.add(new LocalizacaoItensDTO(nomeLocalizacao, totalItensRegistrados));
        }
        return itemList;
    }
}

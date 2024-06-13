package com.perdidoseachados.perdidoseachados.Controllers;

import com.perdidoseachados.perdidoseachados.DTOs.*;
import com.perdidoseachados.perdidoseachados.Servicies.ItemService;
import com.perdidoseachados.perdidoseachados.constantes.EstadoDeDevolucao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;


@RestController
@RequestMapping(value = "/itens")
public class ItemController<itemDTO> {

    @Autowired
    ItemService itemService;

    @GetMapping
    public ResponseEntity <List<ItemDTO>> findAll(){
        return  ResponseEntity.ok(itemService.findAll()) ;
    }

    @GetMapping(value= "/localizacao/{id}")
    public ResponseEntity <List<ItemDTO>> findByLocalizacao(@PathVariable Long id){
        return  ResponseEntity.ok(itemService.findByLocalizacao(id)) ;
    }


    @GetMapping("/feed")
    public ResponseEntity <List<ItemDTO>> findItemsForFeed(){
        return  ResponseEntity.ok(itemService.findItemsForFeed()) ;
    }

    @PostMapping("/registar")
    public ResponseEntity <ItemDTO> insert( @RequestBody ItemDTO itemDTO){
        itemDTO = itemService.Insert(itemDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").
                buildAndExpand(itemDTO.getId()).toUri();
        return ResponseEntity.created(uri).body(itemDTO);
    }

    @PutMapping(value ="/update/{id}")
    public ResponseEntity <ItemDTO> update (@PathVariable Long id,@RequestBody ItemDTO itemDTO){
        itemDTO = itemService.Update(id,itemDTO);
        return  ResponseEntity.ok(itemDTO);
    }

    @GetMapping("/itens_do_usuario_logado")
    public ResponseEntity<List<ItemDTO>> findByUsuario()  {
   List <ItemDTO> list = itemService.itensDeUsuarioCorrente();
        return ResponseEntity.ok( list);

    }
    @GetMapping(value = "/{id}")
    public ResponseEntity <ItemDTO> findById(@PathVariable Long id)  {
        ItemDTO ItemDTO = itemService.findById(id);
        return ResponseEntity.ok(ItemDTO);

    }

    @DeleteMapping(value = "/delete/{id}")
    public  ResponseEntity delete(@PathVariable Long id){
        itemService.delete(id);
        return   ResponseEntity.noContent().build();
    }



    @GetMapping("/by-month")
    public ResponseEntity <List<MesItensDTO>> getItemsRegisteredByMonth(@RequestParam int year) {
        return ResponseEntity.ok(itemService.getItemsRegisteredByMonth(year));
    }

    @GetMapping("/by-category")
    public List<CategoriaItemDTO> getItemsRegisteredByCategory() {
        return itemService.getItemsRegisteredByCategory();
    }

    @GetMapping("/by-location")
    public List<LocalizacaoItensDTO> getItemsRegisteredByLocation() {
        return itemService.getItemsRegisteredByLocation();
}

    @GetMapping("/current-week")
    public ResponseEntity<Long> getItemsRegisteredInCurrentWeek() {

        Long TotalSemana = itemService.getItemsRegisteredInCurrentWeek();;
        return ResponseEntity.ok(TotalSemana);
    }

    @GetMapping("/current-month")
    public ResponseEntity<TotalMesCorrente> getItemsRegisteredInCurrentMonth() {
        Long TotalMes = itemService.getItemsRegisteredInCurrentMonth();
        return ResponseEntity.ok( new TotalMesCorrente(TotalMes));
    }

    @GetMapping("/total-tens")
    public ResponseEntity <TotalDeItem> countTotalItemsRegistered(){
        return ResponseEntity.ok(itemService.countTotalItemsRegistered());
    }



    @GetMapping("/filtrar")
    public ResponseEntity<List<ItemDTO>> findItemsByFilters(
            @RequestParam(required = false) String estadoDeDevolucaoStr,
            @RequestParam(required = false) String categoria,
            @RequestParam(required = false) String localizacao,
            @RequestParam(required = false) String estado) {

        EstadoDeDevolucao estadoDeDevolucao = null;
        if (estadoDeDevolucaoStr != null && !estadoDeDevolucaoStr.isEmpty()) {
            estadoDeDevolucao = EstadoDeDevolucao.valueOf(estadoDeDevolucaoStr.toUpperCase());
        }


        List<ItemDTO> filteredItems = itemService.findItemsByFilters(estadoDeDevolucao, categoria, localizacao, estado);

        return ResponseEntity.ok(filteredItems);
    }

}

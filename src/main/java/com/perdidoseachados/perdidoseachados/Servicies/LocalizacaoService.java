package com.perdidoseachados.perdidoseachados.Servicies;


import com.perdidoseachados.perdidoseachados.DTOs.CategoriaDTO;
import com.perdidoseachados.perdidoseachados.DTOs.LocalizacaoDTO;
import com.perdidoseachados.perdidoseachados.Entidades.Categoria;
import com.perdidoseachados.perdidoseachados.Entidades.Localizacao;
import com.perdidoseachados.perdidoseachados.Repository.LocalizacaoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LocalizacaoService {

    @Autowired
    LocalizacaoRepository localizacaoRepository;
    @Autowired
    public List<LocalizacaoDTO> findAll() {
        List<Localizacao> localizacoes = localizacaoRepository.findAll();
        return localizacoes.stream().map(categoria -> new LocalizacaoDTO(categoria)).collect(Collectors.toList());
    }

    @Transactional
    public LocalizacaoDTO insert (LocalizacaoDTO localizacaoDTO){
        Localizacao entity = new Localizacao();
        entity.setNome(localizacaoDTO.getNome());
        entity = localizacaoRepository.save(entity);
        return new LocalizacaoDTO(entity);
    }
}

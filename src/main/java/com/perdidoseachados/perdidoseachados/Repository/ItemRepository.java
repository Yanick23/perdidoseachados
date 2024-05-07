package com.perdidoseachados.perdidoseachados.Repository;
import com.perdidoseachados.perdidoseachados.Entidades.Item;
import com.perdidoseachados.perdidoseachados.Entidades.Usuario;
import com.perdidoseachados.perdidoseachados.constantes.EstadoDeDevolucao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.User;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item, Long> {

    List <Item> findByUsuario(Usuario user);

    @Query("SELECT i FROM Item i WHERE i.estadoDeDevolucao = :estado AND i.expiracaoNoFeed > :agora")
    List<Item> findItemsForFeed(@Param("estado") EstadoDeDevolucao estado, @Param("agora") Instant agora);

    @Query("SELECT i FROM Item i WHERE (:estado IS NULL OR i.estadoDeDevolucao = :estado) " +
            "AND (:agora IS NULL OR i.expiracaoNoFeed > :agora) " +
            "AND (:nomeCategoria IS NULL OR i.categoria.nome = :nomeCategoria) " +
            "AND (:nomeLocalizacao IS NULL OR i.localizacao.nome = :nomeLocalizacao) " +
            "AND (:nomeEstado IS NULL OR i.estado.nome = :nomeEstado) ")
    List<Item> findItemsByFilters(@Param("estado") EstadoDeDevolucao estado,
                                  @Param("agora") Instant agora,
                                  @Param("nomeCategoria") String nomeCategoria,
                                  @Param("nomeLocalizacao") String nomeLocalizacao,
                                  @Param("nomeEstado") String nomeEstado);





}

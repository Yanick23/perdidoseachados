package com.perdidoseachados.perdidoseachados.Repository;
import com.perdidoseachados.perdidoseachados.Entidades.Estado;
import com.perdidoseachados.perdidoseachados.Entidades.Item;
import com.perdidoseachados.perdidoseachados.Entidades.Localizacao;
import com.perdidoseachados.perdidoseachados.Entidades.Usuario;
import com.perdidoseachados.perdidoseachados.constantes.EstadoDeDevolucao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.User;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item   , Long> {

    List <Item> findByUsuario(Usuario user);

    @Query("SELECT i FROM Item i ORDER BY i.datapublicacao DESC")
    List<Item> findAllOrderedByDatapublicacao();

    @Query("SELECT i FROM Item i WHERE i.estadoDeDevolucao = :estado AND i.expiracaoNoFeed > :agora ORDER BY i.datapublicacao DESC")
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



    
    List<Item> findByEstado(Estado estado);
    List<Item> findByLocalizacao(Localizacao localizacao);

    @Query("SELECT COUNT(*) FROM Item")
    long countTotalItemsRegistered();
    
    @Query("SELECT COUNT(*) FROM Item i WHERE i.datapublicacao BETWEEN :startOfMonth AND :endOfMonth")
    Long findAllItemsRegisteredInCurrentMonth(@Param("startOfMonth") Instant startOfMonth, @Param("endOfMonth") Instant endOfMonth);

    @Query("SELECT i FROM Item i WHERE i.datapublicacao BETWEEN :startOfWeek AND :endOfWeek")
    Long findAllItemsRegisteredInCurrentWeek(@Param("startOfWeek") Instant startOfWeek, @Param("endOfWeek") Instant endOfWeek);



    @Query("SELECT EXTRACT(MONTH FROM i.datapublicacao) AS mes, COUNT(*) AS total_itens_registrados " +
            "FROM Item i WHERE EXTRACT(YEAR FROM i.datapublicacao) = :ano GROUP BY mes")
    List<Object[]> countItemsRegisteredByMonth(@Param("ano") int ano);


    @Query("SELECT i.categoria.nome AS nome_categoria, COUNT(*) AStotal_itens_registrados " +"FROM Item i GROUP BY nome_categoria")
    List<Object[]> countItemsRegisteredByCategory();


    @Query("SELECT i.localizacao.nome AS nome_localizacao, COUNT(*) AS total_itens_registrados FROM Item i GROUP BY nome_localizacao")
    List<Object[]> countItemsRegisteredByLocation();


    @Query("SELECT i.estado.nome AS nome_estado, COUNT(*) AS total_itens_registrados FROM Item i GROUP BY nome_estado")
    List<Object[]> countItemsRegisteredByState();

    @Query("SELECT DATE_TRUNC('day', i.datapublicacao) AS data_criacao, COUNT(*) AS total_itens_registrados FROM Item i GROUP BY data_criacao")
    List<Object[]> countItemsRegisteredByCreationDate();

}

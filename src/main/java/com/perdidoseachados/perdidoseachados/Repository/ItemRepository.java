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

public interface ItemRepository extends JpaRepository<Item, Long> {

    List <Item> findByUsuario(Usuario user);

    @Query("SELECT i FROM Item i ORDER BY i.datapublicacao ASC")
    List<Item> findAllOrderedByDatapublicacao();

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



    
    List<Item> findByEstado(Estado estado);
    List<Item> findByLocalizacao(Localizacao localizacao);


// TODO 1. **Relatório de Estatísticas Gerais de Itens Registrados:** Este relatório pode incluir informações sobre o número total de itens registrados até o momento.

// TODO 2. **Relatório de Itens Registrados por Mês:** Este relatório mostra a quantidade de itens registrados a cada mês, permitindo visualizar tendências ao longo do tempo.

// TODO 3. **Relatório de Itens Registrados por Categoria:** Este relatório divide os itens registrados em diferentes categorias e mostra a distribuição de itens em cada categoria.

// TODO 4. **Relatório de Itens Registrados por Localização:** Este relatório mostra a distribuição geográfica dos itens registrados, permitindo visualizar onde estão concentrados os itens.

// TODO 5. **Relatório de Itens Registrados por Usuário:** Este relatório mostra os itens registrados por cada usuário, permitindo monitorar a atividade de cada usuário.

// TODO 6. **Relatório de Itens Registrados por Estado:** Se os itens tiverem um atributo de estado (por exemplo, "em andamento", "concluído", "pendente"), este relatório pode mostrar a distribuição de itens com base em seus estados.

// TODO 7. **Relatório de Itens Registrados por Prioridade:** Se os itens tiverem uma prioridade associada (por exemplo, "alta", "média", "baixa"), este relatório pode mostrar a distribuição de itens com base em suas prioridades.

// TODO 8. **Relatório de Itens Registrados por Data de Criação:** Este relatório mostra a quantidade de itens registrados em diferentes intervalos de tempo (por exemplo, por dia, por semana, por mês).



}

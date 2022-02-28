package br.com.alura.mvc.demo.repository;

import br.com.alura.mvc.demo.orm.Pedido;
import br.com.alura.mvc.demo.utils.status.SituacaoPedido;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long>, PagingAndSortingRepository<Pedido, Long> {
    @Cacheable("pedidosStatus")
    List<Pedido> findBySituacao(SituacaoPedido situacao, Pageable pageable);

    @Cacheable("pedidosUsuario")
    @Query("SELECT p from Pedido p JOIN p.user u WHERE u.username = :username")
    List<Pedido> findAllByUsuario(@Param("username") String username, Pageable pageable);

    @Cacheable("pedidosStatusUsuario")
    @Query("SELECT p from Pedido p JOIN p.user u WHERE u.username = :username AND p.situacao = :situacao")
    List<Pedido> findByStatusAndUsuario(@Param("situacao") SituacaoPedido status, @Param("username") String username, Pageable pageable);

    @Query("SELECT p FROM Pedido p JOIN p.user u WHERE p.id = :id AND u.username = :username")
    Pedido findByIdAndUser(@Param("id") Long id, @Param("username") String username);
}

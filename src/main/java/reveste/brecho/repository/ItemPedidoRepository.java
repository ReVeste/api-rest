package reveste.brecho.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import reveste.brecho.entity.ItemPedido;
import reveste.brecho.enun.pedido.StatusPedidoEnum;
import reveste.brecho.enun.produto.StatusProdutoEnum;

import java.util.List;
import java.util.Optional;

public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Integer> {


    List<ItemPedido> findByPedidoId(int pedidoId);


    @Modifying @Transactional
    @Query("UPDATE ItemPedido i SET i.quantidade = :quantidade WHERE i.id = :idItemPedido")
    void atualizarQuantidade(Integer idItemPedido, int quantidade);


    @Query(value = "SELECT CASE WHEN COUNT(ip) > 0 THEN TRUE ELSE FALSE END " +
            "FROM ItemPedido ip JOIN ip.produto p JOIN ip.pedido pe " +
            "WHERE pe.id = :pedidoId AND p.id = :produtoId")
    boolean existsByProdutoAndPedido(Integer pedidoId, Integer produtoId);

}

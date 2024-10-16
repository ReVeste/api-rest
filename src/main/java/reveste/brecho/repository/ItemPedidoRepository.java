package reveste.brecho.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import reveste.brecho.entity.itempedido.ItemPedido;

import java.util.List;

public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Integer> {


    List<ItemPedido> findByPedidoId(int pedidoId);
    List<Integer> findIdByPedidoIdAndProdutoId(int pedidoId, int produtoId);
    ItemPedido findByPedidoIdAndProdutoId(int pedidoId, int produtoId);


}

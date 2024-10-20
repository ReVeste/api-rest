package reveste.brecho.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import reveste.brecho.entity.itempedido.ItemPedido;

import java.util.List;

public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Integer> {


    List<ItemPedido> findByPedidoId(int pedidoId);
    @Modifying
    @Transactional
    @Query("UPDATE ItemPedido i SET i.quantidade = :quantidade WHERE i.id = :idItemPedido")
    void atualizarQuantidade(Integer idItemPedido, int quantidade);


}

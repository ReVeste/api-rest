package reveste.brecho.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import reveste.brecho.entity.Pedido;
import reveste.brecho.enun.pedido.StatusPedidoEnum;

import java.util.List;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

    @Modifying
    @Transactional
    @Query("UPDATE Pedido p SET p.valorTotal = :valorTotal WHERE p.id = :idPedido")
    void atualizarValorTotal(Integer idPedido, Double valorTotal);

    @Modifying
    @Transactional
    @Query("UPDATE Pedido p SET p.status = :status WHERE p.id = :idPedido")
    void finalizarPedido(Integer idPedido, StatusPedidoEnum status);

    Pedido findByUsuarioIdAndStatus(Integer usuarioId, StatusPedidoEnum status);

    List<Pedido> findAllByStatus(StatusPedidoEnum status);
}

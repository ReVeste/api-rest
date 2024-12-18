package reveste.brecho.repository;

import jakarta.transaction.Transactional;
import org.springframework.cglib.core.Local;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import reveste.brecho.entity.Pedido;
import reveste.brecho.entity.Produto;
import reveste.brecho.enun.pedido.StatusPedidoEnum;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

    @Modifying
    @Transactional
    @Query("UPDATE Pedido p SET p.valorTotal = :valorTotal WHERE p.id = :idPedido")
    void atualizarValorTotal(Integer idPedido, Double valorTotal);

    @Modifying
    @Transactional
    @Query("UPDATE Pedido p SET p.status = :status, p.dataPagamento = :dataPagamento WHERE p.id = :idPedido")
    void atualizarPedidoPago(Integer idPedido, StatusPedidoEnum status, LocalDate dataPagamento);

    @Modifying
    @Transactional
    @Query("UPDATE Pedido p SET p.status = :status, p.dataConclusao = :dataConclusao WHERE p.id = :idPedido")
    void finalizarPedido(Integer idPedido, StatusPedidoEnum status, LocalDate dataConclusao);

    Pedido findByUsuarioIdAndStatus(Integer usuarioId, StatusPedidoEnum status);

    List<Pedido> findAllByStatus(StatusPedidoEnum status);

    @Query("SELECT p.id FROM Pedido p WHERE p.status = :status AND p.usuario.id = :usuarioId")
    Optional<Integer> findIdPedidoEmAbertoByUsuarioId(StatusPedidoEnum status, Integer usuarioId);

    List<Pedido> findAllByStatusAndUsuarioId(StatusPedidoEnum statusPedidoEnum, Integer idUsuario);

    List<Pedido> findAllByDataPagamentoBetweenAndStatus(
            LocalDate inicio, LocalDate fim, StatusPedidoEnum statusPedidoEnum);

    List<Pedido> findAllByDataConclusaoBetweenAndStatus(
            LocalDate inicio, LocalDate fim, StatusPedidoEnum statusPedidoEnum);

}

package reveste.brecho.repository;

import jakarta.transaction.Transactional;
import org.springframework.cglib.core.Local;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import reveste.brecho.entity.Pedido;
import reveste.brecho.entity.Produto;
import reveste.brecho.enun.pedido.StatusPedidoEnum;
import reveste.brecho.enun.produto.CategoriaEnum;
import reveste.brecho.enun.produto.StatusProdutoEnum;

import java.time.LocalDate;
import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

    List<Produto> findAllByCategoria(CategoriaEnum categoria);

    List<Produto> findAllByStatus(StatusProdutoEnum status);

    @Modifying
    @Transactional
    @Query("UPDATE Produto p SET p.status = :status WHERE p.id IN :idProdutos")
    void finalizarPedido(List<Integer> idProdutos, StatusProdutoEnum status);

    List<Produto> findAllByDataCadastroBetween(LocalDate inicio, LocalDate fim);

}

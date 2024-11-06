package reveste.brecho.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import reveste.brecho.entity.Produto;
import reveste.brecho.enun.pedido.StatusPedidoEnum;
import reveste.brecho.enun.produto.CategoriaEnum;
import reveste.brecho.enun.produto.StatusProdutoEnum;

import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

    List<Produto> findAllByCategoria(CategoriaEnum categoria);

    List<Produto> findAllByStatus(StatusProdutoEnum status);

}

package reveste.brecho.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import reveste.brecho.entity.produto.Produto;

import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

    List<Produto> findAllByCategoria(String categoria);
}

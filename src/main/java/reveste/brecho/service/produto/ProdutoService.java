package reveste.brecho.service.produto;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reveste.brecho.entity.produto.Produto;
import reveste.brecho.repository.ProdutoRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public Produto buscarPorId(int id) {
        Optional<Produto> produtoOpt = produtoRepository.findById(id);

        if (produtoOpt.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado");
        }

        return produtoOpt.get();
    }

    public Produto criar(Produto produto) {
        produto.setId(null);
        return produtoRepository.save(produto);
    }

    public Produto atualizar(int id, Produto produto) {
        if (!produtoRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Não foi localizado um produto com o id especificado");
        }

        produto.setId(id);
        return produtoRepository.save(produto);
    }

    public List<Produto> listar() {
        return produtoRepository.findAll();
    }

    public void deletarPorId(int id) {
        if (!produtoRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado");
        }

        produtoRepository.deleteById(id);
    }









//    public Produto criar(ProdutoCriacaoRequisicaoDto produtoCriacaoRequisicaoDto) {
//
//        Produto novoProduto;
//
//        if (produtoCriacaoRequisicaoDto.getTipo().equals(TipoEnum.RARA)) {
//            novoProduto = new ProdutoEspecial(produtoCriacaoRequisicaoDto);
//        } else {
//            novoProduto = new Produto(produtoCriacaoRequisicaoDto);
//        }
//
//        novoProduto.setId(null);
//        return produtoRepository.save(novoProduto);
//    }
//
//    public Produto atualizarPorId(int id, ProdutoCriacaoRequisicaoDto produto) {
//        if (!produtoRepository.existsById(id)) {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Não foi localizado um produto com o id especificado");
//        }
//
//        Produto produtoExistente = produtoRepository.findById(id).orElse(null);
//
//        if (produto.getTipo().equals(TipoEnum.RARA)) {
//            ProdutoEspecial produtoEspecial;
//
//            if (produtoExistente instanceof ProdutoEspecial) {
//                produtoEspecial = (ProdutoEspecial) produtoExistente;
//            } else {
//                produtoEspecial = new ProdutoEspecial(produto);
//            }
//
//            produtoEspecial.setId(id);
//            return produtoRepository.save(produtoEspecial);
//        }
//
//        produtoExistente.setId(id);
//        return produtoRepository.save(produtoExistente);
//
//    }
}

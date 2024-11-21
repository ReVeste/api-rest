package reveste.brecho.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reveste.brecho.entity.Pedido;
import reveste.brecho.entity.Produto;
import reveste.brecho.enun.produto.CategoriaEnum;
import reveste.brecho.enun.produto.StatusProdutoEnum;
import reveste.brecho.exception.NaoEncontradaException;
import reveste.brecho.repository.ProdutoRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public Produto buscarPorId(int id) {
        Optional<Produto> produtoOpt = produtoRepository.findById(id);

        if (produtoOpt.isEmpty()) {
            throw new NaoEncontradaException("Produto");
        }

        return produtoOpt.get();
    }

    public Produto criar(Produto produto) {
        return produtoRepository.save(produto);
    }

    public Produto atualizar(int id, Produto produto) {
        if (!produtoRepository.existsById(id)) {
            throw new NaoEncontradaException("Produto", id);
        }

        return produtoRepository.save(produto);
    }

    public List<Produto> listar() {
        return produtoRepository.findAll();
    }

    public List<Produto> listarProdutosDisponiveis() {
        return produtoRepository.findAllByStatus(StatusProdutoEnum.DISPONIVEL);
    }

    public void deletarPorId(int id) {
        if (!produtoRepository.existsById(id)) {
            throw new NaoEncontradaException("Produto", id);
        }

        produtoRepository.deleteById(id);
    }

    public List<Produto> listarPorCategoria(CategoriaEnum categoria) {
        return produtoRepository.findAllByCategoria(categoria);
    }

    public void finalizarPedido(List<Integer> listaId) {
        produtoRepository.finalizarPedido(listaId, StatusProdutoEnum.VENDIDO);
    }


    public Integer buscarQtdProdutosCadastradosNoPeriodo(LocalDate inicio, LocalDate fim) {
        List<Produto> produtos = produtoRepository.findAllByDataCadastroBetween(inicio, fim);
        if (produtos.isEmpty()) {return 0;}
        return produtos.size();
    }

}

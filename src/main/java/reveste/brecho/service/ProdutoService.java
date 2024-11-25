package reveste.brecho.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reveste.brecho.dto.dashboards.DashboardMapper;
import reveste.brecho.dto.dashboards.QtdVendasMesDto;
import reveste.brecho.entity.Pedido;
import reveste.brecho.entity.Produto;
import reveste.brecho.enun.produto.CategoriaEnum;
import reveste.brecho.enun.produto.StatusProdutoEnum;
import reveste.brecho.exception.NaoEncontradaException;
import reveste.brecho.repository.ProdutoRepository;
import reveste.brecho.util.PesquisaPeriodos;

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
        produtoRepository.finalizarPedido(listaId, StatusProdutoEnum.VENDIDO, LocalDate.now());
    }

    public Integer buscarQtdProdutosCadastradosNoPeriodo(LocalDate inicio, LocalDate fim) {
        List<Produto> produtos = produtoRepository.findAllByDataCadastroBetween(inicio, fim);
        if (produtos.isEmpty()) {return 0;}
        return produtos.size();
    }

    public QtdVendasMesDto buscarQtdVendasPorMes() {

        LocalDate hoje = LocalDate.now();
        LocalDate mesAnterior1 = hoje.minusMonths(1);
        LocalDate mesAnterior2 = hoje.minusMonths(2);
        LocalDate mesAnterior3 = hoje.minusMonths(3);
        LocalDate mesAnterior4 = hoje.minusMonths(4);
        LocalDate mesAnterior5 = hoje.minusMonths(5);

        List<Produto> produtos = produtoRepository.findAllByDataVendaBetweenAndStatus(
                PesquisaPeriodos.buscarInicioMes(mesAnterior5), PesquisaPeriodos.buscarFimMes(hoje),
                StatusProdutoEnum.VENDIDO);

        int qtdVendasRoupasMesAtual = 0;
        int qtdVendasAcessoriosMesAtual = 0;
        int qtdVendasRoupasMesAnterior1 = 0;
        int qtdVendasAcessoriosMesAnterior1 = 0;
        int qtdVendasRoupasMesAnterior2 = 0;
        int qtdVendasAcessoriosMesAnterior2 = 0;
        int qtdVendasRoupasMesAnterior3 = 0;
        int qtdVendasAcessoriosMesAnterior3 = 0;
        int qtdVendasRoupasMesAnterior4 = 0;
        int qtdVendasAcessoriosMesAnterior4 = 0;
        int qtdVendasRoupasMesAnterior5 = 0;
        int qtdVendasAcessoriosMesAnterior5 = 0;

        for (Produto produto : produtos) {

            if ((produto.getDataVenda().isEqual(PesquisaPeriodos.buscarInicioMes(hoje)) ||
                    produto.getDataVenda().isAfter(PesquisaPeriodos.buscarInicioMes(hoje)))
                    &&
                    (produto.getDataVenda().isEqual(PesquisaPeriodos.buscarFimMes(hoje)) ||
                            produto.getDataVenda().isBefore(PesquisaPeriodos.buscarFimMes(hoje)))) {

                if (produto.getCategoria().equals(CategoriaEnum.ROUPA)){
                    qtdVendasRoupasMesAtual++;
                } else {
                    qtdVendasAcessoriosMesAtual++;
                }

            } else if ((produto.getDataVenda().isEqual(PesquisaPeriodos.buscarInicioMes(mesAnterior1)) ||
                    produto.getDataVenda().isAfter(PesquisaPeriodos.buscarInicioMes(mesAnterior1)))
                    &&
                    (produto.getDataVenda().isEqual(PesquisaPeriodos.buscarFimMes(mesAnterior1)) ||
                            produto.getDataVenda().isBefore(PesquisaPeriodos.buscarFimMes(mesAnterior1)))) {

                if (produto.getCategoria().equals(CategoriaEnum.ROUPA)){
                    qtdVendasRoupasMesAnterior1++;
                } else {
                    qtdVendasAcessoriosMesAnterior1++;
                }

            } else if ((produto.getDataVenda().isEqual(PesquisaPeriodos.buscarInicioMes(mesAnterior2)) ||
                    produto.getDataVenda().isAfter(PesquisaPeriodos.buscarInicioMes(mesAnterior2)))
                    &&
                    (produto.getDataVenda().isEqual(PesquisaPeriodos.buscarFimMes(mesAnterior2)) ||
                            produto.getDataVenda().isBefore(PesquisaPeriodos.buscarFimMes(mesAnterior2)))) {

                if (produto.getCategoria().equals(CategoriaEnum.ROUPA)){
                    qtdVendasRoupasMesAnterior2++;
                } else {
                    qtdVendasAcessoriosMesAnterior2++;
                }

            } else if ((produto.getDataVenda().isEqual(PesquisaPeriodos.buscarInicioMes(mesAnterior3)) ||
                    produto.getDataVenda().isAfter(PesquisaPeriodos.buscarInicioMes(mesAnterior3)))
                    &&
                    (produto.getDataVenda().isEqual(PesquisaPeriodos.buscarFimMes(mesAnterior3)) ||
                            produto.getDataVenda().isBefore(PesquisaPeriodos.buscarFimMes(mesAnterior3)))) {

                if (produto.getCategoria().equals(CategoriaEnum.ROUPA)){
                    qtdVendasRoupasMesAnterior3++;
                } else {
                    qtdVendasAcessoriosMesAnterior3++;
                }

            }  else if ((produto.getDataVenda().isEqual(PesquisaPeriodos.buscarInicioMes(mesAnterior4)) ||
                    produto.getDataVenda().isAfter(PesquisaPeriodos.buscarInicioMes(mesAnterior4)))
                    &&
                    (produto.getDataVenda().isEqual(PesquisaPeriodos.buscarFimMes(mesAnterior4)) ||
                            produto.getDataVenda().isBefore(PesquisaPeriodos.buscarFimMes(mesAnterior4)))) {

                if (produto.getCategoria().equals(CategoriaEnum.ROUPA)){
                    qtdVendasRoupasMesAnterior4++;
                } else {
                    qtdVendasAcessoriosMesAnterior4++;
                }

            } else {

                if (produto.getCategoria().equals(CategoriaEnum.ROUPA)){
                    qtdVendasRoupasMesAnterior5++;
                } else {
                    qtdVendasAcessoriosMesAnterior5++;
                }

            }
        }

        return DashboardMapper.toDetalheQtdVendasMesDto(qtdVendasRoupasMesAtual, qtdVendasAcessoriosMesAtual,
                qtdVendasRoupasMesAnterior1, qtdVendasAcessoriosMesAnterior1, qtdVendasRoupasMesAnterior2,
                qtdVendasAcessoriosMesAnterior2, qtdVendasRoupasMesAnterior3,qtdVendasAcessoriosMesAnterior3,
                qtdVendasRoupasMesAnterior4, qtdVendasAcessoriosMesAnterior4, qtdVendasRoupasMesAnterior5,
                qtdVendasAcessoriosMesAnterior5);

    }

}

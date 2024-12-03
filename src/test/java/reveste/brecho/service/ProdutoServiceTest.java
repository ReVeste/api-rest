package reveste.brecho.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reveste.brecho.dto.dashboards.QtdVendasMesDto;
import reveste.brecho.entity.Produto;
import reveste.brecho.enun.produto.StatusProdutoEnum;
import reveste.brecho.exception.NaoEncontradaException;
import reveste.brecho.repository.ProdutoRepository;
import reveste.brecho.util.PesquisaPeriodos;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static reveste.brecho.enun.produto.CategoriaEnum.ACESSORIO;
import static reveste.brecho.enun.produto.CategoriaEnum.ROUPA;
import static reveste.brecho.enun.produto.StatusProdutoEnum.DISPONIVEL;
import static reveste.brecho.enun.produto.StatusProdutoEnum.VENDIDO;
import static reveste.brecho.enun.produto.TamanhoProdutoEnum.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("4. Classes de teste | ProdutoService")
class ProdutoServiceTest {

    @Mock
    private ProdutoRepository produtoRepository;

    @InjectMocks
    private ProdutoService produtoService;

    @Nested
    @DisplayName("4.1 - buscarPorId")
    class ProdutoService1 {

        @Test
        @DisplayName("Dado que, o Id corresponde a um produto existente, então retorna o produto")
        public void buscarPorIdCorretamente() {

            Produto produto = new Produto(
               1,
               "Chapéu Azul",
               "Saraujo",
               "Chapéu confortável, perfeito para utilizar em dias de sol",
               25.00,
               ACESSORIO,
               1,
               UNICO,
               DISPONIVEL,
               LocalDate.of(2024, 11, 29),
               null,
               null
            );

            Mockito.when(produtoRepository.findById(1)).thenReturn(Optional.of(produto));

            Produto produtoCapturado = produtoService.buscarPorId(1);

            assertEquals(produto.getId(), produtoCapturado.getId());
            assertEquals(produto.getNome(), produtoCapturado.getNome());
            assertEquals(produto.getMarca(), produtoCapturado.getMarca());
            assertEquals(produto.getDescricao(), produtoCapturado.getDescricao());
            assertEquals(produto.getPreco(), produtoCapturado.getPreco());
            assertEquals(produto.getCategoria(), produtoCapturado.getCategoria());
            assertEquals(produto.getQtdEstoque(), produtoCapturado.getQtdEstoque());
            assertEquals(produto.getTamanho(), produtoCapturado.getTamanho());
            assertEquals(produto.getStatus(), produtoCapturado.getStatus());
            assertEquals(produto.getDataCadastro(), produtoCapturado.getDataCadastro());
            assertEquals(produto.getDataVenda(), produtoCapturado.getDataVenda());
            assertEquals(produto.getImagens(), produtoCapturado.getImagens());

        }

        @Test
        @DisplayName("Dado que, o Id não corresponde a nenhum produto, então retorna uma exceção")
        public void buscarPorIdIncorretamente() {

            Mockito.when(produtoRepository.findById(1)).thenReturn(Optional.empty());

            var ex = assertThrows(NaoEncontradaException.class,
                    () -> produtoService.buscarPorId(1));

            assertEquals("Produto não encontrado(a)", ex.getMessage());

        }

    }

    @Nested
    @DisplayName("4.2 - criar")
    class ProdutoService2 {

        @Test
        @DisplayName("Dado que, o registro tem valores válidos, então cadastra ele no banco de dados")
        public void criarCorretamente() {

            Produto produto = new Produto(
                    1,
                    "Chapéu Azul",
                    "Saraujo",
                    "Chapéu confortável, perfeito para utilizar em dias de sol",
                    25.00,
                    ACESSORIO,
                    1,
                    UNICO,
                    DISPONIVEL,
                    LocalDate.now(),
                    null,
                    null
            );

            Mockito.when(produtoRepository.save(produto)).thenReturn(produto);

            Produto produtoCadastrado = produtoService.criar(produto);

            assertEquals(produto.getId(), produtoCadastrado.getId());
            assertEquals(produto.getNome(), produtoCadastrado.getNome());
            assertEquals(produto.getMarca(), produtoCadastrado.getMarca());
            assertEquals(produto.getDescricao(), produtoCadastrado.getDescricao());
            assertEquals(produto.getPreco(), produtoCadastrado.getPreco());
            assertEquals(produto.getCategoria(), produtoCadastrado.getCategoria());
            assertEquals(produto.getQtdEstoque(), produtoCadastrado.getQtdEstoque());
            assertEquals(produto.getTamanho(), produtoCadastrado.getTamanho());
            assertEquals(produto.getStatus(), produtoCadastrado.getStatus());
            assertEquals(produto.getDataCadastro(), produtoCadastrado.getDataCadastro());
            assertEquals(produto.getDataVenda(), produtoCadastrado.getDataVenda());
            assertEquals(produto.getImagens(), produtoCadastrado.getImagens());

            Mockito.verify(produtoRepository, Mockito.times(1)).save(produto);

        }

    }

    @Nested
    @DisplayName("4.3 - atualizar")
    class ProdutoService3 {

        @Test
        @DisplayName("Dado que, o Id é válido, então atualiza o produto relacionado ao Id")
        public void atualizarCorretamente() {

            Produto produto = new Produto(
                    1,
                    "Chapéu Azul",
                    "Saraujo",
                    "Chapéu confortável, perfeito para utilizar em dias de sol",
                    25.00,
                    ACESSORIO,
                    1,
                    UNICO,
                    DISPONIVEL,
                    LocalDate.of(2024, 11, 29),
                    null,
                    null
            );

            Mockito.when(produtoRepository.existsById(1)).thenReturn(true);
            Mockito.when(produtoRepository.save(produto)).thenReturn(produto);

            Produto produtoCapturado = produtoService.atualizar(1, produto);

            assertEquals(produto.getId(), produtoCapturado.getId());
            assertEquals(produto.getNome(), produtoCapturado.getNome());
            assertEquals(produto.getMarca(), produtoCapturado.getMarca());
            assertEquals(produto.getDescricao(), produtoCapturado.getDescricao());
            assertEquals(produto.getPreco(), produtoCapturado.getPreco());
            assertEquals(produto.getCategoria(), produtoCapturado.getCategoria());
            assertEquals(produto.getQtdEstoque(), produtoCapturado.getQtdEstoque());
            assertEquals(produto.getTamanho(), produtoCapturado.getTamanho());
            assertEquals(produto.getStatus(), produtoCapturado.getStatus());
            assertEquals(produto.getDataCadastro(), produtoCapturado.getDataCadastro());
            assertEquals(produto.getDataVenda(), produtoCapturado.getDataVenda());
            assertEquals(produto.getImagens(), produtoCapturado.getImagens());

        }

        @Test
        @DisplayName("Dado que, o Id passado é inválido, então retorna uma exceção")
        public void atualizarIncorretamente() {

            Produto produto = new Produto(
                    1,
                    "Chapéu Azul",
                    "Saraujo",
                    "Chapéu confortável, perfeito para utilizar em dias de sol",
                    25.00,
                    ACESSORIO,
                    1,
                    UNICO,
                    DISPONIVEL,
                    LocalDate.of(2024, 11, 29),
                    null,
                    null
            );

            Mockito.when(produtoRepository.existsById(1)).thenReturn(false);

            var ex = assertThrows(NaoEncontradaException.class,
                    () -> produtoService.atualizar(1, produto));

            assertEquals("Produto não encontrado(a) para o Id: 1", ex.getMessage());

        }

    }

    @Nested
    @DisplayName("4.4 - listar")
    class ProdutoService4 {

        @Test
        @DisplayName("Dado que, exista produtos cadastrados, então retorna todos")
        public void listarCorretamente() {

            List<Produto> produtos = List.of(
                    new Produto(
                            1,
                            "Chapéu Azul",
                            "Saraujo",
                            "Chapéu confortável, perfeito para utilizar em dias de sol",
                            25.00,
                            ACESSORIO,
                            1,
                            UNICO,
                            DISPONIVEL,
                            LocalDate.of(2024, 11, 29),
                            null,
                            null
                    ),
                    new Produto(
                            2,
                            "Camisa Azul",
                            "Saraujo",
                            "Camisa confortável, perfeito para utilizar em dias de sol",
                            45.00,
                            ROUPA,
                            1,
                            M,
                            DISPONIVEL,
                            LocalDate.of(2024, 11, 29),
                            null,
                            null
                    ),
                    new Produto(
                            3,
                            "Calça Azul",
                            "Saraujo",
                            "Calça confortável, perfeito para utilizar em dias de sol",
                            55.00,
                            ROUPA,
                            1,
                            P,
                            DISPONIVEL,
                            LocalDate.of(2024, 11, 29),
                            null,
                            null
                    )
            );

            Mockito.when(produtoRepository.findAll()).thenReturn(produtos);

            List<Produto> produtosCapturados = produtoService.listar();

            for (int i = 0; i < produtosCapturados.size(); i++) {
                Produto produtoEsperado = produtos.get(i);
                Produto produtoRecebido = produtosCapturados.get(i);

                assertEquals(produtoEsperado.getId(), produtoRecebido.getId());
                assertEquals(produtoEsperado.getNome(), produtoRecebido.getNome());
                assertEquals(produtoEsperado.getMarca(), produtoRecebido.getMarca());
                assertEquals(produtoEsperado.getDescricao(), produtoRecebido.getDescricao());
                assertEquals(produtoEsperado.getPreco(), produtoRecebido.getPreco());
                assertEquals(produtoEsperado.getCategoria(), produtoRecebido.getCategoria());
                assertEquals(produtoEsperado.getQtdEstoque(), produtoRecebido.getQtdEstoque());
                assertEquals(produtoEsperado.getTamanho(), produtoRecebido.getTamanho());
                assertEquals(produtoEsperado.getStatus(), produtoRecebido.getStatus());
                assertEquals(produtoEsperado.getDataCadastro(), produtoRecebido.getDataCadastro());
                assertEquals(produtoEsperado.getDataVenda(), produtoRecebido.getDataVenda());
                assertEquals(produtoEsperado.getImagens(), produtoRecebido.getImagens());

            }

            Mockito.verify(produtoRepository, Mockito.times(1)).findAll();

        }

        @Test
        @DisplayName("Dado que, não possui registros de produtos, então retorna uma lista vazia")
        public void listarListaVazia() {

            List<Produto> listaVazia = Collections.emptyList();

            Mockito.when(produtoRepository.findAll()).thenReturn(listaVazia);

            List<Produto> produtosCapturados = produtoService.listar();

            assertTrue(produtosCapturados.isEmpty());

            Mockito.verify(produtoRepository, Mockito.times(1)).findAll();

        }

    }

    @Nested
    @DisplayName("4.5 - listarProdutosDisponiveis")
    class ProdutoService5 {

        @Test
        @DisplayName("Dado que, exista produtos registrados disponíveis, então retorna todos")
        public void listarProdutosDisponveisCorretamente() {

            List<Produto> produtos = List.of(
                    new Produto(
                            1,
                            "Chapéu Azul",
                            "Saraujo",
                            "Chapéu confortável, perfeito para utilizar em dias de sol",
                            25.00,
                            ACESSORIO,
                            1,
                            UNICO,
                            DISPONIVEL,
                            LocalDate.of(2024, 11, 29),
                            null,
                            null
                    ),
                    new Produto(
                            2,
                            "Camisa Azul",
                            "Saraujo",
                            "Camisa confortável, perfeito para utilizar em dias de sol",
                            45.00,
                            ROUPA,
                            1,
                            M,
                            DISPONIVEL,
                            LocalDate.of(2024, 11, 29),
                            null,
                            null
                    ),
                    new Produto(
                            3,
                            "Calça Azul",
                            "Saraujo",
                            "Calça confortável, perfeito para utilizar em dias de sol",
                            55.00,
                            ROUPA,
                            1,
                            P,
                            DISPONIVEL,
                            LocalDate.of(2024, 11, 29),
                            null,
                            null
                    ),
                    new Produto(
                            4,
                            "Camisa Vermelha",
                            "Saraujo",
                            "Camisa confortável e elegante",
                            65.00,
                            ROUPA,
                            1,
                            GG,
                            DISPONIVEL,
                            LocalDate.of(2024, 11, 29),
                            null,
                            null
                    )
            );

            Mockito.when(produtoRepository.findAllByStatus(StatusProdutoEnum.DISPONIVEL)).thenReturn(produtos);

            List<Produto> produtosCapturados = produtoService.listarProdutosDisponiveis();

            assertEquals(4, produtosCapturados.size());

            for (int i = 0; i < produtosCapturados.size(); i++) {
                Produto produtoEsperado = produtos.get(i);
                Produto produtoRecebido = produtosCapturados.get(i);

                assertEquals(produtoEsperado.getId(), produtoRecebido.getId());
                assertEquals(produtoEsperado.getNome(), produtoRecebido.getNome());
                assertEquals(produtoEsperado.getMarca(), produtoRecebido.getMarca());
                assertEquals(produtoEsperado.getDescricao(), produtoRecebido.getDescricao());
                assertEquals(produtoEsperado.getPreco(), produtoRecebido.getPreco());
                assertEquals(produtoEsperado.getCategoria(), produtoRecebido.getCategoria());
                assertEquals(produtoEsperado.getQtdEstoque(), produtoRecebido.getQtdEstoque());
                assertEquals(produtoEsperado.getTamanho(), produtoRecebido.getTamanho());
                assertEquals(produtoEsperado.getStatus(), produtoRecebido.getStatus());
                assertEquals(produtoEsperado.getDataCadastro(), produtoRecebido.getDataCadastro());
                assertEquals(produtoEsperado.getDataVenda(), produtoRecebido.getDataVenda());
                assertEquals(produtoEsperado.getImagens(), produtoRecebido.getImagens());

            }

        }

        @Test
        @DisplayName("Dado que, não possui produtos disponíveis, então retorna uma lista vazia")
        public void listarProdutosDisponiveisListaVazia() {

            List<Produto> listaVazia = Collections.emptyList();

            Mockito.when(produtoRepository.findAllByStatus(StatusProdutoEnum.DISPONIVEL)).thenReturn(listaVazia);

            List<Produto> produtosCapturados = produtoService.listarProdutosDisponiveis();

            assertTrue(produtosCapturados.isEmpty());

            Mockito.verify(produtoRepository, Mockito.times(1)).findAllByStatus(
                    StatusProdutoEnum.DISPONIVEL);

        }

    }

    @Nested
    @DisplayName("4.6 - deletarPorId")
    class ProdutoService6 {

        @Test
        @DisplayName("Dado que, o Id é válido e condiz com um registro, então deleta o produto")
        public void deletarPorIdCorretamente() {

            Produto produto = new Produto(
                    1,
                    "Chapéu Azul",
                    "Saraujo",
                    "Chapéu confortável, perfeito para utilizar em dias de sol",
                    25.00,
                    ACESSORIO,
                    1,
                    UNICO,
                    DISPONIVEL,
                    LocalDate.now(),
                    null,
                    null
            );

            Mockito.when(produtoRepository.existsById(Mockito.anyInt()))
                    .thenReturn(true);

            produtoService.deletarPorId(1);

            var ex = assertThrows(NaoEncontradaException.class,
                    () -> produtoService.buscarPorId(1));

            assertEquals("Produto não encontrado(a)", ex.getMessage());

        }

        @Test
        @DisplayName("Dado que, o Id não é condizente com nenhum produto cadastrado, então retorna uma exceção")
        public void deletarPorIdIncorretamente() {

            Mockito.when(produtoRepository.existsById(Mockito.anyInt()))
                    .thenReturn(false);

            var ex = assertThrows(NaoEncontradaException.class,
                    () -> produtoService.deletarPorId(1));

            assertEquals("Produto não encontrado(a) para o Id: 1", ex.getMessage());

        }

    }

    @Nested
    @DisplayName("4.7 - listarPorCategoria")
    class ProdutoService7 {

        @Test
        @DisplayName("Dado que, exista produtos registrados do tipo roupa, então retorne-os")
        public void listarPorCategoriaRoupa() {

            List<Produto> produtos = List.of(
                    new Produto(
                            2,
                            "Camisa Azul",
                            "Saraujo",
                            "Camisa confortável, perfeito para utilizar em dias de sol",
                            45.00,
                            ROUPA,
                            1,
                            M,
                            DISPONIVEL,
                            LocalDate.of(2024, 11, 29),
                            null,
                            null
                    ),
                    new Produto(
                            3,
                            "Calça Azul",
                            "Saraujo",
                            "Calça confortável, perfeito para utilizar em dias de sol",
                            55.00,
                            ROUPA,
                            1,
                            P,
                            DISPONIVEL,
                            LocalDate.of(2024, 11, 29),
                            null,
                            null
                    ),
                    new Produto(
                            4,
                            "Camisa Vermelha",
                            "Saraujo",
                            "Camisa confortável e elegante",
                            65.00,
                            ROUPA,
                            1,
                            GG,
                            VENDIDO,
                            LocalDate.of(2024, 11, 29),
                            null,
                            null
                    )
            );

            Mockito.when(produtoRepository.findAllByCategoria(ROUPA)).thenReturn(produtos);

            List<Produto> produtosCapturados = produtoService.listarPorCategoria(ROUPA);

            for (int i = 0; i < produtosCapturados.size(); i++) {
                Produto produtoEsperado = produtos.get(i);
                Produto produtoRecebido = produtosCapturados.get(i);

                assertEquals(produtoEsperado.getId(), produtoRecebido.getId());
                assertEquals(produtoEsperado.getNome(), produtoRecebido.getNome());
                assertEquals(produtoEsperado.getMarca(), produtoRecebido.getMarca());
                assertEquals(produtoEsperado.getDescricao(), produtoRecebido.getDescricao());
                assertEquals(produtoEsperado.getPreco(), produtoRecebido.getPreco());
                assertEquals(produtoEsperado.getCategoria(), produtoRecebido.getCategoria());
                assertEquals(produtoEsperado.getQtdEstoque(), produtoRecebido.getQtdEstoque());
                assertEquals(produtoEsperado.getTamanho(), produtoRecebido.getTamanho());
                assertEquals(produtoEsperado.getStatus(), produtoRecebido.getStatus());
                assertEquals(produtoEsperado.getDataCadastro(), produtoRecebido.getDataCadastro());
                assertEquals(produtoEsperado.getDataVenda(), produtoRecebido.getDataVenda());
                assertEquals(produtoEsperado.getImagens(), produtoRecebido.getImagens());

            }

            Mockito.verify(produtoRepository, Mockito.times(1)).findAllByCategoria(ROUPA);

        }

        @Test
        @DisplayName("Dado que, exista produtos registrados do tipo acessório, então retorne-os")
        public void listarPorCategoriaAcessorio() {

            List<Produto> produtos = List.of(
                    new Produto(
                            2,
                            "Camisa Azul",
                            "Saraujo",
                            "Camisa confortável, perfeito para utilizar em dias de sol",
                            45.00,
                            ACESSORIO,
                            1,
                            M,
                            DISPONIVEL,
                            LocalDate.of(2024, 11, 29),
                            null,
                            null
                    ),
                    new Produto(
                            3,
                            "Calça Azul",
                            "Saraujo",
                            "Calça confortável, perfeito para utilizar em dias de sol",
                            55.00,
                            ACESSORIO,
                            1,
                            P,
                            DISPONIVEL,
                            LocalDate.of(2024, 11, 29),
                            null,
                            null
                    ),
                    new Produto(
                            4,
                            "Camisa Vermelha",
                            "Saraujo",
                            "Camisa confortável e elegante",
                            65.00,
                            ACESSORIO,
                            1,
                            GG,
                            VENDIDO,
                            LocalDate.of(2024, 11, 29),
                            null,
                            null
                    )
            );

            Mockito.when(produtoRepository.findAllByCategoria(ACESSORIO)).thenReturn(produtos);

            List<Produto> produtosCapturados = produtoService.listarPorCategoria(ACESSORIO);

            for (int i = 0; i < produtosCapturados.size(); i++) {
                Produto produtoEsperado = produtos.get(i);
                Produto produtoRecebido = produtosCapturados.get(i);

                assertEquals(produtoEsperado.getId(), produtoRecebido.getId());
                assertEquals(produtoEsperado.getNome(), produtoRecebido.getNome());
                assertEquals(produtoEsperado.getMarca(), produtoRecebido.getMarca());
                assertEquals(produtoEsperado.getDescricao(), produtoRecebido.getDescricao());
                assertEquals(produtoEsperado.getPreco(), produtoRecebido.getPreco());
                assertEquals(produtoEsperado.getCategoria(), produtoRecebido.getCategoria());
                assertEquals(produtoEsperado.getQtdEstoque(), produtoRecebido.getQtdEstoque());
                assertEquals(produtoEsperado.getTamanho(), produtoRecebido.getTamanho());
                assertEquals(produtoEsperado.getStatus(), produtoRecebido.getStatus());
                assertEquals(produtoEsperado.getDataCadastro(), produtoRecebido.getDataCadastro());
                assertEquals(produtoEsperado.getDataVenda(), produtoRecebido.getDataVenda());
                assertEquals(produtoEsperado.getImagens(), produtoRecebido.getImagens());

            }

            Mockito.verify(produtoRepository, Mockito.times(1)).findAllByCategoria(ACESSORIO);

        }

        @Test
        @DisplayName("Dado que, não possui produtos da categoria acessório, então retorna uma lista vazia")
        public void listarPorCategoriaListaVazia() {

            List<Produto> listaVazia = Collections.emptyList();

            Mockito.when(produtoRepository.findAllByCategoria(ACESSORIO)).thenReturn(listaVazia);

            List<Produto> produtosCapturados = produtoService.listarPorCategoria(ACESSORIO);

            assertTrue(produtosCapturados.isEmpty());

        }

    }

    @Nested
    @DisplayName("4.8 - finalizarPedido")
    class ProdutoService8 {

        @Test
        @DisplayName("Dado que, foi passado uma lista com Ids válidos de produto, então atualiza o status dos produtos")
        public void finalizarPedidoCorretamente() {

        }

    }

    @Nested
    @DisplayName("4.9 - buscarQtdProdutosCadastradosNoPeriodo")
    class ProdutoService9 {

        @Test
        @DisplayName("Dado que, exista produtos cadastrados no período solicitado, então retorna a quantidade")
        public void buscarProdutosCadastradosCorretamente() {

            List<Produto> produtos = List.of(
                    new Produto(
                            1,
                            "Chapéu Azul",
                            "Saraujo",
                            "Chapéu confortável, perfeito para utilizar em dias de sol",
                            25.00,
                            ACESSORIO,
                            1,
                            UNICO,
                            DISPONIVEL,
                            LocalDate.of(2024, 10, 4),
                            null,
                            null
                    ),
                    new Produto(
                            2,
                            "Camisa Azul",
                            "Saraujo",
                            "Camisa confortável, perfeito para utilizar em dias de sol",
                            45.00,
                            ROUPA,
                            1,
                            M,
                            DISPONIVEL,
                            LocalDate.of(2024, 10, 4),
                            null,
                            null
                    ),
                    new Produto(
                            3,
                            "Calça Azul",
                            "Saraujo",
                            "Calça confortável, perfeito para utilizar em dias de sol",
                            55.00,
                            ROUPA,
                            1,
                            P,
                            DISPONIVEL,
                            LocalDate.of(2024, 10, 4),
                            null,
                            null
                    ),
                    new Produto(
                            4,
                            "Camisa Vermelha",
                            "Saraujo",
                            "Camisa confortável e elegante",
                            65.00,
                            ROUPA,
                            1,
                            GG,
                            DISPONIVEL,
                            LocalDate.of(2024, 10, 4),
                            null,
                            null
                    )
            );

            Mockito.when(produtoRepository.findAllByDataCadastroBetween(
                    LocalDate.of(2024, 10, 1),
                    LocalDate.of(2024, 11, 1))).thenReturn(produtos);

            Integer quantidadeProdutos = produtoService.buscarQtdProdutosCadastradosNoPeriodo(
                    LocalDate.of(2024, 10, 1),
                    LocalDate.of(2024, 11, 1)
            );

            assertEquals(produtos.size(), quantidadeProdutos);

        }

        @Test
        @DisplayName("Dado que, não tenha produtos cadastrados no período solicitado, retorna valor 0")
        public void buscarProdutosCadastradosListaVazia() {

            List<Produto> listaVazia = Collections.emptyList();

            Mockito.when(produtoRepository.findAllByDataCadastroBetween(
                    LocalDate.of(2024, 10, 1),
                    LocalDate.of(2024, 11, 1))).thenReturn(listaVazia);

            Integer quantidadeProdutos = produtoService.buscarQtdProdutosCadastradosNoPeriodo(
                    LocalDate.of(2024, 10, 1),
                    LocalDate.of(2024, 11, 1)
            );

            assertEquals(0, quantidadeProdutos);

        }

    }

    @Nested
    @DisplayName("4.10 - buscarQtdVendasPorMes")
    class ProdutoService10 {

        @Test
        @DisplayName("Dado que, exista produtos vendidos no período, então retorna as quantidades")
        public void buscarQuantidadeVendasPorMes() {

            LocalDate hoje = LocalDate.now();
            LocalDate mesAnterior5 = hoje.minusMonths(5);

            List<Produto> produtos = List.of(
                    new Produto(
                            1,
                            "Chapéu Azul",
                            "Saraujo",
                            "Chapéu confortável, perfeito para utilizar em dias de sol",
                            25.00,
                            ACESSORIO,
                            1,
                            UNICO,
                            DISPONIVEL,
                            LocalDate.of(2023, 10, 4),
                            hoje,
                            null
                    ),
                    new Produto(
                            2,
                            "Camisa Azul",
                            "Saraujo",
                            "Camisa confortável, perfeito para utilizar em dias de sol",
                            45.00,
                            ROUPA,
                            1,
                            M,
                            DISPONIVEL,
                            LocalDate.of(2023, 10, 4),
                            hoje.minusMonths(1),
                            null
                    ),
                    new Produto(
                            3,
                            "Calça Azul",
                            "Saraujo",
                            "Calça confortável, perfeito para utilizar em dias de sol",
                            55.00,
                            ROUPA,
                            1,
                            P,
                            DISPONIVEL,
                            LocalDate.of(2023, 10, 4),
                            hoje.minusMonths(2),
                            null
                    ),
                    new Produto(
                            4,
                            "Camisa Vermelha",
                            "Saraujo",
                            "Camisa confortável e elegante",
                            65.00,
                            ROUPA,
                            1,
                            GG,
                            DISPONIVEL,
                            LocalDate.of(2023, 10, 4),
                            hoje.minusMonths(3),
                            null
                    ),
                    new Produto(
                            5,
                            "Camisa Vermelha",
                            "Saraujo",
                            "Camisa confortável e elegante",
                            65.00,
                            ROUPA,
                            1,
                            GG,
                            DISPONIVEL,
                            LocalDate.of(2023, 10, 4),
                            hoje.minusMonths(4),
                            null
                    ),
                    new Produto(
                            6,
                            "Camisa Vermelha",
                            "Saraujo",
                            "Camisa confortável e elegante",
                            65.00,
                            ROUPA,
                            1,
                            GG,
                            DISPONIVEL,
                            LocalDate.of(2023, 10, 4),
                            hoje.minusMonths(5),
                            null
                    )
            );

            Mockito.when(produtoRepository.findAllByDataVendaBetweenAndStatus(
                    PesquisaPeriodos.buscarInicioMes(mesAnterior5), PesquisaPeriodos.buscarFimMes(hoje),
                    StatusProdutoEnum.VENDIDO
            )).thenReturn(produtos);

            QtdVendasMesDto dto = produtoService.buscarQtdVendasPorMes();

            assertEquals(0, dto.getQtdVendasRoupasMesAtual());
            assertEquals(1, dto.getQtdVendasAcessoriosMesAtual());
            assertEquals(1, dto.getQtdVendasRoupasMesAnterior1());
            assertEquals(0, dto.getQtdVendasAcessoriosMesAnterior1());
            assertEquals(1, dto.getQtdVendasRoupasMesAnterior2());
            assertEquals(0, dto.getQtdVendasAcessoriosMesAnterior2());
            assertEquals(1, dto.getQtdVendasRoupasMesAnterior3());
            assertEquals(0, dto.getQtdVendasAcessoriosMesAnterior3());
            assertEquals(1, dto.getQtdVendasRoupasMesAnterior4());
            assertEquals(0, dto.getQtdVendasAcessoriosMesAnterior4());
            assertEquals(1, dto.getQtdVendasRoupasMesAnterior5());
            assertEquals(0, dto.getQtdVendasAcessoriosMesAnterior5());

            Mockito.verify(produtoRepository, Mockito.times(1)).
                    findAllByDataVendaBetweenAndStatus(
                            PesquisaPeriodos.buscarInicioMes(mesAnterior5), PesquisaPeriodos.buscarFimMes(hoje),
                            StatusProdutoEnum.VENDIDO
            );

        }

    }

}
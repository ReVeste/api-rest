package reveste.brecho.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reveste.brecho.dto.produto.ProdutoDTO;
import reveste.brecho.entity.ItemPedido;
import reveste.brecho.entity.Pedido;
import reveste.brecho.entity.Produto;
import reveste.brecho.exception.ConflitoException;
import reveste.brecho.exception.NaoEncontradaException;
import reveste.brecho.repository.ItemPedidoRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static reveste.brecho.enun.pedido.StatusPedidoEnum.EM_ANDAMENTO;
import static reveste.brecho.enun.pedido.StatusPedidoEnum.PAGO;
import static reveste.brecho.enun.produto.CategoriaEnum.ACESSORIO;
import static reveste.brecho.enun.produto.CategoriaEnum.ROUPA;
import static reveste.brecho.enun.produto.StatusProdutoEnum.DISPONIVEL;
import static reveste.brecho.enun.produto.StatusProdutoEnum.VENDIDO;
import static reveste.brecho.enun.produto.TamanhoProdutoEnum.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("3. Classes de teste | ItemPedidoService")
class ItemPedidoServiceTest {

    @Mock
    private ItemPedidoRepository itemPedidoRepository;
    @Mock
    private ProdutoService produtoService;

    @InjectMocks
    private ItemPedidoService itemPedidoService;

    @Nested
    @DisplayName("3.1 - adicionarProduto")
    class ItemPedidoService1 {

        @Test
        @DisplayName("Dado que, o id do produto seja válido, então relaciona o produto com o pedido")
        public void adicionarProdutoCorretamente() {

            Pedido pedido = new Pedido(
                    1,
                    LocalDateTime.now(),
                    LocalDate.now(),
                    null,
                    "Convencional",
                    2.00,
                    25.00,
                    PAGO,
                    null
            );

            Produto produto = new Produto(
                    1,
                    "Camisa legal",
                    "Saraujos",
                    "Camisa chamativa, agrada aos olhos",
                    50.00,
                    ROUPA,
                    1,
                    PP,
                    DISPONIVEL,
                    LocalDate.of(2024, 11, 29),
                    null,
                    null
            );

            Mockito.when(itemPedidoRepository.existsByProdutoAndPedido(
                    pedido.getId(), produto.getId())).thenReturn(false);

            Mockito.when(produtoService.buscarPorId(produto.getId())).thenReturn(produto);

            itemPedidoService.adicionarProduto(pedido, produto.getId(), 1);

            assertTrue(!itemPedidoRepository.existsByProdutoAndPedido(pedido.getId(), produto.getId()));

        }

        @Test
        @DisplayName("Dado que, o produto já existe em outro pedido, então retorna uma exceção")
        public void adicionarProdutoIncorretamente() {

            Pedido pedido = new Pedido(
                    1,
                    LocalDateTime.now(),
                    LocalDate.now(),
                    null,
                    "Convencional",
                    2.00,
                    25.00,
                    PAGO,
                    null
            );

            Mockito.when(itemPedidoRepository.existsByProdutoAndPedido(
                    1, 1)).thenReturn(true);

            var ex = assertThrows(ConflitoException.class,
                    () -> itemPedidoService.adicionarProduto(pedido,1, 1));

            assertEquals("Pedido com conflito.", ex.getMessage());

        }

    }

    @Nested
    @DisplayName("3.2 - buscaProdutoPorPedido")
    class ItemPedidoService2 {

        @Test
        @DisplayName("Dado que, exista produtos relacionados ao pedido, então devolve uma lista com os produtos")
        public void buscarProdutoPorPedidoCorretamente() {



        }

        @Test
        @DisplayName("Dado que, não exista produtos relacionados ao pedido, então devolve uma exceção")
        public void buscarProdutoPorPedidoSemProdutos() {



        }

    }

    @Nested
    @DisplayName("3.3 - editarQuantidadeProduto")
    class ItemPedidoService3 {



    }

    @Nested
    @DisplayName("3.4 - removerProdutoPedido")
    class ItemPedidoService4 {

        @Test
        @DisplayName("Dado que, o produto esteja atrelado ao pedido, então remova a ligação do produto ao pedido")
        public void removerProdutoCorretamente() {

            Pedido pedido = new Pedido(
                    1,
                    LocalDateTime.now(),
                    LocalDate.now(),
                    null,
                    "Convencional",
                    22.00,
                    105.00,
                    EM_ANDAMENTO,
                    null
            );

            List<Produto> produtos = List.of(
                    new Produto(
                            1,
                            "Camisa legal",
                            "Saraujos",
                            "Camisa chamativa, agrada aos olhos",
                            50.00,
                            ROUPA,
                            1,
                            PP,
                            DISPONIVEL,
                            LocalDate.of(2024, 11, 29),
                            null,
                            null
                    ),
                    new Produto(
                            2,
                            "Calça legal",
                            "Saraujos",
                            "Calça chamativa, perfeita para se destacar",
                            55.00,
                            ROUPA,
                            1,
                            PP,
                            DISPONIVEL,
                            LocalDate.of(2024, 11, 29),
                            null,
                            null
                    )
            );

            List<ItemPedido> itemPedidoList = List.of(
                    new ItemPedido(
                            1,
                            pedido,
                            produtos.get(0),
                            1
                    ),
                    new ItemPedido(
                            2,
                            pedido,
                            produtos.get(1),
                            1
                    )
            );

            Mockito.when(itemPedidoRepository.findByPedidoId(pedido.getId())).thenReturn(itemPedidoList);

            itemPedidoService.removerProdutoPedido(pedido.getId(), produtos.get(0).getId());

            assertTrue(!itemPedidoRepository.existsByProdutoAndPedido(pedido.getId(), produtos.get(0).getId()));

        }

        @Test
        @DisplayName("Dado que, o pedido não possua produtos atrelados, então retorna exceção")
        public void pedidoSemProdutos() {

            List<ItemPedido> listaVazia = Collections.emptyList();
            Mockito.when(itemPedidoRepository.findByPedidoId(1)).thenReturn(listaVazia);

            var ex = assertThrows(NaoEncontradaException.class,
                    () -> itemPedidoService.removerProdutoPedido(1, 1));

            assertEquals("Pedido não encontrado(a) para o Id: 1", ex.getMessage());

        }

        @Test
        @DisplayName("Dado que, o pedido não possua o produto em expecifíco, então retorna exceção")
        public void pedidoSemProdutoEspecifico() {

            Pedido pedido = new Pedido(
                    1,
                    LocalDateTime.now(),
                    LocalDate.now(),
                    null,
                    "Convencional",
                    22.00,
                    105.00,
                    EM_ANDAMENTO,
                    null
            );

            List<Produto> produtos = List.of(
                    new Produto(
                            1,
                            "Camisa legal",
                            "Saraujos",
                            "Camisa chamativa, agrada aos olhos",
                            50.00,
                            ROUPA,
                            1,
                            PP,
                            DISPONIVEL,
                            LocalDate.of(2024, 11, 29),
                            null,
                            null
                    ),
                    new Produto(
                            2,
                            "Calça legal",
                            "Saraujos",
                            "Calça chamativa, perfeita para se destacar",
                            55.00,
                            ROUPA,
                            1,
                            PP,
                            DISPONIVEL,
                            LocalDate.of(2024, 11, 29),
                            null,
                            null
                    )
            );

            List<ItemPedido> itemPedidoList = List.of(
                    new ItemPedido(
                            1,
                            pedido,
                            produtos.get(0),
                            1
                    ),
                    new ItemPedido(
                            2,
                            pedido,
                            produtos.get(1),
                            1
                    )
            );

            Mockito.when(itemPedidoRepository.findByPedidoId(pedido.getId())).thenReturn(itemPedidoList);

            var ex = assertThrows(NaoEncontradaException.class,
                    () -> itemPedidoService.removerProdutoPedido(1, 3));

            assertEquals("Produto não encontrado(a) para o Id: 3", ex.getMessage());

        }

    }

    @Nested
    @DisplayName("3.5 - removerProdutosPorPedido")
    class ItemPedidoService5 {

        @Test
        @DisplayName("Dado que, possua produtos relacionados ao pedido, então delete os registros de itemPedido")
        public void removerProdutoCorretamente() {

            Pedido pedido = new Pedido(
                    1,
                    LocalDateTime.now(),
                    LocalDate.now(),
                    null,
                    "Convencional",
                    22.00,
                    105.00,
                    EM_ANDAMENTO,
                    null
            );

            List<Produto> produtos = List.of(
                    new Produto(
                            1,
                            "Camisa legal",
                            "Saraujos",
                            "Camisa chamativa, agrada aos olhos",
                            50.00,
                            ROUPA,
                            1,
                            PP,
                            DISPONIVEL,
                            LocalDate.of(2024, 11, 29),
                            null,
                            null
                    ),
                    new Produto(
                            2,
                            "Calça legal",
                            "Saraujos",
                            "Calça chamativa, perfeita para se destacar",
                            55.00,
                            ROUPA,
                            1,
                            PP,
                            DISPONIVEL,
                            LocalDate.of(2024, 11, 29),
                            null,
                            null
                    )
            );

            List<ItemPedido> itemPedidoList = List.of(
                    new ItemPedido(
                            1,
                            pedido,
                            produtos.get(0),
                            1
                    ),
                    new ItemPedido(
                            2,
                            pedido,
                            produtos.get(1),
                            1
                    )
            );

            Mockito.when(itemPedidoRepository.findByPedidoId(pedido.getId()).stream().toList()).
                    thenReturn(itemPedidoList);

            itemPedidoService.removerProdutosPorPedido(pedido.getId());

            assertFalse(itemPedidoRepository.existsByProdutoAndPedido(pedido.getId(), produtos.get(0).getId()));
            assertFalse(itemPedidoRepository.existsByProdutoAndPedido(pedido.getId(), produtos.get(1).getId()));

        }

        @Test
        @DisplayName("Dado que, não exista ligações de produtos com o pedido, então retorna exceção")
        public void removerProdutoIncorretamente() {

            List<ItemPedido> listaVazia = Collections.emptyList();

            Mockito.when(itemPedidoRepository.findByPedidoId(1).stream().toList()).
                    thenReturn(listaVazia);

            var ex = assertThrows(NaoEncontradaException.class,
                    () -> itemPedidoService.removerProdutosPorPedido(1));

            assertEquals("Pedido não encontrado(a) para o Id: 1", ex.getMessage());

        }

    }

    @Nested
    @DisplayName("3.6 - finalizarPedido")
    class ItemPedidoService6 {

    }

    @Nested
    @DisplayName("3.7 - buscarProdutosRelacionados")
    class ItemPedidoService7 {

        @Test
        @DisplayName("Dado que, possua pedidos com produtos, então os captura e exibe os produtos relacionados")
        public void buscarProdutosCorretamente() {

        List<Pedido> pedidos = List.of(
                new Pedido(
                        1,
                        LocalDateTime.now(),
                        LocalDate.now(),
                        null,
                        "Convencional",
                        2.00,
                        25.00,
                        PAGO,
                        null
                ),
                new Pedido(
                        2,
                        LocalDateTime.now(),
                        LocalDate.now(),
                        null,
                        "Convencional",
                        2.00,
                        45.00,
                        PAGO,
                        null
                ),
                new Pedido(
                        3,
                        LocalDateTime.now(),
                        LocalDate.now(),
                        null,
                        "Convencional",
                        2.00,
                        55.00,
                        PAGO,
                        null
                )
        );

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

        Mockito.when(itemPedidoRepository.buscarProdutosRelacionados(pedidos)).thenReturn(produtos);

        List<Produto> produtosCapturados = itemPedidoService.buscarProdutosRelacionados(pedidos);

        assertEquals(produtos.size(), produtosCapturados.size());

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

            Mockito.verify(itemPedidoRepository, Mockito.times(1)).
                    buscarProdutosRelacionados(pedidos);

        }

    }

}
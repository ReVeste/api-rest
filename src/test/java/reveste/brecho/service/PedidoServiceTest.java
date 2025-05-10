package reveste.brecho.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reveste.brecho.repository.ItemPedidoRepository;
import reveste.brecho.repository.PedidoRepository;
import reveste.brecho.service.usuario.UsuarioService;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("2. Classes de teste | PedidoService")
class PedidoServiceTest {

    @Mock
    private PedidoRepository pedidoRepository;
    @Mock
    private ProdutoService produtoService;
    @Mock
    private UsuarioService usuarioService;
    @Mock
    private EnderecoService enderecoService;
    @Mock
    private ItemPedidoService itemPedidoService;

    @InjectMocks
    private PedidoService pedidoService;

    @Nested
    @DisplayName("2.1 - adicionarProduto")
    class PedidoService1 {



    }

    @Nested
    @DisplayName("2.2 - listarProdutos")
    class PedidoService2 {



    }

    @Nested
    @DisplayName("2.3 - buscarPedido")
    class PedidoService3 {



    }

    @Nested
    @DisplayName("2.4 - editarQuantidade")
    class PedidoService4 {



    }

    @Nested
    @DisplayName("2.5 - removerProduto")
    class PedidoService5 {



    }

    @Nested
    @DisplayName("2.6 - removerProdutos")
    class PedidoService6 {



    }

    @Nested
    @DisplayName("2.7 - exportarPedidosEmAberto")
    class PedidoService7 {



    }

    @Nested
    @DisplayName("2.8 - calcularValorTotal")
    class PedidoService8 {



    }

    @Nested
    @DisplayName("2.9 - listarPorStatus")
    class PedidoService9 {



    }

    @Nested
    @DisplayName("2.10 - buscarPedidoEmAberto")
    class PedidoService10 {



    }

    @Nested
    @DisplayName("2.11 - finalizarPedido")
    class PedidoService11 {



    }

    @Nested
    @DisplayName("2.12 - atualizarPedidoPago")
    class PedidoService12 {



    }

    @Nested
    @DisplayName("2.13 - buscarPedidoParaEntrega")
    class PedidoService13 {



    }

    @Nested
    @DisplayName("2.14 - buscarEnderecoPedidoEntrega")
    class PedidoService14 {



    }

    @Nested
    @DisplayName("2.15 - buscarLucroTotalMes")
    class PedidoService15 {



    }

    @Nested
    @DisplayName("2.16 - buscarLucroTotalAno")
    class PedidoService16 {



    }

    @Nested
    @DisplayName("2.17 - buscarPedidosPagos")
    class PedidoService17 {



    }

    @Nested
    @DisplayName("2.18 - buscarProdutosDisponiveis")
    class PedidoService18 {



    }

    @Nested
    @DisplayName("2.19 - buscarPorcentagemLucro")
    class PedidoService19 {



    }

    @Nested
    @DisplayName("2.20 - buscarQtdProdutosEnviadosSemana")
    class PedidoService20 {



    }

    @Nested
    @DisplayName("2.21 - buscarQtdProdutosEnviadosMes")
    class PedidoService21 {



    }

    @Nested
    @DisplayName("2.22 - buscarQtdProdutosCadastradosSemana")
    class PedidoService22 {



    }

    @Nested
    @DisplayName("2.23 - buscarQtdProdutosCadastradosMes")
    class PedidoService23 {



    }

    @Nested
    @DisplayName("2.24 - buscarLucrosMensais")
    class PedidoService24 {



    }

}
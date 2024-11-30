package reveste.brecho.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reveste.brecho.dto.dashboards.CadastroPorRegiaoDto;
import reveste.brecho.entity.Endereco;
import reveste.brecho.entity.Usuario;
import reveste.brecho.enun.usuario.TipoUsuarioEnum;
import reveste.brecho.exception.ArgumentoInvalidoException;
import reveste.brecho.exception.NaoEncontradaException;
import reveste.brecho.repository.EnderecoRepository;
import reveste.brecho.service.usuario.UsuarioService;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("5. Classes de teste | EnderecoService")
class EnderecoServiceTest {

    @Mock
    private EnderecoRepository enderecoRepository;

    @Mock
    private UsuarioService usuarioService;

    @InjectMocks
    private EnderecoService enderecoService;

    @Nested
    @DisplayName("5.1 - criar")
    class EnderecoService1 {

        @Test
        @DisplayName("Dado que, foi inserido um valor válido e com um Id de usuário existente, então cria o endereço")
        public void criarCorretamente() {

            Endereco endereco = new Endereco(
                    1,
                    "Casa de praia",
                    "11032220",
                    "Palmitos",
                    24,
                    "Ap 12",
                    "Aparecido",
                    "Santos",
                    "SP",
                    null
            );

            Usuario usuario = new Usuario(
                    1,
                    "Pedro",
                    "11111111111",
                    "13123456789",
                    "pedro.saraujo@sptech.school",
                    "batata1234",
                    TipoUsuarioEnum.cliente,
                    true,
                    LocalDate.now(),
                    "https://www.greenpeace.org/static/planet4-brasil-stateless/2018/07/" +
                            "pinguim-de-barbicha2.jpg"
            );

            Mockito.when(usuarioService.buscarPorId(1))
                    .thenReturn(usuario);

            Mockito.when(enderecoRepository.save(endereco)).thenReturn(endereco);

            Endereco enderecoCriado = enderecoService.criar(endereco, 1);

            assertEquals(endereco.getId(), enderecoCriado.getId());
            assertEquals(endereco.getApelido(), enderecoCriado.getApelido());
            assertEquals(endereco.getCep(), enderecoCriado.getCep());
            assertEquals(endereco.getRua(), enderecoCriado.getRua());
            assertEquals(endereco.getNumero(), enderecoCriado.getNumero());
            assertEquals(endereco.getComplemento(), enderecoCriado.getComplemento());
            assertEquals(endereco.getBairro(), enderecoCriado.getBairro());
            assertEquals(endereco.getCidade(), enderecoCriado.getCidade());
            assertEquals(endereco.getUf(), enderecoCriado.getUf());
            assertEquals(usuario, enderecoCriado.getUsuario());


            Mockito.verify(usuarioService, Mockito.times(1)).buscarPorId(1);

        }

        @Test
        @DisplayName("Dado que, o Id do usuário é nulo, então retorna uma exceção")
        public void criarComUsuarioNulo() {

            Endereco endereco = new Endereco(
                    1,
                    "Casa de praia",
                    "11032220",
                    "Palmitos",
                    24,
                    "Ap 12",
                    "Aparecido",
                    "Santos",
                    "SP",
                    null
            );

            var ex = assertThrows(ArgumentoInvalidoException.class,
                    () -> enderecoService.criar(endereco, null));

            assertEquals("O id do usuário não pode ser nulo", ex.getMessage());

        }

    }

    @Nested
    @DisplayName("5.2 - listarPorUsuario")
    class EnderecoService2 {

        @Test
        @DisplayName("Dado que, o Id do usuário existe, então retorna uma lista com seus endereços")
        public void listarPorUsuarioCorretamente() {

            Usuario usuario = new Usuario(
                    1,
                    "Pedro",
                    "11111111111",
                    "13123456789",
                    "pedro.saraujo@sptech.school",
                    "batata1234",
                    TipoUsuarioEnum.cliente,
                    true,
                    LocalDate.now(),
                    "https://www.greenpeace.org/static/planet4-brasil-stateless/2018/07/" +
                            "pinguim-de-barbicha2.jpg"
            );

            List<Endereco> enderecos = List.of(
                    new Endereco(
                            1,
                            "Casa de praia",
                            "11032220",
                            "Palmitos",
                            24,
                            "Ap 12",
                            "Aparecido",
                            "Santos",
                            "SP",
                            usuario
                    ),
                    new Endereco(
                            2,
                            "Apartamento na Paulista",
                            "22032220",
                            "Avenida Paulista",
                            4,
                            "Ap 222",
                            "Paulo São",
                            "São Paulo",
                            "SP",
                            usuario
                    )
            );

            Mockito.when(enderecoRepository.findAllByUsuarioIdOrderByApelido(1)).thenReturn(enderecos);

            List<Endereco> enderecosCapturados = enderecoService.listarPorUsuario(1);

            for (int i = 0; i < enderecosCapturados.size(); i++) {
                Endereco enderecoEsperado = enderecos.get(i);
                Endereco enderecoRetornado = enderecosCapturados.get(i);

                assertEquals(enderecoEsperado.getId(), enderecoRetornado.getId());
                assertEquals(enderecoEsperado.getApelido(), enderecoRetornado.getApelido());
                assertEquals(enderecoEsperado.getCep(), enderecoRetornado.getCep());
                assertEquals(enderecoEsperado.getRua(), enderecoRetornado.getRua());
                assertEquals(enderecoEsperado.getNumero(), enderecoRetornado.getNumero());
                assertEquals(enderecoEsperado.getComplemento(), enderecoRetornado.getComplemento());
                assertEquals(enderecoEsperado.getBairro(), enderecoRetornado.getBairro());
                assertEquals(enderecoEsperado.getCidade(), enderecoRetornado.getCidade());
                assertEquals(enderecoEsperado.getUf(), enderecoRetornado.getUf());
                assertEquals(enderecoEsperado.getUsuario(), enderecoRetornado.getUsuario());

            }

        }

        @Test
        @DisplayName("Dado que, o Id do usuário é nulo, então retorna exceção")
        public void listarPorUsuarioIdNulo() {

            var ex = assertThrows(ArgumentoInvalidoException.class,
                    () -> enderecoService.listarPorUsuario(null));

            assertEquals("O id do usuário não pode ser nulo", ex.getMessage());

        }

        @Test
        @DisplayName("Dado que, o Id do usuário existe, então retorna uma lista vazia")
        public void listarPorUsuarioListaVazia() {

            Mockito.when(enderecoRepository.findAllByUsuarioIdOrderByApelido(1)).thenReturn(null);

            List<Endereco> enderecosCapturados = enderecoService.listarPorUsuario(1);

            List<Endereco> listaVazia = Collections.emptyList();

            assertNull(enderecosCapturados);

        }

    }

    @Nested
    @DisplayName("5.3 - buscarPorId")
    class EnderecoService3 {

        @Test
        @DisplayName("Dado que, o Id do endereço é válido, então retorna o registro do endereço")
        public void buscarPorIdCorretamente() {

            Usuario usuario = new Usuario(
                    1,
                    "Pedro",
                    "11111111111",
                    "13123456789",
                    "pedro.saraujo@sptech.school",
                    "batata1234",
                    TipoUsuarioEnum.cliente,
                    true,
                    LocalDate.now(),
                    "https://www.greenpeace.org/static/planet4-brasil-stateless/2018/07/" +
                            "pinguim-de-barbicha2.jpg"
            );

            Endereco endereco = new Endereco(
                    1,
                    "Casa de praia",
                    "11032220",
                    "Palmitos",
                    24,
                    "Ap 12",
                    "Aparecido",
                    "Santos",
                    "SP",
                    usuario
            );

            Mockito.when(enderecoRepository.findById(1))
                    .thenReturn(Optional.of(endereco));

            Endereco enderecoCapturado = enderecoService.buscarPorId(1);

            assertNotNull(enderecoCapturado);
            assertEquals(endereco.getId(), enderecoCapturado.getId());

            Mockito.verify(enderecoRepository, Mockito.times(1)).findById(1);
            Mockito.verify(enderecoRepository, Mockito.times(0)).findAll();

        }

        @Test
        @DisplayName("Dado que, inseri um Id de um endereço inexistente, então retorna uma exceção")
        public void buscarPorIdInvalido() {

            Mockito.when(enderecoRepository.findById(1)).thenReturn(Optional.empty());

            var ex = assertThrows(NaoEncontradaException.class,
                    () -> enderecoService.buscarPorId(1));

            assertEquals("Endereço não encontrado(a) para o Id: 1", ex.getMessage());

        }

    }

    @Nested
    @DisplayName("5.4 - atualizar")
    class EnderecoService4 {

        @Test
        @DisplayName("Dado que, passei um Id válido, então retorna o endereço atualizado")
        public void atualizarCorretamente() {

            Usuario usuario = new Usuario(
                    1,
                    "Pedro",
                    "11111111111",
                    "13123456789",
                    "pedro.saraujo@sptech.school",
                    "batata1234",
                    TipoUsuarioEnum.cliente,
                    true,
                    LocalDate.now(),
                    "https://www.greenpeace.org/static/planet4-brasil-stateless/2018/07/" +
                            "pinguim-de-barbicha2.jpg"
            );

            Endereco endereco = new Endereco(
                    1,
                    "Casa de praia",
                    "11032220",
                    "Palmitos",
                    24,
                    "Ap 12",
                    "Aparecido",
                    "Santos",
                    "SP",
                    null
            );

            Mockito.when(enderecoRepository.existsById(1)).thenReturn(true);
            Mockito.when(usuarioService.buscarPorId(1)).thenReturn(usuario);
            Mockito.when(enderecoRepository.save(endereco)).thenReturn(endereco);

            Endereco enderecoRetornado = enderecoService.atualizar(1, endereco, 1);

            assertEquals(endereco.getId(), enderecoRetornado.getId());
            assertEquals(endereco.getApelido(), enderecoRetornado.getApelido());
            assertEquals(endereco.getCep(), enderecoRetornado.getCep());
            assertEquals(endereco.getRua(), enderecoRetornado.getRua());
            assertEquals(endereco.getNumero(), enderecoRetornado.getNumero());
            assertEquals(endereco.getComplemento(), enderecoRetornado.getComplemento());
            assertEquals(endereco.getBairro(), enderecoRetornado.getBairro());
            assertEquals(endereco.getCidade(), enderecoRetornado.getCidade());
            assertEquals(endereco.getUf(), enderecoRetornado.getUf());
            assertEquals(usuario, enderecoRetornado.getUsuario());

        }

        @Test
        @DisplayName("Dado que, passei um Id inválido, então retorna uma exceção")
        public void atualizarIncorretamente() {

            Usuario usuario = new Usuario(
                    1,
                    "Pedro",
                    "11111111111",
                    "13123456789",
                    "pedro.saraujo@sptech.school",
                    "batata1234",
                    TipoUsuarioEnum.cliente,
                    true,
                    LocalDate.now(),
                    "https://www.greenpeace.org/static/planet4-brasil-stateless/2018/07/" +
                            "pinguim-de-barbicha2.jpg"
            );

            Endereco endereco = new Endereco(
                    1,
                    "Casa de praia",
                    "11032220",
                    "Palmitos",
                    24,
                    "Ap 12",
                    "Aparecido",
                    "Santos",
                    "SP",
                    usuario
            );

            Mockito.when(enderecoRepository.existsById(1)).thenReturn(false);

            var ex = assertThrows(NaoEncontradaException.class,
                    () -> enderecoService.atualizar(1, endereco, 1));

            assertEquals("Endereço não encontrado(a) para o Id: 1", ex.getMessage());

        }

    }

    @Nested
    @DisplayName("5.5 - deletarPorId")
    class EnderecoService5 {

        @Test
        @DisplayName("Dado que, passei um Id válido, então deleta o registro")
        public void deletarPorIdCorretamente() {

            Usuario usuario = new Usuario(
                    1,
                    "Pedro",
                    "11111111111",
                    "13123456789",
                    "pedro.saraujo@sptech.school",
                    "batata1234",
                    TipoUsuarioEnum.cliente,
                    true,
                    LocalDate.now(),
                    "https://www.greenpeace.org/static/planet4-brasil-stateless/2018/07/" +
                            "pinguim-de-barbicha2.jpg"
            );

            Endereco endereco = new Endereco(
                    1,
                    "Casa de praia",
                    "11032220",
                    "Palmitos",
                    24,
                    "Ap 12",
                    "Aparecido",
                    "Santos",
                    "SP",
                    usuario
            );

            Mockito.when(enderecoRepository.existsById(Mockito.anyInt()))
                    .thenReturn(true);

            enderecoService.deletarPorId(1);

            var ex = assertThrows(NaoEncontradaException.class,
                    () -> enderecoService.buscarPorId(1));

            assertEquals("Endereço não encontrado(a) para o Id: 1", ex.getMessage());

        }

        @Test
        @DisplayName("Dado que, passei um Id inválido, então retorna uma exceção")
        public void deletarPorIdIncorretamente() {

            Mockito.when(enderecoRepository.existsById(Mockito.anyInt()))
                    .thenReturn(false);

            var ex = assertThrows(NaoEncontradaException.class,
                    () -> enderecoService.deletarPorId(1));

            assertEquals("Endereço não encontrado(a) para o Id: 1", ex.getMessage());

        }

    }

    @Nested
    @DisplayName("5.6 - buscarQtdCadastrosPorRegiao")
    class EnderecoService6 {

        @Test
        @DisplayName("Dado que, temos alguns usuários, buscar quantidade de cadastros de acordo com o endereço," +
                " separando por região")
        public void buscarDadosDashboard() {

            Usuario usuario = new Usuario(
                    1,
                    "Pedro",
                    "11111111111",
                    "13123456789",
                    "pedro.saraujo@sptech.school",
                    "batata1234",
                    TipoUsuarioEnum.cliente,
                    true,
                    LocalDate.now(),
                    "https://www.greenpeace.org/static/planet4-brasil-stateless/2018/07/" +
                            "pinguim-de-barbicha2.jpg"
            );

            List<Endereco> enderecos = List.of(
                    new Endereco(
                            1,
                            "Casa de praia",
                            "11032220",
                            "Palmitos",
                            24,
                            "Ap 12",
                            "Aparecido",
                            "Santos",
                            "SC",
                            usuario
                    ),
                    new Endereco(
                            2,
                            "Apartamento na Paulista",
                            "22032220",
                            "Avenida Paulista",
                            4,
                            "Ap 222",
                            "Paulo São",
                            "São Paulo",
                            "SP",
                            usuario
                    ),
                    new Endereco(
                            3,
                            "Casa padrão",
                            "11033451",
                            "Rua aleatória",
                            24,
                            "Ap 58",
                            "Limoeiro",
                            "Santos",
                            "GO",
                            usuario
                    ),
                    new Endereco(
                            4,
                            "Casa padrão",
                            "11033452",
                            "Rua aleatória",
                            24,
                            "Ap 57",
                            "Limoeiro",
                            "Santos",
                            "BA",
                            usuario
                    ),
                    new Endereco(
                            5,
                            "Casa padrão",
                            "11033453",
                            "Rua aleatória",
                            24,
                            "Ap 56",
                            "Limoeiro",
                            "Santos",
                            "PA",
                            usuario
                    ),
                    new Endereco(
                            6,
                            "Casa padrão",
                            "11033454",
                            "Lobo Haddock",
                            24,
                            "Ap 55",
                            "Limoeiro",
                            "Santos",
                            "SP",
                            usuario
                    )
            );

            Mockito.when(enderecoRepository.findAll())
                    .thenReturn(enderecos);

            CadastroPorRegiaoDto dto = enderecoService.buscarQtdCadastrosPorRegiao();

            assertEquals(2, dto.getCadastrosSudeste());
            assertEquals(1, dto.getCadastrosNorte());
            assertEquals(1, dto.getCadastrosNordeste());
            assertEquals(1, dto.getCadastrosSul());
            assertEquals(1, dto.getCadastrosCentroOeste());

        }

    }

}
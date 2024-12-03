package reveste.brecho.service.usuario;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.server.ResponseStatusException;
import reveste.brecho.dto.dashboards.CadastrosUsuarioDto;
import reveste.brecho.entity.Usuario;
import reveste.brecho.enun.usuario.TipoUsuarioEnum;
import reveste.brecho.repository.UsuarioRepository;
import reveste.brecho.util.PesquisaPeriodos;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("1. Classes de teste | UsuarioService")
class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UsuarioService usuarioService;

    @Nested
    @DisplayName("1.1 - buscarPorId")
    class UsuarioService1 {

        @Test
        @DisplayName("Dado que, passei o id corretamente, então retorna um usuário relacionado ao ID")
        public void buscarPorIdCorretamente() {

            Usuario usuarioEsperado = new Usuario(
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

            Mockito.when(usuarioRepository.findById(Mockito.anyInt()))
                    .thenReturn(Optional.of(usuarioEsperado));

            Usuario usuarioRetornado = usuarioService.buscarPorId(1);

            assertNotNull(usuarioRetornado);
            assertEquals(usuarioEsperado.getId(), usuarioRetornado.getId());

            Mockito.verify(usuarioRepository, Mockito.times(1)).findById(1);
            Mockito.verify(usuarioRepository, Mockito.times(0)).findAll();

        }

        @Test
        @DisplayName("Dado que, passei o id de um usuário que não existe, então retorna uma exceção")
        public void buscarPorIdIncorretamente() {

            Mockito.when(usuarioRepository.findById(Mockito.anyInt()))
                    .thenReturn(Optional.empty());

            var ex = assertThrows(ResponseStatusException.class,
                    () -> usuarioService.buscarPorId(1));

            assertEquals("404 NOT_FOUND \"Usuário não encontrado\"", ex.getMessage());

        }

    }

    @Nested
    @DisplayName("1.2 - listar")
    class UsuarioService2 {

        @Test
        @DisplayName("Dado que, tenho usuários cadastrados, retorna uma lista preenchida")
        public void listarUsuariosListaPreenchida() {

            List<Usuario> usuarios = List.of(
                    new Usuario(
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
                    ),
                    new Usuario(
                        2,
                        "Rabello",
                        "22222222222",
                        "11123456789",
                        "matheus.rabello@sptech.school",
                        "abacate1234",
                        TipoUsuarioEnum.funcionario,
                        true,
                        LocalDate.now(),
                        "https://www.greenpeace.org/static/planet4-brasil-stateless/2018/07/" +
                                "pinguim-de-barbicha2.jpg"
                )
            );

            Mockito.when(usuarioRepository.findAll()).thenReturn(usuarios);

            List<Usuario> resultado = usuarioService.listar();

            assertNotNull(resultado);
            assertFalse(resultado.isEmpty());

            assertEquals(usuarios.size(), resultado.size());

            for (int i = 0; i < resultado.size(); i++) {
                Usuario usuarioEsperado = usuarios.get(i);
                Usuario usuarioRetornado = resultado.get(i);

                assertEquals(usuarioEsperado.getId(), usuarioRetornado.getId());
                assertEquals(usuarioEsperado.getNome(), usuarioRetornado.getNome());
                assertEquals(usuarioEsperado.getCpf(), usuarioRetornado.getCpf());
                assertEquals(usuarioEsperado.getTelefone(), usuarioRetornado.getTelefone());
                assertEquals(usuarioEsperado.getEmail(), usuarioRetornado.getEmail());
                assertEquals(usuarioEsperado.getSenha(), usuarioRetornado.getSenha());
                assertEquals(usuarioEsperado.getTipo(), usuarioRetornado.getTipo());
                assertEquals(usuarioEsperado.getAtivo(), usuarioRetornado.getAtivo());
                assertEquals(usuarioEsperado.getDataCadastro(), usuarioRetornado.getDataCadastro());
                assertEquals(usuarioEsperado.getImagemUrl(), usuarioRetornado.getImagemUrl());

            }

            Mockito.verify(usuarioRepository, Mockito.times(1)).findAll();

        }

        @Test
        @DisplayName("Dado que, não tenho nenhum usuário no banco de dados, retorna uma lista vazia")
        public void listarUsuariosListaVazia() {

            List<Usuario> listaVazia = Collections.emptyList();

            Mockito.when(usuarioRepository.findAll()).thenReturn(listaVazia);

            List<Usuario> resultado = usuarioService.listar();

            assertNotNull(resultado);
            assertTrue(resultado.isEmpty());

            Mockito.verify(usuarioRepository, Mockito.times(1)).findAll();

        }

    }

    @Nested
    @DisplayName("1.3 - criar")
    class UsuarioService3 {

        @Test
        @DisplayName("Dado que, foi passado um usuário válido, cadastra ele no banco de dados")
        public void criarCorretamente() {

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

            Mockito.when(usuarioRepository.existsByEmailOrCpf(
                    usuario.getEmail(), usuario.getCpf())).thenReturn(false);

            Mockito.when(usuarioRepository.save(usuario)).thenReturn(usuario);

            Usuario usuarioCriado = usuarioService.criar(usuario);

            String senhaCriptografada = passwordEncoder.encode(usuario.getSenha());

            assertEquals(usuario.getId(), usuarioCriado.getId());
            assertEquals(usuario.getNome(), usuarioCriado.getNome());
            assertEquals(usuario.getCpf(), usuarioCriado.getCpf());
            assertEquals(usuario.getTelefone(), usuarioCriado.getTelefone());
            assertEquals(usuario.getEmail(), usuarioCriado.getEmail());
            assertEquals(senhaCriptografada, usuarioCriado.getSenha());
            assertEquals(usuario.getTipo(), usuarioCriado.getTipo());
            assertEquals(usuario.getAtivo(), usuarioCriado.getAtivo());
            assertEquals(usuario.getDataCadastro(), usuarioCriado.getDataCadastro());
            assertEquals(usuario.getImagemUrl(), usuarioCriado.getImagemUrl());

        }

        @Test
        @DisplayName("Dado que, passei um usuário utilizando um cpf já cadastrado, retorna exceção")
        public void criarIncorretamente() {

            Usuario usuarioParaCriar = new Usuario(
                    2,
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

            Mockito.when(usuarioRepository.existsByEmailOrCpf(usuarioParaCriar.getEmail(), usuarioParaCriar.getCpf()))
                    .thenReturn(true);

            var ex = assertThrows(ResponseStatusException.class,
                    () -> usuarioService.criar(usuarioParaCriar));

            assertEquals("409 CONFLICT \"Conflito: e-mail ou cpf já registrado\"", ex.getMessage());

            Mockito.verify(usuarioRepository, Mockito.times(0)).save(usuarioParaCriar);
            Mockito.verify(usuarioRepository, Mockito.times(1)).existsByEmailOrCpf(
                    usuarioParaCriar.getEmail(), usuarioParaCriar.getCpf());

        }

    }

    @Nested
    @DisplayName("1.4 - atualizar")
    class UsuarioService4 {

        @Test
        @DisplayName("Dado que, passei os dados de um usuário válido, atualize o registro de usuário")
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

            Mockito.when(usuarioRepository.existsById(usuario.getId()))
                    .thenReturn(true);

            Mockito.when(usuarioRepository.existsByEmailOrCpfAndIdNot(
                    usuario.getEmail(), usuario.getCpf(), usuario.getId()))
                    .thenReturn(false);

            Mockito.when(usuarioRepository.save(usuario)).thenReturn(usuario);

            Usuario usuarioAtualizado = usuarioService.atualizar(usuario);

            assertEquals(usuario.getId(), usuarioAtualizado.getId());
            assertEquals(usuario.getNome(), usuarioAtualizado.getNome());
            assertEquals(usuario.getCpf(), usuarioAtualizado.getCpf());
            assertEquals(usuario.getTelefone(), usuarioAtualizado.getTelefone());
            assertEquals(usuario.getEmail(), usuarioAtualizado.getEmail());
            assertEquals(usuario.getSenha(), usuarioAtualizado.getSenha());
            assertEquals(usuario.getTipo(), usuarioAtualizado.getTipo());
            assertEquals(usuario.getAtivo(), usuarioAtualizado.getAtivo());
            assertEquals(usuario.getDataCadastro(), usuarioAtualizado.getDataCadastro());
            assertEquals(usuario.getImagemUrl(), usuarioAtualizado.getImagemUrl());

        }

        @Test
        @DisplayName("Dado que, passei um usuário com id inexistente, devolve uma exceção")
        public void atualizarIdInexistente() {

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

            Mockito.when(usuarioRepository.existsById(usuario.getId()))
                    .thenReturn(false);

            var ex = assertThrows(ResponseStatusException.class,
                    () -> usuarioService.atualizar(usuario));

            assertEquals("404 NOT_FOUND \"Não foi localizado um usuário com esse id no banco\"",
                    ex.getMessage());

        }

        @Test
        @DisplayName("Dado que, passei um email já cadastrado, devolve uma exceção")
        public void atualizarEmailCadastrado() {

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

            Mockito.when(usuarioRepository.existsById(usuario.getId()))
                    .thenReturn(true);

            Mockito.when(usuarioRepository.existsByEmailOrCpfAndIdNot(
                            usuario.getEmail(), usuario.getCpf(), usuario.getId()))
                    .thenReturn(true);

            var ex = assertThrows(ResponseStatusException.class,
                    () -> usuarioService.atualizar(usuario));

            assertEquals("409 CONFLICT \"Já há um usuário com esse e-mail ou CPF\"",
                    ex.getMessage());

        }

    }

    @Nested
    @DisplayName("1.5 - deletarPorId")
    class UsuarioService5 {

        @Test
        @DisplayName("Dado que, passei o id de um usuário existente, deixe este usuário inativo")
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

            Mockito.when(usuarioRepository.findById(Mockito.anyInt()))
                    .thenReturn(Optional.of(usuario));

            usuarioService.deletarPorId(1);

            assertEquals(false, usuario.getAtivo());

            Mockito.verify(usuarioRepository, Mockito.times(1)).findById(1);
            Mockito.verify(usuarioRepository, Mockito.times(0)).findAll();

        }

        @Test
        @DisplayName("Dado que, passei o id de um usuário existente, deixe este usuário inativo")
        public void deletarPorIdIncorretamente() {

            Mockito.when(usuarioRepository.findById(Mockito.anyInt()))
                    .thenReturn(Optional.empty());

            var ex = assertThrows(ResponseStatusException.class,
                    () -> usuarioService.deletarPorId(1));

            assertEquals("404 NOT_FOUND \"Usuário não encontrado\"", ex.getMessage());

        }

    }

    @Nested
    @DisplayName("1.6 - criarNovoUsuario")
    class UsuarioService6 {



    }

    @Nested
    @DisplayName("1.7 - autenticar")
    class UsuarioService7 {



    }

    @Nested
    @DisplayName("1.8 - buscarQtdCadastroUsuarios")
    class UsuarioService8 {

        @Test
        @DisplayName("Dado que, temos 6 usuários, então retorna a quantidade de cadastros do período de 6 meses")
        public void buscarDadosDashboard() {

            LocalDate hoje = LocalDate.now();
            LocalDate mesAnterior5 = hoje.minusMonths(5);

            List<Usuario> usuarios = List.of(
                    new Usuario(
                            1,
                            "Pedro",
                            "11111111111",
                            "13123456789",
                            "pedro.saraujo@sptech.school",
                            "batata1234",
                            TipoUsuarioEnum.cliente,
                            true,
                            hoje,
                            "https://www.greenpeace.org/static/planet4-brasil-stateless/2018/07/" +
                                    "pinguim-de-barbicha2.jpg"
                    ),
                    new Usuario(
                            2,
                            "Rabello",
                            "22222222222",
                            "11123456789",
                            "matheus.rabello@sptech.school",
                            "abacate1234",
                            TipoUsuarioEnum.funcionario,
                            true,
                            hoje.minusMonths(1),
                            "https://www.greenpeace.org/static/planet4-brasil-stateless/2018/07/" +
                                    "pinguim-de-barbicha2.jpg"
                    ),
                    new Usuario(
                            3,
                            "Karen",
                            "33333333333",
                            "14123456789",
                            "karen.beatriz@sptech.school",
                            "maca1234",
                            TipoUsuarioEnum.cliente,
                            true,
                            hoje.minusMonths(2),
                            "https://www.greenpeace.org/static/planet4-brasil-stateless/2018/07/" +
                                    "pinguim-de-barbicha2.jpg"
                    ),
                    new Usuario(
                            4,
                            "Amanda",
                            "44444444444",
                            "15123456789",
                            "amanda.martins@sptech.school",
                            "laranja1234",
                            TipoUsuarioEnum.cliente,
                            true,
                            hoje.minusMonths(3),
                            "https://www.greenpeace.org/static/planet4-brasil-stateless/2018/07/" +
                                    "pinguim-de-barbicha2.jpg"
                    ),
                    new Usuario(
                            5,
                            "Ketelyn",
                            "55555555555",
                            "16123456789",
                            "ketelyn.medina@sptech.school",
                            "pessego1234",
                            TipoUsuarioEnum.cliente,
                            true,
                            hoje.minusMonths(4),
                            "https://www.greenpeace.org/static/planet4-brasil-stateless/2018/07/" +
                                    "pinguim-de-barbicha2.jpg"
                    ),
                    new Usuario(
                            6,
                            "Ryan",
                            "66666666666",
                            "17123456789",
                            "ryan.barbosa@sptech.school",
                            "mandioca1234",
                            TipoUsuarioEnum.cliente,
                            true,
                            hoje.minusMonths(5),
                            "https://www.greenpeace.org/static/planet4-brasil-stateless/2018/07/" +
                                    "pinguim-de-barbicha2.jpg"
                    )
            );

            Mockito.when(usuarioRepository.findAllByDataCadastroBetween(
                            PesquisaPeriodos.buscarInicioMes(mesAnterior5), PesquisaPeriodos.buscarFimMes(hoje)))
                    .thenReturn(usuarios);

            CadastrosUsuarioDto dto = usuarioService.buscarQtdCadastroUsuarios();

            assertEquals(1, dto.getCadastrosMesAtual());
            assertEquals(1, dto.getCadastrosMesAnterior1());
            assertEquals(1, dto.getCadastrosMesAnterior2());
            assertEquals(1, dto.getCadastrosMesAnterior3());
            assertEquals(1, dto.getCadastrosMesAnterior4());
            assertEquals(1, dto.getCadastrosMesAnterior5());

            Mockito.verify(usuarioRepository, Mockito.times(1)).findAllByDataCadastroBetween(
                    PesquisaPeriodos.buscarInicioMes(mesAnterior5), PesquisaPeriodos.buscarFimMes(hoje)
            );

        }

    }

}
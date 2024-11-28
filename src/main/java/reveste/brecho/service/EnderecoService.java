package reveste.brecho.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reveste.brecho.dto.dashboards.CadastroPorRegiaoDto;
import reveste.brecho.dto.dashboards.DashboardMapper;
import reveste.brecho.entity.Endereco;
import reveste.brecho.enun.endereco.RegiaoEnum;
import reveste.brecho.exception.ArgumentoInvalidoException;
import reveste.brecho.exception.NaoEncontradaException;
import reveste.brecho.repository.EnderecoRepository;
import reveste.brecho.service.usuario.UsuarioService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EnderecoService {

    private final EnderecoRepository repository;
    private final UsuarioService usuarioService;

    public Endereco criar(Endereco endereco, Integer idUsuario) {
        if (idUsuario == null) throw new ArgumentoInvalidoException("id do usuário");

        endereco.setUsuario(usuarioService.buscarPorId(idUsuario));
        return repository.save(endereco);
    }

    public List<Endereco> listarPorUsuario(Integer idUsuario) {
        if (idUsuario == null) throw new ArgumentoInvalidoException("id do usuário");

        return repository.findAllByUsuarioIdOrderByApelido(idUsuario);
    }

    public Endereco buscarPorId(int id) {
        return repository.findById(id)
                .orElseThrow(() -> new NaoEncontradaException("Endereço", id));
    }

    public Endereco atualizar(int id, Endereco endereco, int idUsuario) {
        if (!repository.existsById(id)) throw new NaoEncontradaException("Endereço", id);

        endereco.setUsuario(usuarioService.buscarPorId(idUsuario));
        return repository.save(endereco);
    }

    public void deletarPorId(int id) {
        if (!repository.existsById(id)) throw new NaoEncontradaException("Endereço", id);

        repository.deleteById(id);
    }

    public CadastroPorRegiaoDto buscarQtdCadastrosPorRegiao() {

        List<Endereco> enderecos = repository.findAll();

        int enderecosSudeste = 0;
        int enderecosNorte = 0;
        int enderecosNordeste = 0;
        int enderecosSul = 0;
        int enderecosCentroOeste = 0;

        for (Endereco endereco : enderecos) {
            String uf = endereco.getUf();

            if (RegiaoEnum.NORTE.getUfs().contains(uf)) {
                enderecosNorte++;
            } else if (RegiaoEnum.NORDESTE.getUfs().contains(uf)) {
                enderecosNordeste++;
            } else if (RegiaoEnum.CENTRO_OESTE.getUfs().contains(uf)) {
                enderecosCentroOeste++;
            } else if (RegiaoEnum.SUDESTE.getUfs().contains(uf)) {
                enderecosSudeste++;
            } else if (RegiaoEnum.SUL.getUfs().contains(uf)) {
                enderecosSul++;
            }
        }

        return DashboardMapper.toDetalheCadastroPorRegiaoDto(enderecosSudeste, enderecosNorte, enderecosNordeste,
                enderecosSul, enderecosCentroOeste);

    }

}

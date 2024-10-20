package reveste.brecho.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import reveste.brecho.entity.endereco.Endereco;

import java.util.List;

public interface EnderecoRepository extends JpaRepository<Endereco, Integer> {

    List<Endereco> findAllByUsuarioIdOrderByApelido(Integer idUsuario);
}

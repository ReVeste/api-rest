package reveste.brecho.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import reveste.brecho.entity.usuario.Usuario;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    boolean existsByEmailOrCpf(String email, String cpf);

    Optional<Usuario> findByEmail(String username);

    boolean existsByEmailOrCpfAndIdNot(String email, String cpf, Integer id);

    Optional<Usuario> findByEmailAndAtivo(String email, boolean b);
}

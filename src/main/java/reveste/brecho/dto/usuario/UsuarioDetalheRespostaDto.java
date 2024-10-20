package reveste.brecho.dto.usuario;

import lombok.Builder;
import lombok.Data;
import reveste.brecho.enun.usuario.TipoEnum;

import java.time.LocalDate;

@Data
@Builder
public class UsuarioDetalheRespostaDto {

    private Integer id;
    private final String nome;
    private final String email;
    private final String senha;
    private String cpf;
    private String telefone;
    private LocalDate dataNascimento;
    private TipoEnum tipo;
}

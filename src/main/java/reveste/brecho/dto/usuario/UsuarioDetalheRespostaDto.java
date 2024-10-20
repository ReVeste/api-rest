package reveste.brecho.dto.usuario;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Builder;
import lombok.Data;
import reveste.brecho.enun.usuario.TipoUsuarioEnum;

import java.time.LocalDate;


@Data
@Builder
public class UsuarioDetalheRespostaDto {

    private Integer id;
    private String nome;
    private String cpf;
    private String telefone;
    private LocalDate dataNascimento;
    private String email;
    private String senha;
    @Enumerated(EnumType.STRING)
    private TipoUsuarioEnum tipo;
    private Boolean ativo;
}

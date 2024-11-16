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
    private String email;
    @Enumerated(EnumType.STRING)
    private TipoUsuarioEnum tipo;
    private Boolean ativo;
    private String imagemUrl;
}

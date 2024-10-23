package reveste.brecho.dto.usuario;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Builder;
import lombok.Data;
import reveste.brecho.enun.usuario.TipoUsuarioEnum;

@Data
@Builder
public class UsuarioResumoDto {
    private Integer id;
    private String nome;
    private String email;
    @Enumerated(EnumType.STRING)
    private TipoUsuarioEnum tipo;
    private Boolean ativo;
}

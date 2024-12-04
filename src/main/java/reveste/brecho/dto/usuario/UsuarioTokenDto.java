package reveste.brecho.dto.usuario;

import lombok.Getter;
import lombok.Setter;
import reveste.brecho.enun.usuario.TipoUsuarioEnum;

@Getter @Setter
public class UsuarioTokenDto {

    private Integer userId;
    private String nome;
    private String email;
    private String token;
    private TipoUsuarioEnum tipo;

}

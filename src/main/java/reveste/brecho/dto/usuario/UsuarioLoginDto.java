package reveste.brecho.dto.usuario;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UsuarioLoginDto {
    @NotBlank
    private String email;
    @NotBlank
    private String senha;
}

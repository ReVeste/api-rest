package reveste.brecho.dto.usuario;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

@Getter @Setter
public class UsuarioCriacaoDto {

    @NotBlank @Size(min = 3)
    private String nome;

    @NotBlank @CPF @Size(min = 11, max = 11)
    private String cpf;

    @NotBlank @Size(min = 11, max = 11)
    private String telefone;

    @NotNull @Past
    private LocalDate dataNascimento;

    @NotBlank @Email
    private String email;

    @NotBlank @Size(min = 6)
    private String senha;

    private String imagemUrl;
}

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

    @NotBlank @Email
    private String email;

    @NotBlank
    private String senha;

    @NotBlank @CPF
    private String cpf;

    @NotBlank
    private String telefone;

    @NotNull @Past
    private LocalDate dataNascimento;

}

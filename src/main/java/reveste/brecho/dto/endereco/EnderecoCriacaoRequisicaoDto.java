package reveste.brecho.dto.endereco;

import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EnderecoCriacaoRequisicaoDto {

    private String apelido;

    @NotBlank @Size(min = 8, max = 8)
    private String cep;

    @NotBlank
    private String rua;

    @NotNull
    private Integer numero;

    private String complemento;

    @NotBlank
    private String bairro;

    @NotBlank
    private String cidade;

    @NotBlank @Size(min = 2, max = 2, message = "O estado deve conter apenas dois dígitos")
    private String uf;

    @NotNull
    private Integer idUsuario;

}

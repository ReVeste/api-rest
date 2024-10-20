package reveste.brecho.dto.endereco;

import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EnderecoCriacaoRequisicaoDto {

    private String apelido;

    @NotBlank @Pattern(regexp = "\\d{5}-\\d{3}", message = "CEP deve estar no formato 12345-678")
    private String cep;

    @NotBlank
    private String logradouro;

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

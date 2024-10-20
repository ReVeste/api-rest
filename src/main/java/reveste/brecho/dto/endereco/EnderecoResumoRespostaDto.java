package reveste.brecho.dto.endereco;

import lombok.Builder;
import lombok.Data;

@Data @Builder
public class EnderecoResumoRespostaDto {
    private Integer id;
    private String apelido;
    private String cep;
    private String logradouro;
}

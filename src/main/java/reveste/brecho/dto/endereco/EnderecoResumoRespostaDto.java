package reveste.brecho.dto.endereco;

import lombok.Builder;
import lombok.Data;

@Data @Builder
public class EnderecoResumoRespostaDto {
    private Integer id;
    private String apelido;
    private String cidade;
    private String rua;
}

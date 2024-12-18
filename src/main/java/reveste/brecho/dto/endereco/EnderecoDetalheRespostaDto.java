package reveste.brecho.dto.endereco;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EnderecoDetalheRespostaDto {

    private Integer id;
    private String apelido;
    private String cep;
    private String rua;
    private Integer numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String uf;

    private UsuarioDto usuario;


    @Data @Builder
    static class UsuarioDto {
        private Integer id;
        private String nome;
    }
}

package reveste.brecho.dto.pagamento;

import lombok.Builder;
import lombok.Data;
import java.math.BigDecimal;

@Data @Builder
public class PreferenciaRequisicaoDto {
    private String produtoId;
    private String produtoNome;
    private String produtoDescricao;
    private Integer produtoQuantidade;
    private BigDecimal produtoPreco;
    private String usuarioNome;
    private String usuarioEmail;
    private String usuarioCodigoTelefone;
    private String usuarioNumeroTelefone;
    private String usuarioCpf;
    private String usuarioCep;
    private String usuarioRua;
    private String usuarioNumeroCasa;
}
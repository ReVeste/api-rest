package reveste.brecho.dto.pagamento;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data @Builder
public class PreferenciaRequisicaoDto {
    List<ProdutosDto> itens;

    private String usuarioNome;
    private String usuarioEmail;
    private String usuarioCodigoTelefone;
    private String usuarioNumeroTelefone;
    private String usuarioCpf;
    private String usuarioCep;
    private String usuarioRua;
    private String usuarioNumeroCasa;

}
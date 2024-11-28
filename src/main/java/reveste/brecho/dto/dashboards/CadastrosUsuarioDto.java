package reveste.brecho.dto.dashboards;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CadastrosUsuarioDto {

    private Integer cadastrosMesAtual;
    private Integer cadastrosMesAnterior1;
    private Integer cadastrosMesAnterior2;
    private Integer cadastrosMesAnterior3;
    private Integer cadastrosMesAnterior4;
    private Integer cadastrosMesAnterior5;

}

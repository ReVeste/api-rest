package reveste.brecho.dto.dashboards;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LucrosMensaisDto {

    private Double lucroMesAtual;
    private Double lucroMesAnterior1;
    private Double lucroMesAnterior2;
    private Double lucroMesAnterior3;
    private Double lucroMesAnterior4;
    private Double lucroMesAnterior5;

}

package reveste.brecho.dto.dashboards;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CadastroPorRegiaoDto {

    private Integer cadastrosSudeste;
    private Integer cadastrosNorte;
    private Integer cadastrosNordeste;
    private Integer cadastrosSul;
    private Integer cadastrosCentroOeste;

}

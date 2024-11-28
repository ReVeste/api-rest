package reveste.brecho.dto.dashboards;

import lombok.Builder;
import lombok.Data;

@Data @Builder
public class KpisDto {

    private Double lucroTotalMes;
    private Double lucroTotalAno;
    private Integer pedidosPagos;
    private Integer produtosDisponiveis;
    private Double porcetagemLucro;
    private Integer produtosEnviadosSemana;
    private Integer produtosEnviadosMes;
    private Integer produtosCadastradosSemana;
    private Integer produtosCadastradosMes;

}

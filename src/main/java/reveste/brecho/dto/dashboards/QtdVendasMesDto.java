package reveste.brecho.dto.dashboards;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class QtdVendasMesDto {

    private Integer qtdVendasRoupasMesAtual;
    private Integer qtdVendasAcessoriosMesAtual;
    private Integer qtdVendasRoupasMesAnterior1;
    private Integer qtdVendasAcessoriosMesAnterior1;
    private Integer qtdVendasRoupasMesAnterior2;
    private Integer qtdVendasAcessoriosMesAnterior2;
    private Integer qtdVendasRoupasMesAnterior3;
    private Integer qtdVendasAcessoriosMesAnterior3;
    private Integer qtdVendasRoupasMesAnterior4;
    private Integer qtdVendasAcessoriosMesAnterior4;
    private Integer qtdVendasRoupasMesAnterior5;
    private Integer qtdVendasAcessoriosMesAnterior5;

}

package reveste.brecho.dto.dashboards;

public class DashboardMapper {

    public static KpisDto toDetalheKpisDto (Double lucroTotalMes, Double lucroTotalAno,
                                            Integer pedidosPagos, Integer produtosDisponiveis,
                                            Double porcetagemLucro, Integer produtosEnviadosSemana,
                                            Integer produtosEnviadosMes, Integer produtosCadastradosSemana,
                                            Integer produtosCadastradosMes) {

        if (lucroTotalMes == null || lucroTotalAno == null ||
        pedidosPagos == null || produtosDisponiveis == null ||
        porcetagemLucro == null || produtosEnviadosSemana == null ||
        produtosEnviadosMes == null || produtosCadastradosSemana == null ||
        produtosCadastradosMes == null) {return null;}

        return KpisDto.builder()
                .lucroTotalMes(lucroTotalMes)
                .lucroTotalAno(lucroTotalAno)
                .pedidosPagos(pedidosPagos)
                .produtosDisponiveis(produtosDisponiveis)
                .porcetagemLucro(porcetagemLucro)
                .produtosEnviadosSemana(produtosEnviadosSemana)
                .produtosEnviadosMes(produtosEnviadosMes)
                .produtosCadastradosSemana(produtosCadastradosSemana)
                .produtosCadastradosMes(produtosCadastradosMes)
                .build();

    }

}

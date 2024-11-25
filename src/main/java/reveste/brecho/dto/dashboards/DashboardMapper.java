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

    public static LucrosMensaisDto toDetalheLucrosMensaisDto (Double lucroMesAtual, Double lucroMesAnterior1,
                                                              Double lucroMesAnterior2, Double lucroMesAnterior3,
                                                              Double lucroMesAnterior4, Double lucroMesAnterior5) {

        if (lucroMesAtual == null || lucroMesAnterior1 == null ||
        lucroMesAnterior2 == null || lucroMesAnterior3 == null ||
        lucroMesAnterior4 == null || lucroMesAnterior5 == null) {return null;}

        return LucrosMensaisDto.builder()
                .lucroMesAtual(lucroMesAtual)
                .lucroMesAnterior1(lucroMesAnterior1)
                .lucroMesAnterior2(lucroMesAnterior2)
                .lucroMesAnterior3(lucroMesAnterior3)
                .lucroMesAnterior4(lucroMesAnterior4)
                .lucroMesAnterior5(lucroMesAnterior5)
                .build();

    }

    public static CadastrosUsuarioDto toDetalheCadastrosUsuarioDto (Integer cadastrosMesAtual,
                                                                    Integer cadastrosMesAnterior1,
                                                                    Integer cadastrosMesAnterior2,
                                                                    Integer cadastrosMesAnterior3,
                                                                    Integer cadastrosMesAnterior4,
                                                                    Integer cadastrosMesAnterior5) {

        if (cadastrosMesAtual == null || cadastrosMesAnterior1 == null || cadastrosMesAnterior2 == null ||
        cadastrosMesAnterior3 == null || cadastrosMesAnterior4 == null || cadastrosMesAnterior5 == null) {
            return null;
        }

        return CadastrosUsuarioDto.builder()
                .cadastrosMesAtual(cadastrosMesAtual)
                .cadastrosMesAnterior1(cadastrosMesAnterior1)
                .cadastrosMesAnterior2(cadastrosMesAnterior2)
                .cadastrosMesAnterior3(cadastrosMesAnterior3)
                .cadastrosMesAnterior4(cadastrosMesAnterior4)
                .cadastrosMesAnterior5(cadastrosMesAnterior5)
                .build();

    }

    public static CadastroPorRegiaoDto toDetalheCadastroPorRegiaoDto (Integer cadastrosSudeste, Integer cadastrosNorte,
                                                                      Integer cadastrosNordeste, Integer cadastrosSul,
                                                                      Integer cadastrosCentroOeste) {

        if (cadastrosSudeste == null || cadastrosNorte == null || cadastrosNordeste == null || cadastrosSul == null ||
        cadastrosCentroOeste == null) {return null;}

        return CadastroPorRegiaoDto.builder()
                .cadastrosSudeste(cadastrosSudeste)
                .cadastrosNorte(cadastrosNorte)
                .cadastrosNordeste(cadastrosNordeste)
                .cadastrosSul(cadastrosSul)
                .cadastrosCentroOeste(cadastrosCentroOeste)
                .build();

    }

    public static QtdVendasMesDto toDetalheQtdVendasMesDto (Integer qtdVendasRoupasMesAtual,
                                                            Integer qtdVendasAcessoriosMesAtual,
                                                            Integer qtdVendasRoupasMesAnterior1,
                                                            Integer qtdVendasAcessoriosMesAnterior1,
                                                            Integer qtdVendasRoupasMesAnterior2,
                                                            Integer qtdVendasAcessoriosMesAnterior2,
                                                            Integer qtdVendasRoupasMesAnterior3,
                                                            Integer qtdVendasAcessoriosMesAnterior3,
                                                            Integer qtdVendasRoupasMesAnterior4,
                                                            Integer qtdVendasAcessoriosMesAnterior4,
                                                            Integer qtdVendasRoupasMesAnterior5,
                                                            Integer qtdVendasAcessoriosMesAnterior5) {

        if (qtdVendasRoupasMesAtual == null || qtdVendasAcessoriosMesAtual == null ||
                qtdVendasRoupasMesAnterior1 == null || qtdVendasAcessoriosMesAnterior1 == null ||
                qtdVendasRoupasMesAnterior2 == null || qtdVendasAcessoriosMesAnterior2 == null ||
                qtdVendasRoupasMesAnterior3 == null || qtdVendasAcessoriosMesAnterior3 == null ||
                qtdVendasRoupasMesAnterior4 == null || qtdVendasAcessoriosMesAnterior4 == null ||
                qtdVendasRoupasMesAnterior5 == null || qtdVendasAcessoriosMesAnterior5 == null) {return null;}

        return QtdVendasMesDto.builder()
                .qtdVendasRoupasMesAtual(qtdVendasRoupasMesAtual)
                .qtdVendasAcessoriosMesAtual(qtdVendasAcessoriosMesAtual)
                .qtdVendasRoupasMesAnterior1(qtdVendasRoupasMesAnterior1)
                .qtdVendasAcessoriosMesAnterior1(qtdVendasAcessoriosMesAnterior1)
                .qtdVendasRoupasMesAnterior2(qtdVendasRoupasMesAnterior2)
                .qtdVendasAcessoriosMesAnterior2(qtdVendasAcessoriosMesAnterior2)
                .qtdVendasRoupasMesAnterior3(qtdVendasRoupasMesAnterior3)
                .qtdVendasAcessoriosMesAnterior3(qtdVendasAcessoriosMesAnterior3)
                .qtdVendasRoupasMesAnterior4(qtdVendasRoupasMesAnterior4)
                .qtdVendasAcessoriosMesAnterior4(qtdVendasAcessoriosMesAnterior4)
                .qtdVendasRoupasMesAnterior5(qtdVendasRoupasMesAnterior5)
                .qtdVendasAcessoriosMesAnterior5(qtdVendasAcessoriosMesAnterior5)
                .build();

    }

}

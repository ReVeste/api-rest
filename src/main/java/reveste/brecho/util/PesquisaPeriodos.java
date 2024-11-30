package reveste.brecho.util;

import org.springframework.cglib.core.Local;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

public class PesquisaPeriodos {

    public static LocalDate buscarInicioSemana(LocalDate hoje) {
        return hoje.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
    }

    public static LocalDate buscarFimSemana(LocalDate hoje) {
        return hoje.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));
    }

    public static LocalDate buscarInicioMes(LocalDate hoje) {
        return hoje.with(TemporalAdjusters.firstDayOfMonth());
    }

    public static LocalDate buscarFimMes(LocalDate hoje) {
        return hoje.with(TemporalAdjusters.lastDayOfMonth());
    }

    public static LocalDate buscarInicioMesAnterior(LocalDate hoje) {
        LocalDate mesAnterior = hoje.minusMonths(1);
        return mesAnterior.withDayOfMonth(1);
    }

    public static LocalDate buscarFimMesAnterior(LocalDate hoje) {
        LocalDate mesAnterior = hoje.minusMonths(1);
        return mesAnterior.withDayOfMonth(mesAnterior.lengthOfMonth());
    }

    public static LocalDate buscarInicioDoAno() {
        int anoAtual = LocalDate.now().getYear();
        return LocalDate.of(anoAtual, 1, 1);
    }

    public static LocalDate buscarFimDoAno() {
        int anoAtual = LocalDate.now().getYear();
        return LocalDate.of(anoAtual, 12, 31);
    }

}

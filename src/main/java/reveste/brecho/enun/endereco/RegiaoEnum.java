package reveste.brecho.enun.endereco;

import java.util.Arrays;
import java.util.List;

public enum RegiaoEnum {

    NORTE(Arrays.asList("AC", "AP", "AM", "PA", "RO", "RR", "TO")),
    NORDESTE(Arrays.asList("AL", "BA", "CE", "MA", "PB", "PE", "PI", "RN", "SE")),
    CENTRO_OESTE(Arrays.asList("DF", "GO", "MT", "MS")),
    SUDESTE(Arrays.asList("ES", "MG", "RJ", "SP")),
    SUL(Arrays.asList("PR", "RS", "SC"));

    private final List<String> ufs;

    RegiaoEnum(List<String> ufs) {
        this.ufs = ufs;
    }

    public List<String> getUfs() {
        return ufs;
    }

}

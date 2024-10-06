package reveste.brecho.util;

import reveste.brecho.dto.endereco.EnderecoDetalheRespostaDto;
import reveste.brecho.dto.endereco.EnderecoResumoRespostaDto;
import reveste.brecho.entity.endereco.Endereco;

import java.util.ArrayList;
import java.util.List;

public class Ordenador {

    public static List<EnderecoResumoRespostaDto> ordenarPorApelido(List<EnderecoResumoRespostaDto> enderecos) {

        List<EnderecoResumoRespostaDto> listaMutavel = new ArrayList<>(enderecos);

        for (int i = 0; i < listaMutavel.size() - 1; i++) {
            int indMenor = i;
            for (int j = i + 1; j < listaMutavel.size(); j++) {
                if (listaMutavel.get(j).getApelido().compareToIgnoreCase(listaMutavel.get(indMenor).getApelido()) < 0) {
                    indMenor = j;
                }
            }
            EnderecoResumoRespostaDto temp = listaMutavel.get(indMenor);
            listaMutavel.set(indMenor, listaMutavel.get(i));
            listaMutavel.set(i, temp);
        }
        return listaMutavel;
    }

}

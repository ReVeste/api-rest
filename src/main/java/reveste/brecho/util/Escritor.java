package reveste.brecho.util;

import reveste.brecho.dto.pedido.CarrinhoDto;
import reveste.brecho.entity.pedido.Pedido;
import reveste.brecho.entity.produto.Produto;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class Escritor {

    public static void exportar(List<CarrinhoDto> pedidos){

        try {
            OutputStream outputStream = new FileOutputStream("pedidos-em-andamento.csv");

            BufferedWriter escritor = new BufferedWriter(new OutputStreamWriter(outputStream,
                    StandardCharsets.UTF_8));

            // Escrevendo o cabeçalho
            escritor.write("%s;%s;%s;%s;%s;%s;\n"
                    .formatted("id", "data", "valorTotal", "status", "usuario", "produtos"));

            // Escrevendo os dados

            for (int i = 0; i < pedidos.size(); i++) {
                if (i == pedidos.size()-1){

                    escritor.write("%d;%s;%.2f;%s;%s;%s".formatted(
                            pedidos.get(i).getId(), pedidos.get(i).getData(),
                            pedidos.get(i).getValorTotal(), pedidos.get(i).getStatus(),
                            pedidos.get(i).getUsuario(), pedidos.get(i).getProdutos().toString()
                    ));

                } else {

                    escritor.write("%d;%s;%.2f;%s;%s;%s\n".formatted(
                            pedidos.get(i).getId(), pedidos.get(i).getData(),
                            pedidos.get(i).getValorTotal(), pedidos.get(i).getStatus(),
                            pedidos.get(i).getUsuario(), pedidos.get(i).getProdutos().toString()
                    ));

                }
            }
            escritor.close();

        } catch (FileNotFoundException e) {
            System.out.println("Erro ao criar o arquivo");
        } catch (IOException e) {
            System.out.println("Erro ao escrever arquivo");
        }

    }

}

package reveste.brecho.util;

import reveste.brecho.dto.arquivos.ArquivoDetalhesDownloadDto;
import reveste.brecho.dto.pedido.CarrinhoDto;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class Escritor {

    public static ArquivoDetalhesDownloadDto exportar(List<CarrinhoDto> pedidos){

        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            BufferedWriter escritor = new BufferedWriter(new OutputStreamWriter(outputStream, StandardCharsets.UTF_8));

            // Escrevendo o cabeçalho
            escritor.write("%s;%s;%s;%s;%s;%s;\n".formatted("id", "data", "valorTotal",
                    "status", "usuario", "produtos"));

            // Escrevendo os dados
            for (int i = 0; i < pedidos.size(); i++) {
                escritor.write("%d;%s;%.2f;%s;%s;%s%s".formatted(
                        pedidos.get(i).getId(),
                        pedidos.get(i).getDataHora(),
                        pedidos.get(i).getValorTotal(),
                        pedidos.get(i).getStatus(),
                        pedidos.get(i).getNomeUsuario(),
                        pedidos.get(i).getProdutos(),
                        (i == pedidos.size() - 1) ? "" : "\n"
                ));
            }

            escritor.flush();
            InputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());

            return ArquivoDetalhesDownloadDto.builder()
                    .nomeArquivoOriginal("Pedidos")
                    .inputStream(inputStream)
                    .build();

        } catch (FileNotFoundException e) {
            System.out.println("Erro ao criar o arquivo");
            return null;
        } catch (IOException e) {
            System.out.println("Erro ao escrever arquivo");
            return null;
        }

    }

}

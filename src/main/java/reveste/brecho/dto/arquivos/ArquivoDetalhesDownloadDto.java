package reveste.brecho.dto.arquivos;

import lombok.Builder;
import lombok.Data;

import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.OutputStream;

@Data
@Builder
public class ArquivoDetalhesDownloadDto {

    private String categoria;
    private String nomeArquivoOriginal;
    private String nomeArquivoBlob;
    private String extensaoArquivo;
    private InputStream inputStream;

}

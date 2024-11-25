package reveste.brecho.controller.swagger;

import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.preference.Preference;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import reveste.brecho.dto.pagamento.PreferenciaRequisicaoDto;

public interface PagamentoSwagger {

    @PostMapping()
    public ResponseEntity<Preference> criarPreferencia(@RequestBody PreferenciaRequisicaoDto preferenciaDto) throws MPException, MPApiException;
}

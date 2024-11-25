package reveste.brecho.controller;

import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.preference.Preference;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import reveste.brecho.controller.swagger.PagamentoSwagger;
import reveste.brecho.dto.pagamento.PreferenciaRequisicaoDto;
import reveste.brecho.service.PreferenciaService;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/pagamentos")
public class PagamentoController implements PagamentoSwagger {

    private final PreferenciaService preferenceService;

    public ResponseEntity<Preference> criarPreferencia(@RequestBody PreferenciaRequisicaoDto preferenciaDto) throws MPException, MPApiException {
        Preference preferencia = preferenceService.criar(preferenciaDto);
        System.out.println(preferencia.getId());
        return ResponseEntity.created(null).body(preferencia);
    }
}

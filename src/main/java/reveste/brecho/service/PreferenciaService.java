package reveste.brecho.service;
import com.mercadopago.MercadoPagoConfig;
import com.mercadopago.client.common.AddressRequest;
import com.mercadopago.client.common.IdentificationRequest;
import com.mercadopago.client.common.PhoneRequest;
import com.mercadopago.client.preference.*;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.preference.Preference;
import org.springframework.stereotype.Service;
import reveste.brecho.dto.pagamento.PreferenciaRequisicaoDto;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class PreferenciaService {
    public Preference criar(PreferenciaRequisicaoDto preferenciaDto) throws MPException, MPApiException {
//        if (preferenciaDto.getItens().isEmpty()) {
//            return ResponseEntity.badRequest().body("Items empty");
//        }

        MercadoPagoConfig.setAccessToken("APP_USR-8316962628254681-102618-bed185beea6009c0b2d3b1b41732a09b-1144315648");
        PreferenceClient client = new PreferenceClient();
        PreferenceItemRequest itemRequest = PreferenceItemRequest.builder()
                .id(preferenciaDto.getProdutoId())
                .title(preferenciaDto.getProdutoNome())
                .description(preferenciaDto.getProdutoDescricao())
                .quantity(preferenciaDto.getProdutoQuantidade())
                .currencyId("BRL")
                .unitPrice(preferenciaDto.getProdutoPreco())
                .build();
        List<PreferenceItemRequest> itens = new ArrayList<>();
        itens.add(itemRequest);
        List<PreferencePaymentTypeRequest> tiposDePagamentosNaoAceitos = new ArrayList<>();
        tiposDePagamentosNaoAceitos.add(PreferencePaymentTypeRequest.builder().id("ticket").build());
        PreferenceRequest preferenciaRequest = PreferenceRequest.builder()
                .backUrls(
                        PreferenceBackUrlsRequest.builder()
                                .success("http://test.com/success")
                                .failure("http://test.com/failure")
                                .pending("http://test.com/pending")
                                .build())
                .expires(false)
                .items(itens)
                .marketplaceFee(new BigDecimal("0"))
                .payer(
                        PreferencePayerRequest.builder()
                            .name(preferenciaDto.getUsuarioNome())
                            .email(preferenciaDto.getUsuarioEmail())
                            .phone(
                                    PhoneRequest.builder()
                                            .areaCode(preferenciaDto.getUsuarioCodigoTelefone())
                                            .number(preferenciaDto.getUsuarioNumeroTelefone())
                                            .build()
                            )
                            .identification(
                                    IdentificationRequest.builder()
                                            .type("CPF").number(preferenciaDto.getUsuarioCpf())
                                            .build()
                            )
                            .address(
                                    AddressRequest.builder()
                                            .zipCode(preferenciaDto.getUsuarioCep())
                                            .streetName(preferenciaDto.getUsuarioRua())
                                            .streetNumber(preferenciaDto.getUsuarioNumeroCasa())
                                            .build()
                            )
                            .build()
                )
                .additionalInfo("Discount: 0.00")
                .autoReturn("all")
                .operationType("regular_payment")
                .paymentMethods(
                        PreferencePaymentMethodsRequest.builder()
                                .defaultPaymentMethodId("master")
                                .excludedPaymentTypes(tiposDePagamentosNaoAceitos)
                                .installments(5)
                                .defaultInstallments(1)
                                .build()
                ).build();

        return client.create(preferenciaRequest);
    }
}
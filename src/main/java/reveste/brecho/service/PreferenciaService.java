package reveste.brecho.service;
import com.mercadopago.MercadoPagoConfig;
import com.mercadopago.client.common.AddressRequest;
import com.mercadopago.client.common.IdentificationRequest;
import com.mercadopago.client.common.PhoneRequest;
import com.mercadopago.client.preference.*;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.preference.Preference;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import reveste.brecho.dto.pagamento.PreferenciaRequisicaoDto;
import reveste.brecho.dto.pagamento.ProdutosDto;
import reveste.brecho.exception.ArgumentoInvalidoException;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class PreferenciaService {

    @Value("${mercadopago.token}")
    private String mercadoPagoToken;


    public Preference criar(PreferenciaRequisicaoDto preferenciaDto) throws MPException, MPApiException {
        if (preferenciaDto.getItens().isEmpty()) {
            throw new ArgumentoInvalidoException("produtos");
        }

        MercadoPagoConfig.setAccessToken(mercadoPagoToken);

        List<PreferenceItemRequest> itens = new ArrayList<>();
        for (ProdutosDto item : preferenciaDto.getItens()) {
            PreferenceItemRequest itemRequest = PreferenceItemRequest.builder()
                    .id(item.getProdutoId())
                    .title(item.getProdutoNome())
                    .description(item.getProdutoDescricao())
                    .quantity(item.getProdutoQuantidade())
                    .currencyId("BRL")
                    .unitPrice(item.getProdutoPreco())
                    .build();
            itens.add(itemRequest);
        }
        
        List<PreferencePaymentTypeRequest> tiposDePagamentosNaoAceitos = new ArrayList<>();
        tiposDePagamentosNaoAceitos.add(PreferencePaymentTypeRequest.builder().id("ticket").build());

        PreferenceRequest preferenciaRequest = PreferenceRequest.builder()
                .backUrls(
                        PreferenceBackUrlsRequest.builder()
                                .success("http://localhost:3000/")
                                .failure("http://localhost:3000/configuracao-cliente")
                                .pending("http://test.com/pending")
                                .build())
                .expires(true)
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
                .additionalInfo("Discount: 00.00")
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

        return new PreferenceClient().create(preferenciaRequest);
    }
}
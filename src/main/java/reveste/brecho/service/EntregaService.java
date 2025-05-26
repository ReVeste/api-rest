package reveste.brecho.service;

import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.HashMap;
import java.util.Map;

@Service
public class EntregaService {

    private final String MELHOR_ENVIO_URL = "https://www.melhorenvio.com.br/api/v2/me/shipment/calculate?";
    private final String TOKEN = "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiIxIiwianRpIjoiMGI4MGYxYWM4M2Q4NDIyYzA4MjIwNWVkMjQ0OTVjZDg5MjBhMDg3YjgxYjg3YTNkNTc4NTM2MWQ2YWQxN2NjZGZhMTIxMWIwZTY2NTFjOWIiLCJpYXQiOjE3NDc3ODA0NTAuODgzNjc3LCJuYmYiOjE3NDc3ODA0NTAuODgzNjc5LCJleHAiOjE3NzkzMTY0NTAuODY1NTIsInN1YiI6IjllZjNmYzI4LWQwZGEtNDk3Mi04MDljLWRkZTcyNDM4N2RkNiIsInNjb3BlcyI6WyJzaGlwcGluZy1jYWxjdWxhdGUiXX0.xyczmxrLH97c0MbpsFGcZo9risyPETDq0v8xg1JtOAJM7KGW_BAPDytM3lXdLp0nx7ishm8Qh3va_dJqGiLwtyvgzdik9EFKRWNXe46zX9Lh1u517JA5cfp3alCgrcFzczSLVkD1nic3edEGQAGhzXpZFZW_gU6O1Yhf4IHciE5ud_hGh3Iu8-GJl6dTMd7ZlIRcHJRqTkk0IkuIIbIcgIWgB01BG2E04-3PRdyhAPC-KYXRVFVv1X4oCG8vAN1CZY1OIXiyyzjsPXvgveoaKknmS-fiYAVj-4bwdCYWVY1XtCPjgCYNf1R0Y1KGIvtGwHUw3n_TgH-Wb-SETgPeKpV4gIm130A_MrtJi1xxST_j1iigYZKsfCzg1p2xq_blTshkQNQwrNV6JgA0-48AhFQ_SCs8bglVipGhwa4iqLvn-C44tOSGKJ_GSiPLJSn6m3wkLLE15F7yLOO9Vr3Sqs7MjPnWAo_3kYtNx5nkbm-6L2d9pbzY0Tsdmxn22-tbPxY-Zd_AICPLXhYyZmwEGoyetb9-JIGWPNId0C5d6e9M710tBR2WPmzLx2ET-DWBKuEwrJuV2Cl5UhfV3FtrL_hg4cCzDXlhOMKBjVQ3Yw4LECVZN1uCsD9gJrHck9GJ0QwVCUAoJCb_pfJE5blt_GrN_1fNGWLa2V0zgxrNxSQ";
    private final RestTemplate restTemplate = new RestTemplate();

    public Object calcularFrete(String cepDestino) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(java.util.Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setBearerAuth(TOKEN);
        headers.add("User-Agent", "Aplicação matheusmagsun@gmail.com");

        Map<String, Object> body = new HashMap<>();
        Map<String, String> from = Map.of("postal_code", "1201000");
        Map<String, String> to = Map.of("postal_code", cepDestino);
        Map<String, Object> pack = Map.of(
                "height", 32,
                "width", 19,
                "length", 9,
                "weight", 1
        );

        body.put("from", from);
        body.put("to", to);
        body.put("package", pack);

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(body, headers);

        ResponseEntity<Object> response = restTemplate.postForEntity(MELHOR_ENVIO_URL, entity, Object.class);

        return response.getBody();
    }
}
package reveste.brecho.entity.entrega;

import jakarta.persistence.*;
import lombok.*;
import reveste.brecho.entity.endereco.Endereco;
import reveste.brecho.enun.entrega.statusEntregaEnum;

import java.time.LocalDateTime;

@Entity
@Builder
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Entrega {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String previstoPara;
    private statusEntregaEnum status;
    private String codigoRastreio;
    private LocalDateTime dataHoraEnvio;

    @ManyToOne
    private Endereco endereco;

}

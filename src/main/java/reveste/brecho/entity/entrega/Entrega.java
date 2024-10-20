package reveste.brecho.entity.entrega;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import reveste.brecho.entity.endereco.Endereco;
import reveste.brecho.enun.entrega.statusEntregaEnum;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Entrega {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    private String previstoPara;
    @NotNull
    private statusEntregaEnum status;
    @NotNull
    private String codigoRastreio;
    @NotNull
    private LocalDateTime dataHoraEnvio;

    @ManyToOne
    private Endereco endereco;

}

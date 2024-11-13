package reveste.brecho.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Endereco {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String apelido;
    private String cep;
    private String rua;
    private Integer numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String uf;

    @ManyToOne
    private Usuario usuario;

}
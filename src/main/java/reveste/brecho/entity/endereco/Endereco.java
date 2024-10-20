package reveste.brecho.entity.endereco;

import jakarta.persistence.*;
import lombok.*;
import reveste.brecho.entity.usuario.Usuario;

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
    private String logradouro;
    private String complemento;
    private String bairro;
    private String cidade;
    private String uf;

    @ManyToOne
    private Usuario usuario;

}
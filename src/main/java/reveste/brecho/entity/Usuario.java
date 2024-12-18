package reveste.brecho.entity;

import jakarta.persistence.*;
import lombok.*;
import reveste.brecho.enun.usuario.TipoUsuarioEnum;

import java.time.LocalDate;

@Entity
@Builder
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Usuario {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private String cpf;
    private String telefone;
    private String email;
    private String senha;
    @Enumerated(EnumType.STRING)
    private TipoUsuarioEnum tipo;
    private Boolean ativo;
    private LocalDate dataCadastro;
    private String imagemUrl;
}

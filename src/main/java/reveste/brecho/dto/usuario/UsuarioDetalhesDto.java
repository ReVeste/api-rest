package reveste.brecho.dto.usuario;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import reveste.brecho.entity.Usuario;
import reveste.brecho.enun.usuario.TipoUsuarioEnum;

import java.util.Collection;
import java.util.List;

public class UsuarioDetalhesDto implements UserDetails {

    @Getter
    private final String nome;

    private final String email;

    private final String senha;

    private TipoUsuarioEnum role;

    public UsuarioDetalhesDto(Usuario usuario) {
        this.nome = usuario.getNome();
        this.email = usuario.getEmail();
        this.senha = usuario.getSenha();
        this.role = usuario.getTipo();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.role == TipoUsuarioEnum.admin) return List.of(
                new SimpleGrantedAuthority("ROLE_ADMIN"),
                new SimpleGrantedAuthority("ROLE_USER")
        );

        else return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
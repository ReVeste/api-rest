package reveste.brecho.dto.usuario;

import reveste.brecho.entity.produto.Produto;
import reveste.brecho.entity.usuario.Usuario;

public class UsuarioMapper {
    public static Usuario of(UsuarioCriacaoDto usuarioCriacaoDto) {
        Usuario usuario = new Usuario();

        usuario.setEmail(usuarioCriacaoDto.getEmail());
        usuario.setNome(usuarioCriacaoDto.getNome());
        usuario.setSenha(usuarioCriacaoDto.getSenha());

        return usuario;
    }

    public static UsuarioTokenDto of(Usuario usuario, String token) {
        UsuarioTokenDto usuarioTokenDto = new UsuarioTokenDto();

        usuarioTokenDto.setUserId(usuario.getId());
        usuarioTokenDto.setEmail(usuario.getEmail());
        usuarioTokenDto.setNome(usuario.getNome());
        usuarioTokenDto.setToken(token);

        return usuarioTokenDto;
    }

    public static UsuarioDetalheRespostaDto toDetalheDto(Usuario entidade) {
        if (entidade == null) return null;

        return UsuarioDetalheRespostaDto.builder()
                .id(entidade.getId())
                .nome(entidade.getNome())
                .cpf(entidade.getCpf())
                .telefone(entidade.getTelefone())
                .dataNascimento(entidade.getDataNascimento())
                .email(entidade.getEmail())
                .senha(entidade.getSenha())
                .tipo(entidade.getTipo())
                .build();
    }

    public static UsuarioResumoDto toResumoDto(Usuario entidade) {
        if (entidade == null) return null;

        return UsuarioResumoDto.builder()
                .id(entidade.getId())
                .nome(entidade.getNome())
                .email(entidade.getEmail())
                .tipo(entidade.getTipo())
                .build();
    }

    public static Usuario dtoToEntity(UsuarioCriacaoDto dto) {
        if (dto == null) return null;

        return Usuario.builder()
                .nome(dto.getNome())
                .email(dto.getEmail())
                .senha(dto.getSenha())
                .telefone(dto.getTelefone())
                .cpf(dto.getCpf())
                .dataNascimento(dto.getDataNascimento())
                .build();
    }

    public static Usuario fkDtoToEntity(UsuarioFkDto dto) {
        if (dto == null) return null;

        return Usuario.builder()
                .id(dto.getId())
                .build();
    }
}

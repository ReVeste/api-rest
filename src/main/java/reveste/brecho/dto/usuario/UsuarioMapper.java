package reveste.brecho.dto.usuario;

import reveste.brecho.entity.Usuario;
import reveste.brecho.enun.usuario.TipoUsuarioEnum;

import java.time.LocalDate;

public class UsuarioMapper {
    public static Usuario of(UsuarioCriacaoDto usuarioCriacaoDto) {
        Usuario usuario = new Usuario();

        usuario.setEmail(usuarioCriacaoDto.getEmail());
        usuario.setNome(usuarioCriacaoDto.getNome());
        usuario.setSenha(usuarioCriacaoDto.getSenha());
        usuario.setDataCadastro(LocalDate.now());

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
                .email(entidade.getEmail())
                .tipo(entidade.getTipo())
                .ativo(entidade.getAtivo())
                .imagemUrl(entidade.getImagemUrl())
                .build();
    }

    public static UsuarioResumoDto toResumoDto(Usuario entidade) {
        if (entidade == null) return null;

        return UsuarioResumoDto.builder()
                .id(entidade.getId())
                .nome(entidade.getNome())
                .email(entidade.getEmail())
                .tipo(entidade.getTipo())
                .ativo(entidade.getAtivo())
                .build();
    }

    public static Usuario dtoToEntity(UsuarioCriacaoDto dto) {
        if (dto == null) return null;

        return Usuario.builder()
                .nome(dto.getNome())
                .cpf(dto.getCpf())
                .telefone(dto.getTelefone())
                .email(dto.getEmail())
                .senha(dto.getSenha())
                .tipo(TipoUsuarioEnum.cliente)
                .ativo(true)
                .dataCadastro(LocalDate.now())
                .imagemUrl(dto.getImagemUrl())
                .build();
    }

    public static Usuario atualizacaoToEntity(UsuarioCriacaoDto dto, Integer id) {
        if (dto == null) return null;

        return Usuario.builder()
                .id(id)
                .nome(dto.getNome())
                .cpf(dto.getCpf())
                .telefone(dto.getTelefone())
                .email(dto.getEmail())
                .senha(dto.getSenha())
                .tipo(TipoUsuarioEnum.cliente)
                .ativo(true)
                .imagemUrl(dto.getImagemUrl())
                .build();
    }

}

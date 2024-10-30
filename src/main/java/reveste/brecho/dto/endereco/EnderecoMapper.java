package reveste.brecho.dto.endereco;

import reveste.brecho.entity.endereco.Endereco;

import java.util.List;

public class EnderecoMapper {

    public static Endereco requsicaoDtoToEntity(EnderecoCriacaoRequisicaoDto dto) {
        if (dto == null) return null;

        return Endereco.builder()
                .apelido(dto.getApelido())
                .cep(dto.getCep())
                .cidade(dto.getCidade())
                .rua(dto.getRua())
                .numero(dto.getNumero())
                .complemento(dto.getComplemento())
                .bairro(dto.getBairro())
                .uf(dto.getUf())
                .build();
    }

    public static EnderecoDetalheRespostaDto toDetalheDto(Endereco entidade) {
        if (entidade == null) return null;

        return EnderecoDetalheRespostaDto.builder()
                .id(entidade.getId())
                .cep(entidade.getCep())
                .apelido(entidade.getApelido())
                .cidade(entidade.getCidade())
                .uf(entidade.getUf())
                .rua(entidade.getRua())
                .numero(entidade.getNumero())
                .bairro(entidade.getBairro())
                .usuario(EnderecoDetalheRespostaDto.UsuarioDto.builder()
                        .id(entidade.getUsuario().getId())
                        .nome(entidade.getUsuario().getNome())
                        .build())
                .complemento(entidade.getComplemento())
                .build();
    }

    public static EnderecoResumoRespostaDto entityToResumoDto(Endereco entidade) {
        if (entidade == null) return null;

        return EnderecoResumoRespostaDto.builder()
                .id(entidade.getId())
                .apelido(entidade.getApelido())
                .cidade(entidade.getCidade())
                .rua(entidade.getRua())
                .build();
    }

    public static List<EnderecoResumoRespostaDto> entityListToResumoDtoList(List<Endereco> enderecos) {
        return enderecos.stream().map(EnderecoMapper::entityToResumoDto).toList();
    }
}

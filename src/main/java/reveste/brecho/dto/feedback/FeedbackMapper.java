package reveste.brecho.dto.feedback;

import reveste.brecho.entity.Feedback;

public class FeedbackMapper {

    public static Feedback criacaoDtoToEntity(FeedbackRequisicaoDto dto) {
        if (dto == null) return null;

        return Feedback.builder()
                .nota(dto.getNota())
                .comentario(dto.getComentario())
                .build();
    }

    public static Feedback atualizacaoDtoToEntity(FeedbackRequisicaoDto dto) {
        if (dto == null) return null;

        return Feedback.builder()
                .id(dto.getId())
                .nota(dto.getNota())
                .comentario(dto.getComentario())
                .build();
    }

    public static FeedbackRespostaDto toDto(Feedback entidade) {
        if (entidade == null) return null;

        return FeedbackRespostaDto.builder()
                .id(entidade.getId())
                .nota(entidade.getNota())
                .comentario(entidade.getComentario())
                .usuario(FeedbackRespostaDto.UsuarioFeedbackDto.builder()
                        .id(entidade.getUsuario().getId())
                        .nome(entidade.getUsuario().getNome())
                        .urlFoto(entidade.getUsuario().getImagemUrl())
                        .build())
                .build();
    }
}

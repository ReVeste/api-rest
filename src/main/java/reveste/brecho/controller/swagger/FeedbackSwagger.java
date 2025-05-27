package reveste.brecho.controller.swagger;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reveste.brecho.dto.feedback.FeedbackRequisicaoDto;
import reveste.brecho.dto.feedback.FeedbackRespostaDto;

import java.util.List;

public interface FeedbackSwagger {
    @Operation(summary = "Lista todos os feedbacks", description = "Retorna uma lista de todos os feedbacks cadastrados")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista de feedbacks retornada com sucesso"),
            @ApiResponse(responseCode = "204", description = "Nenhum feedback encontrado")
    })
    @GetMapping
    ResponseEntity<List<FeedbackRespostaDto>> listar();

    @Operation(summary = "Busca feedback por ID", description = "Retorna um feedback específico com base no ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Feedback encontrado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Feedback não encontrado")
    })
    @GetMapping("/{id}")
    ResponseEntity<FeedbackRespostaDto> buscarPorId(@PathVariable int id);

    @Operation(summary = "Cria um novo feedback", description = "Cadastra um novo feedback associado a um usuário")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Feedback criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro de validação nos dados do feedback")
    })
    @PostMapping
    ResponseEntity<FeedbackRespostaDto> criar(@RequestBody @Valid FeedbackRequisicaoDto feedbackDto);

//    @Operation(summary = "Atualiza um feedback", description = "Atualiza as informações de um feedback existente")
//    @ApiResponses({
//            @ApiResponse(responseCode = "200", description = "Feedback atualizado com sucesso"),
//            @ApiResponse(responseCode = "400", description = "Erro de validação nos dados do feedback"),
//            @ApiResponse(responseCode = "404", description = "Feedback não encontrado")
//    })
//    @PutMapping
//    ResponseEntity<FeedbackRespostaDto> atualizar(@RequestBody @Valid FeedbackRequisicaoDto feedbackDto);
//
//    @Operation(summary = "Deleta um feedback por ID", description = "Remove um feedback específico do sistema")
//    @ApiResponses({
//            @ApiResponse(responseCode = "204", description = "Feedback deletado com sucesso"),
//            @ApiResponse(responseCode = "404", description = "Feedback não encontrado")
//    })
//    @DeleteMapping("/{id}")
//    ResponseEntity<Void> deletar(@PathVariable int id);
}

package reveste.brecho.controller.swagger;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reveste.brecho.dto.endereco.EnderecoCriacaoRequisicaoDto;
import reveste.brecho.dto.endereco.EnderecoDetalheRespostaDto;
import reveste.brecho.dto.endereco.EnderecoResumoRespostaDto;

import java.util.List;

public interface EnderecoSwagger {

    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Endereço criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos na requisição"),
    })
    @PostMapping
    ResponseEntity<EnderecoDetalheRespostaDto> registrar(EnderecoCriacaoRequisicaoDto novoEndereco);


    @GetMapping("/usuario/{idUsuario}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de endereços retornada com sucesso"),
            @ApiResponse(responseCode = "204", description = "Nenhum endereço encontrado para o usuário"),
            @ApiResponse(responseCode = "400", description = "Parâmetro inválido fornecido para o id do usuário"),
    })
    ResponseEntity<List<EnderecoDetalheRespostaDto>> buscarPorUsuario(@PathVariable Integer idUsuario);


    @GetMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Endereço encontrado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Endereço não encontrado"),
    })
    ResponseEntity<EnderecoDetalheRespostaDto> buscarPorId(@PathVariable Integer id);


    @PutMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Endereço atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos na requisição"),
            @ApiResponse(responseCode = "404", description = "Endereço não encontrado para o ID fornecido"),
    })
    ResponseEntity<EnderecoDetalheRespostaDto> atualizarPorId(
            @PathVariable Integer id,
            @RequestBody @Valid EnderecoCriacaoRequisicaoDto endereco
    );


    @DeleteMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Endereço deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Endereço não encontrado para o ID fornecido"),
    })
    ResponseEntity<Void> deletar(@PathVariable Integer id);
}

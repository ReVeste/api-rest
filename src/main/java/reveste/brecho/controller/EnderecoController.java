package reveste.brecho.controller;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reveste.brecho.dto.endereco.*;
import reveste.brecho.entity.endereco.Endereco;
import reveste.brecho.service.endereco.EnderecoService;
import reveste.brecho.util.Ordenador;

import java.util.List;

@RequiredArgsConstructor
@RestController @RequestMapping("/enderecos")
public class EnderecoController {

    private final EnderecoService service;

    @PostMapping
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Endereço criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos na requisição"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor ao tentar criar o endereço")
    })
    public ResponseEntity<EnderecoDetalheRespostaDto> registrar(@RequestBody @Valid EnderecoCriacaoRequisicaoDto novoEndereco) {
        Endereco enderecoCriado = service.criar(EnderecoMapper.requsicaoDtoToEntity(novoEndereco), novoEndereco.getIdUsuario());
        return ResponseEntity.created(null).body(EnderecoMapper.toDetalheDto(enderecoCriado));
    }

    @GetMapping("/usuario/{idUsuario}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de endereços retornada com sucesso"),
            @ApiResponse(responseCode = "204", description = "Nenhum endereço encontrado para o usuário"),
            @ApiResponse(responseCode = "400", description = "Parâmetro inválido fornecido para o id do usuário"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor ao buscar endereços do usuário")
    })
    public ResponseEntity<List<EnderecoResumoRespostaDto>> buscarPorUsuario(@PathVariable Integer idUsuario) {
        List<Endereco> enderecos = service.listarPorUsuario(idUsuario);


        return enderecos.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(EnderecoMapper.entityListToResumoDtoList(enderecos));
    }

    @GetMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Endereço encontrado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Endereço não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor ao buscar o endereço")
    })
    public ResponseEntity<EnderecoDetalheRespostaDto> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(EnderecoMapper.toDetalheDto(service.buscarPorId(id)));
    }

    @PutMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Endereço atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos na requisição"),
            @ApiResponse(responseCode = "404", description = "Endereço não encontrado para o ID fornecido"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor ao tentar atualizar o endereço")
    })
    public ResponseEntity<EnderecoDetalheRespostaDto> atualizarPorId(@PathVariable Integer id, @RequestBody @Valid EnderecoCriacaoRequisicaoDto endereco) {
        Endereco enderecoAtualizado = service.atualizar(id, EnderecoMapper.requsicaoDtoToEntity(endereco), endereco.getIdUsuario());
        return ResponseEntity.ok(EnderecoMapper.toDetalheDto(enderecoAtualizado));
    }

    @DeleteMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Endereço deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Endereço não encontrado para o ID fornecido"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor ao tentar deletar o endereço")
    })
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        service.deletarPorId(id);
        return ResponseEntity.noContent().build();
    }

}
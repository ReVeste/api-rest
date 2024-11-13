package reveste.brecho.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reveste.brecho.controller.swagger.EnderecoSwagger;
import reveste.brecho.dto.endereco.*;
import reveste.brecho.entity.Endereco;
import reveste.brecho.service.EnderecoService;


import java.util.List;

@RequiredArgsConstructor
@RestController @RequestMapping("/enderecos")
public class EnderecoController implements EnderecoSwagger {

    private final EnderecoService service;

    @Override
    public ResponseEntity<EnderecoDetalheRespostaDto> registrar(@RequestBody @Valid EnderecoCriacaoRequisicaoDto novoEndereco) {
        Endereco enderecoCriado = service.criar(EnderecoMapper.criacaoDtoToEntity(novoEndereco), novoEndereco.getIdUsuario());
        return ResponseEntity.created(null).body(EnderecoMapper.toDetalheDto(enderecoCriado));
    }

    @Override
    public ResponseEntity<List<EnderecoResumoRespostaDto>> buscarPorUsuario(@PathVariable Integer idUsuario) {
        List<Endereco> enderecos = service.listarPorUsuario(idUsuario);

        return enderecos.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(EnderecoMapper.entityListToResumoDtoList(enderecos));
    }

    @Override
    public ResponseEntity<EnderecoDetalheRespostaDto> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(EnderecoMapper.toDetalheDto(service.buscarPorId(id)));
    }

    @Override
    public ResponseEntity<EnderecoDetalheRespostaDto> atualizarPorId(@PathVariable Integer id,
                                                                     @RequestBody @Valid EnderecoCriacaoRequisicaoDto endereco) {

        Endereco enderecoParaAtualizar = EnderecoMapper.atualizacaoDtoToEntity(endereco);
        Endereco enderecoAtualizado = service.atualizar(id, enderecoParaAtualizar, endereco.getIdUsuario());
        return ResponseEntity.ok(EnderecoMapper.toDetalheDto(enderecoAtualizado));
    }

    @Override
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        service.deletarPorId(id);
        return ResponseEntity.noContent().build();
    }

}
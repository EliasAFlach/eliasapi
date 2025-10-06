package br.edu.infnet.eliasapi.Controller.v1;

import br.edu.infnet.eliasapi.Dto.AdocaoRequest;
import br.edu.infnet.eliasapi.Dto.AdocaoResponse;
import br.edu.infnet.eliasapi.Enum.StatusProcessoEnum;
import br.edu.infnet.eliasapi.Mapper.AdocaoMapper;
import br.edu.infnet.eliasapi.Model.Adocao;
import br.edu.infnet.eliasapi.Service.AdocaoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/adocoes")
@AllArgsConstructor
public class AdocaoController {

    private final AdocaoService adocaoService;
    private final AdocaoMapper adocaoMapper;

    @GetMapping
    public ResponseEntity<List<AdocaoResponse>> getAllAdocoes() {
        List<Adocao> adocoes = adocaoService.buscarTodos();
        List<AdocaoResponse> response = adocaoMapper.toResponseList(adocoes);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdocaoResponse> getAdocaoById(@PathVariable Integer id) {
        Adocao adocao = adocaoService.buscarPorId(id);
        AdocaoResponse response = adocaoMapper.toResponse(adocao);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<AdocaoResponse> adicionarAdocao(@Valid @RequestBody AdocaoRequest request) {
        Adocao adocaoCriada = adocaoService.inserir(request);
        AdocaoResponse response = adocaoMapper.toResponse(adocaoCriada);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(response.getId())
                .toUri();
        return ResponseEntity.created(location).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AdocaoResponse> atualizarAdocao(@PathVariable Integer id, @Valid @RequestBody AdocaoRequest request) {
        Adocao adocaoAtualizada = adocaoService.atualizar(id, request);
        AdocaoResponse response = adocaoMapper.toResponse(adocaoAtualizada);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}/inativar")
    public ResponseEntity<AdocaoResponse> inativarAdocao(@PathVariable Integer id) {
        Adocao adocaoInativada = adocaoService.inativar(id);
        AdocaoResponse response = adocaoMapper.toResponse(adocaoInativada);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirAdocao(@PathVariable Integer id) {
        adocaoService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/aprovacoesAnteriores/{data}")
    public ResponseEntity<List<AdocaoResponse>> getAllAprovadasAntesDe(@PathVariable String data) {
        List<Adocao> adocoes = adocaoService.buscarTodasAdocoesAprovadasAnterioresA(data);
        List<AdocaoResponse> response = adocaoMapper.toResponseList(adocoes);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<AdocaoResponse>> buscarPorStatus(@PathVariable StatusProcessoEnum status) {
        List<Adocao> adocoes = adocaoService.buscarTodasAprovacoesPorStatus(status);
        List<AdocaoResponse> response = adocaoMapper.toResponseList(adocoes);
        return ResponseEntity.ok(response);
    }
}
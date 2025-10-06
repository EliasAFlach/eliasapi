package br.edu.infnet.eliasapi.Controller.v1;

import br.edu.infnet.eliasapi.Model.Adocao;
import br.edu.infnet.eliasapi.Model.EtapaProcesso;
import br.edu.infnet.eliasapi.Service.AdocaoService;
import br.edu.infnet.eliasapi.Service.EtapaProcessoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/etapas")
@AllArgsConstructor
public class EtapaProcessoController {

    private final EtapaProcessoService etapaProcessoService;
    private final AdocaoService adocaoService;

    @GetMapping("/adocoes/{adocaoId}")
    public ResponseEntity<List<EtapaProcesso>> buscarEtapasPorAdocao(@PathVariable Integer adocaoId) {
        List<EtapaProcesso> etapas = etapaProcessoService.buscarTodasPorAdocaoId(adocaoId);
        return ResponseEntity.ok(etapas);
    }

    @GetMapping("/{etapaId}")
    public ResponseEntity<EtapaProcesso> buscarEtapaPorId(@PathVariable Integer etapaId) {
        EtapaProcesso etapa = etapaProcessoService.buscarPorId(etapaId);
        return ResponseEntity.ok(etapa);
    }

    @GetMapping
    public ResponseEntity<List<EtapaProcesso>> buscarTodasEtapas() {
        return ResponseEntity.ok(etapaProcessoService.buscarTodos());
    }

    @PostMapping("/{adocaoId}")
    public ResponseEntity<EtapaProcesso> adicionarEtapa(@PathVariable Integer adocaoId, @Valid @RequestBody EtapaProcesso etapa) {
        Adocao adocao = adocaoService.buscarPorId(adocaoId);
        etapa.setAdocao(adocao);
        EtapaProcesso novaEtapa = etapaProcessoService.inserir(etapa);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(novaEtapa.getId())
                .toUri();
        return ResponseEntity.created(location).body(novaEtapa);
    }

    @PutMapping("/{adocaoId}/{etapaId}")
    public ResponseEntity<EtapaProcesso> atualizarEtapa(@PathVariable Integer adocaoId, @PathVariable Integer etapaId, @Valid @RequestBody EtapaProcesso etapa) {
        Adocao adocao = adocaoService.buscarPorId(adocaoId);
        etapa.setAdocao(adocao);
        EtapaProcesso etapaAtualizada = etapaProcessoService.atualizar(etapaId, etapa);
        return ResponseEntity.ok(etapaAtualizada);
    }

    @PatchMapping("/{etapaId}/inativar")
    public ResponseEntity<EtapaProcesso> inativarEtapa(@PathVariable Integer etapaId) {
        return ResponseEntity.ok(etapaProcessoService.inativar(etapaId));
    }

    @DeleteMapping("/{etapaId}")
    public ResponseEntity<Void> excluirEtapa(@PathVariable Integer etapaId) {
        etapaProcessoService.deletar(etapaId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/data/{etapaData}")
    public ResponseEntity<List<EtapaProcesso>> buscarEtapaPorData(@PathVariable String etapaData) {
        return ResponseEntity.ok(etapaProcessoService.buscarTodasPorDataEtapa(etapaData));
    }
}
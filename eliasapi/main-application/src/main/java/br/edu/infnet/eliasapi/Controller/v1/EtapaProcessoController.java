package br.edu.infnet.eliasapi.Controller.v1;

import br.edu.infnet.eliasapi.Model.Adocao;
import br.edu.infnet.eliasapi.Model.EtapaProcesso;
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

    @GetMapping
    public ResponseEntity<List<EtapaProcesso>> buscarEtapas(
            @RequestParam(required = false) Integer adocaoId,
            @RequestParam(required = false) String dataEtapa) {

        if (adocaoId != null) {
            return ResponseEntity.ok(etapaProcessoService.buscarTodasPorAdocaoId(adocaoId));
        }
        if (dataEtapa != null) {
            return ResponseEntity.ok(etapaProcessoService.buscarTodasPorDataEtapa(dataEtapa));
        }
        return ResponseEntity.ok(etapaProcessoService.buscarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EtapaProcesso> buscarPorId(@PathVariable Integer id) {
        EtapaProcesso etapa = etapaProcessoService.buscarPorId(id);
        return ResponseEntity.ok(etapa);
    }

    @PostMapping
    public ResponseEntity<EtapaProcesso> adicionarEtapa(@Valid @RequestBody EtapaProcesso etapa) {
        EtapaProcesso novaEtapa = etapaProcessoService.inserir(etapa);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(novaEtapa.getId())
                .toUri();
        return ResponseEntity.created(location).body(novaEtapa);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EtapaProcesso> atualizarEtapa(@PathVariable Integer id, @Valid @RequestBody EtapaProcesso etapa) {
        EtapaProcesso etapaAtualizada = etapaProcessoService.atualizar(id, etapa);
        return ResponseEntity.ok(etapaAtualizada);
    }

    @PatchMapping("/{id}/inativar")
    public ResponseEntity<EtapaProcesso> inativarEtapa(@PathVariable Integer id) {
        EtapaProcesso etapaInativada = etapaProcessoService.inativar(id);
        return ResponseEntity.ok(etapaInativada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirEtapa(@PathVariable Integer id) {
        etapaProcessoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
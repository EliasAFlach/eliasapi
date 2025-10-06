package br.edu.infnet.eliasapi.Controller.v1;

import br.edu.infnet.eliasapi.Enum.StatusAdocaoEnum;
import br.edu.infnet.eliasapi.Enum.StatusProcessoEnum;
import br.edu.infnet.eliasapi.Model.Adocao;
import br.edu.infnet.eliasapi.Service.AdocaoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/adocoes")
public class AdocaoController {

    private final AdocaoService adocaoService;

    public AdocaoController(AdocaoService adocaoService) {
        this.adocaoService = adocaoService;
    }

    @GetMapping
    public ResponseEntity<List<Adocao>> getAllAdocaos() {
        List<Adocao> adocoes = adocaoService.buscarTodos();
        return ResponseEntity.ok(adocoes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Adocao> getAdocaoById(@PathVariable Integer id) {
        Adocao adocao = adocaoService.buscarPorId(id);
        return ResponseEntity.ok(adocao);
    }

    @PostMapping
    public ResponseEntity<Adocao> adicionarAdocao(@Valid @RequestBody Adocao adocao) {
        Adocao adocaoCriada = adocaoService.inserir(adocao);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(adocaoCriada.getId())
                .toUri();
        return ResponseEntity.created(location).body(adocaoCriada);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Adocao> atualizarAdocao(@PathVariable Integer id, @Valid @RequestBody Adocao adocao) {
        Adocao adocaoAtualizada = adocaoService.atualizar(id, adocao);
        return ResponseEntity.ok(adocaoAtualizada);
    }

    @PatchMapping("/{id}/inativar")
    public ResponseEntity<Adocao> inativarAdocao(@PathVariable Integer id) {
        Adocao adocaoInativada = adocaoService.inativar(id);
        return ResponseEntity.ok(adocaoInativada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirAdocao(@PathVariable Integer id) {
        adocaoService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/aprovacoesAnteriores/{data}")
    public ResponseEntity<List<Adocao>> getAllAprovadasAntesDe(@PathVariable String data) {
        return ResponseEntity.ok(adocaoService.buscarTodasAdocoesAprovadasAnterioresA(data));
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<Adocao>> get(@PathVariable StatusProcessoEnum status) {
        return ResponseEntity.ok(adocaoService.buscarTodasAprovacoesPorStatus(status));
    }
}
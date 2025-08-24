package br.edu.infnet.eliasapi.Controller.v1;

import br.edu.infnet.eliasapi.Expections.AdocaoInvalidaException;
import br.edu.infnet.eliasapi.Model.Adocao;
import br.edu.infnet.eliasapi.Service.AdocaoService;
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
        try {
            Adocao adocao = adocaoService.buscarPorId(id);
            return ResponseEntity.ok(adocao);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<?> adicionarAdocao(@RequestBody Adocao adocao) {
        try {
            Adocao adocaoCriada = adocaoService.inserir(adocao);
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(adocaoCriada.getId())
                    .toUri();
            return ResponseEntity.created(location).body(adocaoCriada);
        } catch (AdocaoInvalidaException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarAdocao(@PathVariable Integer id, @RequestBody Adocao adocao) {
        try {
            if (!id.equals(adocao.getId())) {
                return ResponseEntity.badRequest().body("O ID na URL deve ser o mesmo do corpo da requisição.");
            }
            Adocao adocaoAtualizada = adocaoService.atualizar(adocao);
            return ResponseEntity.ok(adocaoAtualizada);
        } catch (AdocaoInvalidaException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{id}/inativar")
    public ResponseEntity<Adocao> inativarAdocao(@PathVariable Integer id) {
        try {
            Adocao adocaoInativada = adocaoService.inativar(id);
            return ResponseEntity.ok(adocaoInativada);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirAdocao(@PathVariable Integer id) {
        try {
            adocaoService.deletar(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
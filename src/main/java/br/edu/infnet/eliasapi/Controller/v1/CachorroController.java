package br.edu.infnet.eliasapi.Controller.v1;

import br.edu.infnet.eliasapi.Expections.AnimalInvalidoException;
import br.edu.infnet.eliasapi.Model.Cachorro;
import br.edu.infnet.eliasapi.Service.CachorroService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/cachorros")
@AllArgsConstructor
public class CachorroController {

    private final CachorroService cachorroService;

    @GetMapping
    public ResponseEntity<List<Cachorro>> getAllCachorros() {
        List<Cachorro> cachorros = cachorroService.buscarTodos();
        return ResponseEntity.ok(cachorros);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cachorro> getCachorroById(@PathVariable Integer id) {
        try {
            Cachorro cachorro = cachorroService.buscarPorId(id);
            return ResponseEntity.ok(cachorro);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<?> adicionarCachorro(@RequestBody Cachorro cachorro) {
        try {
            Cachorro cachorroCriado = cachorroService.inserir(cachorro);
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(cachorroCriado.getId())
                    .toUri();
            return ResponseEntity.created(location).body(cachorroCriado);
        } catch (AnimalInvalidoException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarCachorro(@PathVariable Integer id, @RequestBody Cachorro cachorro) {
        try {
            if (!id.equals(cachorro.getId())) {
                return ResponseEntity.badRequest().body("O ID na URL deve ser o mesmo do corpo da requisição.");
            }
            Cachorro cachorroAtualizado = cachorroService.atualizar(cachorro);
            return ResponseEntity.ok(cachorroAtualizado);
        } catch (AnimalInvalidoException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{id}/inativar")
    public ResponseEntity<Cachorro> inativarCachorro(@PathVariable Integer id) {
        try {
            Cachorro cachorroInativado = cachorroService.inativar(id);
            return ResponseEntity.ok(cachorroInativado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirCachorro(@PathVariable Integer id) {
        try {
            cachorroService.deletar(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
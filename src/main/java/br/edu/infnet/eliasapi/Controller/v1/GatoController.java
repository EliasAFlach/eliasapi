package br.edu.infnet.eliasapi.Controller.v1;

import br.edu.infnet.eliasapi.Expections.AnimalInvalidoException;
import br.edu.infnet.eliasapi.Model.Gato;
import br.edu.infnet.eliasapi.Service.GatoService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/gatos")
@AllArgsConstructor
public class GatoController {

    private final GatoService gatoService;

    @GetMapping
    public ResponseEntity<List<Gato>> getAllGatos() {
        List<Gato> gatos = gatoService.buscarTodos();
        return ResponseEntity.ok(gatos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Gato> getGatoById(@PathVariable Integer id) {
        try {
            Gato gato = gatoService.buscarPorId(id);
            return ResponseEntity.ok(gato);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<?> adicionarGato(@RequestBody Gato gato) {
        try {
            Gato gatoCriado = gatoService.inserir(gato);
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(gatoCriado.getId())
                    .toUri();
            return ResponseEntity.created(location).body(gatoCriado);
        } catch (AnimalInvalidoException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarGato(@PathVariable Integer id, @RequestBody Gato gato) {
        try {
            if (!id.equals(gato.getId())) {
                return ResponseEntity.badRequest().body("O ID na URL deve ser o mesmo do corpo da requisição.");
            }
            Gato gatoAtualizado = gatoService.atualizar(gato);
            return ResponseEntity.ok(gatoAtualizado);
        } catch (AnimalInvalidoException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{id}/inativar")
    public ResponseEntity<Gato> inativarGato(@PathVariable Integer id) {
        try {
            Gato gatoInativado = gatoService.inativar(id);
            return ResponseEntity.ok(gatoInativado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirGato(@PathVariable Integer id) {
        try {
            gatoService.deletar(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
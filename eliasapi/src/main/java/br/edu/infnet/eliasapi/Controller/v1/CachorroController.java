package br.edu.infnet.eliasapi.Controller.v1;

import br.edu.infnet.eliasapi.Model.Cachorro;
import br.edu.infnet.eliasapi.Service.CachorroService;
import jakarta.validation.Valid;
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
        Cachorro cachorro = cachorroService.buscarPorId(id);
        return ResponseEntity.ok(cachorro);
    }

    @PostMapping
    public ResponseEntity<Cachorro> adicionarCachorro(@Valid @RequestBody Cachorro cachorro) {
        Cachorro cachorroCriado = cachorroService.inserir(cachorro);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(cachorroCriado.getId())
                .toUri();
        return ResponseEntity.created(location).body(cachorroCriado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cachorro> atualizarCachorro(@PathVariable Integer id, @Valid @RequestBody Cachorro cachorro) {
        Cachorro cachorroAtualizado = cachorroService.atualizar(id, cachorro);
        return ResponseEntity.ok(cachorroAtualizado);
    }

    @PatchMapping("/{id}/inativar")
    public ResponseEntity<Cachorro> inativarCachorro(@PathVariable Integer id) {
        Cachorro cachorroInativado = cachorroService.inativar(id);
        return ResponseEntity.ok(cachorroInativado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirCachorro(@PathVariable Integer id) {
        cachorroService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/idade/{idade}")
    public ResponseEntity<List<Cachorro>> getAllCachorrosByIdadeAproximada(@PathVariable Integer idade) {
        return ResponseEntity.ok(cachorroService.buscarPorIdadeAproximada(idade));
    }

    @GetMapping("/raca/{raca}")
    public ResponseEntity<List<Cachorro>> getAllCachorrosByRacaTerminandoEm(@PathVariable String raca) {
        return ResponseEntity.ok(cachorroService.buscarPorRacaTerminandoEm(raca));
    }
}
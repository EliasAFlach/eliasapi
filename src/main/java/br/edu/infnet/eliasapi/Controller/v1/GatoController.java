package br.edu.infnet.eliasapi.Controller.v1;

import br.edu.infnet.eliasapi.Enum.PorteEnum;
import br.edu.infnet.eliasapi.Model.Gato;
import br.edu.infnet.eliasapi.Service.GatoService;
import jakarta.validation.Valid;
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
        Gato gato = gatoService.buscarPorId(id);
        return ResponseEntity.ok(gato);
    }

    @PostMapping
    public ResponseEntity<Gato> adicionarGato(@Valid @RequestBody Gato gato) {
        Gato gatoCriado = gatoService.inserir(gato);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(gatoCriado.getId())
                .toUri();
        return ResponseEntity.created(location).body(gatoCriado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Gato> atualizarGato(@PathVariable Integer id, @Valid @RequestBody Gato gato) {
        Gato gatoAtualizado = gatoService.atualizar(id, gato);
        return ResponseEntity.ok(gatoAtualizado);
    }

    @PatchMapping("/{id}/inativar")
    public ResponseEntity<Gato> inativarGato(@PathVariable Integer id) {
        Gato gatoInativado = gatoService.inativar(id);
        return ResponseEntity.ok(gatoInativado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirGato(@PathVariable Integer id) {
        gatoService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<Gato> getGatoByNome(@PathVariable String nome) {
        return ResponseEntity.ok(gatoService.buscarPorNome(nome));
    }

    @GetMapping("/porte/{porte}")
    public ResponseEntity<List<Gato>> getGatoByPorte(@PathVariable PorteEnum porte) {
        return ResponseEntity.ok(gatoService.buscarPorPorte(porte));
    }
}
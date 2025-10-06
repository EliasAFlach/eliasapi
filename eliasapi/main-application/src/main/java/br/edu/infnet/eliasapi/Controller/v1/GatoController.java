package br.edu.infnet.eliasapi.Controller.v1;

import br.edu.infnet.eliasapi.Dto.GatoRequest;
import br.edu.infnet.eliasapi.Dto.GatoResponse;
import br.edu.infnet.eliasapi.Mapper.GatoMapper;
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
    private final GatoMapper gatoMapper;

    @GetMapping
    public ResponseEntity<List<GatoResponse>> getAllGatos() {
        List<Gato> gatos = gatoService.buscarTodos();
        List<GatoResponse> response = gatoMapper.toResponseList(gatos);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GatoResponse> getGatoById(@PathVariable Integer id) {
        Gato gato = gatoService.buscarPorId(id);
        GatoResponse response = gatoMapper.toResponse(gato);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<GatoResponse> adicionarGato(@Valid @RequestBody GatoRequest request) {
        Gato gato = gatoMapper.toEntity(request);
        Gato gatoCriado = gatoService.inserir(gato);
        GatoResponse response = gatoMapper.toResponse(gatoCriado);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(response.getId())
                .toUri();
        return ResponseEntity.created(location).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GatoResponse> atualizarGato(@PathVariable Integer id, @Valid @RequestBody GatoRequest request) {
        Gato gato = gatoMapper.toEntity(request);

        Gato gatoAtualizado = gatoService.atualizar(id, gato);
        GatoResponse response = gatoMapper.toResponse(gatoAtualizado);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}/inativar")
    public ResponseEntity<GatoResponse> inativarGato(@PathVariable Integer id) {
        Gato gatoInativado = gatoService.inativar(id);
        GatoResponse response = gatoMapper.toResponse(gatoInativado);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirGato(@PathVariable Integer id) {
        gatoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
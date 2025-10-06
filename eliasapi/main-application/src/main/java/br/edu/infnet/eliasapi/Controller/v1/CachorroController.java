package br.edu.infnet.eliasapi.Controller.v1;

import br.edu.infnet.eliasapi.Dto.CachorroRequest;
import br.edu.infnet.eliasapi.Dto.CachorroResponse;
import br.edu.infnet.eliasapi.Mapper.CachorroMapper;
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
    private final CachorroMapper cachorroMapper;

    @GetMapping
    public ResponseEntity<List<CachorroResponse>> getAllCachorros() {
        List<Cachorro> cachorros = cachorroService.buscarTodos();
        List<CachorroResponse> response = cachorroMapper.toResponseList(cachorros);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CachorroResponse> getCachorroById(@PathVariable Integer id) {
        Cachorro cachorro = cachorroService.buscarPorId(id);
        CachorroResponse response = cachorroMapper.toResponse(cachorro);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<CachorroResponse> adicionarCachorro(@Valid @RequestBody CachorroRequest request) {
        Cachorro cachorro = cachorroMapper.toEntity(request);
        Cachorro cachorroCriado = cachorroService.inserir(cachorro);
        CachorroResponse response = cachorroMapper.toResponse(cachorroCriado);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(response.getId())
                .toUri();
        return ResponseEntity.created(location).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CachorroResponse> atualizarCachorro(@PathVariable Integer id, @Valid @RequestBody CachorroRequest request) {
        Cachorro cachorro = cachorroMapper.toEntity(request);

        Cachorro cachorroAtualizado = cachorroService.atualizar(id, cachorro);
        CachorroResponse response = cachorroMapper.toResponse(cachorroAtualizado);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}/inativar")
    public ResponseEntity<CachorroResponse> inativarCachorro(@PathVariable Integer id) {
        Cachorro cachorroInativado = cachorroService.inativar(id);
        CachorroResponse response = cachorroMapper.toResponse(cachorroInativado);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirCachorro(@PathVariable Integer id) {
        cachorroService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
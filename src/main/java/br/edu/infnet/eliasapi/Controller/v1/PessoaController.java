package br.edu.infnet.eliasapi.Controller.v1;

import br.edu.infnet.eliasapi.Controller.v1.request.PessoaRequest;
import br.edu.infnet.eliasapi.Controller.v1.response.PessoaResponse;
import br.edu.infnet.eliasapi.Expections.PessoaInvalidaException;
import br.edu.infnet.eliasapi.Controller.v1.mapper.PessoaMapper;
import br.edu.infnet.eliasapi.Model.Pessoa;
import br.edu.infnet.eliasapi.Service.PessoaService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/pessoas")
@AllArgsConstructor
public class PessoaController {

    private final PessoaService pessoaService;
    private final PessoaMapper pessoaMapper;

    @GetMapping
    public ResponseEntity<List<PessoaResponse>> getAllPessoas() {
        List<Pessoa> pessoas = pessoaService.buscarTodos();
        return ResponseEntity.ok(pessoaMapper.toResponseList(pessoas));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PessoaResponse> getPessoaById(@PathVariable Integer id) {
        try {
            Pessoa pessoa = pessoaService.buscarPorId(id);
            return ResponseEntity.ok(pessoaMapper.toResponse(pessoa));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<?> adicionarPessoa(@Valid @RequestBody PessoaRequest request) {
        try {
            Pessoa pessoa = pessoaMapper.toEntity(request);
            Pessoa pessoaCriada = pessoaService.inserir(pessoa);
            PessoaResponse response = pessoaMapper.toResponse(pessoaCriada);

            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(response.getId())
                    .toUri();
            return ResponseEntity.created(location).body(response);
        } catch (PessoaInvalidaException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarPessoa(@PathVariable Integer id,
                                             @Valid @RequestBody PessoaRequest request) {
        try {
            Pessoa pessoa = pessoaMapper.toEntity(request);
            pessoa.setId(id);

            Pessoa pessoaAtualizada = pessoaService.atualizar(pessoa);
            return ResponseEntity.ok(pessoaMapper.toResponse(pessoaAtualizada));
        } catch (PessoaInvalidaException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{id}/inativar")
    public ResponseEntity<PessoaResponse> inativarPessoa(@PathVariable Integer id) {
        try {
            Pessoa pessoaInativada = pessoaService.inativar(id);
            return ResponseEntity.ok(pessoaMapper.toResponse(pessoaInativada));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirPessoa(@PathVariable Integer id) {
        try {
            pessoaService.deletar(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
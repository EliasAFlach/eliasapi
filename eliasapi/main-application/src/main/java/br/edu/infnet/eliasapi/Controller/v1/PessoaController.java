package br.edu.infnet.eliasapi.Controller.v1;

import br.edu.infnet.eliasapi.Dto.PessoaCEPRequest;
import br.edu.infnet.eliasapi.Dto.PessoaRequest;
import br.edu.infnet.eliasapi.Dto.PessoaResponse;
import br.edu.infnet.eliasapi.Mapper.PessoaMapper;
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
        List<PessoaResponse> response = pessoaMapper.toResponseList(pessoas);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PessoaResponse> getPessoaById(@PathVariable Integer id) {
        Pessoa pessoa = pessoaService.buscarPorId(id);
        PessoaResponse response = pessoaMapper.toResponse(pessoa);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<PessoaResponse> adicionarPessoa(@Valid @RequestBody PessoaRequest request) {
        Pessoa pessoa = pessoaMapper.toEntity(request);
        Pessoa pessoaCriada = pessoaService.inserir(pessoa);
        PessoaResponse response = pessoaMapper.toResponse(pessoaCriada);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(response.getId())
                .toUri();
        return ResponseEntity.created(location).body(response);
    }

    @PostMapping("/cep")
    public ResponseEntity<Pessoa> adicionarPessoaComCep(@Valid @RequestBody PessoaCEPRequest pessoa) {
        Pessoa pessoaCriada = pessoaService.inserirPessoaComCep(pessoa);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(pessoaCriada.getId())
                .toUri();
        return ResponseEntity.created(location).body(pessoaCriada);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PessoaResponse> atualizarPessoa(@PathVariable Integer id, @Valid @RequestBody PessoaRequest request) {
        Pessoa pessoa = pessoaMapper.toEntity(request);

        Pessoa pessoaAtualizada = pessoaService.atualizar(id, pessoa);
        PessoaResponse response = pessoaMapper.toResponse(pessoaAtualizada);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}/inativar")
    public ResponseEntity<PessoaResponse> inativarPessoa(@PathVariable Integer id) {
        Pessoa pessoaInativada = pessoaService.inativar(id);
        PessoaResponse response = pessoaMapper.toResponse(pessoaInativada);
        return ResponseEntity.ok(response);
    }
}
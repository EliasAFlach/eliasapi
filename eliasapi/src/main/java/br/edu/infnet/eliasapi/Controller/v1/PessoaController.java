package br.edu.infnet.eliasapi.Controller.v1;

import br.edu.infnet.eliasapi.Controller.v1.request.PessoaCEPRequest;
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

    @GetMapping
    public ResponseEntity<List<Pessoa>> getAllPessoas() {
        List<Pessoa> pessoas = pessoaService.buscarTodos();
        return ResponseEntity.ok(pessoas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pessoa> getPessoaById(@PathVariable Integer id) {
        Pessoa pessoa = pessoaService.buscarPorId(id);
        return ResponseEntity.ok(pessoa);
    }

    @PostMapping
    public ResponseEntity<Pessoa> adicionarPessoa(@Valid @RequestBody Pessoa pessoa) {
        Pessoa pessoaCriada = pessoaService.inserir(pessoa);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(pessoaCriada.getId())
                .toUri();
        return ResponseEntity.created(location).body(pessoaCriada);
    }

    @PostMapping
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
    public ResponseEntity<Pessoa> atualizarPessoa(@PathVariable Integer id, @Valid @RequestBody Pessoa pessoa) {
        Pessoa pessoaAtualizada = pessoaService.atualizar(id, pessoa);
        return ResponseEntity.ok(pessoaAtualizada);
    }

    @PatchMapping("/{id}/inativar")
    public ResponseEntity<Pessoa> inativarPessoa(@PathVariable Integer id) {
        Pessoa pessoaInativada = pessoaService.inativar(id);
        return ResponseEntity.ok(pessoaInativada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirPessoa(@PathVariable Integer id) {
        pessoaService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<Pessoa> getPessoaByNome(@PathVariable String nome) {
        Pessoa pessoa = pessoaService.buscarPorNome(nome);
        return ResponseEntity.ok(pessoa);
    }

    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<Pessoa> getPessoaByCpf(@PathVariable String cpf) {
        Pessoa pessoa = pessoaService.buscarPorCpf(cpf);
        return ResponseEntity.ok(pessoa);
    }
}
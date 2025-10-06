package br.edu.infnet.eliasapi.Controller.v1;

import br.edu.infnet.eliasapi.Model.Funcionario;
import br.edu.infnet.eliasapi.Service.FuncionarioService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/funcionarios")
@AllArgsConstructor
public class FuncionarioController {

    private final FuncionarioService funcionarioService;

    @GetMapping
    public ResponseEntity<List<Funcionario>> getAllFuncionarios() {
        List<Funcionario> funcionarios = funcionarioService.buscarTodos();
        return ResponseEntity.ok(funcionarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Funcionario> getFuncionarioById(@PathVariable Integer id) {
        Funcionario funcionario = funcionarioService.buscarPorId(id);
        return ResponseEntity.ok(funcionario);
    }

    @PostMapping
    public ResponseEntity<Funcionario> adicionarFuncionario(@Valid @RequestBody Funcionario funcionario) {
        Funcionario funcionarioCriado = funcionarioService.inserir(funcionario);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(funcionarioCriado.getId())
                .toUri();
        return ResponseEntity.created(location).body(funcionarioCriado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Funcionario> atualizarFuncionario(@PathVariable Integer id, @Valid @RequestBody Funcionario funcionario) {
        Funcionario funcionarioAtualizado = funcionarioService.atualizar(id, funcionario);
        return ResponseEntity.ok(funcionarioAtualizado);
    }

    @PatchMapping("/{id}/inativar")
    public ResponseEntity<Funcionario> inativarFuncionario(@PathVariable Integer id) {
        Funcionario funcionarioInativado = funcionarioService.inativar(id);
        return ResponseEntity.ok(funcionarioInativado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirFuncionario(@PathVariable Integer id) {
        funcionarioService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/nomeCompleto/{nomeCompleto}")
    public ResponseEntity<Funcionario> getFuncionarioByNomeCompleto(@PathVariable String nomeCompleto) {
        Funcionario funcionario = funcionarioService.buscarPorNomeCompleto(nomeCompleto);
        return ResponseEntity.ok(funcionario);
    }

    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<Funcionario> getFuncionarioByCpf(@PathVariable String cpf) {
        Funcionario funcionario = funcionarioService.buscarPorCpf(cpf);
        return ResponseEntity.ok(funcionario);
    }
}
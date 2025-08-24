package br.edu.infnet.eliasapi.Controller.v1;

import br.edu.infnet.eliasapi.Model.Funcionario;
import br.edu.infnet.eliasapi.Service.FuncionarioService;
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
        try {
            Funcionario funcionario = funcionarioService.buscarPorId(id);
            return ResponseEntity.ok(funcionario);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<?> adicionarFuncionario(@RequestBody Funcionario funcionario) {
        try {
            Funcionario funcionarioCriado = funcionarioService.inserir(funcionario);
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(funcionarioCriado.getId())
                    .toUri();
            return ResponseEntity.created(location).body(funcionarioCriado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarFuncionario(@PathVariable Integer id, @RequestBody Funcionario funcionario) {
        try {
            if (!id.equals(funcionario.getId())) {
                return ResponseEntity.badRequest().body("O ID na URL deve ser o mesmo do corpo da requisição.");
            }
            Funcionario funcionarioAtualizado = funcionarioService.atualizar(funcionario);
            return ResponseEntity.ok(funcionarioAtualizado);
        } catch (IllegalArgumentException e) {
            // Esta exceção pode ser tanto para "não encontrado" quanto para validação,
            // dependendo do seu service. Retornar 404 é uma escolha segura para "não encontrado".
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{id}/inativar")
    public ResponseEntity<Funcionario> inativarFuncionario(@PathVariable Integer id) {
        try {
            Funcionario funcionarioInativado = funcionarioService.inativar(id);
            return ResponseEntity.ok(funcionarioInativado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirFuncionario(@PathVariable Integer id) {
        try {
            funcionarioService.deletar(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
package br.edu.infnet.eliasapi.Controller.v1;

import br.edu.infnet.eliasapi.Dto.FuncionarioRequest;
import br.edu.infnet.eliasapi.Dto.FuncionarioResponse;
import br.edu.infnet.eliasapi.Mapper.FuncionarioMapper;
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
    private final FuncionarioMapper funcionarioMapper;

    @GetMapping
    public ResponseEntity<List<FuncionarioResponse>> getAllFuncionarios() {
        List<Funcionario> funcionarios = funcionarioService.buscarTodos();
        List<FuncionarioResponse> response = funcionarioMapper.toResponseList(funcionarios);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FuncionarioResponse> getFuncionarioById(@PathVariable Integer id) {
        Funcionario funcionario = funcionarioService.buscarPorId(id);
        FuncionarioResponse response = funcionarioMapper.toResponse(funcionario);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<FuncionarioResponse> adicionarFuncionario(@Valid @RequestBody FuncionarioRequest request) {
        Funcionario funcionario = funcionarioMapper.toEntity(request);
        Funcionario funcionarioCriado = funcionarioService.inserir(funcionario);
        FuncionarioResponse response = funcionarioMapper.toResponse(funcionarioCriado);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(response.getId())
                .toUri();
        return ResponseEntity.created(location).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FuncionarioResponse> atualizarFuncionario(@PathVariable Integer id, @Valid @RequestBody FuncionarioRequest request) {
        Funcionario funcionario = funcionarioMapper.toEntity(request);

        Funcionario funcionarioAtualizado = funcionarioService.atualizar(id, funcionario);
        FuncionarioResponse response = funcionarioMapper.toResponse(funcionarioAtualizado);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}/inativar")
    public ResponseEntity<FuncionarioResponse> inativarFuncionario(@PathVariable Integer id) {
        Funcionario funcionarioInativado = funcionarioService.inativar(id);
        FuncionarioResponse response = funcionarioMapper.toResponse(funcionarioInativado);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirFuncionario(@PathVariable Integer id) {
        funcionarioService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
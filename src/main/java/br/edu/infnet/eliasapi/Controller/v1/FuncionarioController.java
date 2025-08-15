package br.edu.infnet.eliasapi.Controller.v1;

import br.edu.infnet.eliasapi.Model.Funcionario;
import br.edu.infnet.eliasapi.Service.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/funcionarios")
public class FuncionarioController {

    @Autowired
    private FuncionarioService funcionarioService;

    @GetMapping
    public List<Funcionario> getAllFuncionarios(){
        return funcionarioService.buscarTodos();
    }

    @GetMapping("/{id}")
    public Funcionario getFuncionarioById(@PathVariable int id){
        return funcionarioService.buscarPorId(id);
    }

    @PostMapping
    public Funcionario adicionarFuncionario(@RequestBody Funcionario funcionario){
        return funcionarioService.inserir(funcionario);
    }

    @PutMapping()
    public Funcionario atualizarFuncionario(@RequestBody Funcionario funcionario){
        return funcionarioService.atualizar(funcionario);
    }

    @PatchMapping("/{id}/inativar")
    public Funcionario inativarFuncionario(@PathVariable int id){
        return funcionarioService.inativar(id);
    }

    @DeleteMapping("/{id}")
    public void excluirFuncionario(@PathVariable int id){
        funcionarioService.deletar(id);
    }

}

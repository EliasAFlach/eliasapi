package br.edu.infnet.eliasapi.Controller.v1;

import br.edu.infnet.eliasapi.Model.Pessoa;
import br.edu.infnet.eliasapi.Service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/pessoas")
public class PessoaController {

    @Autowired
    private PessoaService pessoaService;

    @GetMapping
    public List<Pessoa> getAllPessoas(){
        return pessoaService.buscarTodos();
    }

    @GetMapping("/{id}")
    public Pessoa getPessoaById(@PathVariable int id){
        return pessoaService.buscarPorId(id);
    }

    @PostMapping
    public Pessoa adicionarPessoa(@RequestBody Pessoa pessoa){
        return pessoaService.inserir(pessoa);
    }

    @PutMapping()
    public Pessoa atualizarPessoa(@RequestBody Pessoa pessoa){
        return pessoaService.atualizar(pessoa);
    }

    @PatchMapping("/{id}/inativar")
    public Pessoa inativarPessoa(@PathVariable int id){
        return pessoaService.inativar(id);
    }

    @DeleteMapping("/{id}")
    public void excluirPessoa(@PathVariable int id){
        pessoaService.deletar(id);
    }

}

package br.edu.infnet.eliasapi.Controller.v1;

import br.edu.infnet.eliasapi.Model.Cachorro;
import br.edu.infnet.eliasapi.Service.CachorroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cachorros")
public class CachorroController {

    @Autowired
    private CachorroService cachorroService;

    @GetMapping
    public List<Cachorro> getAllCachorros(){
        return cachorroService.buscarTodos();
    }

    @GetMapping("/{id}")
    public Cachorro getCachorroById(@PathVariable int id){
        return cachorroService.buscarPorId(id);
    }

    @PostMapping
    public Cachorro adicionarCachorro(@RequestBody Cachorro cachorro){
        return cachorroService.inserir(cachorro);
    }

    @PutMapping()
    public Cachorro atualizarCachorro(@RequestBody Cachorro cachorro){
        return cachorroService.atualizar(cachorro);
    }

    @PatchMapping("/{id}/inativar")
    public Cachorro inativarCachorro(@PathVariable int id){
        return cachorroService.inativar(id);
    }

    @DeleteMapping("/{id}")
    public void excluirCachorro(@PathVariable int id){
        cachorroService.deletar(id);
    }

}

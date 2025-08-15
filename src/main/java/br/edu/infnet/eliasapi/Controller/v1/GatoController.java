package br.edu.infnet.eliasapi.Controller.v1;

import br.edu.infnet.eliasapi.Model.Gato;
import br.edu.infnet.eliasapi.Service.GatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/gatos")
public class GatoController {

    @Autowired
    private GatoService gatoService;

    @GetMapping
    public List<Gato> getAllGatos(){
        return gatoService.buscarTodos();
    }

    @GetMapping("/{id}")
    public Gato getGatoById(@PathVariable int id){
        return gatoService.buscarPorId(id);
    }

    @PostMapping
    public Gato adicionarGato(@RequestBody Gato gato){
        return gatoService.inserir(gato);
    }

    @PutMapping()
    public Gato atualizarGato(@RequestBody Gato gato){
        return gatoService.atualizar(gato);
    }

    @PatchMapping("/{id}/inativar")
    public Gato inativarGato(@PathVariable int id){
        return gatoService.inativar(id);
    }

    @DeleteMapping("/{id}")
    public void excluirGato(@PathVariable int id){
        gatoService.deletar(id);
    }

}

package br.edu.infnet.eliasapi.Controller.v1;

import br.edu.infnet.eliasapi.Model.Adocao;
import br.edu.infnet.eliasapi.Service.AdocaoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/adocoes")
public class AdocaoController {

    private final AdocaoService adocaoService;

    public AdocaoController(AdocaoService adocaoService) {
        this.adocaoService = adocaoService;
    }

    @GetMapping
    public List<Adocao> getAllAdocaos(){
        return adocaoService.buscarTodos();
    }

    @GetMapping("/{id}")
    public Adocao getAdocaoById(@PathVariable int id){
        return adocaoService.buscarPorId(id);
    }

    @PostMapping
    public Adocao adicionarAdocao(@RequestBody Adocao adocao){
        return adocaoService.inserir(adocao);
    }

    @PutMapping()
    public Adocao atualizarAdocao(@RequestBody Adocao adocao){
        return adocaoService.atualizar(adocao);
    }

    @PatchMapping("/{id}/inativar")
    public Adocao inativarAdocao(@PathVariable int id){
        return adocaoService.inativar(id);
    }

    @DeleteMapping("/{id}")
    public void excluirAdocao(@PathVariable int id){
        adocaoService.deletar(id);
    }

}

package br.edu.infnet.eliasapi.Controller.v1;

import br.edu.infnet.eliasapi.Model.Animal;
import br.edu.infnet.eliasapi.Service.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/animais")
public class AnimalController {

    @Autowired
    private AnimalService animalService;

    @GetMapping
    public List<Animal> getAllAnimais(){
        return animalService.buscarTodos();
    }

    @GetMapping("/{id}")
    public Animal getAnimalById(@PathVariable int id){
        return animalService.buscarPorId(id);
    }
}

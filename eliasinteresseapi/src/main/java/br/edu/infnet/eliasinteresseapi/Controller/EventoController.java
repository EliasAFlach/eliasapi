package br.edu.infnet.eliasinteresseapi.Controller;

import br.edu.infnet.eliasinteresseapi.Dto.AnimalDTO;
import br.edu.infnet.eliasinteresseapi.Service.FilaDeInteresseService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/eventos")
@AllArgsConstructor
public class EventoController {

    private final FilaDeInteresseService filaDeInteresseService;

    @PostMapping("/animal-cadastrado")
    public ResponseEntity<Void> handleAnimalCadastrado(@RequestBody AnimalDTO animalDTO) {
        System.out.println("Matches encontrados: " + filaDeInteresseService.buscarInteressesCompativeis(animalDTO));
        return ResponseEntity.ok().build();
    }
}
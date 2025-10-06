package br.edu.infnet.eliasapi.Service;

import br.edu.infnet.eliasapi.Dto.AnimalDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class NotificacaoService {

    private final WebClient webClient;

    public NotificacaoService() {
        this.webClient = WebClient.create("http://localhost:8081");
    }

    public void notificarNovoAnimal(AnimalDTO animalDTO) {
        System.out.println("Notificando interesse-service sobre o novo animal ID: " + animalDTO.getId());
        webClient.post()
                .uri("/api/v1/eventos/animal-cadastrado")
                .bodyValue(animalDTO)
                .retrieve()
                .toBodilessEntity()
                .subscribe(
                        success -> System.out.println("Notificação enviada com sucesso!"),
                        error -> System.err.println("Erro ao notificar: " + error.getMessage())
                );
    }
}
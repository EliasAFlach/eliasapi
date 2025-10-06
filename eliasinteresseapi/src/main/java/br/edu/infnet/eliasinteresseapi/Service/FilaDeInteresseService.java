package br.edu.infnet.eliasinteresseapi.Service;

import br.edu.infnet.eliasinteresseapi.Dto.AnimalDTO;
import br.edu.infnet.eliasinteresseapi.Enum.StatusInteresseEnum;
import br.edu.infnet.eliasinteresseapi.Model.Interesse;
import br.edu.infnet.eliasinteresseapi.Repository.InteresseRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class FilaDeInteresseService {

    private final InteresseRepository interesseRepository;

    public List<Interesse> buscarInteressesCompativeis(AnimalDTO animal) {
        System.out.println("Buscando interesses compatíveis para o animal ID: " + animal.getId());
        List<Interesse> interessesAtivos = interesseRepository.findAllByStatus(StatusInteresseEnum.ATIVO);

        return interessesAtivos.stream()
                .filter(i -> i.getEspecie() == animal.getEspecie())
                .filter(i -> i.getPorte() == null || i.getPorte() == animal.getPorte())
                .filter(i -> i.getSexo() == null || i.getSexo() == animal.getSexo())
                .filter(i -> i.getIdadeMaximaEmMeses() == null || i.getIdadeMaximaEmMeses() >= animal.getIdadeAproximada())
                .toList();
    }

    public Interesse registrarInteresse(Interesse interesse) {
        if (interesse == null || interesse.getPessoaId() == null) {
            throw new IllegalArgumentException("O interesse e a pessoa associada não podem ser nulos.");
        }
        return interesseRepository.save(interesse);
    }
}

package br.edu.infnet.eliasinteresseapi.Service;

import br.edu.infnet.eliasinteresseapi.Dto.AnimalDTO;
import br.edu.infnet.eliasinteresseapi.Enum.PorteEnum;
import br.edu.infnet.eliasinteresseapi.Enum.StatusInteresseEnum;
import br.edu.infnet.eliasinteresseapi.Enum.TipoAnimalEnum;
import br.edu.infnet.eliasinteresseapi.Model.Interesse;
import br.edu.infnet.eliasinteresseapi.Repository.InteresseRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FilaDeInteresseServiceTest {

    @Mock
    private InteresseRepository interesseRepository;

    @InjectMocks
    private FilaDeInteresseService filaDeInteresseService;

    @Test
    @DisplayName("Deve retornar lista vazia quando não há interesses ativos")
    void buscarInteressesCompativeis_CenarioSemInteresses() {
        AnimalDTO animalDTO = new AnimalDTO();
        when(interesseRepository.findAllByStatus(StatusInteresseEnum.ATIVO)).thenReturn(Collections.emptyList());


        List<Interesse> resultado = filaDeInteresseService.buscarInteressesCompativeis(animalDTO);

        assertTrue(resultado.isEmpty());
    }

    @Test
    @DisplayName("Deve encontrar interesse compatível com a espécie e o porte do AnimalDTO")
    void buscarInteressesCompativeis_CenarioComMatchDePorte() {
        Interesse interessePorCachorroPequeno = new Interesse();
        interessePorCachorroPequeno.setEspecie(TipoAnimalEnum.CACHORRO);
        interessePorCachorroPequeno.setPorte(PorteEnum.PEQUENO);

        when(interesseRepository.findAllByStatus(StatusInteresseEnum.ATIVO)).thenReturn(List.of(interessePorCachorroPequeno));

        AnimalDTO animalCompativel = new AnimalDTO();
        animalCompativel.setEspecie(TipoAnimalEnum.CACHORRO);
        animalCompativel.setPorte(PorteEnum.PEQUENO);

        List<Interesse> resultado = filaDeInteresseService.buscarInteressesCompativeis(animalCompativel);

        assertEquals(1, resultado.size());
        assertEquals(interessePorCachorroPequeno, resultado.getFirst());
    }

    @Test
    @DisplayName("Deve lançar exceção ao tentar registrar um interesse nulo")
    void registrarInteresse_DeveLancarExcecao_QuandoInteresseNulo() {
        Interesse interesseNulo = null;

        assertThrows(IllegalArgumentException.class, () -> {
            filaDeInteresseService.registrarInteresse(interesseNulo);
        });
    }

    @Test
    @DisplayName("Não deve encontrar interesse quando a espécie do animal for diferente")
    void buscarInteressesCompativeis_CenarioSemMatchDeEspecie() {

        Interesse interessePorGato = new Interesse();
        interessePorGato.setEspecie(TipoAnimalEnum.GATO);
        when(interesseRepository.findAllByStatus(StatusInteresseEnum.ATIVO)).thenReturn(List.of(interessePorGato));

        AnimalDTO animalIncompativel = new AnimalDTO();
        animalIncompativel.setEspecie(TipoAnimalEnum.CACHORRO);

        List<Interesse> resultado = filaDeInteresseService.buscarInteressesCompativeis(animalIncompativel);

        assertTrue(resultado.isEmpty());
    }
}

package br.edu.infnet.eliasapi.Service;

import br.edu.infnet.eliasapi.Dto.AnimalDTO;
import br.edu.infnet.eliasapi.Enum.TipoAnimalEnum;
import br.edu.infnet.eliasapi.Expections.OperacaoNaoPermitidaException;
import br.edu.infnet.eliasapi.Model.Cachorro;
import br.edu.infnet.eliasapi.Repository.AdocaoRepository;
import br.edu.infnet.eliasapi.Repository.CachorroRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CachorroService implements CrudService<Cachorro, Integer> {

    private final CachorroRepository cachorroRepository;
    private final AdocaoRepository adocaoRepository;
    private final NotificacaoService notificacaoService;

    @Override
    public Cachorro inserir(Cachorro cachorro) {
        Cachorro cachorroSalvo = cachorroRepository.save(cachorro);
        notificarServicoInteresse(cachorroSalvo);

        return cachorroSalvo;
    }

    @Override
    public void deletar(Integer id) {
        if (adocaoRepository.existsByCachorroId(id)) {
            throw new OperacaoNaoPermitidaException("Este cachorro já possui um processo de adoção associado e não pode ser excluído.");
        }

        cachorroRepository.deleteById(id);
    }

    @Override
    public Cachorro atualizar(Integer id, Cachorro cachorroAtualizado) {
        cachorroRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cachorro não encontrado para o id: " + id));
        cachorroAtualizado.setId(id);
        return cachorroRepository.save(cachorroAtualizado);
    }

    @Override
    public Cachorro inativar(Integer id) {
        Cachorro cachorro = buscarPorId(id);
        cachorro.setAtivo(false);
        return cachorroRepository.save(cachorro);
    }

    @Override
    public Cachorro buscarPorId(Integer id) {
        return cachorroRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cachorro não encontrado para o id: " + id));
    }

    @Override
    public List<Cachorro> buscarTodos() {
        return cachorroRepository.findAll();
    }

    public List<Cachorro> buscarPorRacaTerminandoEm(String raca) {
        return cachorroRepository.findByRacaEndingWithIgnoreCase(raca)
                .orElseThrow(() -> new IllegalArgumentException("Cachorros não encontrado para a raça: " + raca));
    }

    public List<Cachorro> buscarPorIdadeAproximada(Integer idade) {
        return cachorroRepository.findByIdadeAproximada(idade)
                .orElseThrow(() -> new IllegalArgumentException("Cachorros não encontrados para a idade: " + idade));
    }

    private void notificarServicoInteresse(Cachorro cachorro) {
        AnimalDTO dto = new AnimalDTO();
        dto.setId(cachorro.getId());
        dto.setEspecie(TipoAnimalEnum.CACHORRO);
        dto.setPorte(cachorro.getPorte());
        dto.setSexo(cachorro.getSexo());
        dto.setIdadeAproximada(cachorro.getIdadeAproximada());
        notificacaoService.notificarNovoAnimal(dto);
    }
}
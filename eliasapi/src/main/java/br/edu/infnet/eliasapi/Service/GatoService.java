package br.edu.infnet.eliasapi.Service;

import br.edu.infnet.eliasapi.Enum.PorteEnum;
import br.edu.infnet.eliasapi.Expections.AnimalInvalidoException;
import br.edu.infnet.eliasapi.Expections.OperacaoNaoPermitidaException;
import br.edu.infnet.eliasapi.Model.Gato;
import br.edu.infnet.eliasapi.Repository.AdocaoRepository;
import br.edu.infnet.eliasapi.Repository.GatoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GatoService implements CrudService<Gato, Integer> {

    private final GatoRepository gatoRepository;
    private final AdocaoRepository adocaoRepository;

    @Override
    public Gato inserir(Gato gato) {
        return gatoRepository.save(gato);
    }

    @Override
    public void deletar(Integer id) {
        if (adocaoRepository.existsByGatoId(id)) {
            throw new OperacaoNaoPermitidaException("Este gato já possui um processo de adoção associado e não pode ser excluído.");
        }

        gatoRepository.deleteById(id);
    }

    @Override
    public Gato atualizar(Integer id, Gato gatoAtualizado) {
        gatoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Gato não encontrado para o id: " + id));
        gatoAtualizado.setId(id);
        return gatoRepository.save(gatoAtualizado);
    }

    @Override
    public Gato inativar(Integer id) {
        Gato gato = buscarPorId(id);
        gato.setAtivo(false);
        return gatoRepository.save(gato);
    }

    @Override
    public Gato buscarPorId(Integer id) {
        return gatoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Gato não encontrado para o id: " + id));
    }

    @Override
    public List<Gato> buscarTodos() {
        return gatoRepository.findAll();
    }

    public Gato buscarPorNome(String nome) {
        return gatoRepository.findByNomeIgnoreCase(nome)
                .orElseThrow(() -> new IllegalArgumentException("Gato não encontrado para o nome: " + nome));
    }

    public List<Gato> buscarPorPorte(PorteEnum porte) {
        return gatoRepository.findByPorte(porte)
                .orElseThrow(() -> new IllegalArgumentException("Gato não encontrado para o porte: " + porte.name()));
    }



}
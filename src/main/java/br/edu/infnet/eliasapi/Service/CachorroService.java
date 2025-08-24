package br.edu.infnet.eliasapi.Service;

import br.edu.infnet.eliasapi.Expections.AnimalInvalidoException;
import br.edu.infnet.eliasapi.Model.Cachorro;
import br.edu.infnet.eliasapi.Model.Gato;
import br.edu.infnet.eliasapi.Repository.CachorroRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CachorroService implements CrudService<Cachorro, Integer> {

    private final CachorroRepository cachorroRepository;

    private void validar(Cachorro cachorro) {
        if(cachorro == null) {
            throw new IllegalArgumentException("A nome do animal não pode estar nulo!");
        }

        if(cachorro.getNome() == null || cachorro.getNome().trim().isEmpty()) {
            throw new AnimalInvalidoException("O nome do animal é uma informação obrigatória!");
        }
    }

    @Override
    public Cachorro inserir(Cachorro cachorro) {
        validar(cachorro);
        return cachorroRepository.save(cachorro);
    }

    @Override
    public void deletar(Integer id) {
        cachorroRepository.deleteById(id);
    }

    @Override
    public Cachorro atualizar(Cachorro cachorro) {
        validar(cachorro);
        return cachorroRepository.save(cachorro);
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
}
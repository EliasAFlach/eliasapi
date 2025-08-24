package br.edu.infnet.eliasapi.Service;

import br.edu.infnet.eliasapi.Expections.AnimalInvalidoException;
import br.edu.infnet.eliasapi.Model.Gato;
import br.edu.infnet.eliasapi.Repository.GatoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GatoService implements CrudService<Gato, Integer> {

    private final GatoRepository gatoRepository;

    private void validar(Gato gato) {
        if(gato == null) {
            throw new IllegalArgumentException("A nome do animal não pode estar nulo!");
        }

        if(gato.getNome() == null || gato.getNome().trim().isEmpty()) {
            throw new AnimalInvalidoException("O nome do animal é uma informação obrigatória!");
        }
    }

    @Override
    public Gato inserir(Gato gato) {
        validar(gato);
        return gatoRepository.save(gato);
    }

    @Override
    public void deletar(Integer id) {
        gatoRepository.deleteById(id);
    }

    @Override
    public Gato atualizar(Gato gato) {
        validar(gato);
        return gatoRepository.save(gato);
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
}
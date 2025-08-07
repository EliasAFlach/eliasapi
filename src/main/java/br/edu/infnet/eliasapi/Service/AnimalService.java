package br.edu.infnet.eliasapi.Service;

import br.edu.infnet.eliasapi.Expections.AnimalInvalidoException;
import br.edu.infnet.eliasapi.Model.Animal;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class AnimalService implements CrudService<Animal, Integer> {

    private final Map<Integer, Animal> mapa = new ConcurrentHashMap<Integer, Animal>();
    private final AtomicInteger nextId = new AtomicInteger(1);


    @Override
    public Animal inserir(Animal animal) {
        if(animal.getNome() == null) {
            throw new AnimalInvalidoException("O nome do animal não pode ser vazio");
        }

        animal.setId(nextId.getAndIncrement());
        mapa.put(animal.getId(), animal);

        return animal;
    }

    @Override
    public void deletar(Integer animal) {
        mapa.remove(animal);
    }

    @Override
    public Animal buscarPorId(Integer id) {
        Animal animal = mapa.get(id);

        if(animal == null) {
            throw new IllegalArgumentException("Animal não encontrado para o id: " + id);
        }

        return animal;
    }

    @Override
    public List<Animal> buscarTodos() {
        return new ArrayList<>(mapa.values());
    }
}

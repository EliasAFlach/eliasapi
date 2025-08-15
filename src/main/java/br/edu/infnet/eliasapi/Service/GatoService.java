package br.edu.infnet.eliasapi.Service;

import br.edu.infnet.eliasapi.Expections.AnimalInvalidoException;
import br.edu.infnet.eliasapi.Model.Gato;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class GatoService implements CrudService<Gato, Integer> {

    private final Map<Integer, Gato> mapa = new ConcurrentHashMap<Integer, Gato>();
    private final AtomicInteger nextId = new AtomicInteger(1);


    @Override
    public Gato inserir(Gato gato) {
        if(gato.getNome() == null) {
            throw new AnimalInvalidoException("O nome do gato não pode ser vazio");
        }

        gato.setId(nextId.getAndIncrement());
        mapa.put(gato.getId(), gato);

        return gato;
    }

    @Override
    public void deletar(Integer gato) {
        mapa.remove(gato);
    }

    @Override
    public Gato atualizar(Gato gato) {
        return mapa.put(gato.getId(), gato);
    }

    @Override
    public Gato inativar(Integer id) {
        Gato gato = mapa.get(id);
        gato.setAtivo(false);

        return  gato;
    }

    @Override
    public Gato buscarPorId(Integer id) {
        Gato gato = mapa.get(id);

        if(gato == null) {
            throw new IllegalArgumentException("Gato não encontrado para o id: " + id);
        }

        return gato;
    }

    @Override
    public List<Gato> buscarTodos() {
        return new ArrayList<>(mapa.values());
    }
}

package br.edu.infnet.eliasapi.Service;

import br.edu.infnet.eliasapi.Expections.AnimalInvalidoException;
import br.edu.infnet.eliasapi.Model.Cachorro;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class CachorroService implements CrudService<Cachorro, Integer> {

    private final Map<Integer, Cachorro> mapa = new ConcurrentHashMap<Integer, Cachorro>();
    private final AtomicInteger nextId = new AtomicInteger(1);


    @Override
    public Cachorro inserir(Cachorro cachorro) {
        if(cachorro.getNome() == null) {
            throw new AnimalInvalidoException("O nome do cachorro não pode ser vazio");
        }

        cachorro.setId(nextId.getAndIncrement());
        mapa.put(cachorro.getId(), cachorro);

        return cachorro;
    }

    @Override
    public void deletar(Integer cachorro) {
        mapa.remove(cachorro);
    }

    @Override
    public Cachorro atualizar(Cachorro cachorro) {
        return mapa.put(cachorro.getId(), cachorro);
    }

    @Override
    public Cachorro inativar(Integer id) {
        Cachorro cachorro = mapa.get(id);
        cachorro.setAtivo(false);

        return  cachorro;
    }

    @Override
    public Cachorro buscarPorId(Integer id) {
        Cachorro cachorro = mapa.get(id);

        if(cachorro == null) {
            throw new IllegalArgumentException("Cachorro não encontrado para o id: " + id);
        }

        return cachorro;
    }

    @Override
    public List<Cachorro> buscarTodos() {
        return new ArrayList<>(mapa.values());
    }
}

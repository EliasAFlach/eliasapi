package br.edu.infnet.eliasapi.Service;

import br.edu.infnet.eliasapi.Expections.AnimalInvalidoException;
import br.edu.infnet.eliasapi.Model.Adocao;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class AdocaoService implements CrudService<Adocao, Integer> {

    private final Map<Integer, Adocao> mapa = new ConcurrentHashMap<Integer, Adocao>();
    private final AtomicInteger nextId = new AtomicInteger(1);


    @Override
    public Adocao inserir(Adocao adocao) {
        if(Objects.isNull(adocao.getAdotante())) {
            throw new AnimalInvalidoException("O adotante não pode ser vazio");
        }

        adocao.setId(nextId.getAndIncrement());
        mapa.put(adocao.getId(), adocao);

        return adocao;
    }

    @Override
    public void deletar(Integer adocao) {
        mapa.remove(adocao);
    }

    @Override
    public Adocao atualizar(Adocao adocao) {
        return mapa.put(adocao.getId(), adocao);
    }

    @Override
    public Adocao inativar(Integer id) {
        Adocao adocao = mapa.get(id);
        adocao.setAtivo(false);

        return  adocao;
    }

    @Override
    public Adocao buscarPorId(Integer id) {
        Adocao adocao = mapa.get(id);

        if(adocao == null) {
            throw new IllegalArgumentException("Adocao não encontrado para o id: " + id);
        }

        return adocao;
    }

    @Override
    public List<Adocao> buscarTodos() {
        return new ArrayList<>(mapa.values());
    }
}

package br.edu.infnet.eliasapi.Service;

import br.edu.infnet.eliasapi.Expections.PessoaInvalidaException;
import br.edu.infnet.eliasapi.Model.Pessoa;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class PessoaService implements CrudService<Pessoa, Integer> {

    private final Map<Integer, Pessoa> mapa = new ConcurrentHashMap<Integer, Pessoa>();
    private final AtomicInteger nextId = new AtomicInteger(1);


    @Override
    public Pessoa inserir(Pessoa pessoa) {
        if(pessoa.getNome() == null) {
            throw new PessoaInvalidaException("O nome do pessoa não pode ser vazio");
        }

        pessoa.setId(nextId.getAndIncrement());
        mapa.put(pessoa.getId(), pessoa);

        return pessoa;
    }

    @Override
    public void deletar(Integer pessoa) {
        mapa.remove(pessoa);
    }

    @Override
    public Pessoa buscarPorId(Integer id) {
        Pessoa pessoa = mapa.get(id);

        if(pessoa == null) {
            throw new IllegalArgumentException("Pessoa não encontrado para o id: " + id);
        }

        return pessoa;
    }

    @Override
    public List<Pessoa> buscarTodos() {
        return new ArrayList<>(mapa.values());
    }
}

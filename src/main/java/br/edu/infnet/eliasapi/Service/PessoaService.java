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
    public Pessoa inserir(Pessoa Pessoa) {
        if(Pessoa.getNome() == null) {
            throw new PessoaInvalidaException("O nome do Pessoa não pode ser vazio");
        }

        Pessoa.setId(nextId.getAndIncrement());
        mapa.put(Pessoa.getId(), Pessoa);

        return Pessoa;
    }

    @Override
    public void deletar(Integer Pessoa) {
        mapa.remove(Pessoa);
    }

    @Override
    public Pessoa atualizar(Pessoa pessoa) {
        return mapa.put(pessoa.getId(), pessoa);
    }

    @Override
    public Pessoa inativar(Integer id) {
        Pessoa Pessoa = mapa.get(id);
        Pessoa.setAtivo(false);
        
        return  Pessoa;
    }

    @Override
    public Pessoa buscarPorId(Integer id) {
        Pessoa Pessoa = mapa.get(id);

        if(Pessoa == null) {
            throw new IllegalArgumentException("Pessoa não encontrado para o id: " + id);
        }

        return Pessoa;
    }

    @Override
    public List<Pessoa> buscarTodos() {
        return new ArrayList<>(mapa.values());
    }
}

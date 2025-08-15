package br.edu.infnet.eliasapi.Service;

import br.edu.infnet.eliasapi.Expections.AnimalInvalidoException;
import br.edu.infnet.eliasapi.Model.Funcionario;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class FuncionarioService implements CrudService<Funcionario, Integer> {

    private final Map<Integer, Funcionario> mapa = new ConcurrentHashMap<Integer, Funcionario>();
    private final AtomicInteger nextId = new AtomicInteger(1);


    @Override
    public Funcionario inserir(Funcionario funcionario) {
        if(funcionario.getNomeCompleto() == null) {
            throw new AnimalInvalidoException("O nome do funcionario não pode ser vazio");
        }

        funcionario.setId(nextId.getAndIncrement());
        mapa.put(funcionario.getId(), funcionario);

        return funcionario;
    }

    @Override
    public void deletar(Integer funcionario) {
        mapa.remove(funcionario);
    }

    @Override
    public Funcionario atualizar(Funcionario funcionario) {
        return mapa.put(funcionario.getId(), funcionario);
    }

    @Override
    public Funcionario inativar(Integer id) {
        Funcionario funcionario = mapa.get(id);
        funcionario.setAtivo(false);

        return  funcionario;
    }

    @Override
    public Funcionario buscarPorId(Integer id) {
        Funcionario funcionario = mapa.get(id);

        if(funcionario == null) {
            throw new IllegalArgumentException("Funcionario não encontrado para o id: " + id);
        }

        return funcionario;
    }

    @Override
    public List<Funcionario> buscarTodos() {
        return new ArrayList<>(mapa.values());
    }
}

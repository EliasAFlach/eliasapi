package br.edu.infnet.eliasapi.Service;

import br.edu.infnet.eliasapi.Expections.AnimalInvalidoException;
import br.edu.infnet.eliasapi.Model.Cachorro;
import br.edu.infnet.eliasapi.Model.Funcionario;
import br.edu.infnet.eliasapi.Repository.FuncionarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class FuncionarioService implements CrudService<Funcionario, Integer> {

    private final FuncionarioRepository funcionarioRepository;

    private void validar(Funcionario funcionario) {
        if(funcionario == null) {
            throw new IllegalArgumentException("A nome do funcionario não pode estar nulo!");
        }

        if(funcionario.getNomeCompleto() == null || funcionario.getNomeCompleto().trim().isEmpty()) {
            throw new AnimalInvalidoException("O nome do funcionario é uma informação obrigatória!");
        }
    }

    @Override
    public Funcionario inserir(Funcionario funcionario) {
        validar(funcionario);
        return funcionarioRepository.save(funcionario);
    }

    @Override
    public void deletar(Integer id) {
        funcionarioRepository.deleteById(id);
    }

    @Override
    public Funcionario atualizar(Funcionario funcionario) {
        validar(funcionario);
        return funcionarioRepository.save(funcionario);
    }

    @Override
    public Funcionario inativar(Integer id) {
        Funcionario funcionario = buscarPorId(id);
        funcionario.setAtivo(false);
        return funcionarioRepository.save(funcionario);
    }

    @Override
    public Funcionario buscarPorId(Integer id) {
        return funcionarioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Funcionário não encontrado para o id: " + id));
    }

    @Override
    public List<Funcionario> buscarTodos() {
        return funcionarioRepository.findAll();
    }
}
package br.edu.infnet.eliasapi.Service;

import br.edu.infnet.eliasapi.Expections.AnimalInvalidoException;
import br.edu.infnet.eliasapi.Model.Cachorro;
import br.edu.infnet.eliasapi.Model.Funcionario;
import br.edu.infnet.eliasapi.Repository.FuncionarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class FuncionarioService implements CrudService<Funcionario, Integer> {

    private final FuncionarioRepository funcionarioRepository;

   @Override
    public Funcionario inserir(Funcionario funcionario) {
        return funcionarioRepository.save(funcionario);
    }

    @Override
    public void deletar(Integer id) {
        funcionarioRepository.deleteById(id);
    }

    @Override
    public Funcionario atualizar(Integer id, Funcionario funcionarioAtualizado) {
        funcionarioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Funcionário não encontrado para o id: " + id));
        funcionarioAtualizado.setId(id);
        return funcionarioRepository.save(funcionarioAtualizado);
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

    public Funcionario buscarPorNomeCompleto(String nomeCompleto) {
        return funcionarioRepository.findByNomeCompleto(nomeCompleto)
                .orElseThrow(() -> new IllegalArgumentException("Funcionário não encontrado para o nome: " + nomeCompleto));
    }

    public Funcionario buscarPorCpf(String cpf) {
        return funcionarioRepository.findByCpf(cpf)
                .orElseThrow(() -> new IllegalArgumentException("Funcionário não encontrado para o cpf: " + cpf));
    }
}
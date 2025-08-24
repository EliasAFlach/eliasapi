package br.edu.infnet.eliasapi.Service;

import br.edu.infnet.eliasapi.Expections.PessoaInvalidaException;
import br.edu.infnet.eliasapi.Model.Pessoa;
import br.edu.infnet.eliasapi.Repository.PessoaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PessoaService implements CrudService<Pessoa, Integer> {

    PessoaRepository pessoaRepository;

    private void validar(Pessoa pessoa) {
        if(pessoa == null) {
            throw new IllegalArgumentException("A pessoa não pode estar nula!");
        }

        if(pessoa.getNome() == null || pessoa.getNome().trim().isEmpty()) {
            throw new PessoaInvalidaException("O nome da pessoa é uma informação obrigatória!");
        }
    }

    @Override
    public Pessoa inserir(Pessoa pessoa) {
        validar(pessoa);

        return pessoaRepository.save(pessoa);
    }

    @Override
    public void deletar(Integer id) {
        pessoaRepository.deleteById(id);
    }

    @Override
    public Pessoa atualizar(Pessoa pessoa) {
        validar(pessoa);

        return pessoaRepository.save(pessoa);
    }

    @Override
    public Pessoa inativar(Integer id) {
        Pessoa pessoa = buscarPorId(id);
        pessoa.setAtivo(false);
        return pessoaRepository.save(pessoa);
    }

    @Override
    public Pessoa buscarPorId(Integer id) {
        return pessoaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Pessoa não encontrada para o id: " + id));
    }

    @Override
    public List<Pessoa> buscarTodos() {
        return pessoaRepository.findAll();
    }
}

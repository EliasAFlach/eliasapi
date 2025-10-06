package br.edu.infnet.eliasapi.Service;

import br.edu.infnet.eliasapi.Client.InteresseServiceClient;
import br.edu.infnet.eliasapi.Controller.v1.mapper.PessoaMapper;
import br.edu.infnet.eliasapi.Controller.v1.request.PessoaCEPRequest;
import br.edu.infnet.eliasapi.Dto.EnderecoViaCepDTO;
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
    private final InteresseServiceClient interesseServiceClient;

    public Pessoa inserirPessoaComCep(PessoaCEPRequest pessoaRequest) {
        Pessoa pessoa = PessoaMapper.toEntity(pessoaRequest);
        EnderecoViaCepDTO enderecoDTO = interesseServiceClient.buscarEnderecoPorCep(pessoaRequest.getCep());

        String enderecoFormatado = String.format("%s, %s, %s - %s",
                pessoaRequest.getLogradouro(),
                enderecoDTO.getBairro(),
                enderecoDTO.getLocalidade(),
                enderecoDTO.getUf()
        );
        pessoa.setEndereco(enderecoFormatado);

        return pessoaRepository.save(pessoa);
    }

    @Override
    public Pessoa inserir(Pessoa pessoa) {
        pessoa.setAtivo(true);
        return pessoaRepository.save(pessoa);
    }

    @Override
    public void deletar(Integer id) {
        pessoaRepository.deleteById(id);
    }

    @Override
    public Pessoa atualizar(Integer id, Pessoa pessoaAtualizada) {
        pessoaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Pessoa não encontrada para o id: " + id));
        pessoaAtualizada.setId(id);
        return pessoaRepository.save(pessoaAtualizada);
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

    public Pessoa buscarPorCpf(String cpf) {
        return pessoaRepository.findByCpf(cpf)
                .orElseThrow(() -> new IllegalArgumentException("Pessoa não encontrada para o cpf: " + cpf));
    }

    public Pessoa buscarPorNome(String nome) {
        return pessoaRepository.findByNomeLike(nome)
                .orElseThrow(() -> new IllegalArgumentException("Pessoa não encontrada para o nome: " + nome));
    }
}

package br.edu.infnet.eliasapi.Mapper;

import br.edu.infnet.eliasapi.Dto.PessoaCEPRequest;
import br.edu.infnet.eliasapi.Dto.PessoaRequest;
import br.edu.infnet.eliasapi.Dto.PessoaResponse;
import br.edu.infnet.eliasapi.Model.Pessoa;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PessoaMapper {

    public static Pessoa toEntityCEP(PessoaCEPRequest request) {
        Pessoa pessoa = new Pessoa();
        pessoa.setCpf(request.getCpf());
        pessoa.setNome(request.getNome());
        pessoa.setDataNascimento(request.getDataNascimento());
        pessoa.setTelefone(request.getTelefone());
        pessoa.setAtivo(true);
        return pessoa;
    }

    public Pessoa toEntity(PessoaRequest request) {
        Pessoa pessoa = new Pessoa();
        pessoa.setCpf(request.getCpf());
        pessoa.setNome(request.getNome());
        pessoa.setDataNascimento(request.getDataNascimento());
        pessoa.setEndereco(request.getEndereco());
        pessoa.setTelefone(request.getTelefone());
        pessoa.setAtivo(true);
        return pessoa;
    }

    public PessoaResponse toResponse(Pessoa pessoa) {
        PessoaResponse response = new PessoaResponse();
        response.setId(pessoa.getId());
        response.setCpf(pessoa.getCpf());
        response.setNome(pessoa.getNome());
        response.setDataNascimento(pessoa.getDataNascimento());
        response.setEndereco(pessoa.getEndereco());
        response.setTelefone(pessoa.getTelefone());
        response.setAtivo(pessoa.isAtivo());
        return response;
    }

    public List<PessoaResponse> toResponseList(List<Pessoa> pessoas) {
        return pessoas.stream().map(this::toResponse).collect(Collectors.toList());
    }
}
package br.edu.infnet.eliasapi.Controller.v1.mapper;

import br.edu.infnet.eliasapi.Controller.v1.request.PessoaCEPRequest;
import br.edu.infnet.eliasapi.Controller.v1.response.PessoaResponse;
import br.edu.infnet.eliasapi.Model.Pessoa;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PessoaMapper {

    public static Pessoa toEntity(PessoaCEPRequest request) {
        Pessoa pessoa = new Pessoa();
        pessoa.setCpf(request.getCpf());
        pessoa.setNome(request.getNome());
        pessoa.setDataNascimento(request.getDataNascimento());
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
package br.edu.infnet.eliasapi.Mapper;

import br.edu.infnet.eliasapi.Dto.FuncionarioRequest;
import br.edu.infnet.eliasapi.Dto.FuncionarioResponse;
import br.edu.infnet.eliasapi.Model.Funcionario;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class FuncionarioMapper {

    public Funcionario toEntity(FuncionarioRequest request) {
        Funcionario funcionario = new Funcionario();
        funcionario.setCpf(request.getCpf());
        funcionario.setNomeCompleto(request.getNomeCompleto());
        funcionario.setEmail(request.getEmail());
        funcionario.setSenha(request.getSenha());
        funcionario.setMatricula(request.getMatricula());
        funcionario.setCargo(request.getCargo());
        funcionario.setDataAdmissao(request.getDataAdmissao());
        funcionario.setAtivo(true);
        return funcionario;
    }

    public FuncionarioResponse toResponse(Funcionario funcionario) {
        FuncionarioResponse response = new FuncionarioResponse();
        response.setId(funcionario.getId());
        response.setNomeCompleto(funcionario.getNomeCompleto());
        response.setEmail(funcionario.getEmail());
        response.setMatricula(funcionario.getMatricula());
        response.setCargo(funcionario.getCargo());
        response.setDataAdmissao(funcionario.getDataAdmissao());
        response.setAtivo(funcionario.isAtivo());
        return response;
    }

    public List<FuncionarioResponse> toResponseList(List<Funcionario> funcionarios) {
        return funcionarios.stream().map(this::toResponse).collect(Collectors.toList());
    }
}
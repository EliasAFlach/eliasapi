package br.edu.infnet.eliasapi.Controller.v1.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class PessoaResponse {

    private Integer id;
    private String cpf;
    private String nome;
    private LocalDate dataNascimento;
    private String endereco;
    private String telefone;
    private boolean ativo;
}
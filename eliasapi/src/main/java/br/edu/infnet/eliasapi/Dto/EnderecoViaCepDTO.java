package br.edu.infnet.eliasapi.Dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoViaCepDTO {
    private String cep;
    private String logradouro;
    private String complemento;
    private String bairro;
    private String localidade;
    private String uf;
}
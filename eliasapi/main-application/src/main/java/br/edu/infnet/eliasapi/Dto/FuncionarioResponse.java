package br.edu.infnet.eliasapi.Dto;

import br.edu.infnet.eliasapi.Enum.CargoEnum;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class FuncionarioResponse {

    private Integer id;
    private String nomeCompleto;
    private String email;
    private String matricula;
    private CargoEnum cargo;
    private LocalDate dataAdmissao;
    private boolean ativo;
}
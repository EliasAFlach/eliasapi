package br.edu.infnet.eliasapi.Model;

import br.edu.infnet.eliasapi.Enum.CargoEnum;
import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Funcionario {

    private int id;
    private String cpf;
    private String nomeCompleto;
    private String email;
    private String senha;
    private String matricula;
    private CargoEnum cargo;
    private LocalDate dataAdmissao;
    private boolean ativo;

}

package br.edu.infnet.eliasapi.Model;

import br.edu.infnet.eliasapi.Enum.CargoEnum;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

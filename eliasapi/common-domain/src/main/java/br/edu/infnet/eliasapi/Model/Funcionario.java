package br.edu.infnet.eliasapi.Model;

import br.edu.infnet.eliasapi.Enum.CargoEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

@Data
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
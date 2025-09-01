package br.edu.infnet.eliasapi.Model;

import br.edu.infnet.eliasapi.Enum.CargoEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.validator.constraints.br.CPF;

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

    @NotBlank(message = "O CPF é obrigatório.")
    @CPF(message = "O CPF informado é inválido.")
    private String cpf;

    @NotBlank(message = "O nome completo é obrigatório.")
    @Size(min = 2, max = 100, message = "O nome deve ter entre 2 e 100 caracteres.")
    private String nomeCompleto;

    @NotBlank(message = "O email é obrigatório.")
    @Email(message = "O formato do email é inválido.")
    private String email;

    @NotBlank(message = "A senha é obrigatória.")
    @Size(min = 6, message = "A senha deve ter no mínimo 6 caracteres.")
    private String senha;

    @NotBlank(message = "A matrícula é obrigatória.")
    private String matricula;

    @NotNull(message = "O cargo é obrigatório.")
    @Enumerated(EnumType.STRING)
    private CargoEnum cargo;

    @NotNull(message = "A data de admissão é obrigatória.")
    @PastOrPresent(message = "A data de admissão não pode ser uma data futura.")
    private LocalDate dataAdmissao;

    private boolean ativo;
}
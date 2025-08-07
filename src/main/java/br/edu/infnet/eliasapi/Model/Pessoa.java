package br.edu.infnet.eliasapi.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Pessoa {

    private int id;
    private String cpf;
    private String nome;
    private LocalDate dataNascimento;
    private String endereco;

    @Override
    public String toString() {
        return String.format("%d - %s - %s - %s", id, nome, dataNascimento, endereco);
    }

}

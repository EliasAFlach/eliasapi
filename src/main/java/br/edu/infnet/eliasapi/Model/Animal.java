package br.edu.infnet.eliasapi.Model;

import br.edu.infnet.eliasapi.Enum.Porte;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Animal {

    private int id;
    private String nome;
    private String raca;
    private LocalDate dataNascimento;
    private Porte porte;
    private boolean adotado;
    private Pessoa dono;

    @Override
    public String toString() {
        return String.format("%d - %s - %s - %s - %s - %s - %s", id, nome, raca, dataNascimento, porte, adotado, dono.getNome());
    }

}

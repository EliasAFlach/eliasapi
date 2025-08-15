package br.edu.infnet.eliasapi.Model;

import br.edu.infnet.eliasapi.Enum.StatusProcessoEnum;
import lombok.*;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Adocao {

    private int id;
    private Pessoa adotante;
    private Cachorro cachorro;
    private Gato gato;
    private LocalDate dataSolicitacao;
    private LocalDate dataAprovacao;
    private StatusProcessoEnum status;
    private String observacoes;
    private boolean ativo;
}
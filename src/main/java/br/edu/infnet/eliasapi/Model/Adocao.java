package br.edu.infnet.eliasapi.Model;

import br.edu.infnet.eliasapi.Enum.StatusProcessoEnum;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Entity
public class Adocao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pessoa_id", nullable = false)
    private Pessoa adotante;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cachorro_id", nullable = true)
    private Cachorro cachorro;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gato_id", nullable = true)
    private Gato gato;

    private LocalDate dataSolicitacao;
    private LocalDate dataAprovacao;
    private StatusProcessoEnum status;
    private String observacoes;
    private boolean ativo;

}
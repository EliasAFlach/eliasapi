package br.edu.infnet.eliasapi.Model;

import br.edu.infnet.eliasapi.Enum.StatusProcessoEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

    @NotNull(message = "O adotante é obrigatório.")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pessoa_id", nullable = false)
    private Pessoa adotante;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cachorro_id", nullable = true)
    private Cachorro cachorro;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gato_id", nullable = true)
    private Gato gato;

    @NotNull(message = "A data de solicitação é obrigatória.")
    @PastOrPresent(message = "A data de solicitação não pode ser no futuro.")
    private LocalDate dataSolicitacao;

    @PastOrPresent(message = "A data de aprovação não pode ser no futuro.")
    private LocalDate dataAprovacao;

    @NotNull(message = "O status do processo é obrigatório.")
    @Enumerated(EnumType.STRING)
    private StatusProcessoEnum status;

    @Size(max = 500, message = "As observações não podem exceder 500 caracteres.")
    private String observacoes;

    @OneToMany(
            mappedBy = "adocao",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    private List<EtapaProcesso> etapas = new ArrayList<>();

    private boolean ativo;
}
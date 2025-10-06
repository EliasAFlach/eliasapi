package br.edu.infnet.eliasapi.Model;

import br.edu.infnet.eliasapi.Enum.StatusProcessoEnum;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
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

    @Enumerated(EnumType.STRING)
    private StatusProcessoEnum status;

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
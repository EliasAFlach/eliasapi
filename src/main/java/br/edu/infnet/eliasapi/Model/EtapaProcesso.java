package br.edu.infnet.eliasapi.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
public class EtapaProcesso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "A descrição da etapa é obrigatória.")
    private String descricao;

    @NotNull(message = "A data da etapa é obrigatória.")
    @PastOrPresent(message = "A data da etapa não pode ser no futuro.")
    private LocalDate dataEtapa;

    private String observacoes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "adocao_id", nullable = false)
    @JsonIgnore
    private Adocao adocao;

    private boolean ativo;
}
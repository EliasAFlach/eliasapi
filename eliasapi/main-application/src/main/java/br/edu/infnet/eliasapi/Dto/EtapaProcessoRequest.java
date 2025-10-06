package br.edu.infnet.eliasapi.Dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class EtapaProcessoRequest {

    @NotBlank(message = "A descrição da etapa é obrigatória.")
    @Size(max = 255, message = "A descrição não pode exceder 255 caracteres.")
    private String descricao;

    @NotNull(message = "A data da etapa é obrigatória.")
    @PastOrPresent(message = "A data da etapa não pode ser no futuro.")
    private LocalDate dataEtapa;

    @Size(max = 500, message = "As observações não podem exceder 500 caracteres.")
    private String observacoes;
}
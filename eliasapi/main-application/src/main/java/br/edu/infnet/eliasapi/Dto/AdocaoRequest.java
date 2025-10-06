package br.edu.infnet.eliasapi.Dto;

import br.edu.infnet.eliasapi.Enum.StatusProcessoEnum;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class AdocaoRequest {

    @NotNull(message = "O ID do adotante é obrigatório.")
    private Integer pessoaId;

    private Integer cachorroId;
    private Integer gatoId;

    @NotNull(message = "A data de solicitação é obrigatória.")
    @PastOrPresent(message = "A data de solicitação não pode ser no futuro.")
    private LocalDate dataSolicitacao;

    @PastOrPresent(message = "A data de aprovação não pode ser no futuro.")
    private LocalDate dataAprovacao;

    @NotNull(message = "O status do processo é obrigatório.")
    private StatusProcessoEnum status;

    @Size(max = 500, message = "As observações não podem exceder 500 caracteres.")
    private String observacoes;
}
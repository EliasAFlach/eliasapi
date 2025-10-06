package br.edu.infnet.eliasapi.Dto;

import br.edu.infnet.eliasapi.Enum.StatusProcessoEnum;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AdocaoResponse {

    private Integer id;
    private LocalDate dataSolicitacao;
    private LocalDate dataAprovacao;
    private StatusProcessoEnum status;
    private String observacoes;
    private boolean ativo;

    private PessoaResponse adotante;
    private CachorroResponse cachorro;
    private GatoResponse gato;
    private List<EtapaProcessoResponse> etapas;
}
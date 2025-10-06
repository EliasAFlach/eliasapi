package br.edu.infnet.eliasapi.Dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class EtapaProcessoResponse {

    private Integer id;
    private String descricao;
    private LocalDate dataEtapa;
    private String observacoes;
    private boolean ativo;
    private Integer adocaoId;
}
package br.edu.infnet.eliasapi.Mapper;

import br.edu.infnet.eliasapi.Dto.EtapaProcessoRequest;
import br.edu.infnet.eliasapi.Dto.EtapaProcessoResponse;
import br.edu.infnet.eliasapi.Model.EtapaProcesso;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EtapaProcessoMapper {

    public EtapaProcesso toEntity(EtapaProcessoRequest request) {
        EtapaProcesso etapa = new EtapaProcesso();
        etapa.setDescricao(request.getDescricao());
        etapa.setDataEtapa(request.getDataEtapa());
        etapa.setObservacoes(request.getObservacoes());
        etapa.setAtivo(true);
        return etapa;
    }

    public EtapaProcessoResponse toResponse(EtapaProcesso etapa) {
        EtapaProcessoResponse response = new EtapaProcessoResponse();
        response.setId(etapa.getId());
        response.setDescricao(etapa.getDescricao());
        response.setDataEtapa(etapa.getDataEtapa());
        response.setObservacoes(etapa.getObservacoes());
        response.setAtivo(etapa.isAtivo());
        if (etapa.getAdocao() != null) {
            response.setAdocaoId(etapa.getAdocao().getId());
        }
        return response;
    }

    public List<EtapaProcessoResponse> toResponseList(List<EtapaProcesso> etapas) {
        return etapas.stream().map(this::toResponse).collect(Collectors.toList());
    }
}
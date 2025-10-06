package br.edu.infnet.eliasapi.Mapper;

import br.edu.infnet.eliasapi.Dto.AdocaoResponse;
import br.edu.infnet.eliasapi.Model.Adocao;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class AdocaoMapper {

    // Injeta os outros mappers para compor a resposta
    private final PessoaMapper pessoaMapper;
    private final CachorroMapper cachorroMapper;
    private final GatoMapper gatoMapper;
    private final EtapaProcessoMapper etapaProcessoMapper;

    public AdocaoResponse toResponse(Adocao adocao) {
        AdocaoResponse response = new AdocaoResponse();
        response.setId(adocao.getId());
        response.setDataSolicitacao(adocao.getDataSolicitacao());
        response.setDataAprovacao(adocao.getDataAprovacao());
        response.setStatus(adocao.getStatus());
        response.setObservacoes(adocao.getObservacoes());
        response.setAtivo(adocao.isAtivo());

        if (adocao.getAdotante() != null) {
            response.setAdotante(pessoaMapper.toResponse(adocao.getAdotante()));
        }
        if (adocao.getCachorro() != null) {
            response.setCachorro(cachorroMapper.toResponse(adocao.getCachorro()));
        }
        if (adocao.getGato() != null) {
            response.setGato(gatoMapper.toResponse(adocao.getGato()));
        }
        if (adocao.getEtapas() != null && !adocao.getEtapas().isEmpty()) {
            response.setEtapas(etapaProcessoMapper.toResponseList(adocao.getEtapas()));
        }

        return response;
    }

    public List<AdocaoResponse> toResponseList(List<Adocao> adocoes) {
        return adocoes.stream().map(this::toResponse).collect(Collectors.toList());
    }
}
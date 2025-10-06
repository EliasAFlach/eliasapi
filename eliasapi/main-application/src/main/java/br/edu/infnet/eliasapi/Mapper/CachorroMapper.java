package br.edu.infnet.eliasapi.Mapper;

import br.edu.infnet.eliasapi.Dto.CachorroRequest;
import br.edu.infnet.eliasapi.Dto.CachorroResponse;
import br.edu.infnet.eliasapi.Model.Cachorro;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CachorroMapper {

    public Cachorro toEntity(CachorroRequest request) {
        Cachorro cachorro = new Cachorro();
        cachorro.setNome(request.getNome());
        cachorro.setIdadeAproximada(request.getIdadeAproximada());
        cachorro.setPorte(request.getPorte());
        cachorro.setSexo(request.getSexo());
        cachorro.setStatus(request.getStatus());
        cachorro.setHistoria(request.getHistoria());
        cachorro.setTemperamento(request.getTemperamento());
        cachorro.setCastrado(request.isCastrado());
        cachorro.setVacinado(request.isVacinado());
        cachorro.setFotosUrl(request.getFotosUrl());
        cachorro.setRaca(request.getRaca());
        cachorro.setNivelEnergia(request.getNivelEnergia());
        cachorro.setAtivo(true);
        return cachorro;
    }

    public CachorroResponse toResponse(Cachorro cachorro) {
        CachorroResponse response = new CachorroResponse();
        response.setId(cachorro.getId());
        response.setNome(cachorro.getNome());
        response.setIdadeAproximada(cachorro.getIdadeAproximada());
        response.setPorte(cachorro.getPorte());
        response.setSexo(cachorro.getSexo());
        response.setStatus(cachorro.getStatus());
        response.setHistoria(cachorro.getHistoria());
        response.setTemperamento(cachorro.getTemperamento());
        response.setCastrado(cachorro.isCastrado());
        response.setVacinado(cachorro.isVacinado());
        response.setFotosUrl(cachorro.getFotosUrl());
        response.setAtivo(cachorro.isAtivo());
        response.setRaca(cachorro.getRaca());
        response.setNivelEnergia(cachorro.getNivelEnergia());
        return response;
    }

    public List<CachorroResponse> toResponseList(List<Cachorro> cachorros) {
        return cachorros.stream().map(this::toResponse).collect(Collectors.toList());
    }
}
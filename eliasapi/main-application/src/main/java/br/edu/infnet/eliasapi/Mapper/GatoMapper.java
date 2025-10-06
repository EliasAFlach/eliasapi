package br.edu.infnet.eliasapi.Mapper;

import br.edu.infnet.eliasapi.Dto.GatoRequest;
import br.edu.infnet.eliasapi.Dto.GatoResponse;
import br.edu.infnet.eliasapi.Model.Gato;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class GatoMapper {

    public Gato toEntity(GatoRequest request) {
        Gato gato = new Gato();
        gato.setNome(request.getNome());
        gato.setIdadeAproximada(request.getIdadeAproximada());
        gato.setPorte(request.getPorte());
        gato.setSexo(request.getSexo());
        gato.setStatus(request.getStatus());
        gato.setHistoria(request.getHistoria());
        gato.setTemperamento(request.getTemperamento());
        gato.setCastrado(request.isCastrado());
        gato.setVacinado(request.isVacinado());
        gato.setFotosUrl(request.getFotosUrl());
        gato.setTesteFivFelv(request.getTesteFivFelv());
        gato.setAtivo(true);
        return gato;
    }

    public GatoResponse toResponse(Gato gato) {
        GatoResponse response = new GatoResponse();
        response.setId(gato.getId());
        response.setNome(gato.getNome());
        response.setIdadeAproximada(gato.getIdadeAproximada());
        response.setPorte(gato.getPorte());
        response.setSexo(gato.getSexo());
        response.setStatus(gato.getStatus());
        response.setHistoria(gato.getHistoria());
        response.setTemperamento(gato.getTemperamento());
        response.setCastrado(gato.isCastrado());
        response.setVacinado(gato.isVacinado());
        response.setFotosUrl(gato.getFotosUrl());
        response.setAtivo(gato.isAtivo());
        response.setTesteFivFelv(gato.getTesteFivFelv());
        return response;
    }

    public List<GatoResponse> toResponseList(List<Gato> gatos) {
        return gatos.stream().map(this::toResponse).collect(Collectors.toList());
    }
}
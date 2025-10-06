package br.edu.infnet.eliasapi.Dto;

import br.edu.infnet.eliasapi.Enum.NivelEnergiaEnum;
import br.edu.infnet.eliasapi.Enum.PorteEnum;
import br.edu.infnet.eliasapi.Enum.SexoAnimalEnum;
import br.edu.infnet.eliasapi.Enum.StatusAdocaoEnum;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CachorroResponse {

    private Integer id;
    private String nome;
    private int idadeAproximada;
    private PorteEnum porte;
    private SexoAnimalEnum sexo;
    private StatusAdocaoEnum status;
    private String historia;
    private String temperamento;
    private boolean castrado;
    private boolean vacinado;
    private List<String> fotosUrl;
    private boolean ativo;
    private String raca;
    private NivelEnergiaEnum nivelEnergia;
}
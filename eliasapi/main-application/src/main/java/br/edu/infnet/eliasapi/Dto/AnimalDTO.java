package br.edu.infnet.eliasapi.Dto;

import br.edu.infnet.eliasapi.Enum.PorteEnum;
import br.edu.infnet.eliasapi.Enum.SexoAnimalEnum;
import br.edu.infnet.eliasapi.Enum.TipoAnimalEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnimalDTO {
    private Integer id;
    private TipoAnimalEnum especie;
    private PorteEnum porte;
    private SexoAnimalEnum sexo;
    private int idadeAproximada;
}
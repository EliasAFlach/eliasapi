package br.edu.infnet.eliasinteresseapi.Dto;

import br.edu.infnet.eliasinteresseapi.Enum.TipoAnimalEnum;
import br.edu.infnet.eliasinteresseapi.Enum.PorteEnum;
import br.edu.infnet.eliasinteresseapi.Enum.SexoAnimalEnum;
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
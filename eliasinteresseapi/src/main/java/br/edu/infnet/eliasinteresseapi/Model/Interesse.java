package br.edu.infnet.eliasinteresseapi.Model;

import br.edu.infnet.eliasinteresseapi.Enum.TipoAnimalEnum;
import br.edu.infnet.eliasinteresseapi.Enum.PorteEnum;
import br.edu.infnet.eliasinteresseapi.Enum.SexoAnimalEnum;
import br.edu.infnet.eliasinteresseapi.Enum.StatusInteresseEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Interesse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "O interesse deve estar associado ao ID de uma pessoa.")
    private Integer pessoaId;

    @NotNull(message = "O status do interesse é obrigatório.")
    @Enumerated(EnumType.STRING)
    private StatusInteresseEnum status;

    @NotNull(message = "A espécie é obrigatória.")
    @Enumerated(EnumType.STRING)
    private TipoAnimalEnum especie;

    @Enumerated(EnumType.STRING)
    private PorteEnum porte;

    @Enumerated(EnumType.STRING)
    private SexoAnimalEnum sexo;

    @Min(value = 0, message = "A idade máxima não pode ser negativa.")
    private Integer idadeMaximaEmMeses;
}
package br.edu.infnet.eliasapi.Dto;

import br.edu.infnet.eliasapi.Enum.NivelEnergiaEnum;
import br.edu.infnet.eliasapi.Enum.PorteEnum;
import br.edu.infnet.eliasapi.Enum.SexoAnimalEnum;
import br.edu.infnet.eliasapi.Enum.StatusAdocaoEnum;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;

import java.util.List;

@Getter
@Setter
public class CachorroRequest {

    @NotBlank(message = "O nome do animal é obrigatório.")
    @Size(min = 2, max = 50, message = "O nome deve ter entre 2 e 50 caracteres.")
    private String nome;

    @Min(value = 0, message = "A idade aproximada não pode ser negativa.")
    private int idadeAproximada;

    @NotNull(message = "O porte é obrigatório.")
    private PorteEnum porte;

    @NotNull(message = "O sexo é obrigatório.")
    private SexoAnimalEnum sexo;

    @NotNull(message = "O status da adoção é obrigatório.")
    private StatusAdocaoEnum status;

    @NotBlank(message = "A história do animal é obrigatória.")
    @Size(max = 2000, message = "A história não pode exceder 2000 caracteres.")
    private String historia;

    @NotBlank(message = "O temperamento é obrigatório.")
    private String temperamento;

    private boolean castrado;
    private boolean vacinado;

    @NotEmpty(message = "A lista de fotos não pode ser vazia.")
    private List<@URL(message = "A URL da foto fornecida é inválida.") String> fotosUrl;

    @NotBlank(message = "A raça é obrigatória (pode ser 'SRD' para sem raça definida).")
    @Size(max = 50, message = "A raça não pode exceder 50 caracteres.")
    private String raca;

    @NotNull(message = "O nível de energia é obrigatório.")
    private NivelEnergiaEnum nivelEnergia;
}
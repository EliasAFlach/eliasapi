package br.edu.infnet.eliasapi.Model;

import br.edu.infnet.eliasapi.Enum.PorteEnum;
import br.edu.infnet.eliasapi.Enum.SexoAnimalEnum;
import br.edu.infnet.eliasapi.Enum.StatusAdocaoEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.validator.constraints.URL;

import java.util.List;

@Data
@ToString
@MappedSuperclass
public abstract class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "O nome do animal é obrigatório.")
    @Size(min = 2, max = 50, message = "O nome deve ter entre 2 e 50 caracteres.")
    private String nome;

    @Min(value = 0, message = "A idade aproximada não pode ser negativa.")
    private int idadeAproximada;

    @NotNull(message = "O porte é obrigatório.")
    @Enumerated(EnumType.STRING)
    private PorteEnum porte;

    @NotNull(message = "O sexo é obrigatório.")
    @Enumerated(EnumType.STRING)
    private SexoAnimalEnum sexo;

    @NotNull(message = "O status da adoção é obrigatório.")
    @Enumerated(EnumType.STRING)
    private StatusAdocaoEnum status;

    @NotBlank(message = "A história do animal é obrigatória.")
    @Size(max = 2000, message = "A história não pode exceder 2000 caracteres.")
    private String historia;

    @NotBlank(message = "O temperamento é obrigatório.")
    private String temperamento;

    private boolean castrado;
    private boolean vacinado;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "animal_fotos", joinColumns = @JoinColumn(name = "animal_id"))
    @Column(name = "foto_url")
    private List<@URL(message = "A URL da foto fornecida é inválida.") String> fotosUrl;

    private boolean ativo;
}
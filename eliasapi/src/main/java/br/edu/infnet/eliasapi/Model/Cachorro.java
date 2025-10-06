package br.edu.infnet.eliasapi.Model;

import br.edu.infnet.eliasapi.Enum.NivelEnergiaEnum;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Entity
public class Cachorro extends Animal {

    @NotBlank(message = "A raça é obrigatória (pode ser 'SRD' para sem raça definida).")
    @Size(max = 50, message = "A raça não pode exceder 50 caracteres.")
    private String raca;

    @NotNull(message = "O nível de energia é obrigatório.")
    @Enumerated(EnumType.STRING)
    private NivelEnergiaEnum nivelEnergia;
}
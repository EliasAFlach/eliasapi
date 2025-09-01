package br.edu.infnet.eliasapi.Model;

import br.edu.infnet.eliasapi.Enum.TesteFivFelvEnum;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Entity
public class Gato extends Animal {

    @NotNull(message = "O resultado do teste de FIV/FeLV é obrigatório.")
    @Enumerated(EnumType.STRING)
    private TesteFivFelvEnum testeFivFelv;
}
package br.edu.infnet.eliasapi.Model;

import br.edu.infnet.eliasapi.Enum.NivelEnergiaEnum;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(callSuper = true)
public class Cachorro extends Animal {

    private String raca;
    private NivelEnergiaEnum nivelEnergia;

}
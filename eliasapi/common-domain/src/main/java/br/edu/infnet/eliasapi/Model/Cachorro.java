package br.edu.infnet.eliasapi.Model;

import br.edu.infnet.eliasapi.Enum.NivelEnergiaEnum;
import jakarta.persistence.Entity;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Entity
public class Cachorro extends Animal {

    private String raca;

    private NivelEnergiaEnum nivelEnergia;
}
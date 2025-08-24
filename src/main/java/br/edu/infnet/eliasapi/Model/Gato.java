package br.edu.infnet.eliasapi.Model;

import br.edu.infnet.eliasapi.Enum.TesteFivFelvEnum;
import jakarta.persistence.Entity;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(callSuper = true)
@Entity
public class Gato extends Animal {

    private TesteFivFelvEnum testeFivFelv;
}
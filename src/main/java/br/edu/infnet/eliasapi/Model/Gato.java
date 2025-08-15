package br.edu.infnet.eliasapi.Model;

import br.edu.infnet.eliasapi.Enum.TesteFivFelvEnum;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(callSuper = true)
public class Gato extends Animal {

    private TesteFivFelvEnum testeFivFelv;
}
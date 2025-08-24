package br.edu.infnet.eliasapi.Model;

import br.edu.infnet.eliasapi.Enum.PorteEnum;
import br.edu.infnet.eliasapi.Enum.SexoAnimalEnum;
import br.edu.infnet.eliasapi.Enum.StatusAdocaoEnum;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.*;

import java.util.List;

@Data
@ToString
@MappedSuperclass
public abstract class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nome;
    private int idadeAproximada;
    private PorteEnum porte;
    private SexoAnimalEnum sexo;
    private StatusAdocaoEnum status;
    private String historia;
    private String temperamento;
    private boolean castrado;
    private boolean vacinado;
    private List<String> fotosUrl;
    private boolean ativo;

}

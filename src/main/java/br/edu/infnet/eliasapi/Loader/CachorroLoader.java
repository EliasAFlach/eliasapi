package br.edu.infnet.eliasapi.Loader;

import br.edu.infnet.eliasapi.Enum.NivelEnergiaEnum;
import br.edu.infnet.eliasapi.Enum.PorteEnum;
import br.edu.infnet.eliasapi.Enum.SexoAnimalEnum;
import br.edu.infnet.eliasapi.Enum.StatusAdocaoEnum;
import br.edu.infnet.eliasapi.Model.Cachorro;
import br.edu.infnet.eliasapi.Service.CachorroService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;

@Component
@Order(2)
public class CachorroLoader implements ApplicationRunner {

    private final CachorroService cachorroService;

    public CachorroLoader(CachorroService cachorroService) {
        this.cachorroService = cachorroService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        FileReader arquivo = new FileReader("cachorro.txt");
        BufferedReader leitura = new BufferedReader(arquivo);
        String linha = leitura.readLine();

        String[] campos = null;

        while (linha != null){
            campos = linha.split(";");

            Cachorro cachorro = new Cachorro();
            cachorro.setNome(campos[0]);
            cachorro.setIdadeAproximada(Integer.parseInt(campos[1]));
            cachorro.setPorte(PorteEnum.valueOf(campos[2]));
            cachorro.setSexo(SexoAnimalEnum.valueOf(campos[3]));
            cachorro.setStatus(StatusAdocaoEnum.valueOf(campos[4]));
            cachorro.setHistoria(campos[5]);
            cachorro.setTemperamento(campos[6]);
            cachorro.setCastrado(Boolean.parseBoolean(campos[7]));
            cachorro.setVacinado(Boolean.parseBoolean(campos[8]));
            cachorro.setRaca(campos[9]);
            cachorro.setNivelEnergia(NivelEnergiaEnum.valueOf(campos[10]));
            cachorro.setAtivo(true);

            cachorroService.inserir(cachorro);

            linha = leitura.readLine();
        }

        leitura.close();
    }
}
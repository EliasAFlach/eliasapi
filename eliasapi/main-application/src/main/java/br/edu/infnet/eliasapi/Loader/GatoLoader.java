package br.edu.infnet.eliasapi.Loader;

import br.edu.infnet.eliasapi.Enum.PorteEnum;
import br.edu.infnet.eliasapi.Enum.SexoAnimalEnum;
import br.edu.infnet.eliasapi.Enum.StatusAdocaoEnum;
import br.edu.infnet.eliasapi.Enum.TesteFivFelvEnum;
import br.edu.infnet.eliasapi.Model.Gato;
import br.edu.infnet.eliasapi.Service.GatoService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;

@Component
@Order(1)
public class GatoLoader implements ApplicationRunner {

    private final GatoService gatoService;

    public GatoLoader(GatoService gatoService) {
        this.gatoService = gatoService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        FileReader arquivo = new FileReader("gato.txt");
        BufferedReader leitura = new BufferedReader(arquivo);
        String linha = leitura.readLine();

        String[] campos = null;

        while (linha != null){
            campos = linha.split(";");

            Gato gato = new Gato();
            gato.setNome(campos[0]);
            gato.setIdadeAproximada(Integer.parseInt(campos[1]));
            gato.setPorte(PorteEnum.valueOf(campos[2]));
            gato.setSexo(SexoAnimalEnum.valueOf(campos[3]));
            gato.setStatus(StatusAdocaoEnum.valueOf(campos[4]));
            gato.setHistoria(campos[5]);
            gato.setTemperamento(campos[6]);
            gato.setCastrado(Boolean.parseBoolean(campos[7]));
            gato.setVacinado(Boolean.parseBoolean(campos[8]));
            gato.setTesteFivFelv(TesteFivFelvEnum.valueOf(campos[9]));
            gato.setAtivo(true);

            gatoService.inserir(gato);

            linha = leitura.readLine();
        }

        leitura.close();
    }
}
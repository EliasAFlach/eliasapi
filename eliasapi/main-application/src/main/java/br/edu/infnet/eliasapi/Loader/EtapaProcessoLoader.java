package br.edu.infnet.eliasapi.Loader;

import br.edu.infnet.eliasapi.Model.EtapaProcesso;
import br.edu.infnet.eliasapi.Service.AdocaoService;
import br.edu.infnet.eliasapi.Service.EtapaProcessoService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDate;

@Component
@Order(6)
public class EtapaProcessoLoader implements ApplicationRunner {

    private final EtapaProcessoService etapaProcessoService;
    private final AdocaoService adocaoService;

    public EtapaProcessoLoader(EtapaProcessoService etapaProcessoService, AdocaoService adocaoService) {
        this.etapaProcessoService = etapaProcessoService;
        this.adocaoService = adocaoService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        FileReader arquivo = new FileReader("etapaProcesso.txt");
        BufferedReader leitura = new BufferedReader(arquivo);
        String linha = leitura.readLine();

        String[] campos = null;

        while (linha != null){
            campos = linha.split(";");

            EtapaProcesso etapa = new EtapaProcesso();
            etapa.setDescricao(campos[0]);
            etapa.setDataEtapa(LocalDate.parse(campos[1]));
            etapa.setObservacoes(campos[2]);
            etapa.setAdocao(adocaoService.buscarPorId(Integer.valueOf(campos[3])));
            etapa.setAtivo(true);

            etapaProcessoService.inserir(etapa);

            linha = leitura.readLine();
        }

        leitura.close();
    }
}
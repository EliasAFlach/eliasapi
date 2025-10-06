package br.edu.infnet.eliasapi.Loader;

import br.edu.infnet.eliasapi.Enum.StatusProcessoEnum;
import br.edu.infnet.eliasapi.Model.Adocao;
import br.edu.infnet.eliasapi.Service.AdocaoService;
import br.edu.infnet.eliasapi.Service.CachorroService;
import br.edu.infnet.eliasapi.Service.PessoaService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDate;

@Component
@Order(5)
public class AdocaoLoader implements ApplicationRunner {

    private final AdocaoService adocaoService;
    private final PessoaService pessoaService;
    private final CachorroService cachorroService;

    public AdocaoLoader(AdocaoService adocaoService, PessoaService pessoaService, CachorroService cachorroService) {
        this.adocaoService = adocaoService;
        this.pessoaService = pessoaService;
        this.cachorroService = cachorroService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        FileReader arquivo = new FileReader("adocao.txt");
        BufferedReader leitura = new BufferedReader(arquivo);
        String linha = leitura.readLine();

        String[] campos = null;

        while (linha != null){
            campos = linha.split(";");

            Adocao adocao = new Adocao();
            adocao.setAdotante(pessoaService.buscarPorId(Integer.parseInt(campos[0])));
            adocao.setCachorro(cachorroService.buscarPorId(Integer.parseInt(campos[1])));
            adocao.setDataSolicitacao(LocalDate.parse(campos[2]));
            adocao.setDataAprovacao(LocalDate.parse(campos[3]));
            adocao.setStatus(StatusProcessoEnum.valueOf(campos[4]));
            adocao.setObservacoes(campos[5]);
            adocao.setAtivo(true);

            adocaoService.inserir(adocao);

            linha = leitura.readLine();
        }

        leitura.close();
    }
}
package br.edu.infnet.eliasapi.Loader;

import br.edu.infnet.eliasapi.Model.Pessoa;
import br.edu.infnet.eliasapi.Service.PessoaService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDate;

@Component
@Order(3)
public class PessoaLoader implements ApplicationRunner {

    private final PessoaService pessoaService;

    public PessoaLoader(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        FileReader arquivo = new FileReader("pessoa.txt");
        BufferedReader leitura = new BufferedReader(arquivo);
        String linha = leitura.readLine();

        String[] campos = null;

        while (linha != null){
            campos = linha.split(";");

            Pessoa pessoa = new Pessoa();
            pessoa.setCpf(campos[0]);
            pessoa.setNome(campos[1]);
            pessoa.setDataNascimento(LocalDate.parse(campos[2]));
            pessoa.setEndereco(campos[3]);
            pessoa.setTelefone(campos[4]);
            pessoa.setAtivo(true);

            pessoaService.inserir(pessoa);

            linha = leitura.readLine();
        }

        leitura.close();
    }
}
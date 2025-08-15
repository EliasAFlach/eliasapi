package br.edu.infnet.eliasapi.Loader;

import br.edu.infnet.eliasapi.Enum.CargoEnum;
import br.edu.infnet.eliasapi.Model.Funcionario;
import br.edu.infnet.eliasapi.Service.FuncionarioService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDate;

@Component
@Order(4)
public class FuncionarioLoader implements ApplicationRunner {

    private final FuncionarioService funcionarioService;

    public FuncionarioLoader(FuncionarioService funcionarioService) {
        this.funcionarioService = funcionarioService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        FileReader arquivo = new FileReader("funcionario.txt");
        BufferedReader leitura = new BufferedReader(arquivo);
        String linha = leitura.readLine();

        String[] campos = null;

        while (linha != null){
            campos = linha.split(";");

            Funcionario funcionario = new Funcionario();
            funcionario.setCpf(campos[0]);
            funcionario.setNomeCompleto(campos[1]);
            funcionario.setEmail(campos[2]);
            funcionario.setSenha(campos[3]);
            funcionario.setMatricula(campos[4]);
            funcionario.setCargo(CargoEnum.valueOf(campos[5]));
            funcionario.setDataAdmissao(LocalDate.parse(campos[6]));
            funcionario.setAtivo(true);

            funcionarioService.inserir(funcionario);

            linha = leitura.readLine();
        }

        leitura.close();
    }
}
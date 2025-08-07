package br.edu.infnet.eliasapi;

import br.edu.infnet.eliasapi.Enum.Porte;
import br.edu.infnet.eliasapi.Model.Animal;
import br.edu.infnet.eliasapi.Model.Pessoa;
import br.edu.infnet.eliasapi.Service.AnimalService;
import br.edu.infnet.eliasapi.Service.PessoaService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDate;

@Component
public class ApplicationLoader implements ApplicationRunner {

    private final PessoaService pessoaService;
    private final AnimalService animalService;

    public ApplicationLoader(PessoaService pessoaService, AnimalService animalService) {
        this.pessoaService = pessoaService;
        this.animalService = animalService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        FileReader arquivoPessoas = new FileReader("pessoa.txt");
        BufferedReader leituraPessoas = new BufferedReader(arquivoPessoas);
        String linhaPessoas = leituraPessoas.readLine();

        FileReader arquivoAnimais = new FileReader("animal.txt");
        BufferedReader leituraAnimais = new BufferedReader(arquivoAnimais);
        String linhaAnimais = leituraAnimais.readLine();

        String[] campos = null;

        while (linhaPessoas != null){
            campos = linhaPessoas.split(";");

            Pessoa pessoa = new Pessoa();
            pessoa.setCpf(campos[0]);
            pessoa.setNome(campos[1]);
            pessoa.setDataNascimento(LocalDate.parse(campos[2]));
            pessoa.setEndereco(campos[3]);
            pessoaService.inserir(pessoa);

            linhaPessoas = leituraPessoas.readLine();
        }

        leituraPessoas.close();

        while(linhaAnimais != null) {

            campos = linhaAnimais.split(";");

            Animal animal = new Animal();
            animal.setNome(campos[0]);
            animal.setRaca(campos[1]);
            animal.setDataNascimento(LocalDate.parse(campos[2]));
            animal.setPorte(Porte.valueOf(campos[3]));
            animal.setAdotado(Boolean.parseBoolean(campos[4]));

            Pessoa pessoa = pessoaService.buscarPorId(Integer.valueOf(campos[5]));
            animal.setDono(pessoa);

            animalService.inserir(animal);

            System.out.println(animal);

            linhaAnimais = leituraAnimais.readLine();
        }

        System.out.println(animalService.buscarTodos());

        leituraAnimais.close();
    }
}

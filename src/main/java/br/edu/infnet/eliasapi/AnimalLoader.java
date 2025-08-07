package br.edu.infnet.eliasapi;

import br.edu.infnet.eliasapi.Enum.Porte;
import br.edu.infnet.eliasapi.Model.Animal;
import br.edu.infnet.eliasapi.Model.Pessoa;
import br.edu.infnet.eliasapi.Service.AnimalService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDate;

@Component
public class AnimalLoader implements ApplicationRunner {

    private final AnimalService animalService;

    public AnimalLoader(AnimalService animalService) {
        this.animalService = animalService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        FileReader arquivo = new FileReader("animal.txt");
        BufferedReader leitura = new BufferedReader(arquivo);

        String linha = leitura.readLine();

        String[] campos = null;

        while(linha != null) {

            campos = linha.split(";");

            Pessoa pessoa = new Pessoa();
            pessoa.setCpf(campos[5]);
            pessoa.setNome(campos[6]);
            pessoa.setEndereco(campos[8]);
            pessoa.setDataNascimento(LocalDate.parse(campos[7]));

            Animal animal = new Animal();
            animal.setNome(campos[0]);
            animal.setRaca(campos[1]);
            animal.setDataNascimento(LocalDate.parse(campos[2]));
            animal.setPorte(Porte.valueOf(campos[3]));
            animal.setAdotado(Boolean.parseBoolean(campos[4]));
            animal.setDono(pessoa);

            animalService.inserir(animal);

            System.out.println(animal);

            linha = leitura.readLine();
        }

        System.out.println(animalService.buscarTodos());

        leitura.close();
    }
}

package br.edu.infnet.eliasapi.Client;

import br.edu.infnet.eliasapi.Dto.EnderecoViaCepDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "interesse-service", url = "http://localhost:8081/api/v1")
public interface InteresseServiceClient {

    @GetMapping("/enderecos/{cep}")
    EnderecoViaCepDTO buscarEnderecoPorCep(@PathVariable("cep") String cep);
}

package br.edu.infnet.eliasinteresseapi.Controller;

import br.edu.infnet.eliasinteresseapi.Dto.EnderecoViaCepDTO;
import br.edu.infnet.eliasinteresseapi.Service.EnderecoService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/enderecos")
@AllArgsConstructor
public class EnderecoController {

    private final EnderecoService enderecoService;

    @GetMapping("/{cep}")
    public ResponseEntity<EnderecoViaCepDTO> getEnderecoPorCep(@PathVariable String cep) {
        EnderecoViaCepDTO endereco = enderecoService.consultarCep(cep);
        return ResponseEntity.ok(endereco);
    }
}
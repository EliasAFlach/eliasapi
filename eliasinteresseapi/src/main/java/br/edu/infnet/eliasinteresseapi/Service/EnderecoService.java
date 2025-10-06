package br.edu.infnet.eliasinteresseapi.Service;

import br.edu.infnet.eliasinteresseapi.Client.ViaCepClient;
import br.edu.infnet.eliasinteresseapi.Dto.EnderecoViaCepDTO;
import feign.FeignException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EnderecoService {

    private final ViaCepClient viaCepClient;

    public EnderecoViaCepDTO consultarCep(String cep) {
        try {
            return viaCepClient.buscarEnderecoPorCep(cep);
        } catch (FeignException e) {
            throw new IllegalArgumentException("CEP inválido ou não encontrado.");
        }
    }
}
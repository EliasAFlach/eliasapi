package br.edu.infnet.eliasapi.Service;

import br.edu.infnet.eliasapi.Dto.AdocaoRequest;
import br.edu.infnet.eliasapi.Enum.StatusProcessoEnum;
import br.edu.infnet.eliasapi.Expections.AdocaoInvalidaException;
import br.edu.infnet.eliasapi.Model.Adocao;
import br.edu.infnet.eliasapi.Model.Cachorro;
import br.edu.infnet.eliasapi.Model.Gato;
import br.edu.infnet.eliasapi.Model.Pessoa;
import br.edu.infnet.eliasapi.Repository.AdocaoRepository;
import br.edu.infnet.eliasapi.Repository.CachorroRepository;
import br.edu.infnet.eliasapi.Repository.GatoRepository;
import br.edu.infnet.eliasapi.Repository.PessoaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class AdocaoService {

    private final AdocaoRepository adocaoRepository;
    private final PessoaRepository pessoaRepository;
    private final CachorroRepository cachorroRepository;
    private final GatoRepository gatoRepository;

    public Adocao inserir(Adocao adocao) {
        return adocaoRepository.save(adocao);
    }

    public Adocao inserir(AdocaoRequest request) {
        if ((request.getCachorroId() == null && request.getGatoId() == null) ||
                (request.getCachorroId() != null && request.getGatoId() != null)) {
            throw new AdocaoInvalidaException("A adoção deve estar associada a exatamente um animal (cachorro ou gato).");
        }

        Pessoa adotante = pessoaRepository.findById(request.getPessoaId())
                .orElseThrow(() -> new IllegalArgumentException("Adotante não encontrado para o id: " + request.getPessoaId()));

        Adocao adocao = new Adocao();
        adocao.setAdotante(adotante);

        if (request.getCachorroId() != null) {
            Cachorro cachorro = cachorroRepository.findById(request.getCachorroId())
                    .orElseThrow(() -> new IllegalArgumentException("Cachorro não encontrado para o id: " + request.getCachorroId()));
            adocao.setCachorro(cachorro);
        } else {
            Gato gato = gatoRepository.findById(request.getGatoId())
                    .orElseThrow(() -> new IllegalArgumentException("Gato não encontrado para o id: " + request.getGatoId()));
            adocao.setGato(gato);
        }

        adocao.setDataSolicitacao(request.getDataSolicitacao());
        adocao.setDataAprovacao(request.getDataAprovacao());
        adocao.setStatus(request.getStatus());
        adocao.setObservacoes(request.getObservacoes());
        adocao.setAtivo(true);

        return adocaoRepository.save(adocao);
    }

    public Adocao atualizar(Integer id, AdocaoRequest request) {
        Adocao adocaoExistente = buscarPorId(id);

        Pessoa adotante = pessoaRepository.findById(request.getPessoaId())
                .orElseThrow(() -> new IllegalArgumentException("Adotante não encontrado para o id: " + request.getPessoaId()));

        adocaoExistente.setAdotante(adotante);

        adocaoExistente.setDataSolicitacao(request.getDataSolicitacao());
        adocaoExistente.setDataAprovacao(request.getDataAprovacao());
        adocaoExistente.setStatus(request.getStatus());
        adocaoExistente.setObservacoes(request.getObservacoes());

        return adocaoRepository.save(adocaoExistente);
    }

    public Adocao inativar(Integer id) {
        Adocao adocao = buscarPorId(id);
        adocao.setAtivo(false);
        return adocaoRepository.save(adocao);
    }

    public void deletar(Integer id) {
        if (!adocaoRepository.existsById(id)) {
            throw new IllegalArgumentException("Adoção não encontrada para o id: " + id);
        }
        adocaoRepository.deleteById(id);
    }

    public Adocao buscarPorId(Integer id) {
        return adocaoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Adoção não encontrada para o id: " + id));
    }

    public List<Adocao> buscarTodos() {
        return adocaoRepository.findAll();
    }

    public List<Adocao> buscarTodasAdocoesAprovadasAnterioresA(String data) {
        LocalDate dataFiltro = LocalDate.parse(data);
        return adocaoRepository.findByDataAprovacaoBefore(dataFiltro);
    }

    public List<Adocao> buscarTodasAprovacoesPorStatus(StatusProcessoEnum status) {
        return adocaoRepository.findByStatus(status);
    }
}
package br.edu.infnet.eliasapi.Service;

import br.edu.infnet.eliasapi.Enum.StatusProcessoEnum;
import br.edu.infnet.eliasapi.Expections.AdocaoInvalidaException;
import br.edu.infnet.eliasapi.Expections.AnimalInvalidoException;
import br.edu.infnet.eliasapi.Model.Adocao;
import br.edu.infnet.eliasapi.Model.Cachorro;
import br.edu.infnet.eliasapi.Repository.AdocaoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class AdocaoService implements CrudService<Adocao, Integer> {

    private final AdocaoRepository adocaoRepository;

    @Override
    public Adocao inserir(Adocao adocao) {
        return adocaoRepository.save(adocao);
    }

    @Override
    public void deletar(Integer id) {
        adocaoRepository.deleteById(id);
    }

    @Override
    public Adocao atualizar(Integer id, Adocao adocaoAtualizada) {
        adocaoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Adoção não encontrada para o id: " + id));
        adocaoAtualizada.setId(id);
        return adocaoRepository.save(adocaoAtualizada);
    }

    @Override
    public Adocao inativar(Integer id) {
        Adocao adocao = buscarPorId(id);
        adocao.setAtivo(false);
        return adocaoRepository.save(adocao);
    }

    @Override
    public Adocao buscarPorId(Integer id) {
        return adocaoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Adoção não encontrada para o id: " + id));
    }

    @Override
    public List<Adocao> buscarTodos() {
        return adocaoRepository.findAll();
    }

    public List<Adocao> buscarTodasAdocoesAprovadasAnterioresA(String dataAprovacao) {
        return adocaoRepository.findByDataAprovacaoBefore(LocalDate.parse(dataAprovacao))
                .orElseThrow(() -> new IllegalArgumentException("Nenhuma adoção anterior a data informada: " + dataAprovacao));
    }

    public List<Adocao> buscarTodasAprovacoesPorStatus(StatusProcessoEnum statusProcessoEnum) {
        return adocaoRepository.findByStatus(statusProcessoEnum)
                .orElseThrow(() -> new IllegalArgumentException("Nenhuma adoção encontrada para o status: " + statusProcessoEnum));
    }
}
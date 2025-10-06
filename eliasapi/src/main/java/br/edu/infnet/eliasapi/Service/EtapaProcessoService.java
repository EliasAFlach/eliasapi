package br.edu.infnet.eliasapi.Service;

import br.edu.infnet.eliasapi.Model.EtapaProcesso;
import br.edu.infnet.eliasapi.Repository.AdocaoRepository;
import br.edu.infnet.eliasapi.Repository.EtapaProcessoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class EtapaProcessoService implements CrudService<EtapaProcesso, Integer> {

    EtapaProcessoRepository etapaProcessoRepository;
    AdocaoRepository adocaoRepository;

    @Override
    public EtapaProcesso inserir(EtapaProcesso etapaProcesso) {
        etapaProcesso.setAtivo(true);
        return etapaProcessoRepository.save(etapaProcesso);
    }

    @Override
    public void deletar(Integer id) {
        etapaProcessoRepository.deleteById(id);
    }

    @Override
    public EtapaProcesso atualizar(Integer id, EtapaProcesso etapaProcessoAtualizada) {
        etapaProcessoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Etapa Processo não encontrada para o id: " + id));
        etapaProcessoAtualizada.setId(id);
        return etapaProcessoRepository.save(etapaProcessoAtualizada);
    }

    @Override
    public EtapaProcesso inativar(Integer id) {
        EtapaProcesso etapaProcesso = buscarPorId(id);
        etapaProcesso.setAtivo(false);
        return etapaProcessoRepository.save(etapaProcesso);
    }

    @Override
    public EtapaProcesso buscarPorId(Integer id) {
        return etapaProcessoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Etapa Processo não encontrada para o id: " + id));
    }

    @Override
    public List<EtapaProcesso> buscarTodos() {
        return etapaProcessoRepository.findAll();
    }

    public List<EtapaProcesso> buscarTodasPorAdocaoId(Integer adocaoId) {
        return etapaProcessoRepository.findByAdocaoId(adocaoId)
                .orElseThrow(() -> new IllegalArgumentException("Etapas não encontradas para a adocao de id: " + adocaoId));
    }

    public List<EtapaProcesso> buscarTodasPorDataEtapa(String dataEtapa) {
        return etapaProcessoRepository.findByDataEtapa(LocalDate.parse(dataEtapa))
                .orElseThrow(() -> new IllegalArgumentException("Etapas não encontradas para a data: " + dataEtapa));
    }
}

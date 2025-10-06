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

    private final EtapaProcessoRepository etapaProcessoRepository;
    private final AdocaoRepository adocaoRepository;

    private void validar(EtapaProcesso etapa) {
        if (etapa == null) {
            throw new IllegalArgumentException("A etapa do processo não pode ser nula.");
        }
        if (etapa.getDescricao() == null || etapa.getDescricao().trim().isEmpty()) {
            throw new IllegalArgumentException("A descrição da etapa é uma informação obrigatória.");
        }
    }

    @Override
    public EtapaProcesso inserir(EtapaProcesso etapaProcesso) {
        validar(etapaProcesso);
        etapaProcesso.setAtivo(true);
        return etapaProcessoRepository.save(etapaProcesso);
    }

    @Override
    public EtapaProcesso atualizar(Integer id, EtapaProcesso etapaProcessoAtualizada) {
        validar(etapaProcessoAtualizada);
        if (!etapaProcessoRepository.existsById(id)) {
            throw new IllegalArgumentException("Etapa Processo não encontrada para o id: " + id);
        }
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
    public void deletar(Integer id) {
        if (!etapaProcessoRepository.existsById(id)) {
            throw new IllegalArgumentException("Etapa Processo não encontrada para o id: " + id);
        }
        etapaProcessoRepository.deleteById(id);
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
        if (!adocaoRepository.existsById(adocaoId)) {
            throw new IllegalArgumentException("Adoção não encontrada para o id: " + adocaoId);
        }
        return etapaProcessoRepository.findByAdocaoId(adocaoId);
    }

    public List<EtapaProcesso> buscarTodasPorDataEtapa(String dataEtapa) {
        LocalDate data = LocalDate.parse(dataEtapa);
        return etapaProcessoRepository.findByDataEtapa(data);
    }
}
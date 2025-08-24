package br.edu.infnet.eliasapi.Service;

import br.edu.infnet.eliasapi.Expections.AdocaoInvalidaException;
import br.edu.infnet.eliasapi.Expections.AnimalInvalidoException;
import br.edu.infnet.eliasapi.Model.Adocao;
import br.edu.infnet.eliasapi.Model.Cachorro;
import br.edu.infnet.eliasapi.Repository.AdocaoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class AdocaoService implements CrudService<Adocao, Integer> {

    private final AdocaoRepository adocaoRepository;

    private void validar(Adocao adocao) {
        if (Objects.isNull(adocao.getAdotante())) {
            throw new AdocaoInvalidaException("O adotante não pode ser vazio");
        }
        if (Objects.isNull(adocao.getCachorro()) && Objects.isNull(adocao.getGato())) {
            throw new AdocaoInvalidaException("A adoção deve estar associada a um cachorro ou a um gato.");
        }
        if (Objects.nonNull(adocao.getCachorro()) && Objects.nonNull(adocao.getGato())) {
            throw new AdocaoInvalidaException("A adoção não pode conter um cachorro e um gato simultaneamente.");
        }
    }

    @Override
    public Adocao inserir(Adocao adocao) {
        validar(adocao);
        return adocaoRepository.save(adocao);
    }

    @Override
    public void deletar(Integer id) {
        adocaoRepository.deleteById(id);
    }

    @Override
    public Adocao atualizar(Adocao adocao) {
        validar(adocao);
        return adocaoRepository.save(adocao);
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
}
package br.edu.infnet.eliasapi.Repository;

import br.edu.infnet.eliasapi.Enum.StatusProcessoEnum;
import br.edu.infnet.eliasapi.Model.Adocao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AdocaoRepository extends JpaRepository<Adocao, Integer> {

    List<Adocao> findByDataAprovacaoBefore(LocalDate dataAprovacao);
    List<Adocao> findByStatus(StatusProcessoEnum status);
    boolean existsByCachorroId(Integer cachorroId);
    boolean existsByGatoId(Integer gatoId);

}
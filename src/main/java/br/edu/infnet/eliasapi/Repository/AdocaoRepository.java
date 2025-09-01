package br.edu.infnet.eliasapi.Repository;

import br.edu.infnet.eliasapi.Enum.StatusProcessoEnum;
import br.edu.infnet.eliasapi.Model.Adocao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface AdocaoRepository extends JpaRepository<Adocao, Integer> {

    Optional<List<Adocao>> findByDataAprovacaoBefore(LocalDate dataAprovacao);
    Optional<List<Adocao>> findByStatus(StatusProcessoEnum status);
    boolean existsByCachorroId(Integer cachorroId);
    boolean existsByGatoId(Integer gatoId);

}
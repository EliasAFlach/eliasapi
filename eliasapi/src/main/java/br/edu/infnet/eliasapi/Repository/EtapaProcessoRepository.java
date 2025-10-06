package br.edu.infnet.eliasapi.Repository;

import br.edu.infnet.eliasapi.Model.EtapaProcesso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface EtapaProcessoRepository extends JpaRepository<EtapaProcesso, Integer> {

    Optional<List<EtapaProcesso>> findByAdocaoId(Integer adocaoId);
    Optional<List<EtapaProcesso>> findByDataEtapa(LocalDate dataEtapaProcessos);

}
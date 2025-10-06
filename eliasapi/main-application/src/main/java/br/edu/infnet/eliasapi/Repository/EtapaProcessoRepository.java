package br.edu.infnet.eliasapi.Repository;

import br.edu.infnet.eliasapi.Model.EtapaProcesso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EtapaProcessoRepository extends JpaRepository<EtapaProcesso, Integer> {

    // Retorna uma LISTA de etapas para um ID de adoção.
    List<EtapaProcesso> findByAdocaoId(Integer adocaoId);

    // Retorna uma LISTA de etapas para uma data específica.
    List<EtapaProcesso> findByDataEtapa(LocalDate dataEtapa);

}
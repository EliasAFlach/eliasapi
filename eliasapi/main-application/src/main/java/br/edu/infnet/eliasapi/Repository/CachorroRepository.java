package br.edu.infnet.eliasapi.Repository;

import br.edu.infnet.eliasapi.Model.Cachorro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CachorroRepository extends JpaRepository<Cachorro, Integer> {

    Optional<List<Cachorro>> findByRacaEndingWithIgnoreCase(String raca);
    Optional<List<Cachorro>> findByIdadeAproximada(Integer idadeAprox);

}
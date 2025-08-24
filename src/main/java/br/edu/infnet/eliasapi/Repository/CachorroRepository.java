package br.edu.infnet.eliasapi.Repository;

import br.edu.infnet.eliasapi.Model.Cachorro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CachorroRepository extends JpaRepository<Cachorro, Integer> {
}
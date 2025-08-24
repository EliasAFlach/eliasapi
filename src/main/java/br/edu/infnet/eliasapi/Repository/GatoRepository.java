package br.edu.infnet.eliasapi.Repository;

import br.edu.infnet.eliasapi.Model.Gato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GatoRepository extends JpaRepository<Gato, Integer> {
}
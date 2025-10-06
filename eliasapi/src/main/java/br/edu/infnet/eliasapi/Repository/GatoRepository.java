package br.edu.infnet.eliasapi.Repository;

import br.edu.infnet.eliasapi.Enum.PorteEnum;
import br.edu.infnet.eliasapi.Model.Gato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GatoRepository extends JpaRepository<Gato, Integer> {

    Optional<Gato> findByNomeIgnoreCase(String nome);
    Optional<List<Gato>> findByPorte(PorteEnum porte);
}
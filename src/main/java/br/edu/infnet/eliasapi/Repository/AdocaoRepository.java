package br.edu.infnet.eliasapi.Repository;

import br.edu.infnet.eliasapi.Model.Adocao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdocaoRepository extends JpaRepository<Adocao, Integer> {
}
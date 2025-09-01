package br.edu.infnet.eliasapi.Repository;

import br.edu.infnet.eliasapi.Model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {

    Optional<Pessoa> findByNomeLike(String nome);
    Optional<Pessoa> findByCpf(String cpf);

}
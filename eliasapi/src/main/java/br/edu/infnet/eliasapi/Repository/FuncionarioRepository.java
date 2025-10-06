package br.edu.infnet.eliasapi.Repository;

import br.edu.infnet.eliasapi.Model.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Integer> {

    Optional<Funcionario> findByNomeCompleto(String nome);
    Optional<Funcionario> findByCpf(String cpf);

}
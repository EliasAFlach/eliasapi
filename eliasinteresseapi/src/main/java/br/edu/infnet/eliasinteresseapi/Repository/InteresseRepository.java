package br.edu.infnet.eliasinteresseapi.Repository;

import br.edu.infnet.eliasinteresseapi.Enum.StatusInteresseEnum;
import br.edu.infnet.eliasinteresseapi.Model.Interesse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InteresseRepository extends JpaRepository<Interesse, Integer> {

    List<Interesse> findAllByStatus(StatusInteresseEnum status);
}
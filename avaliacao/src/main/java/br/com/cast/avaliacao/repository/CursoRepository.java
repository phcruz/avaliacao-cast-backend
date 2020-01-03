package br.com.cast.avaliacao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.cast.avaliacao.model.Curso;

/**
 * Interface que prove acesso ao banco de dados.
 * @author paulo
 *
 */
@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {

}

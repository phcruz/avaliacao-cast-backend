package br.com.cast.avaliacao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.cast.avaliacao.model.Categoria;

/**
 * Interface que prove acesso ao banco de dados.
 * @author paulo
 *
 */
@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

}

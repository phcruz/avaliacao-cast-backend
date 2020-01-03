package br.com.cast.avaliacao.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.cast.avaliacao.exception.CustomException;
import br.com.cast.avaliacao.model.Categoria;
import br.com.cast.avaliacao.repository.CategoriaRepository;

/**
 * Classe que contém as validações e verificações de regras de negócio sobre o modelo Categoria.
 * @author paulo
 *
 */
@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	/**
	 * Método responsável por obter uma lista de categorias
	 * @return List<Categoria>
	 */
	public List<Categoria> listarCategorias() {
		return categoriaRepository.findAll();
	}
	
	/**
	 * Método responsável por obter uma categoria de acordo com um id especifico
	 * @param id: Long
	 * @return Categoria
	 */
	public Categoria buscarCategoriaId(Long id) {
		Optional<Categoria> op = categoriaRepository.findById(id);
		if(op.isPresent()) {
			return op.get();
		}
		throw new CustomException("Nenhuma categoria encontrada com o código informado.", HttpStatus.NOT_FOUND);
	}
	
	/**
	 * Método responsável por salvar uma categoria no banco de dados
	 * @param categoria: Categoria
	 * @param isUpdate: boolean
	 * @return Categoria
	 */
	public Categoria salvarCategoria(Categoria categoria) {
		return categoriaRepository.save(categoria);
	}
	
	/**
	 * Método responsável por deletar uma categoria do banco de dados através de seu id
	 * @param id: Long
	 */
	public void deletarCategoria(Long id) {
		buscarCategoriaId(id);
		categoriaRepository.deleteById(id);
	}
}

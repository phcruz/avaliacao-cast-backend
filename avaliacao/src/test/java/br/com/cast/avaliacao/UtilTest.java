package br.com.cast.avaliacao;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.cast.avaliacao.model.Categoria;
import br.com.cast.avaliacao.model.Curso;

public class UtilTest {

	public static String obtemCursoJson() {
		 return converteObjetoEmJson(criarCurso());
	}
	
	private static Curso criarCurso() {
		Curso c = new Curso();
		c.setDescricao("Curso de programação web com Java.");
		c.setDataInicio(converteDate(LocalDate.of(2018, 6, 28)));
		c.setDataFim(converteDate(LocalDate.now()));
		c.setQtdAlunos(200);
		c.setCategoria(new Categoria(1L, "Comportamental"));

		return c;
	}

	private static Date converteDate(LocalDate localDate) {
		return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
	}
	
	private static String converteObjetoEmJson(Object object) {
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			String jsonStr = objectMapper.writeValueAsString(object); 
			return jsonStr;
		} catch (Exception e) {
			return null;
		}
	}
	
	public static String obtemCategoriaJson() {
		 return converteObjetoEmJson(criarCategoria());
	}
	
	private static Categoria criarCategoria() {
		Categoria c = new Categoria();
		c.setDescricao("SAP");
		return c;
	}
}

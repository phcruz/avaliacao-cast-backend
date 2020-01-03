package br.com.cast.avaliacao;

import java.util.List;
import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import br.com.cast.avaliacao.model.Categoria;
import br.com.cast.avaliacao.repository.CategoriaRepository;

@SpringBootApplication
public class AvaliacaoApplication {

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(AvaliacaoApplication.class, args);
	}

	@PostConstruct
	void started() {
	    TimeZone.setDefault(TimeZone.getTimeZone("Etc/UTC"));
	}
	
	@Bean
	public CommandLineRunner commandLineRunner() {
		return args -> {
			List<Categoria> categorias = categoriaRepository.findAll();
			
			if(categorias.isEmpty()) {
				criaCategorias();
			}
		};
	}
	
	private void criaCategorias() {
		categoriaRepository.save(new Categoria(null, "Comportamental"));
		categoriaRepository.save(new Categoria(null, "Programação"));
		categoriaRepository.save(new Categoria(null, "Qualidade"));
		categoriaRepository.save(new Categoria(null, "Processos"));
	}
}

package br.com.cast.avaliacao;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CursoControllerTest {

	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}

	@Test
	public void listarCursos() throws Exception {
		mockMvc.perform(
				MockMvcRequestBuilders.get("/cursos")
				.accept(MediaType.APPLICATION_JSON))
		.andDo(print())
		.andExpect(status().isOk());
	}

	@Test
	public void buscarCursoId() throws Exception {
		mockMvc.perform(
				MockMvcRequestBuilders.get("/cursos/{id}", 1)
				.accept(MediaType.APPLICATION_JSON))
		.andDo(print())
		.andExpect(status().isOk());
	}

	@Test
	public void deletarCurso() throws Exception {
		mockMvc.perform(
				MockMvcRequestBuilders.delete("/cursos/{id}", 1))
		.andDo(print())
		.andExpect(status().isNoContent());
	}

	@Test
	public void inserirCurso() throws Exception {
		String cursoJson = UtilTest.obtemCursoJson();

		mockMvc.perform(
				MockMvcRequestBuilders.post("/cursos")
				.content(cursoJson)
				.contentType(MediaType.APPLICATION_JSON))
		.andDo(print())
		.andExpect(status().isCreated());
	}
}

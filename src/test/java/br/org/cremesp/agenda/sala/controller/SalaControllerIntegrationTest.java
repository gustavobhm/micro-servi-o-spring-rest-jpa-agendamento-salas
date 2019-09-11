package br.org.cremesp.agenda.sala.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.google.gson.Gson;

import br.org.cremesp.agenda.sala.AgendamentoSalasApplication;
import br.org.cremesp.agenda.sala.entity.Sala;
import br.org.cremesp.agenda.sala.repository.SalaRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.MOCK, classes = AgendamentoSalasApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
public class SalaControllerIntegrationTest {

	@Autowired
	private MockMvc mvc;

	@Autowired
	private SalaRepository repository;

	private Gson gson = new Gson();

	@Before
	public void init() {

		Sala sala1 = new Sala(null, "Sala 1", "1º", 10, true);
		repository.saveAndFlush(sala1);

		Sala sala2 = new Sala(null, "Sala 2", "2º", 20, false);
		repository.saveAndFlush(sala2);

	}

	@Test
	public void getSalas_ValidTest() throws Exception {

		mvc.perform(get("/salas") //
				.contentType(MediaType.APPLICATION_JSON)) //
				.andExpect(status().isOk()) //
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON)) //
				.andExpect(jsonPath("$", hasSize(2))) //
				.andExpect(jsonPath("$[1].nome", is("Sala 2")));
	}

	@Test
	public void getSalasFiltrar_ValidTest() throws Exception {

		mvc.perform(get("/salas/filtrar?qtdPessoas=20&impressora=false") //
				.contentType(MediaType.APPLICATION_JSON)) //
				.andExpect(status().isOk()) //
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON)) //
				.andExpect(jsonPath("$", hasSize(1))) //
				.andExpect(jsonPath("$[0].nome", is("Sala 2")));
	}

	@Test
	public void getByIdSala_ValidTest() throws Exception {

		mvc.perform(get("/salas/2") //
				.contentType(MediaType.APPLICATION_JSON)) //
				.andExpect(status().isOk()) //
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON)) //
				.andExpect(jsonPath("$.id", is(2))) //
				.andExpect(jsonPath("$.nome", is("Sala 2")));
	}

	@Test
	public void getByIdSala_InvalidTest() throws Exception {

		mvc.perform(get("/salas/3") //
				.contentType(MediaType.APPLICATION_JSON)) //
				.andExpect(status().isBadRequest());
	}

	@Test
	public void addSala_ValidTest() throws Exception {

		Sala sala = new Sala(null, "Sala 3", "3º", 30, false);

		mvc.perform(post("/salas") //
				.contentType(MediaType.APPLICATION_JSON) //
				.content(gson.toJson(sala))) //
				.andExpect(status().isOk());
	}

	@Test
	public void addSala_InvalidTest() throws Exception {

		Sala sala = new Sala(null, "Sala 2", "3º", 30, false);

		mvc.perform(post("/salas") //
				.contentType(MediaType.APPLICATION_JSON) //
				.content(gson.toJson(sala))) //
				.andExpect(status().isBadRequest());
	}

	@Test
	public void updateSala_ValidTest() throws Exception {

		Sala sala = new Sala(1, "Sala 1", "2º", 20, true);

		mvc.perform(put("/salas") //
				.contentType(MediaType.APPLICATION_JSON) //
				.content(gson.toJson(sala))) //
				.andExpect(status().isOk());
	}

	@Test
	public void updateSala_invalidTest() throws Exception {

		Sala sala = new Sala(3, "Sala 1", "2º", 20, true);

		mvc.perform(put("/salas") //
				.contentType(MediaType.APPLICATION_JSON) //
				.content(gson.toJson(sala))) //
				.andExpect(status().isBadRequest());
	}

	@Test
	public void deleteSala_ValidTest() throws Exception {

		mvc.perform(delete("/salas/1") //
				.contentType(MediaType.APPLICATION_JSON)) //
				.andExpect(status().isOk());
	}

	@Test
	public void deleteSala_invalidTest() throws Exception {

		mvc.perform(delete("/salas/3") //
				.contentType(MediaType.APPLICATION_JSON)) //
				.andExpect(status().isBadRequest());
	}

}

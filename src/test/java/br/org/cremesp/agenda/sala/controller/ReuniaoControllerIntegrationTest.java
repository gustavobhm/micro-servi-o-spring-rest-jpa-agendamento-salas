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
import br.org.cremesp.agenda.sala.constantes.PublicoEnum;
import br.org.cremesp.agenda.sala.entity.Reuniao;
import br.org.cremesp.agenda.sala.repository.ReuniaoRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.MOCK, classes = AgendamentoSalasApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
public class ReuniaoControllerIntegrationTest {

	@Autowired
	private MockMvc mvc;

	@Autowired
	private ReuniaoRepository repository;

	private Gson gson = new Gson();

	@Before
	public void init() {

		Reuniao reuniao1 = new Reuniao(null, 2, "Responsável 1", "Reunião 1", 10, PublicoEnum.INTERNO.getTexto(), true,
				true, true, true, true, 10);
		repository.saveAndFlush(reuniao1);

		Reuniao reuniao2 = new Reuniao(null, 4, "Responsável 2", "Reunião 2", 20, PublicoEnum.EXTERNO.getTexto(), true,
				true, false, true, false, 20);
		repository.saveAndFlush(reuniao2);

	}

	@Test
	public void getReuniao_ValidTest() throws Exception {

		mvc.perform(get("/reunioes") //
				.contentType(MediaType.APPLICATION_JSON)) //
				.andExpect(status().isOk()) //
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON)) //
				.andExpect(jsonPath("$", hasSize(2))) //
				.andExpect(jsonPath("$[1].tema", is("Reunião 2")));
	}

	@Test
	public void getByIdReuniao_ValidTest() throws Exception {

		mvc.perform(get("/reunioes/2") //
				.contentType(MediaType.APPLICATION_JSON)) //
				.andExpect(status().isOk()) //
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON)) //
				.andExpect(jsonPath("$.id", is(2))) //
				.andExpect(jsonPath("$.tema", is("Reunião 2")));
	}

	@Test
	public void getByIdReuniao_InvalidTest() throws Exception {

		mvc.perform(get("/reunioes/3") //
				.contentType(MediaType.APPLICATION_JSON)) //
				.andExpect(status().isBadRequest());
	}

	@Test
	public void addReuniao_ValidTest() throws Exception {

		Reuniao reuniao = new Reuniao(null, 2, "Responsável 1", "Reunião 3", 10, PublicoEnum.INTERNO.getTexto(), true,
				true, true, true, true, 10);

		mvc.perform(post("/reunioes") //
				.contentType(MediaType.APPLICATION_JSON) //
				.content(gson.toJson(reuniao))) //
				.andExpect(status().isOk());
	}

	@Test
	public void addReuniao_InvalidTest() throws Exception {

		Reuniao reuniao = new Reuniao(null, 2, "Responsável 1", "Reunião 1", 10, PublicoEnum.INTERNO.getTexto(), true,
				true, true, true, true, 10);

		mvc.perform(post("/reunioes") //
				.contentType(MediaType.APPLICATION_JSON) //
				.content(gson.toJson(reuniao))) //
				.andExpect(status().isBadRequest());
	}

	@Test
	public void updateReuniao_ValidTest() throws Exception {

		Reuniao reuniao = new Reuniao(2, 2, "Responsável 1", "Reunião 2 update", 10, PublicoEnum.INTERNO.getTexto(),
				true, true, true, true, true, 10);

		mvc.perform(put("/reunioes") //
				.contentType(MediaType.APPLICATION_JSON) //
				.content(gson.toJson(reuniao))) //
				.andExpect(status().isOk());
	}

	@Test
	public void updateReuniao_InvalidTest() throws Exception {

		Reuniao reuniao = new Reuniao(3, 2, "Responsável 1", "Reunião 3 update", 10, PublicoEnum.INTERNO.getTexto(),
				true, true, true, true, true, 10);

		mvc.perform(put("/reunioes") //
				.contentType(MediaType.APPLICATION_JSON) //
				.content(gson.toJson(reuniao))) //
				.andExpect(status().isBadRequest());
	}

	@Test
	public void deleteReuniao_ValidTest() throws Exception {

		mvc.perform(delete("/reunioes/1") //
				.contentType(MediaType.APPLICATION_JSON)) //
				.andExpect(status().isOk());
	}

	@Test
	public void deleteReuniao_InvalidTest() throws Exception {

		mvc.perform(delete("/reunioes/3") //
				.contentType(MediaType.APPLICATION_JSON)) //
				.andExpect(status().isBadRequest());
	}

}

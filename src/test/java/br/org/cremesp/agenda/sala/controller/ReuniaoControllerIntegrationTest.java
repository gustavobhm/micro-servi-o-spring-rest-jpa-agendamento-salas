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

import br.com.six2six.fixturefactory.Fixture;
import br.org.cremesp.agenda.sala.Application;
import br.org.cremesp.agenda.sala.entity.Reuniao;
import br.org.cremesp.agenda.sala.fixture.BaseFixture;
import br.org.cremesp.agenda.sala.fixture.ReuniaoFixture;
import br.org.cremesp.agenda.sala.repository.ReuniaoRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.MOCK, classes = Application.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
public class ReuniaoControllerIntegrationTest extends BaseFixture {

	@Autowired
	private MockMvc mvc;

	@Autowired
	private ReuniaoRepository repository;

	private Gson gson = new Gson();

	private Reuniao reuniao1;

	private Reuniao reuniao2;

	@Before
	public void init() {

		reuniao1 = Fixture //
				.from(Reuniao.class) //
				.gimme(ReuniaoFixture.VALID_REUNIAO_1);

		repository.save(reuniao1);

		reuniao2 = Fixture //
				.from(Reuniao.class) //
				.gimme(ReuniaoFixture.VALID_REUNIAO_2);

		repository.save(reuniao2);

	}

	@Test
	public void getReuniao_ValidTest() throws Exception {

		mvc.perform(get("/reunioes?pagina=1&tamanho=1") //
				.contentType(MediaType.APPLICATION_JSON)) //
				.andExpect(status().isOk()) //
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON)) //
				.andExpect(jsonPath("$.content", hasSize(1))) //
				.andExpect(jsonPath("$.content[0].tema", is("Reunião 2")));
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
	public void getReunioesFiltrar_ValidTest() throws Exception {

		mvc.perform(get("/reunioes/filtrar?idSolicitante=4") //
				.contentType(MediaType.APPLICATION_JSON)) //
				.andExpect(status().isOk()) //
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON)) //
				.andExpect(jsonPath("$.content", hasSize(1))) //
				.andExpect(jsonPath("$.content[0].tema", is("Reunião 2")));
	}

	@Test
	public void addReuniao_ValidTest() throws Exception {

		Reuniao reuniao = Fixture //
				.from(Reuniao.class) //
				.gimme(ReuniaoFixture.VALID);

		mvc.perform(post("/reunioes") //
				.contentType(MediaType.APPLICATION_JSON) //
				.content(gson.toJson(reuniao))) //
				.andExpect(status().isOk());
	}

	@Test
	public void updateReuniao_ValidTest() throws Exception {

		reuniao1.setTema("Nova Reunião 1");

		mvc.perform(put("/reunioes") //
				.contentType(MediaType.APPLICATION_JSON) //
				.content(gson.toJson(reuniao1.convertToDTO()))) //
				.andExpect(status().isOk());
	}

	@Test
	public void updateReuniao_InvalidTest() throws Exception {

		reuniao1.setId(3);

		mvc.perform(put("/reunioes") //
				.contentType(MediaType.APPLICATION_JSON) //
				.content(gson.toJson(reuniao1.convertToDTO()))) //
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

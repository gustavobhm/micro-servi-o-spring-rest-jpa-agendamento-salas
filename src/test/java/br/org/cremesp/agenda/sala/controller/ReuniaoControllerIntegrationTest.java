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

		Reuniao reuniao1 = Reuniao.builder() //
				.id(null) //
				.idSolicitante(2) //
				.responsavel("Responsável 1") //
				.tema("Reunião 1") //
				.qtdPessoas(10) //
				.publico(PublicoEnum.INTERNO.getTexto()) //
				.projetor(true) //
				.impressora(true) //
				.extraAgua(true) //
				.extraCafe(true) //
				.extraBiscoito(true) //
				.qtdNotebooks(10) //
				.build();

		repository.save(reuniao1);

		Reuniao reuniao2 = Reuniao.builder() //
				.id(null) //
				.idSolicitante(4) //
				.responsavel("Responsável 2") //
				.tema("Reunião 2") //
				.qtdPessoas(20) //
				.publico(PublicoEnum.EXTERNO.getTexto()) //
				.projetor(true) //
				.impressora(true) //
				.extraAgua(false) //
				.extraCafe(true) //
				.extraBiscoito(false) //
				.qtdNotebooks(20) //
				.build();

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

		Reuniao reuniao = Reuniao.builder() //
				.id(null) //
				.idSolicitante(2) //
				.responsavel("Responsável 1") //
				.tema("Reunião 3") //
				.qtdPessoas(10) //
				.publico(PublicoEnum.INTERNO.getTexto()) //
				.projetor(true) //
				.impressora(true) //
				.extraAgua(true) //
				.extraCafe(true) //
				.extraBiscoito(true) //
				.qtdNotebooks(10) //
				.build();

		mvc.perform(post("/reunioes") //
				.contentType(MediaType.APPLICATION_JSON) //
				.content(gson.toJson(reuniao))) //
				.andExpect(status().isOk());
	}

	@Test
	public void updateReuniao_ValidTest() throws Exception {

		Reuniao reuniao = Reuniao.builder() //
				.id(2) //
				.idSolicitante(2) //
				.responsavel("Responsável 1") //
				.tema("Reunião 2 update") //
				.qtdPessoas(10) //
				.publico(PublicoEnum.INTERNO.getTexto()) //
				.projetor(true) //
				.impressora(true) //
				.extraAgua(true) //
				.extraCafe(true) //
				.extraBiscoito(true) //
				.qtdNotebooks(10) //
				.build();

		mvc.perform(put("/reunioes") //
				.contentType(MediaType.APPLICATION_JSON) //
				.content(gson.toJson(reuniao))) //
				.andExpect(status().isOk());
	}

	@Test
	public void updateReuniao_InvalidTest() throws Exception {

		Reuniao reuniao = Reuniao.builder() //
				.id(3) //
				.idSolicitante(2) //
				.responsavel("Responsável 1") //
				.tema("Reunião 3 update") //
				.qtdPessoas(10) //
				.publico(PublicoEnum.INTERNO.getTexto()) //
				.projetor(true) //
				.impressora(true) //
				.extraAgua(true) //
				.extraCafe(true) //
				.extraBiscoito(true) //
				.qtdNotebooks(10) //
				.build();

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

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
import br.org.cremesp.agenda.sala.entity.Horario;
import br.org.cremesp.agenda.sala.entity.Reserva;
import br.org.cremesp.agenda.sala.entity.Reuniao;
import br.org.cremesp.agenda.sala.entity.Sala;
import br.org.cremesp.agenda.sala.fixture.BaseFixture;
import br.org.cremesp.agenda.sala.fixture.HorarioFixture;
import br.org.cremesp.agenda.sala.fixture.ReservaFixture;
import br.org.cremesp.agenda.sala.fixture.ReuniaoFixture;
import br.org.cremesp.agenda.sala.fixture.SalaFixture;
import br.org.cremesp.agenda.sala.repository.HorarioRepository;
import br.org.cremesp.agenda.sala.repository.ReservaRepository;
import br.org.cremesp.agenda.sala.repository.ReuniaoRepository;
import br.org.cremesp.agenda.sala.repository.SalaRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.MOCK, classes = Application.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
public class SalaControllerIntegrationTest extends BaseFixture {

	@Autowired
	private MockMvc mvc;

	@Autowired
	private SalaRepository salaRepository;

	@Autowired
	private ReservaRepository reservaRepository;

	@Autowired
	private HorarioRepository horarioRepository;

	@Autowired
	private ReuniaoRepository reuniaoRepository;

	private Gson gson = new Gson();

	private Sala sala1;

	private Sala sala2;

	@Before
	public void init() {

		sala1 = Fixture //
				.from(Sala.class) //
				.gimme(SalaFixture.VALID_SALA_1);

		salaRepository.save(sala1);

		sala2 = Fixture //
				.from(Sala.class) //
				.gimme(SalaFixture.VALID_SALA_2);

		salaRepository.save(sala2);

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
	public void getSalasFiltrarByImpressora_ValidTest() throws Exception {

		mvc.perform(get("/salas/filtrar?qtdPessoas=10&impressora=true") //
				.contentType(MediaType.APPLICATION_JSON)) //
				.andExpect(status().isOk()) //
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON)) //
				.andExpect(jsonPath("$", hasSize(1))) //
				.andExpect(jsonPath("$[0].nome", is("Sala 1")));
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

		Sala sala = Fixture //
				.from(Sala.class) //
				.gimme(SalaFixture.VALID);

		mvc.perform(post("/salas") //
				.contentType(MediaType.APPLICATION_JSON) //
				.content(gson.toJson(sala.convertToDTO()))) //
				.andExpect(status().isOk());
	}

	@Test
	public void addSala_InvalidTest() throws Exception {

		sala1.setId(null);

		mvc.perform(post("/salas") //
				.contentType(MediaType.APPLICATION_JSON) //
				.content(gson.toJson(sala1.convertToDTO()))) //
				.andExpect(status().isBadRequest());
	}

	@Test
	public void updateSala_ValidTest() throws Exception {

		sala1.setAndar("1º Andar");

		mvc.perform(put("/salas") //
				.contentType(MediaType.APPLICATION_JSON) //
				.content(gson.toJson(sala1.convertToDTO()))) //
				.andExpect(status().isOk());
	}

	@Test
	public void updateSala_InvalidTest() throws Exception {

		sala1.setId(3);

		mvc.perform(put("/salas") //
				.contentType(MediaType.APPLICATION_JSON) //
				.content(gson.toJson(sala1.convertToDTO()))) //
				.andExpect(status().isBadRequest());
	}

	@Test
	public void deleteSala_ValidTest() throws Exception {

		mvc.perform(delete("/salas/1") //
				.contentType(MediaType.APPLICATION_JSON)) //
				.andExpect(status().isOk());
	}

	@Test
	public void deleteSala_InvalidTest() throws Exception {

		mvc.perform(delete("/salas/3") //
				.contentType(MediaType.APPLICATION_JSON)) //
				.andExpect(status().isBadRequest());
	}

	@Test
	public void deleteSalaWithReserva_InvalidTest() throws Exception {

		Reserva reserva = newReserva( //
				ReuniaoFixture.VALID_REUNIAO_1, //
				SalaFixture.VALID_SALA_1, //
				HorarioFixture.VALID_HORARIO_1, //
				ReservaFixture.VALID_RESERVA_1 //
		);
		reservaRepository.save(reserva);

		mvc.perform(delete("/salas/1") //
				.contentType(MediaType.APPLICATION_JSON)) //
				.andExpect(status().isBadRequest());
	}

	private Reserva newReserva( //
			String reuniaoType, //
			String salaType, //
			String horarioType, //
			String reservaType //
	) {

		reuniaoRepository.save(Fixture.from(Reuniao.class).gimme(reuniaoType));
		salaRepository.save(Fixture.from(Sala.class).gimme(salaType));
		horarioRepository.save(Fixture.from(Horario.class).gimme(horarioType));

		return Fixture.from(Reserva.class).gimme(reservaType);
	}

}

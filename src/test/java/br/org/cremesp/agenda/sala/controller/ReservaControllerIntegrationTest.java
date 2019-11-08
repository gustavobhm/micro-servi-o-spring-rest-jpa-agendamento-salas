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

import java.text.ParseException;

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
import br.org.cremesp.agenda.sala.common.DataUtils;
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
public class ReservaControllerIntegrationTest extends BaseFixture {

	@Autowired
	private MockMvc mvc;

	@Autowired
	private ReuniaoRepository reuniaoRepository;

	@Autowired
	private SalaRepository salaRepository;

	@Autowired
	private HorarioRepository horarioRepository;

	@Autowired
	private ReservaRepository reservaRepository;

	@Autowired
	private Gson gson;

	private Reserva reserva1;

	private Reserva reserva2;

	@Before
	public void init() throws ParseException {

		reserva1 = newReserva( //
				ReuniaoFixture.VALID_REUNIAO_1, //
				SalaFixture.VALID_SALA_1, //
				HorarioFixture.VALID_HORARIO_1, //
				ReservaFixture.VALID_RESERVA_1 //
		);
		reservaRepository.save(reserva1);

		reserva2 = newReserva( //
				ReuniaoFixture.VALID_REUNIAO_2, //
				SalaFixture.VALID_SALA_2, //
				HorarioFixture.VALID_HORARIO_2, //
				ReservaFixture.VALID_RESERVA_2 //
		);
		reservaRepository.save(reserva2);

	}

	@Test
	public void getReserva_ValidTest() throws Exception {

		mvc.perform(get("/reservas") //
				.contentType(MediaType.APPLICATION_JSON)) //
				.andExpect(status().isOk()) //
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON)) //
				.andExpect(jsonPath("$", hasSize(2))) //
				.andExpect(jsonPath("$[1].reuniao.tema", is("Reunião 2")));
	}

	@Test
	public void getByIdReserva_ValidTest() throws Exception {

		mvc.perform(get("/reservas/2") //
				.contentType(MediaType.APPLICATION_JSON)) //
				.andExpect(status().isOk()) //
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON)) //
				.andExpect(jsonPath("$.id", is(2))) //
				.andExpect(jsonPath("$.reuniao.tema", is("Reunião 2")));
	}

	@Test
	public void getByIdReserva_InvalidTest() throws Exception {

		mvc.perform(get("/reservas/3") //
				.contentType(MediaType.APPLICATION_JSON)) //
				.andExpect(status().isBadRequest());
	}

	@Test
	public void getReservasFiltrar_ValidTest() throws Exception {

		mvc.perform(get("/reservas/filtrar?data=2019-09-12&idSala=1") //
				.contentType(MediaType.APPLICATION_JSON)) //
				.andExpect(status().isOk()) //
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON)) //
				.andExpect(jsonPath("$", hasSize(1))) //
				.andExpect(jsonPath("$[0].reuniao.tema", is("Reunião 1")));
	}

	@Test
	public void getReservasByReuniao_ValidTest() throws Exception {

		mvc.perform(get("/reservas/reuniao/1") //
				.contentType(MediaType.APPLICATION_JSON)) //
				.andExpect(status().isOk()) //
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON)) //
				.andExpect(jsonPath("$", hasSize(1))) //
				.andExpect(jsonPath("$[0].sala.nome", is("Sala 1")));
	}

	@Test
	public void findDistinctByReuniaoIdAndDataAfterOrderByDataAsc_ValidTest() throws Exception {

		Reserva reserva = Fixture //
				.from(Reserva.class) //
				.gimme(ReservaFixture.VALID);

		reservaRepository.save(reserva);

		mvc.perform(get("/reservas/reuniao/1/tomorrow") //
				.contentType(MediaType.APPLICATION_JSON)) //
				.andExpect(status().isOk()) //
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON)) //
				.andExpect(jsonPath("$", hasSize(1))) //
				.andExpect(jsonPath("$[0].sala.nome", is("Sala 1")));
	}

	@Test
	public void addReserva_ValidTest() throws Exception {

		Reserva reserva = Fixture //
				.from(Reserva.class) //
				.gimme(ReservaFixture.VALID);

		mvc.perform(post("/reservas") //
				.contentType(MediaType.APPLICATION_JSON) //
				.content(gson.toJson(reserva.convertToDTO()))) //
				.andExpect(status().isOk());
	}

	@Test
	public void addReserva_InvalidTest() throws Exception {

		reserva1.setId(null);

		mvc.perform(post("/reservas") //
				.contentType(MediaType.APPLICATION_JSON) //
				.content(gson.toJson(reserva1.convertToDTO()))) //
				.andExpect(status().isBadRequest());
	}

	@Test
	public void updateReserva_ValidTest() throws Exception {

		reserva1.setData(DataUtils.newDateWithFormat("2020-12-12"));

		mvc.perform(put("/reservas") //
				.contentType(MediaType.APPLICATION_JSON) //
				.content(gson.toJson(reserva1.convertToDTO()))) //
				.andExpect(status().isOk());
	}

	@Test
	public void updateReserva_InvalidTest() throws Exception {

		reserva1.setId(3);

		mvc.perform(put("/reservas") //
				.contentType(MediaType.APPLICATION_JSON) //
				.content(gson.toJson(reserva1.convertToDTO()))) //
				.andExpect(status().isBadRequest());
	}

	@Test
	public void updateReserva_DataIntegrityViolationException_InvalidTest() throws Exception {

		reserva1.setId(2);

		mvc.perform(put("/reservas") //
				.contentType(MediaType.APPLICATION_JSON) //
				.content(gson.toJson(reserva1.convertToDTO()))) //
				.andExpect(status().isBadRequest());

	}

	@Test
	public void deleteReserva_ValidTest() throws Exception {

		mvc.perform(delete("/reservas/1") //
				.contentType(MediaType.APPLICATION_JSON)) //
				.andExpect(status().isOk());
	}

	@Test
	public void deleteReserva_InvalidTest() throws Exception {

		mvc.perform(delete("/reservas/3") //
				.contentType(MediaType.APPLICATION_JSON)) //
				.andExpect(status().isBadRequest());
	}

	@Test
	public void deleteReservasByReuniao_ValidTest() throws Exception {

		mvc.perform(delete("/reservas/reuniao/1") //
				.contentType(MediaType.APPLICATION_JSON)) //
				.andExpect(status().isOk());
	}

	@Test
	public void deleteReservaByReuniao_InvalidTest() throws Exception {

		mvc.perform(delete("/reservas/reuniao/3") //
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

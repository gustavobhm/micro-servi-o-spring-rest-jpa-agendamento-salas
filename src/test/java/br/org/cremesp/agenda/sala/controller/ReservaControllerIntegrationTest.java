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

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

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

import br.org.cremesp.agenda.sala.AgendamentoSalasApplication;
import br.org.cremesp.agenda.sala.constantes.PublicoEnum;
import br.org.cremesp.agenda.sala.entity.Horario;
import br.org.cremesp.agenda.sala.entity.Reserva;
import br.org.cremesp.agenda.sala.entity.Reuniao;
import br.org.cremesp.agenda.sala.entity.Sala;
import br.org.cremesp.agenda.sala.repository.HorarioRepository;
import br.org.cremesp.agenda.sala.repository.ReservaRepository;
import br.org.cremesp.agenda.sala.repository.ReuniaoRepository;
import br.org.cremesp.agenda.sala.repository.SalaRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.MOCK, classes = AgendamentoSalasApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
public class ReservaControllerIntegrationTest {

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

	private Reserva reserva1;

	private Reserva reserva2;

	@Before
	public void init() throws ParseException {

		reserva1 = newReserva("Reunião 1", "2019-09-12", "Sala 1", "08:00 - 09:00");
		reservaRepository.saveAndFlush(reserva1);

		reserva2 = newReserva("Reunião 2", "2019-09-12", "Sala 2", "09:00 - 10:00");
		reservaRepository.saveAndFlush(reserva2);

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
	public void addReserva_ValidTest() throws Exception {

		String reservaJson = "{ " //
				+ " \"reuniao\":{ \"id\":1 }, " //
				+ " \"data\"   :\"2019-09-06\", " //
				+ " \"sala\"   :{ \"id\":1 }, " //
				+ " \"horario\":{ \"id\":1 }" //
				+ "}";

		mvc.perform(post("/reservas") //
				.contentType(MediaType.APPLICATION_JSON) //
				.content(reservaJson)) //
				.andExpect(status().isOk());
	}

	@Test
	public void addReserva_InvalidTest() throws Exception {

		String reservaJson = "{ " //
				+ " \"reuniao\":{ \"id\":1 }, " //
				+ " \"data\"   :\"2019-09-12\", " //
				+ " \"sala\"   :{ \"id\":1 }, " //
				+ " \"horario\":{ \"id\":1 }" //
				+ "}";

		mvc.perform(post("/reservas") //
				.contentType(MediaType.APPLICATION_JSON) //
				.content(reservaJson)) //
				.andExpect(status().isBadRequest());
	}

	@Test
	public void updateReserva_ValidTest() throws Exception {

		String reservaJson = "{ \"id\":1 ," //
				+ " \"reuniao\":{ \"id\":1 }, " //
				+ " \"data\"   :\"2019-09-06\", " //
				+ " \"sala\"   :{ \"id\":1 }, " //
				+ " \"horario\":{ \"id\":1 }" //
				+ "}";

		mvc.perform(put("/reservas") //
				.contentType(MediaType.APPLICATION_JSON) //
				.content(reservaJson)) //
				.andExpect(status().isOk());
	}

	@Test
	public void updateReserva_InvalidTest() throws Exception {

		String reservaJson = "{ " //
				+ "	\"id\":3 ," //
				+ " \"reuniao\":{ \"id\":1 }, " //
				+ " \"data\"   :\"2019-09-06\", " //
				+ " \"sala\"   :{ \"id\":1 }, " //
				+ " \"horario\":{ \"id\":1 }" //
				+ "}";

		mvc.perform(put("/reservas") //
				.contentType(MediaType.APPLICATION_JSON) //
				.content(reservaJson)) //
				.andExpect(status().isBadRequest());
	}

	@Test
	public void updateReserva_DataIntegrityViolationException_InvalidTest() throws Exception {

		String reservaJson = "{ " //
				+ "	\"id\":2 ," //
				+ " \"reuniao\":{ \"id\":1 }, " //
				+ " \"data\"   :\"2019-09-12\", " //
				+ " \"sala\"   :{ \"id\":1 }, " //
				+ " \"horario\":{ \"id\":1 }" //
				+ "}";

		mvc.perform(put("/reservas") //
				.contentType(MediaType.APPLICATION_JSON) //
				.content(reservaJson)) //
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

	private Reserva newReserva(String temaReuniao, String stringData, String nomeSala, String hora)
			throws ParseException {

		DateFormat formato = new SimpleDateFormat("yy-MM-dd", Locale.ENGLISH);
		Date data = formato.parse(stringData);

		Reuniao reuniao = Reuniao.builder() //
				.id(null) //
				.idSolicitante(2) //
				.responsavel("Responsável 1") //
				.tema(temaReuniao) //
				.qtdPessoas(10) //
				.publico(PublicoEnum.INTERNO.getTexto()) //
				.projetor(true) //
				.impressora(true) //
				.extraAgua(true) //
				.extraCafe(true) //
				.extraBiscoito(true) //
				.qtdNotebooks(10) //
				.build();

		reuniao = reuniaoRepository.saveAndFlush(reuniao);

		Sala sala = Sala.builder() //
				.id(null) //
				.nome(nomeSala) //
				.andar("1º") //
				.qtdPessoas(10) //
				.impressora(true) //
				.computador(true) //
				.build();

		sala = salaRepository.save(sala);

		Horario horario = Horario.builder() //
				.id(null) //
				.hora(hora) //
				.build();

		horario = horarioRepository.saveAndFlush(horario);

		return Reserva.builder() //
				.id(null) //
				.reuniao(reuniao) //
				.data(data) //
				.sala(sala) //
				.horario(horario) //
				.build();

	}

}

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

import com.google.gson.Gson;

import br.org.cremesp.agenda.sala.AgendamentoSalasApplication;
import br.org.cremesp.agenda.sala.constantes.PublicoEnum;
import br.org.cremesp.agenda.sala.dto.SalaDTO;
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
public class SalaControllerIntegrationTest {

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

	@Before
	public void init() {

		Sala sala1 = Sala.builder() //
				.id(null) //
				.nome("Sala 1") //
				.andar("1º") //
				.qtdPessoas(10) //
				.impressora(true) //
				.computador(true) //
				.build();

		salaRepository.save(sala1);

		Sala sala2 = Sala.builder() //
				.id(null) //
				.nome("Sala 2") //
				.andar("2º") //
				.qtdPessoas(20) //
				.impressora(false) //
				.computador(false) //
				.build();

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

		SalaDTO salaDTO = SalaDTO.builder() //
				.id(null) //
				.nome("Sala 3") //
				.andar("3º") //
				.qtdPessoas(30) //
				.impressora(false) //
				.computador(false) //
				.build();

		mvc.perform(post("/salas") //
				.contentType(MediaType.APPLICATION_JSON) //
				.content(gson.toJson(salaDTO))) //
				.andExpect(status().isOk());
	}

	@Test
	public void addSala_InvalidTest() throws Exception {

		SalaDTO salaDTO = SalaDTO.builder() //
				.id(null) //
				.nome("Sala 2") //
				.andar("3º") //
				.qtdPessoas(30) //
				.impressora(false) //
				.computador(false) //
				.build();

		mvc.perform(post("/salas") //
				.contentType(MediaType.APPLICATION_JSON) //
				.content(gson.toJson(salaDTO))) //
				.andExpect(status().isBadRequest());
	}

	@Test
	public void updateSala_ValidTest() throws Exception {

		SalaDTO salaDTO = SalaDTO.builder() //
				.id(1) //
				.nome("Sala 1") //
				.andar("2º") //
				.qtdPessoas(20) //
				.impressora(true) //
				.computador(false) //
				.build();

		mvc.perform(put("/salas") //
				.contentType(MediaType.APPLICATION_JSON) //
				.content(gson.toJson(salaDTO))) //
				.andExpect(status().isOk());
	}

	@Test
	public void updateSala_InvalidTest() throws Exception {

		SalaDTO salaDTO = SalaDTO.builder() //
				.id(3) //
				.nome("Sala 1") //
				.andar("2º") //
				.qtdPessoas(20) //
				.impressora(true) //
				.computador(false) //
				.build();

		mvc.perform(put("/salas") //
				.contentType(MediaType.APPLICATION_JSON) //
				.content(gson.toJson(salaDTO))) //
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

		DateFormat formato = new SimpleDateFormat("yy-MM-dd", Locale.ENGLISH);
		Date data = formato.parse("2019-09-12");

		Reuniao reuniao = Reuniao.builder() //
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

		reuniao = reuniaoRepository.save(reuniao);

		Sala sala = Sala.builder() //
				.id(null) //
				.nome("Sala 3") //
				.andar("3º") //
				.qtdPessoas(30) //
				.impressora(true) //
				.computador(true) //
				.build();

		sala = salaRepository.save(sala);

		Horario horario = Horario.builder() //
				.id(null) //
				.hora("11:00 - 12:00") //
				.build();

		horario = horarioRepository.save(horario);

		Reserva reserva = Reserva.builder() //
				.id(null) //
				.reuniao(reuniao) //
				.data(data) //
				.sala(sala) //
				.horario(horario) //
				.build();
		
		reserva = reservaRepository.save(reserva);

		mvc.perform(delete("/salas/3") //
				.contentType(MediaType.APPLICATION_JSON)) //
				.andExpect(status().isBadRequest());
	}

}

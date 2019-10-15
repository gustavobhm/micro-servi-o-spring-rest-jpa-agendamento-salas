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
import br.org.cremesp.agenda.sala.dto.HorarioDTO;
import br.org.cremesp.agenda.sala.entity.Horario;
import br.org.cremesp.agenda.sala.repository.HorarioRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.MOCK, classes = AgendamentoSalasApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
public class HorarioControllerIntegrationTest {

	@Autowired
	private MockMvc mvc;

	@Autowired
	private HorarioRepository repository;

	private Gson gson = new Gson();

	@Before
	public void init() {

		Horario horario1 = Horario.builder() //
				.id(null) //
				.hora("08:00 - 09:00") //
				.build();

		repository.save(horario1);

		Horario horario2 = Horario.builder() //
				.id(null) //
				.hora("09:00 - 10:00") //
				.build();

		repository.save(horario2);

	}

	@Test
	public void getHorarios_ValidTest() throws Exception {
		mvc.perform(get("/horarios") //
				.contentType(MediaType.APPLICATION_JSON)) //
				.andExpect(status().isOk()) //
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON)) //
				.andExpect(jsonPath("$", hasSize(2))).andExpect(jsonPath("$[1].hora", is("09:00 - 10:00")));
	}

	@Test
	public void getByIdHorario_ValidTest() throws Exception {
		mvc.perform(get("/horarios/1") //
				.contentType(MediaType.APPLICATION_JSON)) //
				.andExpect(status().isOk()) //
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON)) //
				.andExpect(jsonPath("$.id", is(1))) //
				.andExpect(jsonPath("$.hora", is("08:00 - 09:00")));
	}

	@Test
	public void getByIdHorario_InvalidTest() throws Exception {
		mvc.perform(get("/horarios/3") //
				.contentType(MediaType.APPLICATION_JSON)) //
				.andExpect(status().isBadRequest());
	}

	@Test
	public void addHorario_ValidTest() throws Exception {

		HorarioDTO horarioDTO = HorarioDTO.builder() //
				.id(null) //
				.hora("10:00 - 11:00") //
				.build();

		mvc.perform(post("/horarios") //
				.contentType(MediaType.APPLICATION_JSON) //
				.content(gson.toJson(horarioDTO))) //
				.andExpect(status().isOk());
	}

	@Test
	public void addHorario_InvalidTest() throws Exception {

		HorarioDTO horarioDTO = HorarioDTO.builder() //
				.id(null) //
				.hora("08:00 - 09:00") //
				.build();

		mvc.perform(post("/horarios") //
				.contentType(MediaType.APPLICATION_JSON) //
				.content(gson.toJson(horarioDTO))) //
				.andExpect(status().isBadRequest());
	}

	@Test
	public void updateHorario_ValidTest() throws Exception {

		HorarioDTO horarioDTO = HorarioDTO.builder() //
				.id(2) //
				.hora("10:00 - 11:00") //
				.build();

		mvc.perform(put("/horarios") //
				.contentType(MediaType.APPLICATION_JSON) //
				.content(gson.toJson(horarioDTO))) //
				.andExpect(status().isOk());
	}

	@Test
	public void updateHorario_InvalidTest() throws Exception {

		HorarioDTO horarioDTO = HorarioDTO.builder() //
				.id(3) //
				.hora("09:00 - 10:00") //
				.build();

		mvc.perform(put("/horarios") //
				.contentType(MediaType.APPLICATION_JSON) //
				.content(gson.toJson(horarioDTO))) //
				.andExpect(status().isBadRequest());
	}

	@Test
	public void deleteHorario_ValidTest() throws Exception {
		mvc.perform(delete("/horarios/1") //
				.contentType(MediaType.APPLICATION_JSON)) //
				.andExpect(status().isOk());
	}

	@Test
	public void deleteHorario_InvalidTest() throws Exception {
		mvc.perform(delete("/horarios/3") //
				.contentType(MediaType.APPLICATION_JSON)) //
				.andExpect(status().isBadRequest());
	}

}

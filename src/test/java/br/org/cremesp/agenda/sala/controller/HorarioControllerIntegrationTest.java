package br.org.cremesp.agenda.sala.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import br.org.cremesp.agenda.sala.AgendamentoSalasApplication;
import br.org.cremesp.agenda.sala.entity.Horario;
import br.org.cremesp.agenda.sala.entity.Reserva;
import br.org.cremesp.agenda.sala.repository.HorarioRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.MOCK, classes = AgendamentoSalasApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
public class HorarioControllerIntegrationTest {

	@Autowired
	private MockMvc mvc;

	@Autowired
	private HorarioRepository repository;

	@Test
	public void givenHorarios_whenGetHorarios_thenStatus200() throws Exception {

		Horario horario = new Horario(null, "08:00 - 09:00", new ArrayList<Reserva>());
		repository.save(horario);
		
		Horario horario2 = new Horario(null, "09:00 - 10:00", new ArrayList<Reserva>());
		repository.save(horario2);

		mvc.perform(get("/horarios") //
				.contentType(MediaType.APPLICATION_JSON)) //
				.andExpect(status().isOk()) //
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON)) //
				.andExpect(jsonPath("$[1].hora", is("09:00 - 10:00")));
	}

}

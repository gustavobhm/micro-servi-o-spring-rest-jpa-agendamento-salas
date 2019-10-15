package br.org.cremesp.agenda.sala.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import br.org.cremesp.agenda.sala.entity.Horario;
import br.org.cremesp.agenda.sala.service.HorarioService;

@RunWith(SpringRunner.class)
@WebMvcTest(HorarioController.class)
public class HorarioControllerTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private HorarioService service;

	@Test
	public void givenHorarios_whenGetHorarios_thenReturnJsonArray() throws Exception {

		Horario horario = Horario.builder() //
				.id(null) //
				.hora("08:00 - 09:00") //
				.build();

		List<Horario> allHorarios = Arrays.asList(horario);

		given(service.getAll()).willReturn(allHorarios);

		mvc.perform(get("/horarios") //
				.contentType(MediaType.APPLICATION_JSON)) //
				.andExpect(status().isOk()) //
				.andExpect(jsonPath("$", hasSize(1))) //
				.andExpect(jsonPath("$[0].hora", is(horario.getHora())));
	}

}

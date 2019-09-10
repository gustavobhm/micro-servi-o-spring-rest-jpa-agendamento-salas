package br.org.cremesp.agenda.sala.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import br.org.cremesp.agenda.sala.entity.Horario;
import br.org.cremesp.agenda.sala.entity.Reserva;
import br.org.cremesp.agenda.sala.exception.BadRequestException;
import br.org.cremesp.agenda.sala.repository.HorarioRepository;

@RunWith(SpringRunner.class)
public class HorarioServiceTest {

	@TestConfiguration
	static class HorarioServiceTestContextConfiguration {

		@Bean
		public HorarioService horarioService() {
			return new HorarioService();
		}
	}

	@Autowired
	private HorarioService horarioService;

	@MockBean
	private HorarioRepository horarioRepository;

	@Before
	public void setUp() {

		Horario horario = new Horario(null, "08:00 - 09:00", new ArrayList<Reserva>());

		Mockito.when(horarioRepository.findByHora(horario.getHora())).thenReturn(horario);
	}

	@Test
	public void whenValidHora_thenHorarioShouldBeFound() throws BadRequestException {

		String hora = "08:00 - 09:00";

		Horario found = horarioService.getHoraBy(hora);

		assertThat(found.getHora()).isEqualTo(hora);
	}

}

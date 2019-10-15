package br.org.cremesp.agenda.sala.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import br.org.cremesp.agenda.sala.entity.Horario;
import br.org.cremesp.agenda.sala.exception.BadRequestException;
import br.org.cremesp.agenda.sala.repository.HorarioRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HorarioServiceTest {

	@Autowired
	private HorarioService horarioService;

	@MockBean
	private HorarioRepository horarioRepository;

	@Before
	public void setUp() {

		Horario horario = Horario.builder() //
				.id(1) //
				.hora("08:00 - 09:00") //
				.build();

		Mockito.when(horarioRepository.findById(1)).thenReturn(Optional.of(horario));
	}

	@Test
	public void whenValidHora_thenHorarioShouldBeFound() throws BadRequestException {

		String hora = "08:00 - 09:00";

		Horario found = horarioService.get(1);

		assertThat(found.getHora()).isEqualTo(hora);
	}

}

package br.org.cremesp.agenda.sala.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import br.org.cremesp.agenda.sala.entity.Horario;
import br.org.cremesp.agenda.sala.entity.Reserva;

@RunWith(SpringRunner.class)
@DataJpaTest
public class HorarioRepositoryTest {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private HorarioRepository horarioRepository;

	@Test
	public void whenFindByHora_thenReturnHorario() {
		
		// given
		Horario horario = new Horario(null, "08:00 - 09:00", new ArrayList<Reserva>());
		entityManager.persist(horario);
		entityManager.flush();

		// when
		Horario found = horarioRepository.findByHora(horario.getHora());

		// then
		assertThat(found.getHora()).isEqualTo(horario.getHora());
		assertThat(1).isEqualTo(horario.getId());
	}

}

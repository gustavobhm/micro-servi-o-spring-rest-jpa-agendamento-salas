package br.org.cremesp.agenda.sala.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import br.org.cremesp.agenda.sala.entity.Horario;

@Repository
public interface HorarioRepository extends PagingAndSortingRepository<Horario, Integer> {

	public List<Horario> findAllByOrderByIdAsc();
}

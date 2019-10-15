package br.org.cremesp.agenda.sala.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import br.org.cremesp.agenda.sala.entity.Reserva;
import br.org.cremesp.agenda.sala.projection.ReservaByReuniaoView;

@Repository
public interface ReservaRepository extends PagingAndSortingRepository<Reserva, Integer> {

	public List<Reserva> findAllByOrderByIdAsc();

	public List<Reserva> findByDataAndSalaIdOrderByHorarioIdAsc(Date data, Integer idSala);

	public List<Reserva> findByReuniaoIdOrderByIdAsc(Integer idReuniao);

	public List<ReservaByReuniaoView> findDistinctByReuniaoIdOrderByDataAscSalaIdAsc(Integer idReuniao);

	public void deleteByReuniaoId(Integer idReuniao);
}
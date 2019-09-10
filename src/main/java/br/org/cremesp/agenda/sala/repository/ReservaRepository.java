package br.org.cremesp.agenda.sala.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.org.cremesp.agenda.sala.entity.Reserva;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Integer> {

	public List<Reserva> findAllByOrderByIdAsc();

	public List<Reserva> findByDataAndSalaIdOrderByHorarioIdAsc(Date data, Integer idSala);
}
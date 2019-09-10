package br.org.cremesp.agenda.sala.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.org.cremesp.agenda.sala.entity.Horario;

@Repository
public interface HorarioRepository extends JpaRepository<Horario, Integer> {

	public Horario findByHora(String hora);

	public List<Horario> findAllByOrderByIdAsc();
}
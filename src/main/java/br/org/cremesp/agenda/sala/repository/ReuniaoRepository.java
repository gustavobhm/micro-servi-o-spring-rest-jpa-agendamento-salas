package br.org.cremesp.agenda.sala.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.org.cremesp.agenda.sala.entity.Reuniao;

@Repository
public interface ReuniaoRepository extends JpaRepository<Reuniao, Integer> {

	public List<Reuniao> findAllByOrderByIdAsc();

	public List<Reuniao> findByIdSolicitanteOrderByTemaAsc(Integer idSolicitante);
}

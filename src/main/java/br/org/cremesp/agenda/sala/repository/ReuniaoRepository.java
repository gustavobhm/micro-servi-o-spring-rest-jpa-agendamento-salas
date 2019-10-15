package br.org.cremesp.agenda.sala.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.org.cremesp.agenda.sala.entity.Reuniao;

@Repository
public interface ReuniaoRepository extends JpaRepository<Reuniao, Integer> {

	public Page<Reuniao> findAllByOrderByIdAsc(Pageable pageable);

	public Page<Reuniao> findByIdSolicitanteOrderByTemaAsc(Integer idSolicitante, Pageable pageable);
}

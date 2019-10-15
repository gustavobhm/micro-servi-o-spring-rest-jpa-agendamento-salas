package br.org.cremesp.agenda.sala.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import br.org.cremesp.agenda.sala.entity.Reuniao;

@Repository
public interface ReuniaoRepository extends PagingAndSortingRepository<Reuniao, Integer> {

	public Page<Reuniao> findAllByOrderByIdAsc(Pageable pageable);

	public Page<Reuniao> findByIdSolicitanteOrderByTemaAsc(Integer idSolicitante, Pageable pageable);
}

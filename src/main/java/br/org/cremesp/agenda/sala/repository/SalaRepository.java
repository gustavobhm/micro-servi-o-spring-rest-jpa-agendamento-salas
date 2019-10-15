package br.org.cremesp.agenda.sala.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import br.org.cremesp.agenda.sala.entity.Sala;

@Repository
public interface SalaRepository extends PagingAndSortingRepository<Sala, Integer> {
	
	public List<Sala> findAllByOrderByIdAsc();

	public List<Sala> findByQtdPessoasGreaterThanEqualAndImpressoraOrderByQtdPessoasAsc(Integer qtdPessoas,
			Boolean impressora);

	public List<Sala> findByQtdPessoasGreaterThanEqualOrderByQtdPessoasAsc(Integer qtdPessoas);

}

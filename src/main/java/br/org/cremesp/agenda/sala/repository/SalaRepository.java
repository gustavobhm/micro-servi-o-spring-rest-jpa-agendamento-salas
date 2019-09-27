package br.org.cremesp.agenda.sala.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.org.cremesp.agenda.sala.entity.Sala;

@Repository
public interface SalaRepository extends JpaRepository<Sala, Integer> {

	public List<Sala> findAllByOrderByIdAsc();

	public List<Sala> findByQtdPessoasGreaterThanEqualAndImpressoraOrderByQtdPessoasAsc(Integer qtdPessoas, Boolean impressora);
	
	public List<Sala> findByQtdPessoasGreaterThanEqualOrderByQtdPessoasAsc(Integer qtdPessoas);

}

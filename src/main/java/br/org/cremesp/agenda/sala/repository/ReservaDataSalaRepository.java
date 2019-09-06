package br.org.cremesp.agenda.sala.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.org.cremesp.agenda.sala.entity.ReservaDataSala;

@Repository
public interface ReservaDataSalaRepository extends JpaRepository<ReservaDataSala, Integer> {

	public List<ReservaDataSala> findAllByOrderByIdAsc();

	public List<ReservaDataSala> findByDataAndSalaQtdPessoasGreaterThanEqualAndSalaImpressoraOrderByIdAsc(Date data, Integer qtdPessoas, Boolean impressora);
}

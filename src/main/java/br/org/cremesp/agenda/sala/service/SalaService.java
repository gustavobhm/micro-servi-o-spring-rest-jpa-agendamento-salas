package br.org.cremesp.agenda.sala.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.org.cremesp.agenda.sala.entity.Sala;
import br.org.cremesp.agenda.sala.exception.BadRequestException;
import br.org.cremesp.agenda.sala.repository.SalaRepository;

@Service
public class SalaService {

	@Autowired
	private SalaRepository salaRepository;

	public List<Sala> getAll() {
		return salaRepository.findAllByOrderByIdAsc();
	}

	public Sala get(int id) throws BadRequestException {
		try {
			return salaRepository.findById(id).get();
		} catch (Exception e) {
			throw new BadRequestException(e.getMessage());
		}
	}

	public List<Sala> getSalasBy(Integer qtdPessoas, Boolean impressora) {
		return salaRepository.findByQtdPessoasGreaterThanEqualAndImpressoraOrderByQtdPessoasAsc(qtdPessoas, impressora);
	}

	public Sala add(Sala sala) throws BadRequestException {
		try {
			return salaRepository.save(sala);
		} catch (Exception e) {
			throw new BadRequestException(e.getMessage());
		}
	}

	public Sala edit(Sala sala) throws BadRequestException {
		try {
			Sala s = salaRepository.findById(sala.getId()).get();
			s.setNome(sala.getNome());
			s.setAndar(sala.getAndar());
			s.setQtdPessoas(sala.getQtdPessoas());
			s.setImpressora(sala.getImpressora());
			return salaRepository.save(s);
		} catch (Exception e) {
			throw new BadRequestException(e.getMessage());
		}
	}

	public void delete(int id) throws BadRequestException {
		try {
			salaRepository.deleteById(id);
		} catch (Exception e) {
			throw new BadRequestException(e.getMessage());
		}

	}
}

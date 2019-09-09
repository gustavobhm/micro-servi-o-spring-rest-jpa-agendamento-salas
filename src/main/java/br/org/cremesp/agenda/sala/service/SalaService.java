package br.org.cremesp.agenda.sala.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.org.cremesp.agenda.sala.constantes.AgendamentoSalasEnum;
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
			throw new BadRequestException(AgendamentoSalasEnum.MSG_ERRO.getTexto());
		}
	}

	public List<Sala> getSalasBy(Integer qtdPessoas, Boolean impressora) throws BadRequestException {
		try {
			return salaRepository.findByQtdPessoasGreaterThanEqualAndImpressoraOrderByQtdPessoasAsc(qtdPessoas, impressora);
		} catch (Exception e) {
			throw new BadRequestException(AgendamentoSalasEnum.MSG_ERRO.getTexto());
		}
	}

	public Sala add(Sala sala) throws BadRequestException {
		try {
			return salaRepository.save(sala);
		} catch (Exception e) {
			throw new BadRequestException(AgendamentoSalasEnum.MSG_ERRO.getTexto());
		}
	}

	public Sala edit(Sala sala) throws BadRequestException {
		try {
			salaRepository.deleteById(sala.getId());
			return salaRepository.save(sala);
		} catch (Exception e) {
			throw new BadRequestException(AgendamentoSalasEnum.MSG_ERRO.getTexto());
		}
	}

	public void delete(int id) throws BadRequestException {
		try {
			salaRepository.deleteById(id);
		} catch (Exception e) {
			throw new BadRequestException(AgendamentoSalasEnum.MSG_ERRO.getTexto());
		}

	}
}

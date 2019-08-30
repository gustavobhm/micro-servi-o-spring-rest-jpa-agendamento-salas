package br.org.cremesp.agenda.sala.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.org.cremesp.agenda.sala.constantes.AgendamentoEnum;
import br.org.cremesp.agenda.sala.entity.Agendamento;
import br.org.cremesp.agenda.sala.exception.BadRequestException;
import br.org.cremesp.agenda.sala.repository.AgendamentoRepository;

@Service
public class AgendamentoService {

	@Autowired
	private AgendamentoRepository agendamentoRepository;

	public List<Agendamento> getAll() {
		return agendamentoRepository.findAllByOrderByIdSolicitanteAsc();
	}

	public Agendamento get(int id) throws BadRequestException {
		try {
			return agendamentoRepository.findById(id).get();
		} catch (Exception e) {
			throw new BadRequestException(AgendamentoEnum.MSG_ERRO.getTexto());
		}
	}

	public Agendamento add(Agendamento agendamento) throws BadRequestException {
		try {
			return agendamentoRepository.save(agendamento);
		} catch (Exception e) {
			throw new BadRequestException(AgendamentoEnum.MSG_ERRO.getTexto());
		}
	}

	public Agendamento edit(Agendamento agendamento) throws BadRequestException {
		try {
			agendamentoRepository.deleteById(agendamento.getId());
			return agendamentoRepository.save(agendamento);
		} catch (Exception e) {
			throw new BadRequestException(AgendamentoEnum.MSG_ERRO.getTexto());
		}
	}

	public void delete(int id) throws BadRequestException {
		try {
			agendamentoRepository.deleteById(id);
		} catch (Exception e) {
			throw new BadRequestException(AgendamentoEnum.MSG_ERRO.getTexto());
		}

	}
}

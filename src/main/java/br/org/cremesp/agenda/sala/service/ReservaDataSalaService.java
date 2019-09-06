package br.org.cremesp.agenda.sala.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.org.cremesp.agenda.sala.constantes.AgendamentoSalasEnum;
import br.org.cremesp.agenda.sala.entity.ReservaDataSala;
import br.org.cremesp.agenda.sala.exception.BadRequestException;
import br.org.cremesp.agenda.sala.repository.ReservaDataSalaRepository;

@Service
public class ReservaDataSalaService {

	@Autowired
	private ReservaDataSalaRepository reservaDataSalaRepository;

	public List<ReservaDataSala> getAll() {
		return reservaDataSalaRepository.findAllByOrderByIdAsc();
	}

	public ReservaDataSala get(int id) throws BadRequestException {
		try {
			return reservaDataSalaRepository.findById(id).get();
		} catch (Exception e) {
			throw new BadRequestException(AgendamentoSalasEnum.MSG_ERRO.getTexto());
		}
	}

	public List<ReservaDataSala> getReservasDataSalaBy(Date data, Integer qtdPessoas, Boolean impressora) throws BadRequestException {
		try {
			System.out.println(impressora);
			return reservaDataSalaRepository.findByDataAndSalaQtdPessoasGreaterThanEqualAndSalaImpressoraOrderByIdAsc(data, qtdPessoas, impressora);
		} catch (Exception e) {
			throw new BadRequestException(AgendamentoSalasEnum.MSG_ERRO.getTexto());
		}
	}

	public ReservaDataSala add(ReservaDataSala reservaDataSala) throws BadRequestException {
		try {
			return reservaDataSalaRepository.save(reservaDataSala);
		} catch (Exception e) {
			throw new BadRequestException(AgendamentoSalasEnum.MSG_ERRO.getTexto());
		}
	}

	public ReservaDataSala edit(ReservaDataSala reservaDataSala) throws BadRequestException {
		try {
			reservaDataSalaRepository.deleteById(reservaDataSala.getId());
			return reservaDataSalaRepository.save(reservaDataSala);
		} catch (Exception e) {
			throw new BadRequestException(AgendamentoSalasEnum.MSG_ERRO.getTexto());
		}
	}

	public void delete(int id) throws BadRequestException {
		try {
			reservaDataSalaRepository.deleteById(id);
		} catch (Exception e) {
			throw new BadRequestException(AgendamentoSalasEnum.MSG_ERRO.getTexto());
		}

	}
}

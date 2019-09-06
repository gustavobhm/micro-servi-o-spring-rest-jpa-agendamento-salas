package br.org.cremesp.agenda.sala.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.org.cremesp.agenda.sala.constantes.AgendamentoSalasEnum;
import br.org.cremesp.agenda.sala.entity.Reserva;
import br.org.cremesp.agenda.sala.exception.BadRequestException;
import br.org.cremesp.agenda.sala.repository.ReservaRepository;

@Service
public class ReservaService {

	@Autowired
	private ReservaRepository reservaRepository;

	public List<Reserva> getAll() {
		return reservaRepository.findAllByOrderByIdAsc();
	}

	public Reserva get(int id) throws BadRequestException {
		try {
			return reservaRepository.findById(id).get();
		} catch (Exception e) {
			throw new BadRequestException(AgendamentoSalasEnum.MSG_ERRO.getTexto());
		}
	}

	public List<Reserva> getReservasBy(Date data, Integer qtdPessoas, Boolean impressora) throws BadRequestException {
		try {
			return reservaRepository.findByDataAndSalaQtdPessoasGreaterThanEqualAndSalaImpressoraOrderByIdAsc(data,
					qtdPessoas, impressora);
		} catch (Exception e) {
			throw new BadRequestException(AgendamentoSalasEnum.MSG_ERRO.getTexto());
		}
	}

	public Reserva add(Reserva reserva) throws BadRequestException {
		try {
			return reservaRepository.save(reserva);
		} catch (Exception e) {
			throw new BadRequestException(AgendamentoSalasEnum.MSG_ERRO.getTexto());
		}
	}

	public Reserva edit(Reserva reserva) throws BadRequestException {
		try {
			reservaRepository.deleteById(reserva.getId());
			return reservaRepository.save(reserva);
		} catch (Exception e) {
			throw new BadRequestException(AgendamentoSalasEnum.MSG_ERRO.getTexto());
		}
	}

	public void delete(int id) throws BadRequestException {
		try {
			reservaRepository.deleteById(id);
		} catch (Exception e) {
			throw new BadRequestException(AgendamentoSalasEnum.MSG_ERRO.getTexto());
		}

	}
}

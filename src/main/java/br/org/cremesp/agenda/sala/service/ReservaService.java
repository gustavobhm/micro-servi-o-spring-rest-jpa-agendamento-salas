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
		return reservaRepository.findById(id)
				.orElseThrow(() -> new BadRequestException(AgendamentoSalasEnum.MSG_RESERVA_FIND_ERRO.getTexto()));
	}

	public List<Reserva> getReservasBy(Date data, Integer idSala) {
		return reservaRepository.findByDataAndSalaIdOrderByHorarioIdAsc(data, idSala);
	}

	public Reserva add(Reserva reserva) throws BadRequestException {
		try {
			return reservaRepository.save(reserva);
		} catch (Exception e) {
			throw new BadRequestException(
					AgendamentoSalasEnum.MSG_RESERVA_SAVE_ERRO.getTexto() + " -> " + e.getMessage());
		}
	}

	public Reserva edit(Reserva reserva) throws BadRequestException {
		Reserva r = reservaRepository.findById(reserva.getId())
				.orElseThrow(() -> new BadRequestException(AgendamentoSalasEnum.MSG_RESERVA_UPDATE_ERRO.getTexto()
						+ " -> " + AgendamentoSalasEnum.MSG_RESERVA_FIND_ERRO.getTexto()));
		r.setData(reserva.getData());
		r.setHorario(reserva.getHorario());
		r.setReuniao(reserva.getReuniao());
		r.setSala(reserva.getSala());
		return reservaRepository.save(r);
	}

	public void delete(int id) throws BadRequestException {
		try {
			reservaRepository.deleteById(id);
		} catch (Exception e) {
			throw new BadRequestException(
					AgendamentoSalasEnum.MSG_RESERVA_DELETE_ERRO.getTexto() + " -> " + e.getMessage());
		}

	}
}

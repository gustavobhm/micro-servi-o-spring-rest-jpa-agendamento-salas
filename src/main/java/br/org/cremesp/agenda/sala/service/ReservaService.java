package br.org.cremesp.agenda.sala.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.org.cremesp.agenda.sala.constantes.AgendamentoSalasEnum;
import br.org.cremesp.agenda.sala.entity.Reserva;
import br.org.cremesp.agenda.sala.exception.BadRequestException;
import br.org.cremesp.agenda.sala.projection.ReservaByReuniaoView;
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

	public List<ReservaByReuniaoView> getReservasByReuniao(Integer idReuniao) {
		return reservaRepository.findDistinctByReuniaoIdOrderByDataAscSalaIdAsc(idReuniao);
	}

	public Reserva add(Reserva reserva) throws BadRequestException {
		try {
			return reservaRepository.save(reserva);
		} catch (DataIntegrityViolationException e) {
			throw new BadRequestException(AgendamentoSalasEnum.MSG_RESERVA_SAVE_CONSTRAINT_ERRO.getTexto());
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

		try {
			return reservaRepository.save(r);
		} catch (DataIntegrityViolationException e) {
			throw new BadRequestException(AgendamentoSalasEnum.MSG_RESERVA_UPDATE_CONSTRAINT_ERRO.getTexto());
		}
	}

	public void delete(int id) throws BadRequestException {
		try {
			reservaRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new BadRequestException(AgendamentoSalasEnum.MSG_RESERVA_DELETE_ERRO.getTexto() + " -> "
					+ AgendamentoSalasEnum.MSG_RESERVA_FIND_ERRO.getTexto());
		}
	}

	@Transactional
	public void deleteReservasByReuniao(int idReuniao) throws BadRequestException {

		List<Reserva> resevas = reservaRepository.findByReuniaoIdOrderByIdAsc(idReuniao);

		if (resevas.isEmpty()) {
			throw new BadRequestException(AgendamentoSalasEnum.MSG_RESERVA_DELETE_ERRO.getTexto() + " -> "
					+ AgendamentoSalasEnum.MSG_RESERVA_FIND_ERRO.getTexto());
		}

		reservaRepository.deleteByReuniaoId(idReuniao);

	}

}

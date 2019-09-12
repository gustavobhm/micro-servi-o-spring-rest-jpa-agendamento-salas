package br.org.cremesp.agenda.sala.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
			throw new BadRequestException(e.getMessage());
		}
	}

	public List<Reserva> getReservasBy(Date data, Integer idSala) throws BadRequestException {
		try {
			return reservaRepository.findByDataAndSalaIdOrderByHorarioIdAsc(data, idSala);
		} catch (Exception e) {
			throw new BadRequestException(e.getMessage());
		}
	}

	public Reserva add(Reserva reserva) throws BadRequestException {
		try {
			return reservaRepository.save(reserva);
		} catch (Exception e) {
			throw new BadRequestException(e.getMessage());
		}
	}

	public Reserva edit(Reserva reserva) throws BadRequestException {
		try {
			Reserva r = reservaRepository.findById(reserva.getId()).get();
			r.setData(reserva.getData());
			r.setHorario(reserva.getHorario());
			r.setReuniao(reserva.getReuniao());
			r.setSala(reserva.getSala());
			return reservaRepository.save(r);
		} catch (Exception e) {
			throw new BadRequestException(e.getMessage());
		}
	}

	public void delete(int id) throws BadRequestException {
		try {
			reservaRepository.deleteById(id);
		} catch (Exception e) {
			throw new BadRequestException(e.getMessage());
		}

	}
}

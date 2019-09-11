package br.org.cremesp.agenda.sala.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.org.cremesp.agenda.sala.constantes.AgendamentoSalasEnum;
import br.org.cremesp.agenda.sala.entity.Horario;
import br.org.cremesp.agenda.sala.exception.BadRequestException;
import br.org.cremesp.agenda.sala.repository.HorarioRepository;

@Service
public class HorarioService {

	@Autowired
	private HorarioRepository horarioRepository;

	public List<Horario> getAll() {
		return horarioRepository.findAllByOrderByIdAsc();
	}

	public Horario get(int id) throws BadRequestException {
		try {
			return horarioRepository.findById(id).get();
		} catch (Exception e) {
			throw new BadRequestException(AgendamentoSalasEnum.MSG_ERRO.getTexto());
		}
	}

	public Horario add(Horario horario) throws BadRequestException {
		try {
			return horarioRepository.save(horario);
		} catch (Exception e) {
			throw new BadRequestException(e.getMessage());
		}
	}

	public Horario edit(Horario horario) throws BadRequestException {

		try {
			Horario h = horarioRepository.findById(horario.getId()).get();
			h.setHora(horario.getHora());
			return horarioRepository.save(h);
		} catch (Exception e) {
			throw new BadRequestException(e.getMessage());
		}

	}

	public void delete(int id) throws BadRequestException {
		try {
			horarioRepository.deleteById(id);
		} catch (Exception e) {
			throw new BadRequestException(e.getMessage());
		}

	}
}

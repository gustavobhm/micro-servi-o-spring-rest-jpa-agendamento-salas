package br.org.cremesp.agenda.sala.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.org.cremesp.agenda.sala.constantes.AgendamentoSalasEnum;
import br.org.cremesp.agenda.sala.entity.Reuniao;
import br.org.cremesp.agenda.sala.exception.BadRequestException;
import br.org.cremesp.agenda.sala.repository.ReuniaoRepository;

@Service
public class ReuniaoService {

	@Autowired
	private ReuniaoRepository reuniaoRepository;

	public List<Reuniao> getAll() {
		return reuniaoRepository.findAllByOrderByIdAsc();
	}

	public Reuniao get(int id) throws BadRequestException {
		try {
			return reuniaoRepository.findById(id).get();
		} catch (Exception e) {
			throw new BadRequestException(e.getMessage());
		}
	}

	public Reuniao add(Reuniao reuniao) throws BadRequestException {
		try {
			return reuniaoRepository.save(reuniao);
		} catch (Exception e) {
			throw new BadRequestException(e.getMessage());
		}
	}

	public Reuniao edit(Reuniao reuniao) throws BadRequestException {
		try {
			reuniaoRepository.deleteById(reuniao.getId());
			return reuniaoRepository.save(reuniao);
		} catch (Exception e) {
			throw new BadRequestException(e.getMessage());
		}
	}

	public void delete(int id) throws BadRequestException {
		try {
			reuniaoRepository.deleteById(id);
		} catch (Exception e) {
			throw new BadRequestException(e.getMessage());
		}

	}
}

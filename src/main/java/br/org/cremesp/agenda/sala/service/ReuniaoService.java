package br.org.cremesp.agenda.sala.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
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
		return reuniaoRepository.findById(id)
				.orElseThrow(() -> new BadRequestException(AgendamentoSalasEnum.MSG_REUNIAO_FIND_ERRO.getTexto()));

	}

	public List<Reuniao> getReunioesBy(Integer idSolicitante) {
		return reuniaoRepository.findByIdSolicitanteOrderByTemaAsc(idSolicitante);
	}

	public Reuniao add(Reuniao reuniao) throws BadRequestException {
		try {
			return reuniaoRepository.save(reuniao);
		} catch (DataIntegrityViolationException e) {
			throw new BadRequestException(AgendamentoSalasEnum.MSG_REUNIAO_SAVE_CONSTRAINT_ERRO.getTexto());
		}
	}

	public Reuniao edit(Reuniao reuniao) throws BadRequestException {
		Reuniao r = reuniaoRepository.findById(reuniao.getId())
				.orElseThrow(() -> new BadRequestException(AgendamentoSalasEnum.MSG_REUNIAO_UPDATE_ERRO.getTexto()
						+ " -> " + AgendamentoSalasEnum.MSG_REUNIAO_FIND_ERRO.getTexto()));
		r.setExtraAgua(reuniao.getExtraAgua());
		r.setExtraBiscoito(reuniao.getExtraBiscoito());
		r.setExtraCafe(reuniao.getExtraCafe());
		r.setResponsavel(reuniao.getResponsavel());
		r.setIdSolicitante(reuniao.getIdSolicitante());
		r.setImpressora(reuniao.getImpressora());
		r.setProjetor(reuniao.getProjetor());
		r.setPublico(reuniao.getPublico());
		r.setQtdNotebooks(reuniao.getQtdNotebooks());
		r.setQtdPessoas(reuniao.getQtdPessoas());
		r.setTema(reuniao.getTema());

		try {
			return reuniaoRepository.save(r);
		} catch (DataIntegrityViolationException e) {
			throw new BadRequestException(AgendamentoSalasEnum.MSG_REUNIAO_UPDATE_CONSTRAINT_ERRO.getTexto());
		}
	}

	public void delete(int id) throws BadRequestException {
		try {
			reuniaoRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new BadRequestException(AgendamentoSalasEnum.MSG_REUNIAO_DELETE_ERRO.getTexto() + " -> "
					+ AgendamentoSalasEnum.MSG_REUNIAO_FIND_ERRO.getTexto());
		}

	}
}

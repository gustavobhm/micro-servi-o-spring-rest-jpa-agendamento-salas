package br.org.cremesp.agenda.sala.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import br.org.cremesp.agenda.sala.constantes.AgendamentoSalasEnum;
import br.org.cremesp.agenda.sala.entity.Reuniao;
import br.org.cremesp.agenda.sala.exception.BadRequestException;
import br.org.cremesp.agenda.sala.repository.ReuniaoRepository;

@Service
public class ReuniaoService {

	@Autowired
	private ReuniaoRepository reuniaoRepository;

	public Page<Reuniao> getAll(Integer pagina, Integer tamanho) {
		return reuniaoRepository.findAllByOrderByIdAsc(PageRequest.of(pagina, tamanho));
	}

	public Reuniao get(int id) throws BadRequestException {
		return reuniaoRepository.findById(id)
				.orElseThrow(() -> new BadRequestException(AgendamentoSalasEnum.MSG_REUNIAO_FIND_ERRO.getTexto()));

	}

	public Page<Reuniao> getReunioesBy(Integer idSolicitante, Integer pagina, Integer tamanho) {
		return reuniaoRepository.findByIdSolicitanteOrderByTemaAsc(idSolicitante, PageRequest.of(pagina, tamanho));
	}

	public Reuniao add(Reuniao reuniao) {
		return reuniaoRepository.save(reuniao);
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

		return reuniaoRepository.save(r);
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

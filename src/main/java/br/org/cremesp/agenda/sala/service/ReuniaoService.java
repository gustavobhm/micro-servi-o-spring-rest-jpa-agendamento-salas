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
		return reuniaoRepository.findById(id)
				.orElseThrow(() -> new BadRequestException(AgendamentoSalasEnum.MSG_ERRO.getTexto()));

	}

	public Reuniao add(Reuniao reuniao) throws BadRequestException {
		try {
			return reuniaoRepository.save(reuniao);
		} catch (Exception e) {
			throw new BadRequestException(e.getMessage());
		}
	}

	public Reuniao edit(Reuniao reuniao) throws BadRequestException {
		Reuniao r = reuniaoRepository.findById(reuniao.getId())
				.orElseThrow(() -> new BadRequestException(AgendamentoSalasEnum.MSG_ERRO.getTexto()));
		r.setExtraAgua(reuniao.getExtraAgua());
		r.setExtraBiscoito(reuniao.getExtraBiscoito());
		r.setExtraCafe(reuniao.getExtraCafe());
		r.setIdResponsavel(reuniao.getIdResponsavel());
		r.setIdSolicitante(reuniao.getIdSolicitante());
		r.setImpressora(reuniao.getImpressora());
		r.setProjetor(reuniao.getProjetor());
		r.setPublicoExterno(reuniao.getPublicoExterno());
		r.setPublicoInterno(reuniao.getPublicoInterno());
		r.setQtdNotebooks(reuniao.getQtdNotebooks());
		r.setQtdPessoas(reuniao.getQtdPessoas());
		r.setTema(reuniao.getTema());
		return reuniaoRepository.save(r);
	}

	public void delete(int id) throws BadRequestException {
		try {
			reuniaoRepository.deleteById(id);
		} catch (Exception e) {
			throw new BadRequestException(e.getMessage());
		}

	}
}

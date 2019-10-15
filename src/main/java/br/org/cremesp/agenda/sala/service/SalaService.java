package br.org.cremesp.agenda.sala.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import br.org.cremesp.agenda.sala.constantes.AgendamentoSalasEnum;
import br.org.cremesp.agenda.sala.entity.Sala;
import br.org.cremesp.agenda.sala.exception.BadRequestException;
import br.org.cremesp.agenda.sala.repository.SalaRepository;

@Service
public class SalaService {

	@Autowired
	private SalaRepository salaRepository;

	public List<Sala> getAll() {
		return salaRepository.findAllByOrderByIdAsc();
	}

	public Page<Sala> getAllPageable(Integer pagina, Integer tamanho) {
		return salaRepository.findAll(PageRequest.of(pagina, tamanho));
	}

	public Sala get(int id) throws BadRequestException {
		return salaRepository.findById(id)
				.orElseThrow(() -> new BadRequestException(AgendamentoSalasEnum.MSG_SALA_FIND_ERRO.getTexto()));
	}

	public List<Sala> getSalasBy(Integer qtdPessoas, Boolean impressora) {
		if (!impressora) {
			return salaRepository.findByQtdPessoasGreaterThanEqualOrderByQtdPessoasAsc(qtdPessoas);
		} else {
			return salaRepository.findByQtdPessoasGreaterThanEqualAndImpressoraOrderByQtdPessoasAsc(qtdPessoas,
					impressora);
		}
	}

	public Sala add(Sala sala) throws BadRequestException {
		try {
			return salaRepository.save(sala);
		} catch (Exception e) {
			throw new BadRequestException(AgendamentoSalasEnum.MSG_SALA_SAVE_ERRO.getTexto());
		}
	}

	public Sala edit(Sala sala) throws BadRequestException {
		Sala s = salaRepository.findById(sala.getId())
				.orElseThrow(() -> new BadRequestException(AgendamentoSalasEnum.MSG_SALA_UPDATE_ERRO.getTexto()));
		s.setNome(sala.getNome());
		s.setAndar(sala.getAndar());
		s.setQtdPessoas(sala.getQtdPessoas());
		s.setImpressora(sala.getImpressora());
		return salaRepository.save(s);
	}

	public void delete(int id) throws BadRequestException {
		try {
			salaRepository.deleteById(id);
		} catch (Exception e) {
			throw new BadRequestException(AgendamentoSalasEnum.MSG_SALA_DELETE_ERRO.getTexto());
		}

	}
}

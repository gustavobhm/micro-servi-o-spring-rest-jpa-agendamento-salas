package br.org.cremesp.agenda.sala.dto;

import java.util.Date;

import org.modelmapper.ModelMapper;

import br.org.cremesp.agenda.sala.entity.Reuniao;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ReuniaoDTO {

	private Integer id;

	private Integer idSolicitante;

	private String responsavel;

	private String tema;

	private Integer qtdPessoas;

	private String publico;

	private Boolean projetor;

	private Boolean impressora;

	private Boolean extraAgua;

	private Boolean extraCafe;

	private Boolean extraBiscoito;

	private Integer qtdNotebooks;

	private Date dataCriacao;

	public Reuniao convertToEntity() {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(this, Reuniao.class);
	}

}

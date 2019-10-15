package br.org.cremesp.agenda.sala.dto;

import org.modelmapper.ModelMapper;

import br.org.cremesp.agenda.sala.entity.Sala;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class SalaDTO {

	private Integer id;

	private String nome;

	private String andar;

	private Integer qtdPessoas;

	private Boolean impressora;

	private Boolean computador;

	public Sala convertToEntity() {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(this, Sala.class);
	}

}

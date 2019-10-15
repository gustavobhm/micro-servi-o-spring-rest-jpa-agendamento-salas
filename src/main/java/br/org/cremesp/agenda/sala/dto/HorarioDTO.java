package br.org.cremesp.agenda.sala.dto;

import org.modelmapper.ModelMapper;

import br.org.cremesp.agenda.sala.entity.Horario;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class HorarioDTO {

	private Integer id;

	private String hora;

	public Horario convertToEntity() {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(this, Horario.class);
	}

}

package br.org.cremesp.agenda.sala.dto;

import java.util.Date;

import org.modelmapper.ModelMapper;

import br.org.cremesp.agenda.sala.entity.Horario;
import br.org.cremesp.agenda.sala.entity.Reserva;
import br.org.cremesp.agenda.sala.entity.Reuniao;
import br.org.cremesp.agenda.sala.entity.Sala;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ReservaDTO {

	private Integer id;

	private Reuniao reuniao;

	private Date data;

	private Sala sala;

	private Horario horario;

	public Reserva convertToEntity() {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(this, Reserva.class);
	}

}

package br.org.cremesp.agenda.sala.projection;

import java.util.Date;

import br.org.cremesp.agenda.sala.entity.Sala;

public interface ReservaByReuniaoView {
	Date getData();

	Sala getSala();
}

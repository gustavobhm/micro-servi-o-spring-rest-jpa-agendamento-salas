package br.org.cremesp.agenda.sala.fixture;

import java.text.SimpleDateFormat;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import br.org.cremesp.agenda.sala.common.DataUtils;
import br.org.cremesp.agenda.sala.entity.Horario;
import br.org.cremesp.agenda.sala.entity.Reserva;
import br.org.cremesp.agenda.sala.entity.Reuniao;
import br.org.cremesp.agenda.sala.entity.Sala;

public class ReservaFixture implements TemplateLoader {

	public static final String VALID = "valid";
	public static final String VALID_RESERVA_1 = "valid_reserva_1";
	public static final String VALID_RESERVA_2 = "valid_reserva_2";

	@Override
	public void load() {
		Fixture.of(Reserva.class).addTemplate(VALID, new Rule() {
			{
				add("id", uniqueRandom(3, 100));
				add("reuniao", one(Reuniao.class, ReuniaoFixture.VALID_REUNIAO_1));
				add("data", randomDate("2020-01-01", "2020-12-31", new SimpleDateFormat("yyyy-MM-dd")));
				add("sala", one(Sala.class, SalaFixture.VALID_SALA_1));
				add("horario", one(Horario.class, HorarioFixture.VALID_HORARIO_1));
				add("cafe", random(Boolean.class));
			}
		});

		Fixture.of(Reserva.class).addTemplate(VALID_RESERVA_1, new Rule() {
			{
				add("id", 1);
				add("id", uniqueRandom(3, 100));
				add("reuniao", one(Reuniao.class, ReuniaoFixture.VALID_REUNIAO_1));
				add("data", DataUtils.newDateWithFormat("2019-09-12"));
				add("sala", one(Sala.class, SalaFixture.VALID_SALA_1));
				add("horario", one(Horario.class, HorarioFixture.VALID_HORARIO_1));
				add("cafe", true);
			}
		});

		Fixture.of(Reserva.class).addTemplate(VALID_RESERVA_2, new Rule() {
			{
				add("id", 2);
				add("reuniao", one(Reuniao.class, ReuniaoFixture.VALID_REUNIAO_2));
				add("data", DataUtils.newDateWithFormat("2019-09-12"));
				add("sala", one(Sala.class, SalaFixture.VALID_SALA_2));
				add("horario", one(Horario.class, HorarioFixture.VALID_HORARIO_2));
				add("cafe", false);
			}
		});

	}

}

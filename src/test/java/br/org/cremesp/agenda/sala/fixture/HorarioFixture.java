package br.org.cremesp.agenda.sala.fixture;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import br.org.cremesp.agenda.sala.entity.Horario;

public class HorarioFixture implements TemplateLoader {

	public static final String VALID = "valid";
	public static final String VALID_HORARIO_1 = "valid_horario_1";
	public static final String VALID_HORARIO_2 = "valid_horario_2";

	@Override
	public void load() {
		Fixture.of(Horario.class).addTemplate(VALID, new Rule() {
			{
				add("id", uniqueRandom(3, 100));
				add("hora", uniqueRandom("10:00 - 11:00", "11:00 - 12:00", "12:00 - 13:00", "13:00 - 14:00"));
			}
		});

		Fixture.of(Horario.class).addTemplate(VALID_HORARIO_1, new Rule() {
			{
				add("id", 1);
				add("hora", "08:00 - 09:00");
			}
		});

		Fixture.of(Horario.class).addTemplate(VALID_HORARIO_2, new Rule() {
			{
				add("id", 2);
				add("hora", "09:00 - 10:00");
			}
		});

	}

}

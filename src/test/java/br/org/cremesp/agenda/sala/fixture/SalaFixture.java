package br.org.cremesp.agenda.sala.fixture;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import br.org.cremesp.agenda.sala.entity.Sala;

public class SalaFixture implements TemplateLoader {

	public static final String VALID = "valid";
	public static final String VALID_SALA_1 = "valid_sala_1";
	public static final String VALID_SALA_2 = "valid_sala_2";

	@Override
	public void load() {
		Fixture.of(Sala.class).addTemplate(VALID, new Rule() {
			{
				add("id", uniqueRandom(3, 100));
				add("nome", uniqueRandom("Sala 3", "Sala 4", "Sala 5", "Sala 6", "Sala 7"));
				add("andar", regex("[A-Z]{1}[A-Z a-z]{9,29}"));
				add("qtdPessoas", random(Integer.class, range(1, 10)));
				add("impressora", random(Boolean.class));
				add("computador", random(Boolean.class));
				add("ativa", random(Boolean.class));
			}
		});

		Fixture.of(Sala.class).addTemplate(VALID_SALA_1, new Rule() {
			{
				add("id", 1);
				add("nome", "Sala 1");
				add("andar", "1º");
				add("qtdPessoas", 10);
				add("impressora", true);
				add("computador", true);
				add("ativa", true);
			}
		});

		Fixture.of(Sala.class).addTemplate(VALID_SALA_2, new Rule() {
			{
				add("id", 2);
				add("nome", "Sala 2");
				add("andar", "2º");
				add("qtdPessoas", 20);
				add("impressora", false);
				add("computador", false);
				add("ativa", false);
			}
		});

	}

}

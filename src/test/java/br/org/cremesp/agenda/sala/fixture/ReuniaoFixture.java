package br.org.cremesp.agenda.sala.fixture;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import br.org.cremesp.agenda.sala.constantes.PublicoEnum;
import br.org.cremesp.agenda.sala.entity.Reuniao;

public class ReuniaoFixture implements TemplateLoader {

	public static final String VALID = "valid";
	public static final String VALID_REUNIAO_1 = "valid_reuniao_1";
	public static final String VALID_REUNIAO_2 = "valid_reuniao_2";

	@Override
	public void load() {
		Fixture.of(Reuniao.class).addTemplate(VALID, new Rule() {
			{
				add("id", uniqueRandom(3, 100));
				add("idSolicitante", random(Integer.class, range(1, 100)));
				add("responsavel", regex("[A-Z]{1}[A-Z a-z]{9,29}"));
				add("tema", regex("[A-Z]{1}[A-Z a-z]{9,29}"));
				add("qtdPessoas", random(Integer.class, range(1, 100)));
				add("publico", random(PublicoEnum.EXTERNO.getTexto(), PublicoEnum.INTERNO.getTexto()));
				add("projetor", random(Boolean.class));
				add("impressora", random(Boolean.class));
				add("extraAgua", random(Boolean.class));
				add("extraCafe", random(Boolean.class));
				add("extraBiscoito", random(Boolean.class));
				add("qtdNotebooks", random(Integer.class, range(1, 100)));
			}
		});

		Fixture.of(Reuniao.class).addTemplate(VALID_REUNIAO_1, new Rule() {
			{
				add("id", 1);
				add("idSolicitante", 2);
				add("responsavel", "Responsável 1");
				add("tema", "Reunião 1");
				add("qtdPessoas", 10);
				add("publico", PublicoEnum.INTERNO.getTexto());
				add("projetor", true);
				add("impressora", true);
				add("extraAgua", true);
				add("extraCafe", true);
				add("extraBiscoito", true);
				add("qtdNotebooks", 10);
			}
		});

		Fixture.of(Reuniao.class).addTemplate(VALID_REUNIAO_2, new Rule() {
			{
				add("id", 2);
				add("idSolicitante", 4);
				add("responsavel", "Responsável 2");
				add("tema", "Reunião 2");
				add("qtdPessoas", 20);
				add("publico", PublicoEnum.EXTERNO.getTexto());
				add("projetor", true);
				add("impressora", true);
				add("extraAgua", false);
				add("extraCafe", true);
				add("extraBiscoito", false);
				add("qtdNotebooks", 20);
			}
		});

	}

}

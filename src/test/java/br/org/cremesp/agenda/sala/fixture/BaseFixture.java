package br.org.cremesp.agenda.sala.fixture;

import org.junit.BeforeClass;

import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;

public class BaseFixture {

	protected static String FIXTURES_PATH = "br.org.cremesp.agenda.sala.fixture";

	@BeforeClass
	public static void setUp() {
		FixtureFactoryLoader.loadTemplates(FIXTURES_PATH);
	}

}

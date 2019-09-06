package br.org.cremesp.agenda.sala;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class AgendamentoSalasApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(AgendamentoSalasApplication.class, args);
	}

}

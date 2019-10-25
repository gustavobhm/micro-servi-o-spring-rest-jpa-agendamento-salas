package br.org.cremesp.agenda.sala;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class Application extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2) //
				.select() //
				.apis(RequestHandlerSelectors.any()) //
				.paths(PathSelectors.any()) //
				.build() //
				.apiInfo(apiInfo()) //
				.tags(new Tag("API Sala", "Métodos para sala") //
						, new Tag("API Horario", "Métodos para horário") //
						, new Tag("API Reserva", "Métodos para reserva") //
						, new Tag("API Reuniao", "Métodos para reunião"));
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("API - Agendamento de Salas") //
				.description("APIs para agendamento de salas do Cremesp") //
				.version("1.0.0") //
				.license("") //
				.licenseUrl("") //
				.contact(new Contact("Cremesp", "https://www.cremesp.org.br", "contato@cremesp.org.br")) //
				.build();
	}

}

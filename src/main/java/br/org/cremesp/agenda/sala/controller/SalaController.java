package br.org.cremesp.agenda.sala.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.org.cremesp.agenda.sala.entity.Sala;
import br.org.cremesp.agenda.sala.exception.BadRequestException;
import br.org.cremesp.agenda.sala.service.SalaService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/salas")
public class SalaController {

	@Autowired
	private SalaService salaService;

	@GetMapping
	public List<Sala> getAll() {
		return salaService.getAll();
	}

	@GetMapping("/{id}")
	public Sala get(@PathVariable int id) throws BadRequestException {
		return salaService.get(id);
	}

	@GetMapping("/filtrar")
	public List<Sala> getReservasBy( //
			@RequestParam Integer qtdPessoas, //
			@RequestParam Boolean impressora) {
		return salaService.getSalasBy(qtdPessoas, impressora);
	}

	@PostMapping
	public Sala add(@RequestBody Sala sala) throws BadRequestException {
		return salaService.add(sala);
	}

	@PutMapping
	public Sala edit(@RequestBody Sala sala) throws BadRequestException {
		return salaService.edit(sala);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable int id) throws BadRequestException {
		salaService.delete(id);
	}
}

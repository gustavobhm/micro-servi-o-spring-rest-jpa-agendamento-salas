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
import org.springframework.web.bind.annotation.RestController;

import br.org.cremesp.agenda.sala.entity.Agendamento;
import br.org.cremesp.agenda.sala.exception.BadRequestException;
import br.org.cremesp.agenda.sala.service.AgendamentoService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/")
public class AgendamentoController {

	@Autowired
	private AgendamentoService agendamentoService;

	@GetMapping
	public List<Agendamento> getAll() {
		return agendamentoService.getAll();
	}

	@GetMapping("/{id}")
	public Agendamento get(@PathVariable int id) throws BadRequestException {
		return agendamentoService.get(id);
	}

	@PostMapping
	public Agendamento add(@RequestBody Agendamento agendamento) throws BadRequestException {
		return agendamentoService.add(agendamento);
	}

	@PutMapping
	public Agendamento edit(@RequestBody Agendamento agendamento) throws BadRequestException {
		return agendamentoService.edit(agendamento);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable int id) throws BadRequestException {
		agendamentoService.delete(id);
	}
}

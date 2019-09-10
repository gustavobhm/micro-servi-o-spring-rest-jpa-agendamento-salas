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

import br.org.cremesp.agenda.sala.entity.Horario;
import br.org.cremesp.agenda.sala.exception.BadRequestException;
import br.org.cremesp.agenda.sala.service.HorarioService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/horarios")
public class HorarioController {

	@Autowired
	private HorarioService horarioService;

	@GetMapping
	public List<Horario> getAll() {
		return horarioService.getAll();
	}

	@GetMapping("/{id}")
	public Horario get(@PathVariable int id) throws BadRequestException {
		return horarioService.get(id);
	}

	@PostMapping
	public Horario add(@RequestBody Horario horario) throws BadRequestException {
		return horarioService.add(horario);
	}

	@PutMapping
	public Horario edit(@RequestBody Horario horario) throws BadRequestException {
		return horarioService.edit(horario);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable int id) throws BadRequestException {
		horarioService.delete(id);
	}
}
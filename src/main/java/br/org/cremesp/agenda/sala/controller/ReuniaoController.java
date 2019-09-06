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

import br.org.cremesp.agenda.sala.entity.Reuniao;
import br.org.cremesp.agenda.sala.exception.BadRequestException;
import br.org.cremesp.agenda.sala.service.ReuniaoService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/reunioes")
public class ReuniaoController {

	@Autowired
	private ReuniaoService reuniaoService;

	@GetMapping
	public List<Reuniao> getAll() {
		return reuniaoService.getAll();
	}

	@GetMapping("/{id}")
	public Reuniao get(@PathVariable int id) throws BadRequestException {
		return reuniaoService.get(id);
	}

	@PostMapping
	public Reuniao add(@RequestBody Reuniao reuniao) throws BadRequestException {
		return reuniaoService.add(reuniao);
	}

	@PutMapping
	public Reuniao edit(@RequestBody Reuniao reuniao) throws BadRequestException {
		return reuniaoService.edit(reuniao);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable int id) throws BadRequestException {
		reuniaoService.delete(id);
	}
}

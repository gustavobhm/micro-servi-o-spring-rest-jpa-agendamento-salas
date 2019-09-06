package br.org.cremesp.agenda.sala.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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

import br.org.cremesp.agenda.sala.entity.ReservaDataSala;
import br.org.cremesp.agenda.sala.exception.BadRequestException;
import br.org.cremesp.agenda.sala.service.ReservaDataSalaService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/reservas-data-sala")
public class ReservaDataSalaController {

	@Autowired
	private ReservaDataSalaService reservaDataSalaService;

	@GetMapping
	public List<ReservaDataSala> getAll() {
		return reservaDataSalaService.getAll();
	}

	@GetMapping("/{id}")
	public ReservaDataSala get(@PathVariable int id) throws BadRequestException {
		return reservaDataSalaService.get(id);
	}

	@GetMapping("/filtrar")
	public List<ReservaDataSala> getReservaDataSalasBy( //
			@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date data, //
			@RequestParam Integer qtdPessoas, //
			@RequestParam Boolean impressora) //
			throws BadRequestException {
		return reservaDataSalaService.getReservasDataSalaBy(data, qtdPessoas, impressora);
	}

	@PostMapping
	public ReservaDataSala add(@RequestBody ReservaDataSala reservaDataSala) throws BadRequestException {
		return reservaDataSalaService.add(reservaDataSala);
	}

	@PutMapping
	public ReservaDataSala edit(@RequestBody ReservaDataSala reservaDataSala) throws BadRequestException {
		return reservaDataSalaService.edit(reservaDataSala);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable int id) throws BadRequestException {
		reservaDataSalaService.delete(id);
	}
}

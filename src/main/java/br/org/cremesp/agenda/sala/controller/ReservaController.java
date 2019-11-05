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

import br.org.cremesp.agenda.sala.dto.ReservaDTO;
import br.org.cremesp.agenda.sala.entity.Reserva;
import br.org.cremesp.agenda.sala.exception.BadRequestException;
import br.org.cremesp.agenda.sala.projection.ReservaByReuniaoView;
import br.org.cremesp.agenda.sala.service.ReservaService;
import io.swagger.annotations.Api;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/reservas")
@Api(tags = { "API Reserva" })
public class ReservaController {

	@Autowired
	private ReservaService reservaService;

	@GetMapping
	public List<Reserva> getAll() {
		return reservaService.getAll();
	}

	@GetMapping("/{id}")
	public Reserva get(@PathVariable int id) throws BadRequestException {
		return reservaService.get(id);
	}

	@GetMapping("/reuniao/{idReuniao}")
	public List<ReservaByReuniaoView> getReservasByReuniao(@PathVariable int idReuniao) {
		return reservaService.getReservasByReuniao(idReuniao);
	}
	
	@GetMapping("/reuniao/{idReuniao}/tomorrow")
	public List<ReservaByReuniaoView> getReservasByReuniaoFromTomorrow(@PathVariable int idReuniao) {
		return reservaService.getReservasByReuniaoFromTomorrow(idReuniao);
	}

	@GetMapping("/filtrar")
	public List<Reserva> getReservasBy( //
			@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date data, //
			@RequestParam Integer idSala) {
		return reservaService.getReservasBy(data, idSala);
	}

	@PostMapping
	public Reserva add(@RequestBody ReservaDTO reservaDTO) throws BadRequestException {
		return reservaService.add(reservaDTO.convertToEntity());
	}

	@PutMapping
	public Reserva edit(@RequestBody ReservaDTO reservaDTO) throws BadRequestException {
		return reservaService.edit(reservaDTO.convertToEntity());
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable int id) throws BadRequestException {
		reservaService.delete(id);
	}

	@DeleteMapping("/reuniao/{idReuniao}")
	public void deleteReservasByReuniao(@PathVariable int idReuniao) throws BadRequestException {
		reservaService.deleteReservasByReuniao(idReuniao);
	}

}

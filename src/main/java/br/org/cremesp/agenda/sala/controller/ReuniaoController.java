package br.org.cremesp.agenda.sala.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

import br.org.cremesp.agenda.sala.dto.ReuniaoDTO;
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
	public Page<Reuniao> getAll(//
			@RequestParam(defaultValue = "0", name = "pagina") int pagina, //
			@RequestParam(defaultValue = "5", name = "tamanho") int tamanho) {
		return reuniaoService.getAll(pagina, tamanho);
	}

	@GetMapping("/{id}")
	public Reuniao get(@PathVariable int id) throws BadRequestException {
		return reuniaoService.get(id);
	}

	@GetMapping("/filtrar")
	public Page<Reuniao> getReservasBy( //
			@RequestParam Integer idSolicitante, //
			@RequestParam(defaultValue = "0", name = "pagina") int pagina, //
			@RequestParam(defaultValue = "5", name = "tamanho") int tamanho) {
		return reuniaoService.getReunioesBy(idSolicitante, pagina, tamanho);
	}

	@PostMapping
	public Reuniao add(@RequestBody ReuniaoDTO reuniaoDTO) {
		return reuniaoService.add(reuniaoDTO.convertToEntity());
	}

	@PutMapping
	public Reuniao edit(@RequestBody ReuniaoDTO reuniaoDTO) throws BadRequestException {
		return reuniaoService.edit(reuniaoDTO.convertToEntity());
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable int id) throws BadRequestException {
		reuniaoService.delete(id);
	}
}

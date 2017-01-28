package br.com.posweb.serviceusuario.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.posweb.serviceusuario.entity.Reserva;
import br.com.posweb.serviceusuario.service.ReservaService;

@RestController
public class ReservaController {

	@Autowired
	private ReservaService reservaService;
	
	@RequestMapping(value="/agenda/reserva", method = RequestMethod.POST)
	public ResponseEntity<String> cadastroUsuario(@RequestParam Integer codigoReserva, @RequestParam Integer codigoUsuario) {
		try {
			reservaService.reservar(codigoReserva, codigoUsuario);
			return new ResponseEntity<String>(HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value = "/reservas", method = RequestMethod.GET)
	public ResponseEntity<List<Reserva>> listAllReservas() {
		return new ResponseEntity< List<Reserva> >(reservaService.listAllReservas(), HttpStatus.OK);
	}
	
}

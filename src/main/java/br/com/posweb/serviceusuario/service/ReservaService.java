package br.com.posweb.serviceusuario.service;

import java.util.List;

import br.com.posweb.serviceusuario.entity.Reserva;

public interface ReservaService {
	
	void reservar(Integer codigoReserva, Integer codigoUsuario) throws Exception;
	
	List<Reserva> listAllReservas();

}

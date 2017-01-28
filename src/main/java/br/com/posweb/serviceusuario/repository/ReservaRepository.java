package br.com.posweb.serviceusuario.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import br.com.posweb.serviceusuario.entity.Reserva;

public interface ReservaRepository extends CrudRepository<Reserva, Integer> {

	@Query("SELECT r FROM Reserva r")
	List<Reserva> listAllReservas();

}

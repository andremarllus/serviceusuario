package br.com.posweb.serviceusuario.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import br.com.posweb.serviceusuario.entity.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, Integer> {

	@Query("SELECT u FROM Usuario u")
	List<Usuario> listAllUsuarios();
}

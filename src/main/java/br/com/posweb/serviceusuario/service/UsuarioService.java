package br.com.posweb.serviceusuario.service;

import java.util.List;

import br.com.posweb.serviceusuario.entity.Usuario;

public interface UsuarioService {

	void save(Usuario usuario);
	
	List<Usuario> listAllUsuarios();
	
}

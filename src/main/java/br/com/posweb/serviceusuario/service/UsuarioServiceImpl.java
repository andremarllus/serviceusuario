package br.com.posweb.serviceusuario.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.posweb.serviceusuario.entity.Usuario;
import br.com.posweb.serviceusuario.repository.UsuarioRepository;

@Service
public class UsuarioServiceImpl implements UsuarioService{

	@Autowired
	private UsuarioRepository usuarioRepository;

	public void save(Usuario usuario) {
		usuarioRepository.save(usuario);
	}

	public List<Usuario> listAllUsuarios() {
		return usuarioRepository.listAllUsuarios();
	}
	
}

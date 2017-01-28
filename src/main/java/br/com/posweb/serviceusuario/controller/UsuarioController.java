package br.com.posweb.serviceusuario.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.posweb.serviceusuario.entity.Usuario;
import br.com.posweb.serviceusuario.service.UsuarioServiceImpl;

@RestController
public class UsuarioController {
	
	@Autowired
	private UsuarioServiceImpl usuarioService;
	
	@RequestMapping(value="/usuario/cadastro", method = RequestMethod.POST)
	public ResponseEntity<String> cadastroUsuario(@RequestBody Usuario usuario) {
		try {
			usuarioService.save(usuario);
			return new ResponseEntity<String>(HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value = "/usuarios", method = RequestMethod.GET)
	public ResponseEntity<List<Usuario>> listAllUsuarios() {
		return new ResponseEntity< List<Usuario> >(usuarioService.listAllUsuarios(), HttpStatus.OK);
	}
}

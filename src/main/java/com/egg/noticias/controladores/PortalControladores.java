package com.egg.noticias.controladores;

import com.egg.noticias.entidades.Noticias;
import com.egg.noticias.excepciones.MiException;
import com.egg.noticias.servicio.NoticiaServicio;
import com.egg.noticias.servicio.UsuarioServicio;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class PortalControladores {

	@Autowired
	private UsuarioServicio usuarioServicio;

	@Autowired
	private NoticiaServicio noticiaServicio;

	@GetMapping("/cuerpo")
	public String cuerpo() {
		return "cuerpo.html";
	}

//	@GetMapping("/")
//	public String listar(ModelMap modelo) {
//		try {
//			List<Noticias> listNoticias = noticiaServicio.listarActivos();
//			modelo.addAttribute("listNoticias", listNoticias);
//		} catch (MiException ex) {
//			modelo.put("error", ex.getMessage());
//			return "admin.html";
//		}
//		return "index.html";
//	}

	@GetMapping("/cuerpo/{id}")
	public String cuerpo(@PathVariable Integer id, ModelMap modelo) throws MiException {
		System.out.println("traemos la noticia");
		noticiaServicio.obtenerNoticiaId(id);

		return "/cuerpo.html";
	}

	@GetMapping("/registrar")
	public String registrar() {
		return "registro.html";
	}

	@PostMapping("/registro")
	public String registro(@RequestParam String nombre, @RequestParam String email, @RequestParam String password,
			@RequestParam String password2, ModelMap modelo) {

		try {
			usuarioServicio.registrar(nombre, email, password, password2);

			modelo.put("exito", "Usuario registrado con exito");

			return "/index.html";

		} catch (MiException e) {

			modelo.put("error", e.getMessage());
			modelo.put("nombre", nombre);
			modelo.put("email", email);

			return "/registrar.html";
		}

	}

	@GetMapping("/login")
	public String login(@RequestParam(required = false) String error, ModelMap modelo) {
		if (error != null) {
			modelo.put("error", "Usuario o contraseña invalido");
		}
		return "login.html";
	}

	@PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN', 'ROLE_PERIODISTA')")
	@GetMapping("/inicio")
	public String iniciListar(ModelMap modelo) {
		try {
			List<Noticias> listNoticias = noticiaServicio.listarActivos();
			modelo.addAttribute("listNoticias", listNoticias);
		} catch (MiException ex) {
			modelo.put("error", ex.getMessage());
			return "admin.html";
		}
		return "inicio.html";
	}
}

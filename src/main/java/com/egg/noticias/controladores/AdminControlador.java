package com.egg.noticias.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminControlador {
	
	@GetMapping("/dsahboard")
	public String panelAdministrativo() {
		return "panel.html";
	}

}

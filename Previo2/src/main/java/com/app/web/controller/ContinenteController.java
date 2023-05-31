package com.app.web.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.app.web.entities.Continente;
import com.app.web.repository.ContinenteRepository;

@Controller
public class ContinenteController {
	@Autowired
	private ContinenteRepository continenteRepository;
		
		@GetMapping({"/continentes","/continentes/registrar"})
		public String mostrarFormulario(Model modelo) {
			Continente continente= new Continente();
			modelo.addAttribute("continente",continente);
			return "formulario_continente";
		}
		@PostMapping({ "/continentes" })
		public String guardarContinente(Continente continente) {
			continenteRepository.save(continente);
			return "redirect:/selecciones";
		}
}

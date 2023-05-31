package com.app.web.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.app.web.entities.Continente;
import com.app.web.entities.Seleccion;
import com.app.web.repository.ContinenteRepository;



@Controller
public class ContinenteController {
	@Autowired
	private ContinenteRepository continenteRepository;
    
    @GetMapping({"/","/continentes","/continentes/listar"})
	public String listar(Model model) {
		List<Continente> continentes = continenteRepository.findAll();
		model.addAttribute("continentes", continentes);
		return "index";
	}
	
	@GetMapping("/continentes/crear")
	public String mostrarFormulario(Model modelo) {
		Continente continente = new Continente();
		modelo.addAttribute("continente", continente);
		return "formulario_continente";
	}

	@GetMapping("/continentes/ver/{id}")
	public String mostrarVerContinente(@PathVariable Integer id, Model modelo) {
		Continente continente = continenteRepository.findById(id).get();
		modelo.addAttribute("continente", continente);
		List<Seleccion> selecciones = continente.getSelecciones();
		modelo.addAttribute("selecciones",selecciones);
		return "ver_continente";
	}

	@PostMapping({ "/continentes", "/continentes/listar" })
	public String guardarContinente(Continente continente) {
		continenteRepository.save(continente);
		return "redirect:/continentes";
	}

	@GetMapping("/continentes/editar/{id}")
	public String mostrarFormularioEditar(@PathVariable Integer id, Model modelo) {
		Continente continente =  continenteRepository.findById(id).get();
		modelo.addAttribute("continente", continente);
		return "formulario_continente";
	}

	@GetMapping("/continentes/{id}")
	public String eliminarContinente(@PathVariable Integer id, Model modelo) {
		continenteRepository.deleteById(id);
		return "redirect:/continentes";
	}
	@GetMapping("/verContinente")
	public String verContinente() {
		return "redirect:/continentes";
	}
}

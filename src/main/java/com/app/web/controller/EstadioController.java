package com.app.web.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import com.app.web.entities.Estadio;
import com.app.web.repository.EstadioRepository;

@Controller
public class EstadioController {
	@Autowired
	private EstadioRepository estadioRepository;
    
    @GetMapping({"/estadios","/estadios/listar"})
	public String listar(Model model) {
		List<Estadio> estadios = estadioRepository.findAll();
		model.addAttribute("estadios", estadios);
		return "estadios_index";
	}
	
	@GetMapping("/estadios/crear")
	public String mostrarFormulario(Model modelo) {
		Estadio estadio = new Estadio();
		modelo.addAttribute("estadio", estadio);
		return "formulario_estadio";
	}

	@GetMapping("/estadios/ver/{id}")
	public String mostrarVerEstadio(@PathVariable Integer id, Model modelo) {
		Estadio estadio = estadioRepository.findById(id).get();
		modelo.addAttribute("estadio", estadio);
		return "ver_estadio";
	}

	@PostMapping({ "/estadios", "/estadios/listar" })
	public String guardarContinente(Estadio estadio) {
		estadioRepository.save(estadio);
		return "redirect:/estadios";
	}

	@GetMapping("/estadios/editar/{id}")
	public String mostrarFormularioEditar(@PathVariable Integer id, Model modelo) {
		Estadio estadio =  estadioRepository.findById(id).get();
		modelo.addAttribute("estadio", estadio);
		return "formulario_estadio";
	}

	@GetMapping("/estadios/{id}")
	public String eliminarContinente(@PathVariable Integer id, Model modelo) {
		estadioRepository.deleteById(id);
		return "redirect:/estadios";
	}
	@GetMapping("/verEstadio")
	public String verEstadio() {
		return "redirect:/estadios";
	}
}

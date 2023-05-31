package com.app.web.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.app.web.entities.Partido;
import com.app.web.entities.Estadio;
import com.app.web.repository.EstadioRepository;
import com.app.web.repository.PartidoRepository;

@Controller
public class PartidoController {
	
	@Autowired
	private PartidoRepository partidoRepository;
	
	@Autowired
	private EstadioRepository estadioRepository;
    
    @GetMapping({"/partidos","/partidos/listar"})
	public String listar(Model modelo) {
		List<Partido> partidos = partidoRepository.findAll();
		modelo.addAttribute("partidos",partidos);
		return "partidos_index";
	}
	
	@GetMapping("/partidos/crear")
	public String mostrarFormulario(Model modelo) {
		Partido partido = new Partido();
		modelo.addAttribute("partido", partido);
		List<Estadio> listaEstadios = estadioRepository.findAll();
		modelo.addAttribute("listaEstadios",listaEstadios);
		return "formulario_partido";
	}
	
	@PostMapping({ "/partidos", "/partidos/listar" })
	public String guardarPartido(Partido partido) {
		partidoRepository.save(partido);
		return "redirect:/partidos";
	}


	@GetMapping("/partidos/ver/{id}")
	public String mostrarVerPartido(@PathVariable Integer id, Model modelo) {
		Partido partido = partidoRepository.findById(id).get();
		modelo.addAttribute("partido", partido);
		return "ver_partido";
	}

	@GetMapping("/partidos/editar/{id}")
	public String mostrarFormularioEditar(@PathVariable Integer id, Model modelo) {
		Partido partido  =  partidoRepository.findById(id).get();
		modelo.addAttribute("partido",partido);
		List<Estadio> listaEstadios = estadioRepository.findAll();
		modelo.addAttribute("listaEstadios", listaEstadios);
		return "editar_partido";
	}

	@PostMapping("/partidos/{id}")
	public String actualizarPartido(@PathVariable Integer id, @ModelAttribute("partido") Partido partido ,
			Model modelo) {
		Partido partidoExistente = partidoRepository.findById(id).get();
		partidoExistente.setId(partido.getId());
		partidoExistente.setFecha(partido.getFecha());
		partidoExistente.setEstadio(partido.getEstadio());
		partidoRepository.save(partidoExistente);
		return "redirect:/partidos";
	}
	@GetMapping("/partidos/{id}")
	public String eliminarPartido(@PathVariable Integer id, Model modelo) {
		partidoRepository.deleteById(id);
		return "redirect:/partidos";
	}
	@GetMapping("/verPartidos")
	public String verPartido() {
		return "redirect:/partidos";
	}
}

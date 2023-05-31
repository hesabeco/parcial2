package com.app.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.app.web.entities.Continente;
import com.app.web.entities.Resultado;
import com.app.web.entities.Seleccion;
import com.app.web.repository.ContinenteRepository;
import com.app.web.repository.SeleccionRepository;

@Controller
public class SeleccionController {
	
	@Autowired
	private SeleccionRepository seleccionRepository;
	
	@Autowired
	private ContinenteRepository continenteRepository;
    
    @GetMapping({"/selecciones","/selecciones/listar"})
	public String listar(Model model) {
		List<Seleccion> selecciones = seleccionRepository.findAll();
		model.addAttribute("selecciones", selecciones);
		return "selecciones_index";
	}
	
	@GetMapping("/selecciones/crear")
	public String mostrarFormulario(Model modelo) {
		Seleccion seleccion = new Seleccion();
		modelo.addAttribute("seleccion", seleccion);
		List<Continente> listaContinentes = continenteRepository.findAll();
		modelo.addAttribute("listaContinentes", listaContinentes);
		return "formulario_seleccion";
	}
	
	@PostMapping({ "/selecciones", "/selecciones/listar" })
	public String guardarSeleccion(Seleccion seleccion) {
		seleccionRepository.save(seleccion);
		return "redirect:/selecciones";
	}


	@GetMapping("/selecciones/ver/{id}")
	public String mostrarVerSeleccion(@PathVariable Integer id, Model modelo) {
		Seleccion seleccion = seleccionRepository.findById(id).get();
		modelo.addAttribute("seleccion", seleccion);
		List<Resultado> resultados = seleccion.getResultados();
		modelo.addAttribute("resultados",resultados);
		return "ver_seleccion";
	}

	@GetMapping("/selecciones/editar/{id}")
	public String mostrarFormularioEditar(@PathVariable Integer id, Model modelo) {
		Seleccion seleccion =  seleccionRepository.findById(id).get();
		modelo.addAttribute("seleccion", seleccion);
		List<Continente> listaContinentes = continenteRepository.findAll();
		modelo.addAttribute("listaContinentes", listaContinentes);
		return "editar_seleccion";
	}

	@PostMapping("/selecciones/{id}")
	public String actualizarSeleccion(@PathVariable Integer id, @ModelAttribute("seleccion") Seleccion seleccion,
			Model modelo) {
		Seleccion seleccionExistente = seleccionRepository.findById(id).get();
		seleccionExistente.setId(seleccion.getId());
		seleccionExistente.setNombre(seleccion.getNombre());
		seleccionExistente.setGrupo(seleccion.getGrupo());
		seleccionExistente.setContinente(seleccion.getContinente());
		seleccionRepository.save(seleccionExistente);
		return "redirect:/selecciones";
	}
	@GetMapping("/selecciones/{id}")
	public String eliminarSeleccion(@PathVariable Integer id, Model modelo) {
		seleccionRepository.deleteById(id);
		return "redirect:/selecciones";
	}
	@GetMapping("/verSelecciones")
	public String verSeleccion() {
		return "redirect:/selecciones";
	}
	
}

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
import com.app.web.repository.SeleccionRepository;


@Controller
public class SeleccionController {
	@Autowired
	private SeleccionRepository seleccionRepository;
	
	@Autowired
    private ContinenteRepository continenteRepository;
	
	
	 @GetMapping({"/seleciones","/selecciones/listar"})
		public String listar(Model model) {
			List<Seleccion> seleccion = seleccionRepository.findAll();
			model.addAttribute("seleccion", seleccion);
			return "index";
		}
		
		@GetMapping({"/selecciones/crear"})
		public String mostrarFormulario(Model modelo) {
			List<Continente> listaContinentes = continenteRepository.findAll();
			System.out.println(listaContinentes);
			Seleccion seleccion = new Seleccion();
			modelo.addAttribute("seleccion", seleccion);
	        modelo.addAttribute("listaContinentes", listaContinentes);
			return "formulario_seleccion";
		}

		@GetMapping("/selecciones/ver/{id}")
		public String mostrarVerSeleccion(@PathVariable Integer id, Model modelo) {
			Seleccion seleccion = seleccionRepository.findById(id).get();
			modelo.addAttribute("seleccion", seleccion);
			return "ver_seleccion";
		}

		@PostMapping({ "/selecciones", "/selecciones/listar" })
		public String guardarSeleccion(Seleccion seleccion) {
			seleccionRepository.save(seleccion);
			return "redirect:/selecciones";
		}

		@GetMapping("/selecciones/editar/{id}")
		public String mostrarFormularioEditar(@PathVariable Integer id, Model modelo) {
			Seleccion seleccion =  seleccionRepository.findById(id).get();
			modelo.addAttribute("seleccion", seleccion);
			return "formulario_seleccion";
		}

		@GetMapping("/selecciones/{id}")
		public String eliminarSeleccion(@PathVariable Integer id, Model modelo) {
			seleccionRepository.deleteById(id);
			return "redirect:/selecciones";
		}
		@GetMapping("/verSeleccion")
		public String verSeleccion() {
			return "redirect:/selecciones";
		}
}

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
import com.app.web.entities.Resultado;
import com.app.web.entities.Seleccion;
import com.app.web.repository.PartidoRepository;
import com.app.web.repository.ResultadoRepository;
import com.app.web.repository.SeleccionRepository;
@Controller
public class ResultadosController {
	@Autowired
	private ResultadoRepository resultadoRepository;
	
	@Autowired
	private PartidoRepository partidoRepository;
	
	@Autowired
	private SeleccionRepository seleccionRepository;
    
    @GetMapping({"/resultados","/resultados/listar"})
	public String listar(Model model) {
		List<Resultado> resultados = resultadoRepository.findAll();
		model.addAttribute("resultados", resultados);
		return "resultados_index";
	}
	
	@GetMapping("/resultados/crear")
	public String mostrarFormulario(Model modelo) {
		Resultado resultado = new Resultado();
		modelo.addAttribute("resultado", resultado);
		List<Partido> listaPartidos = partidoRepository.findAll();
		modelo.addAttribute("listaPartidos", listaPartidos);
		List<Seleccion> listaSelecciones = seleccionRepository.findAll();
		modelo.addAttribute("listaSelecciones", listaSelecciones);
		return "formulario_resultado";
	}
	
	@PostMapping({ "/resultados", "/resultados/listar" })
	public String guardarResultado(Resultado resultado) {
		resultadoRepository.save(resultado);
		return "redirect:/resultados";
	}


	@GetMapping("/resultados/ver/{id}")
	public String mostrarVerResultado(@PathVariable Integer id, Model modelo) {
		Resultado resultado =  resultadoRepository.findById(id).get();
		modelo.addAttribute("resultado", resultado);
		return "ver_resultado";
	}

	@GetMapping("/resultados/editar/{id}")
	public String mostrarFormularioEditar(@PathVariable Integer id, Model modelo) {
		Resultado resultado =  resultadoRepository.findById(id).get();
		modelo.addAttribute("resultado", resultado);
		List<Partido> listaPartidos = partidoRepository.findAll();
		modelo.addAttribute("listaPartidos", listaPartidos);
		List<Seleccion> listaSelecciones = seleccionRepository.findAll();
		modelo.addAttribute("listaSelecciones", listaSelecciones);
		return "editar_resultado";
	}

	@PostMapping("/resultados/{id}")
	public String actualizarResultado(@PathVariable Integer id, @ModelAttribute("resultado") Resultado resultado,
			Model modelo) {
		Resultado resultadoExistente = resultadoRepository.findById(id).get();
		resultadoExistente.setId(resultado.getId());
		resultadoExistente.setPartido(resultado.getPartido());
		resultadoExistente.setSeleccion(resultado.getSeleccion());
		resultadoExistente.setGoles(resultado.getGoles());
		resultadoExistente.setAmarillas(resultado.getAmarillas());
		resultadoExistente.setRojas(resultado.getRojas());
		resultadoRepository.save(resultadoExistente);
		return "redirect:/resultados";
	}
	@GetMapping("/resultados/{id}")
	public String eliminarResultado(@PathVariable Integer id, Model modelo) {
		resultadoRepository.deleteById(id);
		return "redirect:/resultados";
	}
	@GetMapping("/verResultado")
	public String verResultado() {
		return "redirect:/resultados";
	}
}

package com.judicial.controlador;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.judicial.interfacesServicio.InterfazSedeServicio;
import com.judicial.interfacesServicio.InterfazVentanillaServicio;
import com.judicial.modelo.Sede;
import com.judicial.modelo.Ventanilla;

@Controller
@RequestMapping(value = "/ventanilla")
public class ControladorVentanilla {
	@Autowired
	private InterfazVentanillaServicio servicioVentanilla;
	@Autowired
	private InterfazSedeServicio servicioSede;

	@GetMapping("")
	public String listar(Model modelo) {
		List<Ventanilla> ventanilla = servicioVentanilla.listar();
		modelo.addAttribute("envioLista", ventanilla);
		return "VistaVentanilla_Listar";
	}

	@GetMapping("/nuevo")
	public String nuevo(Model modelo) {
		List<Sede> sedes = servicioSede.listarPorNombre();
		modelo.addAttribute("ventanilla", new Ventanilla());
		modelo.addAttribute("sedes", sedes);
		return "VistaVentanilla_Formulario";
	}

	@PostMapping("/guardar")
	public String guardar(@Valid Ventanilla u, Model modelo) {
		servicioVentanilla.guardar(u);
		return "redirect:/ventanilla";
	}

	@GetMapping("/editar/{id}")
	public String editar(@PathVariable int id, Model modelo) {
		List<Sede> sedes = servicioSede.listarPorNombre();
		Optional<Ventanilla> ventanilla = servicioVentanilla.listarId(id);
		modelo.addAttribute("ventanilla", ventanilla.get());
		modelo.addAttribute("sedes", sedes);
		return "VistaVentanilla_Formulario";
	}

	@GetMapping("/eliminar/{id}")
	public String eliminar(@PathVariable int id, Model modelo) {
		Optional<Ventanilla> ventanilla = servicioVentanilla.listarId(id);
		ventanilla.get().setS_estado_ventanilla("I");
		servicioVentanilla.guardar(ventanilla.get());
		return "redirect:/ventanilla";
	}

	@GetMapping("/habilitar/{id}")
	public String habilitar(@PathVariable int id, Model modelo) {
		Optional<Ventanilla> ventanilla = servicioVentanilla.listarId(id);
		ventanilla.get().setS_estado_ventanilla("A");
		servicioVentanilla.guardar(ventanilla.get());
		return "redirect:/ventanilla";
	}
}

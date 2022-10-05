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

import com.judicial.interfacesServicio.InterfazEspecialidadServicio;
import com.judicial.interfacesServicio.InterfazSedeServicio;
import com.judicial.modelo.Especialidad;
import com.judicial.modelo.Sede;

@Controller
@RequestMapping(value = "/especialidad")
public class ControladorEspecialidad {
	@Autowired
	private InterfazEspecialidadServicio servicio;
	@Autowired
	private InterfazSedeServicio servicioSede;

	@GetMapping("")
	public String listar(Model modelo) {
		List<Especialidad> sede = servicio.listar();
		modelo.addAttribute("envioLista", sede);
		return "VistaEspecialidad_Listar";
	}

	@GetMapping("/nuevo")
	public String nuevo(Model modelo) {
		List<Sede> sedes = servicioSede.listarPorNombre();
		modelo.addAttribute("especialidad", new Especialidad());
		modelo.addAttribute("sedes", sedes);
		return "VistaEspecialidad_Formulario";
	}

	@PostMapping("/guardar")
	public String guardar(@Valid Especialidad u, Model modelo) {
		servicio.guardar(u);
		return "redirect:/especialidad";
	}

	@GetMapping("/editar/{id}")
	public String editar(@PathVariable int id, Model modelo) {
		Optional<Especialidad> especialidad = servicio.listarId(id);
		List<Sede> sedes = servicioSede.listarPorNombre();
		modelo.addAttribute("sedes", sedes);
		modelo.addAttribute("especialidad", especialidad.get());
		modelo.addAttribute("edit", "true");
		return "VistaEspecialidad_Formulario";
	}

	@GetMapping("/eliminar/{id}")
	public String eliminar(@PathVariable int id, Model modelo) {
		Optional<Especialidad> especialidad = servicio.listarId(id);
		especialidad.get().setS_estado_especialidad("I");
		servicio.guardar(especialidad.get());
		return "redirect:/especialidad";
	}

	@GetMapping("/habilitar/{id}")
	public String habilitar(@PathVariable int id, Model modelo) {
		Optional<Especialidad> especialidad = servicio.listarId(id);
		especialidad.get().setS_estado_especialidad("A");
		servicio.guardar(especialidad.get());
		return "redirect:/especialidad";
	}
}
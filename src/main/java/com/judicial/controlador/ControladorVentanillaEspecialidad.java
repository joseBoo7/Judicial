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

import com.judicial.interfacesServicio.InterfazVentanillaEspecialidadServicio;
import com.judicial.interfacesServicio.InterfazVentanillaServicio;
import com.judicial.modelo.Especialidad;
import com.judicial.modelo.Ventanilla;
import com.judicial.modelo.VentanillaEspecialidad;

@Controller
@RequestMapping(value = "/ventanilla/especialidad")
public class ControladorVentanillaEspecialidad {
	@Autowired
	private InterfazVentanillaEspecialidadServicio servicioVentanillaEspecialidad;
	@Autowired
	private InterfazVentanillaServicio servicioVentanilla;

	@GetMapping("/{id}")
	public String listar(@PathVariable int id, Model modelo) {
		Optional<Ventanilla> temp = servicioVentanilla.listarId(id);
		List<VentanillaEspecialidad> envio = servicioVentanillaEspecialidad.listarPorVentanilla(id);
		modelo.addAttribute("id", id);
		modelo.addAttribute("ventanilla", temp.get().getN_numero_ventanilla());
		modelo.addAttribute("envioLista", envio);
		return "VistaVentanillaEspecialidad_Listar";
	}

	@GetMapping("/nuevo/{id}")
	public String nuevo(@PathVariable int id, Model modelo) {
		List<Especialidad> datos = servicioVentanillaEspecialidad.listarVentanillaNoTiene(id);
		modelo.addAttribute("id", id);
		modelo.addAttribute("ventanillaEspecialidad", new VentanillaEspecialidad());
		modelo.addAttribute("especialidades", datos);
		return "VistaVentanillaEspecialidad_Formulario";
	}

	@PostMapping("/guardar/{id}")
	public String guardar(@Valid VentanillaEspecialidad u, Model modelo, @PathVariable int id) {
		servicioVentanillaEspecialidad.guardar(u);
		return "redirect:/ventanilla/especialidad/" + id;
	}

	@GetMapping("/eliminar/{id}/{id2}")
	public String eliminar(@PathVariable int id, @PathVariable int id2, Model modelo) {
		servicioVentanillaEspecialidad.eliminar(id);
		return "redirect:/ventanilla/especialidad/" + id2;
	}
}

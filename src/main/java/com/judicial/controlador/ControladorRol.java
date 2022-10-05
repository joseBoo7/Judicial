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

import com.judicial.interfacesServicio.InterfazRolServicio;
import com.judicial.modelo.Rol;

@Controller
@RequestMapping(value = "/rol")
public class ControladorRol {
	@Autowired
	private InterfazRolServicio servicio;

	@GetMapping("")
	public String listar(Model modelo) {
		List<Rol> sede = servicio.listar();
		modelo.addAttribute("envioLista", sede);
		return "VistaRol_Listar";
	}

	@GetMapping("/nuevo")
	public String nuevo(Model modelo) {
		modelo.addAttribute("rol", new Rol());
		return "VistaRol_Formulario";
	}

	@PostMapping("/guardar")
	public String guardar(@Valid Rol u, Model modelo) {
		servicio.guardar(u);
		return "redirect:/rol";
	}

	@GetMapping("/editar/{id}")
	public String editar(@PathVariable int id, Model modelo) {
		Optional<Rol> rol = servicio.listarId(id);
		modelo.addAttribute("rol", rol.get());
		modelo.addAttribute("edit","true");
		return "VistaRol_Formulario";
	}

	@GetMapping("/eliminar/{id}")
	public String eliminar(@PathVariable int id, Model modelo) {
		Optional<Rol> rol = servicio.listarId(id);
		rol.get().setS_estado_rol("I");
		servicio.guardar(rol.get());
		return "redirect:/rol";
	}
	
	@GetMapping("/habilitar/{id}")
	public String habilitar(@PathVariable int id, Model modelo) {
		Optional<Rol> rol = servicio.listarId(id);
		rol.get().setS_estado_rol("A");
		servicio.guardar(rol.get());
		return "redirect:/rol";
	}
}
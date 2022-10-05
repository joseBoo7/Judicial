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
import com.judicial.modelo.Sede;

@Controller
@RequestMapping(value = "/sede")
public class ControladorSede {
	@Autowired
	private InterfazSedeServicio servicio;
	
	@GetMapping("")
	public String listar(Model modelo) {
		List<Sede> sede = servicio.listar();
		modelo.addAttribute("envioLista", sede);
		return "VistaSede_Listar";
	}
	@GetMapping("/nuevo")
	public String nuevo(Model modelo) {
		modelo.addAttribute("sede", new Sede());
		return "VistaSede_Formulario";
	}
	@PostMapping("/guardar")
	public String guardar(@Valid Sede u, Model modelo) {
		servicio.guardar(u);
		return "redirect:/sede";
	}
	@GetMapping("/editar/{id}")
	public String editar(@PathVariable int id, Model modelo) {
		Optional<Sede>local = servicio.listarId(id);
		modelo.addAttribute("sede",local.get());
		modelo.addAttribute("edit","true");
		return "VistaSede_Formulario";
	}
	@GetMapping("/eliminar/{id}")
	public String eliminar(@PathVariable int id, Model modelo) {
		Optional<Sede>local = servicio.listarId(id);
		local.get().setS_estado_sede("I");
		servicio.guardar(local.get());
		return "redirect:/sede";
	}
	@GetMapping("/habilitar/{id}")
	public String habilitar(@PathVariable int id, Model modelo) {
		Optional<Sede>local = servicio.listarId(id);
		local.get().setS_estado_sede("A");
		servicio.guardar(local.get());
		return "redirect:/sede";
	}
}
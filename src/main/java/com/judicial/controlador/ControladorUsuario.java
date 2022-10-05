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
import com.judicial.interfacesServicio.InterfazSedeServicio;
import com.judicial.interfacesServicio.InterfazUsuarioServicio;
import com.judicial.modelo.Rol;
import com.judicial.modelo.Sede;
import com.judicial.modelo.Usuario;

@Controller
@RequestMapping(value = "/usuario")
public class ControladorUsuario {
	@Autowired
	private InterfazUsuarioServicio servicioUsuario;
	@Autowired
	private InterfazSedeServicio servicioSede;
	@Autowired
	private InterfazRolServicio servicioRol;

	@GetMapping("")
	public String listar(Model modelo) {
		List<Usuario> usuario = servicioUsuario.listar();
		modelo.addAttribute("envioLista", usuario);
		return "VistaUsuario_Listar";
	}

	@GetMapping("/nuevo")
	public String nuevo(Model modelo) {
		List<Sede> sedes = servicioSede.listarPorNombre();
		List<Rol> roles = servicioRol.listarPorNombre();
		modelo.addAttribute("usuario", new Usuario());
		modelo.addAttribute("sedes", sedes);
		modelo.addAttribute("roles", roles);
		return "VistaUsuario_Formulario";
	}

	@PostMapping("/guardar")
	public String guardar(@Valid Usuario u, Model modelo) {
		servicioUsuario.guardar(u);
		return "redirect:/usuario";
	}

	@PostMapping("/guardarEditado")
	public String guardarEditado(@Valid Usuario u, Model modelo) {
		servicioUsuario.guardarEdit(u);
		return "redirect:/usuario";
	}

	@GetMapping("/editar/{id}")
	public String editar(@PathVariable int id, Model modelo) {
		List<Sede> sedes = servicioSede.listarPorNombre();
		List<Rol> roles = servicioRol.listarPorNombre();
		Optional<Usuario> usuario = servicioUsuario.listarId(id);
		modelo.addAttribute("usuario", usuario.get());
		modelo.addAttribute("sedes", sedes);
		modelo.addAttribute("roles", roles);
		return "VistaUsuario_FormularioEditar";
	}

	@GetMapping("/eliminar/{id}")
	public String eliminar(@PathVariable int id, Model modelo) {
		Optional<Usuario> usuario = servicioUsuario.listarId(id);
		usuario.get().setS_estado_usuario("I");
		servicioUsuario.guardarEdit(usuario.get());
		return "redirect:/sede";
	}

	@GetMapping("/habilitar/{id}")
	public String habilitar(@PathVariable int id, Model modelo) {
		Optional<Usuario> usuario = servicioUsuario.listarId(id);
		usuario.get().setS_estado_usuario("A");
		servicioUsuario.guardarEdit(usuario.get());
		return "redirect:/sede";
	}
}

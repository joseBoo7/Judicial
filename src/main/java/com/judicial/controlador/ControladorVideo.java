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
import com.judicial.interfacesServicio.InterfazVideoServicio;
import com.judicial.modelo.Sede;
import com.judicial.modelo.Video;

@Controller
@RequestMapping(value = "/video")
public class ControladorVideo {
	@Autowired
	private InterfazVideoServicio servicioVideo;
	@Autowired
	private InterfazSedeServicio servicioSede;

	@GetMapping("")
	public String listar(Model modelo) {
		List<Video> video = servicioVideo.listar();
		modelo.addAttribute("envioLista", video);
		return "VistaVideo_Listar";
	}

	@GetMapping("/nuevo")
	public String nuevo(Model modelo) {
		List<Sede> sedes = servicioSede.listarPorNombre();
		modelo.addAttribute("sedes", sedes);
		modelo.addAttribute("video", new Video());
		return "VistaVideo_Formulario";
	}

	@PostMapping("/guardar")
	public String guardar(@Valid Video u, Model modelo) {
		servicioVideo.guardar(u);
		return "redirect:/video";
	}

	@GetMapping("/editar/{id}")
	public String editar(@PathVariable int id, Model modelo) {
		Optional<Video> video = servicioVideo.listarId(id);
		List<Sede> sedes = servicioSede.listarPorNombre();
		modelo.addAttribute("sedes", sedes);
		modelo.addAttribute("video", video.get());
		modelo.addAttribute("edit", "true");
		return "VistaVideo_Formulario";
	}

	@GetMapping("/eliminar/{id}")
	public String eliminar(@PathVariable int id, Model modelo) {
		Optional<Video> video = servicioVideo.listarId(id);
		video.get().setS_estado_video("I");
		servicioVideo.guardar(video.get());
		return "redirect:/video";
	}

	@GetMapping("/habilitar/{id}")
	public String habilitar(@PathVariable int id, Model modelo) {
		Optional<Video> video = servicioVideo.listarId(id);
		video.get().setS_estado_video("A");
		servicioVideo.guardar(video.get());
		return "redirect:/video";
	}

}

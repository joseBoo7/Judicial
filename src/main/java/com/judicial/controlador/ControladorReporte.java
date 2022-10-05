package com.judicial.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.judicial.interfacesServicio.InterfazSedeServicio;
import com.judicial.modelo.Sede;

@Controller
@RequestMapping(value = "/reporte")
public class ControladorReporte {
	
	@Autowired
	private InterfazSedeServicio servicioSede;
	
	@GetMapping("")
	public String listar(Model modelo) {
		List<Sede>sedes = servicioSede.listar();
		modelo.addAttribute("sedes", sedes);
		return "VistaReporte_Reporte";
	}

}

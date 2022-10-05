package com.judicial.servicio;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.judicial.interfaces.InterfazVentanillaEspecialidad;
import com.judicial.interfacesServicio.InterfazVentanillaEspecialidadServicio;
import com.judicial.interfacesServicio.InterfazVentanillaServicio;
import com.judicial.modelo.Especialidad;
import com.judicial.modelo.VentanillaEspecialidad;

@Service
public class VentanillaEspecialidadServicio implements InterfazVentanillaEspecialidadServicio {

	@Autowired
	private InterfazVentanillaEspecialidad dato;
	@Autowired
	private InterfazVentanillaServicio servicioVentanilla;
	
	@Override
	public List<VentanillaEspecialidad> listar() {
		return (List<VentanillaEspecialidad>) dato.findAll();
	}

	@Override
	public Optional<VentanillaEspecialidad> listarId(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int guardar(VentanillaEspecialidad u) {
		int respuesta = 0;
		VentanillaEspecialidad ventanillaEspecialidad = dato.save(u);
		if (!ventanillaEspecialidad.equals(null))
			respuesta = 1;
		return respuesta;
	}

	@Override
	public void eliminar(int id) {
		dato.deleteById(id);
		
	}

	@Override
	public List<VentanillaEspecialidad> listarPorVentanilla(int id) {
		return dato.findByVentanilla(servicioVentanilla.listarId(id).get());
	}

	@Override
	public List<Especialidad> listarVentanillaNoTiene(int id) {
		return dato.NotInVentanilla(servicioVentanilla.listarId(id).get());
	}

}

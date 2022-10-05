package com.judicial.interfacesServicio;

import java.util.List;
import java.util.Optional;

import com.judicial.modelo.Especialidad;
import com.judicial.modelo.VentanillaEspecialidad;

public interface InterfazVentanillaEspecialidadServicio {
	public List<VentanillaEspecialidad> listar();

	public Optional<VentanillaEspecialidad> listarId(int id);

	public int guardar(VentanillaEspecialidad u);

	public void eliminar(int id);
	
	public List<VentanillaEspecialidad> listarPorVentanilla(int id);
	
	public List<Especialidad> listarVentanillaNoTiene(int id);
}

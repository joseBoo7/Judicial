package com.judicial.interfacesServicio;

import java.util.List;
import java.util.Optional;

import com.judicial.modelo.Especialidad;

public interface InterfazEspecialidadServicio {
	public List<Especialidad> listar();

	public Optional<Especialidad> listarId(int id);

	public int guardar(Especialidad u);

	public void eliminar(int id);
	
	public List<Especialidad> listarPorNombre();
	
	public List<Especialidad> listarPorSede(int id);
}

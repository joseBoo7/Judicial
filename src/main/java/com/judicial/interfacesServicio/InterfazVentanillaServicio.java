package com.judicial.interfacesServicio;

import java.util.List;
import java.util.Optional;

import com.judicial.modelo.Ventanilla;

public interface InterfazVentanillaServicio {
	public List<Ventanilla> listar();

	public Optional<Ventanilla> listarId(int id);

	public int guardar(Ventanilla u);

	public void eliminar(int id);
	
	public List<Ventanilla> listarPorNumero();
	
	public List<Ventanilla> ventanillasPorSede(int id);
	
	public List<Ventanilla> listarActivosSedeEspecialidad(int id, int id2);
	
	public List<Ventanilla> listarActivosSede(int id);
	
	public List<Ventanilla> listarPreferencialSede(int id);
}

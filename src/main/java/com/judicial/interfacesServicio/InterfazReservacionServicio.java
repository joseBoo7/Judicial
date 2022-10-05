package com.judicial.interfacesServicio;

import java.util.List;
import java.util.Optional;

import com.judicial.modelo.Reservacion;

public interface InterfazReservacionServicio {
	
	public List<Reservacion> listar();

	public Optional<Reservacion> listarId(int id);

	public int guardar(Reservacion u);

	public void eliminar(int id);
	
	public List<Reservacion> listarPreferencial(int id);
	
	public List<Reservacion> listarPorEspecialidadDeVentanilla(int id, int id2);
	
	public List<Reservacion> listarPorSede(int id);
	
	public List<Reservacion> listarPorEspecialidad(int id, int id2);
	
}

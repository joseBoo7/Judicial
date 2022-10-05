package com.judicial.interfacesServicio;

import java.util.List;
import java.util.Optional;

import com.judicial.modelo.Sede;

public interface InterfazSedeServicio {
	public List<Sede> listar();

	public Optional<Sede> listarId(int id);

	public int guardar(Sede u);

	public void eliminar(int id);
	
	public List<Sede> listarPorNombre();
}

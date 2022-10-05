package com.judicial.interfacesServicio;

import java.util.List;
import java.util.Optional;

import com.judicial.modelo.Rol;

public interface InterfazRolServicio {
	public List<Rol> listar();

	public Optional<Rol> listarId(int id);

	public int guardar(Rol u);

	public void eliminar(int id);
	
	public List<Rol> listarPorNombre();
}

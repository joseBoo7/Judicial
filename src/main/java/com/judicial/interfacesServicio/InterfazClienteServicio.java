package com.judicial.interfacesServicio;

import java.util.List;
import java.util.Optional;

import com.judicial.modelo.Cliente;

public interface InterfazClienteServicio {
	public List<Cliente> listar();

	public Optional<Cliente> listarId(int id);

	public int guardar(Cliente u);

	public void eliminar(int id);
	
	public boolean buscarClienteDni(int dni);
	
	public Cliente obtenerClienteDni(int dni);
	
	public int cantidadPorFecha(int year, int month, int day);
	
	public List<Cliente> listarPendientes();
}

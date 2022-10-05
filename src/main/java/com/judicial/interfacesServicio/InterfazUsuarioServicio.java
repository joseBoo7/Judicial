package com.judicial.interfacesServicio;

import java.util.List;
import java.util.Optional;

import com.judicial.modelo.Usuario;

public interface InterfazUsuarioServicio {
	public List<Usuario> listar();

	public Optional<Usuario> listarId(int id);

	public int guardar(Usuario u);

	public int guardarEdit(Usuario u);

	public void eliminar(int id);

	public List<Usuario> encontrarUsuario(String nombre);
}

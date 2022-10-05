package com.judicial.servicio;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.judicial.interfaces.InterfazRol;
import com.judicial.interfacesServicio.InterfazRolServicio;
import com.judicial.modelo.Rol;

@Service
public class RolServicio implements InterfazRolServicio {

	@Autowired
	private InterfazRol dato;

	@Override
	public List<Rol> listar() {
		return (List<Rol>) dato.findAll();
	}

	@Override
	public Optional<Rol> listarId(int id) {
		return dato.findById(id);
	}

	@Override
	public int guardar(Rol u) {
		int respuesta = 0;
		Rol sede = dato.save(u);
		if (!sede.equals(null))
			respuesta = 1;
		return respuesta;
	}

	@Override
	public void eliminar(int id) {
		dato.deleteById(id);

	}

	@Override
	public List<Rol> listarPorNombre() {
		return dato.listarOrdenadamente();
	}

}

package com.judicial.servicio;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.judicial.interfaces.InterfazSede;
import com.judicial.interfacesServicio.InterfazSedeServicio;
import com.judicial.modelo.Sede;

@Service
public class SedeServicio implements InterfazSedeServicio {
	@Autowired
	private InterfazSede dato;
	
	@Override
	public List<Sede> listar() {
		return (List<Sede>)dato.findAll();
	}
	@Override
	public Optional<Sede> listarId(int id) {
		return dato.findById(id);
	}

	@Override
	public int guardar(Sede u) {
		int respuesta=0;
		Sede sede=dato.save(u);
		if(!sede.equals(null))
			respuesta=1;
		return respuesta;
	}

	@Override
	public void eliminar(int id) {
		dato.deleteById(id);
		
	}
	@Override
	public List<Sede> listarPorNombre() {
		return dato.listarOrdenadamente();
	}
}

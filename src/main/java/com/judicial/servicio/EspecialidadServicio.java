package com.judicial.servicio;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.judicial.interfaces.InterfazEspecialidad;
import com.judicial.interfacesServicio.InterfazEspecialidadServicio;
import com.judicial.interfacesServicio.InterfazSedeServicio;
import com.judicial.modelo.Especialidad;

@Service
public class EspecialidadServicio implements InterfazEspecialidadServicio {

	@Autowired
	private InterfazEspecialidad dato;
	@Autowired
	private InterfazSedeServicio servicioSede;

	@Override
	public List<Especialidad> listar() {
		return (List<Especialidad>) dato.findAll();
	}

	@Override
	public Optional<Especialidad> listarId(int id) {
		return dato.findById(id);
	}

	@Override
	public int guardar(Especialidad u) {
		int respuesta = 0;
		Especialidad sede = dato.save(u);
		if (!sede.equals(null))
			respuesta = 1;
		return respuesta;
	}

	@Override
	public void eliminar(int id) {
		dato.deleteById(id);

	}

	@Override
	public List<Especialidad> listarPorNombre() {
		return dato.listarOrdenadamente();
	}

	@Override
	public List<Especialidad> listarPorSede(int id) {
		return dato.listarPorSede(servicioSede.listarId(id).get());
	}

}

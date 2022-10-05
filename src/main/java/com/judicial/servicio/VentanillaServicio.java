package com.judicial.servicio;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.judicial.interfaces.InterfazVentanilla;
import com.judicial.interfacesServicio.InterfazEspecialidadServicio;
import com.judicial.interfacesServicio.InterfazSedeServicio;
import com.judicial.interfacesServicio.InterfazVentanillaServicio;
import com.judicial.modelo.Ventanilla;

@Service
public class VentanillaServicio implements InterfazVentanillaServicio {

	@Autowired
	private InterfazVentanilla dato;
	@Autowired
	private InterfazSedeServicio servicioSede;
	@Autowired
	private InterfazEspecialidadServicio servicioEspecialidad;

	@Override
	public List<Ventanilla> listar() {
		return (List<Ventanilla>) dato.findAll();
	}

	@Override
	public Optional<Ventanilla> listarId(int id) {
		return dato.findById(id);
	}

	@Override
	public int guardar(Ventanilla u) {
		int respuesta = 0;
		Ventanilla ventanilla = dato.save(u);
		if (!ventanilla.equals(null))
			respuesta = 1;
		return respuesta;
	}

	@Override
	public void eliminar(int id) {
		dato.deleteById(id);

	}

	@Override
	public List<Ventanilla> listarPorNumero() {
		return dato.listarOrdenadamente();
	}

	@Override
	public List<Ventanilla> ventanillasPorSede(int id) {
		return dato.ventanillasPorSede(servicioSede.listarId(id).get());
	}

	@Override
	public List<Ventanilla> listarActivosSedeEspecialidad(int id, int id2) {
		return dato.listarActivosSedeEspecialidad(servicioSede.listarId(id).get(),servicioEspecialidad.listarId(id2).get());
	}

	@Override
	public List<Ventanilla> listarActivosSede(int id) {
		return dato.listarActivosSede(servicioSede.listarId(id).get());
	}

	@Override
	public List<Ventanilla> listarPreferencialSede(int id) {
		return dato.listarPreferencialSede(servicioSede.listarId(id).get());
	}

}

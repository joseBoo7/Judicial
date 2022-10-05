package com.judicial.servicio;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.judicial.dto.DtoGrafico_Lineal;
import com.judicial.interfaces.InterfazEspecialidadReservacion;
import com.judicial.interfacesServicio.InterfazEspecialidadReservacionServicio;
import com.judicial.modelo.EspecialidadReservacion;

@Service
public class EspecialidadReservacionServicio implements InterfazEspecialidadReservacionServicio {

	@Autowired
	private InterfazEspecialidadReservacion dato;

	@Override
	public List<EspecialidadReservacion> listar() {
		return (List<EspecialidadReservacion>) dato.findAll();
	}

	@Override
	public Optional<EspecialidadReservacion> listarId(int id) {
		return dato.findById(id);
	}

	@Override
	public int guardar(EspecialidadReservacion u) {
		int respuesta = 0;
		EspecialidadReservacion especialidadReservacion = dato.save(u);
		if (!especialidadReservacion.equals(null))
			respuesta = 1;
		return respuesta;
	}

	@Override
	public void eliminar(int id) {
		dato.deleteById(id);

	}

	@Override
	public List<DtoGrafico_Lineal> listarEspecialidadesContadas() {
		return dato.listarEspecialidadContador();
	}

}

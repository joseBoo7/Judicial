package com.judicial.servicio;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.judicial.interfaces.InterfazReservacion;
import com.judicial.interfacesServicio.InterfazEspecialidadServicio;
import com.judicial.interfacesServicio.InterfazReservacionServicio;
import com.judicial.interfacesServicio.InterfazSedeServicio;
import com.judicial.interfacesServicio.InterfazVentanillaServicio;
import com.judicial.modelo.Reservacion;

@Service
public class ReservacionServicio implements InterfazReservacionServicio {

	@Autowired
	private InterfazReservacion dato;
	@Autowired
	private InterfazVentanillaServicio servicioVentanilla;
	@Autowired
	private InterfazSedeServicio servicioSede;
	@Autowired
	private InterfazEspecialidadServicio servicioEspecialidad;

	@Override
	public List<Reservacion> listar() {
		return (List<Reservacion>) dato.findAll();
	}

	@Override
	public Optional<Reservacion> listarId(int id) {
		return dato.findById(id);
	}

	@Override
	public int guardar(Reservacion u) {
		Reservacion reservacion = dato.save(u);
		return reservacion.getN_id_reservacion();
	}

	@Override
	public void eliminar(int id) {
		dato.deleteById(id);

	}

	@Override
	public List<Reservacion> listarPreferencial(int id) {
		Calendar fecha = Calendar.getInstance();
		int año = fecha.get(Calendar.YEAR);
		int mes = fecha.get(Calendar.MONTH) + 1;
		int dia = fecha.get(Calendar.DAY_OF_MONTH);
		return dato.findByPreferencial(año, mes, dia, servicioSede.listarId(id).get());
	}

	@Override
	public List<Reservacion> listarPorEspecialidadDeVentanilla(int id, int id2) {
		Calendar fecha = Calendar.getInstance();
		int año = fecha.get(Calendar.YEAR);
		int mes = fecha.get(Calendar.MONTH) + 1;
		int dia = fecha.get(Calendar.DAY_OF_MONTH);
		return dato.findByEspecialidadVentanilla(año, mes, dia, servicioVentanilla.listarId(id).get(),
				servicioSede.listarId(id2).get());
	}

	@Override
	public List<Reservacion> listarPorSede(int id) {
		Calendar fecha = Calendar.getInstance();
		int año = fecha.get(Calendar.YEAR);
		int mes = fecha.get(Calendar.MONTH) + 1;
		int dia = fecha.get(Calendar.DAY_OF_MONTH);
		return dato.findBySede(año, mes, dia, servicioSede.listarId(id).get());
	}

	@Override
	public List<Reservacion> listarPorEspecialidad(int id, int id2) {
		Calendar fecha = Calendar.getInstance();
		int año = fecha.get(Calendar.YEAR);
		int mes = fecha.get(Calendar.MONTH) + 1;
		int dia = fecha.get(Calendar.DAY_OF_MONTH);
		return dato.findByEspecialidad(año, mes, dia, servicioSede.listarId(id).get(),
				servicioEspecialidad.listarId(id2).get());
	}

}

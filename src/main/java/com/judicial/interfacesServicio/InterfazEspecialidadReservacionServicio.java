package com.judicial.interfacesServicio;

import java.util.List;
import java.util.Optional;

import com.judicial.dto.DtoGrafico_Lineal;
import com.judicial.modelo.EspecialidadReservacion;

public interface InterfazEspecialidadReservacionServicio {

	public List<EspecialidadReservacion> listar();

	public Optional<EspecialidadReservacion> listarId(int id);

	public int guardar(EspecialidadReservacion u);

	public void eliminar(int id);
	
	public List<DtoGrafico_Lineal> listarEspecialidadesContadas();
}

package com.judicial.interfaces;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.judicial.dto.DtoGrafico_Lineal;
import com.judicial.modelo.EspecialidadReservacion;

@Repository
public interface InterfazEspecialidadReservacion extends JpaRepository<EspecialidadReservacion, Integer> {

	@Query(value = "SELECT c.s_nombre_especialidad AS station, COUNT(*) AS early FROM EspecialidadReservacion a "
			+ "INNER JOIN Reservacion b " + "ON a.Reservacion_n_id_reservacion = b.n_id_reservacion "
			+ "INNER JOIN Especialidad c " + "ON a.Especialidad_n_id_especialidad = c.n_id_especialidad "
			+ "group by a.Especialidad_n_id_especialidad")
	public List<DtoGrafico_Lineal> listarEspecialidadContador();

}

package com.judicial.interfaces;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.judicial.modelo.Especialidad;
import com.judicial.modelo.Reservacion;
import com.judicial.modelo.Sede;
import com.judicial.modelo.Ventanilla;

@Repository
public interface InterfazReservacion extends JpaRepository<Reservacion, Integer> {

	@Query(value = "SELECT a FROM Reservacion a " + "WHERE extract(year from  a.d_fecha_hora_reservacion) = :year "
			+ "AND extract(month from  a.d_fecha_hora_reservacion) = :month "
			+ "AND extract(day from  a.d_fecha_hora_reservacion) = :day " + "AND a.s_preferencial_reservacion = 'A' "
			+ "AND a.s_estado_reservacion = 'P' " + "AND a.Sede_n_id_sede = :sede "
			+ "ORDER BY a.d_fecha_hora_reservacion")
	public List<Reservacion> findByPreferencial(@Param(value = "year") int year, @Param(value = "month") int month,
			@Param(value = "day") int day, @Param(value = "sede") Sede sede);

	@Query(value = "SELECT a FROM Reservacion a " + "INNER JOIN EspecialidadReservacion b "
			+ "ON a.n_id_reservacion = b.Reservacion_n_id_reservacion "
			+ "WHERE extract(year from  a.d_fecha_hora_reservacion) = :year "
			+ "AND extract(month from  a.d_fecha_hora_reservacion) = :month "
			+ "AND extract(day from  a.d_fecha_hora_reservacion) = :day " + "AND a.s_preferencial_reservacion = 'I' "
			+ "AND a.s_estado_reservacion = 'P' " + "AND a.Sede_n_id_sede = :sede "
			+ "AND b.Especialidad_n_id_especialidad "
			+ "IN (SELECT v.Especialidad_n_id_especialidad FROM VentanillaEspecialidad v "
			+ "WHERE v.Ventanilla_n_id_ventanilla = :ventanilla) " + "ORDER BY a.d_fecha_hora_reservacion")
	public List<Reservacion> findByEspecialidadVentanilla(@Param(value = "year") int year,
			@Param(value = "month") int month, @Param(value = "day") int day,
			@Param(value = "ventanilla") Ventanilla ventanilla, @Param(value = "sede") Sede sede);

	@Query(value = "SELECT a FROM Reservacion a " + "WHERE a.Sede_n_id_sede = :sede "
			+ "AND a.s_estado_reservacion = 'P' " + "AND extract(year from  a.d_fecha_hora_reservacion) = :year "
			+ "AND extract(month from  a.d_fecha_hora_reservacion) = :month "
			+ "AND extract(day from  a.d_fecha_hora_reservacion) = :day " + "ORDER BY a.d_fecha_hora_reservacion")
	public List<Reservacion> findBySede(@Param(value = "year") int year, @Param(value = "month") int month,
			@Param(value = "day") int day, @Param(value = "sede") Sede sede);

	@Query(value = "SELECT a FROM Reservacion a " + "INNER JOIN EspecialidadReservacion b "
			+ "ON a.n_id_reservacion = b.Reservacion_n_id_reservacion "
			+ "WHERE extract(year from  a.d_fecha_hora_reservacion) = :year "
			+ "AND extract(month from  a.d_fecha_hora_reservacion) = :month "
			+ "AND extract(day from  a.d_fecha_hora_reservacion) = :day " + "AND a.s_preferencial_reservacion = 'I' "
			+ "AND a.s_estado_reservacion = 'P' " + "AND a.Sede_n_id_sede = :sede "
			+ "AND b.Especialidad_n_id_especialidad = :especialidad " + "ORDER BY a.d_fecha_hora_reservacion")
	public List<Reservacion> findByEspecialidad(@Param(value = "year") int year, @Param(value = "month") int month,
			@Param(value = "day") int day, @Param(value = "sede") Sede sede,
			@Param(value = "especialidad") Especialidad especialidad);

}

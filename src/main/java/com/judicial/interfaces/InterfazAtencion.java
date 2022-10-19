package com.judicial.interfaces;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.judicial.dto.DtoCampo;
import com.judicial.dto.DtoCampoPromedio;
import com.judicial.dto.DtoReporte_Atencion;
import com.judicial.dto.DtoReporte_Modal;
import com.judicial.modelo.Atencion;
import com.judicial.modelo.Especialidad;
import com.judicial.modelo.Sede;
import com.judicial.modelo.Usuario;
import com.judicial.modelo.Ventanilla;

@Repository
public interface InterfazAtencion extends JpaRepository<Atencion, Integer> {

	@Query(value = "SELECT a FROM Atencion a " + "WHERE a.s_estado_atencion = 'P'")
	public List<Atencion> findByState();

	@Query(value = "SELECT COUNT(a) FROM Atencion a " + "WHERE extract(year from  a.d_fecha_atencion) = :year "
			+ "AND extract(month from  a.d_fecha_atencion) = :month "
			+ "AND extract(day from  a.d_fecha_atencion) = :day " + "AND a.Ventanilla_n_id_ventanilla = :ventanilla")
	public int findByDate(@Param(value = "year") int year, @Param(value = "month") int month,
			@Param(value = "day") int day, @Param(value = "ventanilla") Ventanilla ventanilla);

	@Query(value = "SELECT a FROM Atencion a " + "INNER JOIN Ventanilla v "
			+ "ON a.Ventanilla_n_id_ventanilla = v.n_id_ventanilla " + "WHERE v.Sede_n_id_sede = :Sede "
			+ "AND a.s_estado_atencion = 'P' " + "ORDER BY a.d_fecha_atencion")
	public List<Atencion> findBySede(@Param(value = "Sede") Sede Sede);

	@Query(value = "SELECT d.n_id_usuario AS codigo, d.s_nombre_usuario AS nombre, a.s_ausente_atencion AS estado, CAST(a.d_fecha_atencion AS date) AS fecha, AVG(TIME_TO_SEC(TIMEDIFF(a.d_fecha_atencion,b.d_fecha_hora_reservacion))) AS tiempo1, AVG(TIME_TO_SEC(TIMEDIFF(a.d_fecha_fin_atencion,a.d_fecha_atencion))) AS tiempo2, AVG(TIME_TO_SEC(TIMEDIFF(a.d_fecha_fin_atencion,b.d_fecha_hora_reservacion))) AS tiempo3 FROM Atencion a "
			+ "INNER JOIN Reservacion b " + "ON a.Reservacion_n_id_reservacion = b.n_id_reservacion "
			+ "INNER JOIN Ventanilla c " + "ON a.Ventanilla_n_id_ventanilla = c.n_id_ventanilla "
			+ "INNER JOIN Usuario d " + "ON a.Usuario_n_id_usuario = d.n_id_usuario " + "INNER JOIN Cliente e "
			+ "ON b.Cliente_n_id_cliente = e.n_id_cliente " + "WHERE c.Sede_n_id_sede = :sede "
			+ "AND b.s_preferencial_reservacion = 'I' "
			+ "AND b.n_id_reservacion IN (SELECT f.Reservacion_n_id_reservacion FROM EspecialidadReservacion f WHERE f.Especialidad_n_id_especialidad = :especialidad) "
			+ "AND CAST(b.d_fecha_hora_reservacion AS date) >=  CAST(:fecha1 AS date) "
			+ "AND CAST(b.d_fecha_hora_reservacion AS date) <=  CAST(:fecha2 AS date) "
			+ "GROUP BY d.s_nombre_usuario, fecha, a.s_ausente_atencion " + "ORDER BY fecha")
	public List<DtoReporte_Atencion> reportAtencion(@Param(value = "fecha1") String fecha1,
			@Param(value = "fecha2") String fecha2, @Param(value = "sede") Sede sede,
			@Param(value = "especialidad") Especialidad especialidad);

	@Query(value = "SELECT d.n_id_usuario AS codigo, d.s_nombre_usuario AS nombre, a.s_ausente_atencion AS estado, CAST(a.d_fecha_atencion AS date) AS fecha, AVG(TIME_TO_SEC(TIMEDIFF(a.d_fecha_atencion,b.d_fecha_hora_reservacion))) AS tiempo1, AVG(TIME_TO_SEC(TIMEDIFF(a.d_fecha_fin_atencion,a.d_fecha_atencion))) AS tiempo2, AVG(TIME_TO_SEC(TIMEDIFF(a.d_fecha_fin_atencion,b.d_fecha_hora_reservacion))) AS tiempo3 FROM Atencion a "
			+ "INNER JOIN Reservacion b " + "ON a.Reservacion_n_id_reservacion = b.n_id_reservacion "
			+ "INNER JOIN Ventanilla c " + "ON a.Ventanilla_n_id_ventanilla = c.n_id_ventanilla "
			+ "INNER JOIN Usuario d " + "ON a.Usuario_n_id_usuario = d.n_id_usuario " + "INNER JOIN Cliente e "
			+ "ON b.Cliente_n_id_cliente = e.n_id_cliente " + "WHERE c.Sede_n_id_sede = :sede "
			+ "AND b.s_preferencial_reservacion = 'A' "
			+ "AND CAST(b.d_fecha_hora_reservacion AS date) >=  CAST(:fecha1 AS date) "
			+ "AND CAST(b.d_fecha_hora_reservacion AS date) <=  CAST(:fecha2 AS date) "
			+ "GROUP BY d.s_nombre_usuario, fecha, a.s_ausente_atencion " + "ORDER BY fecha")
	public List<DtoReporte_Atencion> reportAtencionPreferencial(@Param(value = "fecha1") String fecha1,
			@Param(value = "fecha2") String fecha2, @Param(value = "sede") Sede sede);

	@Query(value = "SELECT d.n_id_usuario AS codigo, d.s_nombre_usuario AS nombre, a.s_ausente_atencion AS estado, CAST(a.d_fecha_atencion AS date) AS fecha, AVG(TIME_TO_SEC(TIMEDIFF(a.d_fecha_atencion,b.d_fecha_hora_reservacion))) AS tiempo1, AVG(TIME_TO_SEC(TIMEDIFF(a.d_fecha_fin_atencion,a.d_fecha_atencion))) AS tiempo2, AVG(TIME_TO_SEC(TIMEDIFF(a.d_fecha_fin_atencion,b.d_fecha_hora_reservacion))) AS tiempo3 FROM Atencion a "
			+ "INNER JOIN Reservacion b " + "ON a.Reservacion_n_id_reservacion = b.n_id_reservacion "
			+ "INNER JOIN Ventanilla c " + "ON a.Ventanilla_n_id_ventanilla = c.n_id_ventanilla "
			+ "INNER JOIN Usuario d " + "ON a.Usuario_n_id_usuario = d.n_id_usuario " + "INNER JOIN Cliente e "
			+ "ON b.Cliente_n_id_cliente = e.n_id_cliente " + "WHERE c.Sede_n_id_sede = :sede "
			+ "AND CAST(b.d_fecha_hora_reservacion AS date) >=  CAST(:fecha1 AS date) "
			+ "AND CAST(b.d_fecha_hora_reservacion AS date) <=  CAST(:fecha2 AS date) "
			+ "GROUP BY d.s_nombre_usuario, fecha, a.s_ausente_atencion " + "ORDER BY fecha")
	public List<DtoReporte_Atencion> reportAtencionAll(@Param(value = "fecha1") String fecha1,
			@Param(value = "fecha2") String fecha2, @Param(value = "sede") Sede sede);

	@Query(value = "SELECT d.s_nombre_usuario AS nombre, COUNT(*) AS valor  FROM Atencion a "
			+ "INNER JOIN Reservacion b " + "ON a.Reservacion_n_id_reservacion = b.n_id_reservacion "
			+ "INNER JOIN Ventanilla c " + "ON a.Ventanilla_n_id_ventanilla = c.n_id_ventanilla "
			+ "INNER JOIN Usuario d " + "ON a.Usuario_n_id_usuario = d.n_id_usuario " + "INNER JOIN Cliente e "
			+ "ON b.Cliente_n_id_cliente = e.n_id_cliente " + "WHERE c.Sede_n_id_sede = :sede "
			+ "AND CAST(b.d_fecha_hora_reservacion AS date) >=  CAST(:fecha1 AS date) "
			+ "AND CAST(b.d_fecha_hora_reservacion AS date) <=  CAST(:fecha2 AS date) " + "GROUP BY d.s_nombre_usuario")
	public List<DtoCampo> reportAtencionPorUsuario(@Param(value = "fecha1") String fecha1,
			@Param(value = "fecha2") String fecha2, @Param(value = "sede") Sede sede);

	@Query(value = "SELECT CONCAT('VENTANILLA ',c.n_numero_ventanilla) AS nombre, COUNT(*) AS valor  FROM Atencion a "
			+ "INNER JOIN Reservacion b " + "ON a.Reservacion_n_id_reservacion = b.n_id_reservacion "
			+ "INNER JOIN Ventanilla c " + "ON a.Ventanilla_n_id_ventanilla = c.n_id_ventanilla "
			+ "INNER JOIN Usuario d " + "ON a.Usuario_n_id_usuario = d.n_id_usuario " + "INNER JOIN Cliente e "
			+ "ON b.Cliente_n_id_cliente = e.n_id_cliente " + "WHERE c.Sede_n_id_sede = :sede "
			+ "AND CAST(b.d_fecha_hora_reservacion AS date) >=  CAST(:fecha1 AS date) "
			+ "AND CAST(b.d_fecha_hora_reservacion AS date) <=  CAST(:fecha2 AS date) "
			+ "GROUP BY c.n_numero_ventanilla")
	public List<DtoCampo> reportAtencionPorVentanilla(@Param(value = "fecha1") String fecha1,
			@Param(value = "fecha2") String fecha2, @Param(value = "sede") Sede sede);

	@Query(value = "SELECT d.s_nombre_usuario AS nombre, AVG(TIME_TO_SEC(TIMEDIFF(a.d_fecha_atencion,b.d_fecha_hora_reservacion))) AS tiempo1, "
			+ "AVG(TIME_TO_SEC(TIMEDIFF(a.d_fecha_fin_atencion,a.d_fecha_atencion))) AS tiempo2, "
			+ "AVG(TIME_TO_SEC(TIMEDIFF(a.d_fecha_fin_atencion,b.d_fecha_hora_reservacion))) AS tiempo3 FROM Atencion a "
			+ "INNER JOIN Reservacion b " + "ON a.Reservacion_n_id_reservacion = b.n_id_reservacion "
			+ "INNER JOIN Ventanilla c " + "ON a.Ventanilla_n_id_ventanilla = c.n_id_ventanilla "
			+ "INNER JOIN Usuario d " + "ON a.Usuario_n_id_usuario = d.n_id_usuario " + "INNER JOIN Cliente e "
			+ "ON b.Cliente_n_id_cliente = e.n_id_cliente " + "WHERE c.Sede_n_id_sede = :sede "
			+ "AND CAST(b.d_fecha_hora_reservacion AS date) >=  CAST(:fecha1 AS date) "
			+ "AND CAST(b.d_fecha_hora_reservacion AS date) <=  CAST(:fecha2 AS date) " + "GROUP BY d.s_nombre_usuario")
	public List<DtoCampoPromedio> reportAtencionPromedioUsuario(@Param(value = "fecha1") String fecha1,
			@Param(value = "fecha2") String fecha2, @Param(value = "sede") Sede sede);

	@Query(value = "SELECT d.s_nombre_usuario AS nombre, c.n_numero_ventanilla AS ventanilla, CAST(a.d_fecha_atencion AS date) AS fecha, TIME_TO_SEC(TIMEDIFF(a.d_fecha_atencion,b.d_fecha_hora_reservacion)) AS tiempo1, TIME_TO_SEC(TIMEDIFF(a.d_fecha_fin_atencion,a.d_fecha_atencion)) AS tiempo2, TIME_TO_SEC(TIMEDIFF(a.d_fecha_fin_atencion,b.d_fecha_hora_reservacion)) AS tiempo3 FROM Atencion a "
			+ "INNER JOIN Reservacion b " + "ON a.Reservacion_n_id_reservacion = b.n_id_reservacion "
			+ "INNER JOIN Ventanilla c " + "ON a.Ventanilla_n_id_ventanilla = c.n_id_ventanilla "
			+ "INNER JOIN Usuario d " + "ON a.Usuario_n_id_usuario = d.n_id_usuario "
			+ "WHERE c.Sede_n_id_sede = :sede " + "AND a.s_ausente_atencion = :ausente "
			+ "AND d.n_id_usuario = :usuario "
			+ "AND CAST(b.d_fecha_hora_reservacion AS date) =  CAST(:fecha AS date) ")
	public List<DtoReporte_Modal> reportDataModal(@Param(value = "fecha") String fecha,
			@Param(value = "ausente") String ausente, @Param(value = "sede") Sede sede,
			@Param(value = "usuario") int usuario);

}

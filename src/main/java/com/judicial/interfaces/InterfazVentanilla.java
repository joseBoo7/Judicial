package com.judicial.interfaces;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.judicial.modelo.Especialidad;
import com.judicial.modelo.Sede;
import com.judicial.modelo.Ventanilla;

@Repository
public interface InterfazVentanilla extends JpaRepository<Ventanilla, Integer> {

	@Query(value = "SELECT a FROM Ventanilla a " + "ORDER BY a.n_numero_ventanilla")
	public List<Ventanilla> listarOrdenadamente();

	@Query(value = "SELECT a FROM Ventanilla a " + "INNER JOIN VentanillaEspecialidad b "
			+ "ON b.Ventanilla_n_id_ventanilla = a.n_id_ventanilla " + "WHERE a.s_estado_ventanilla = 'A' "
			+ "AND a.Sede_n_id_sede = :sede " + "AND b.Especialidad_n_id_especialidad= :especialidad")
	public List<Ventanilla> listarActivosSedeEspecialidad(@Param(value = "sede") Sede sede,
			@Param(value = "especialidad") Especialidad especialidad);

	@Query(value = "SELECT a FROM Ventanilla a " + "WHERE a.Sede_n_id_sede = :sede " + "ORDER BY a.n_numero_ventanilla")
	public List<Ventanilla> ventanillasPorSede(@Param(value = "sede") Sede sede);

	@Query(value = "SELECT a FROM Ventanilla a " + "WHERE a.s_estado_ventanilla = 'A' "
			+ "AND a.Sede_n_id_sede = :sede ")
	public List<Ventanilla> listarActivosSede(@Param(value = "sede") Sede sede);

	@Query(value = "SELECT a FROM Ventanilla a " + "WHERE a.s_estado_ventanilla = 'A' "
			+ "AND a.Sede_n_id_sede = :sede " + "AND a.s_preferencial_ventanilla = 'A'")
	public List<Ventanilla> listarPreferencialSede(@Param(value = "sede") Sede sede);

}

package com.judicial.interfaces;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.judicial.modelo.Especialidad;
import com.judicial.modelo.Ventanilla;
import com.judicial.modelo.VentanillaEspecialidad;

@Repository
public interface InterfazVentanillaEspecialidad extends JpaRepository<VentanillaEspecialidad, Integer> {

	@Query(value = "SELECT a FROM VentanillaEspecialidad a " + "WHERE a.Ventanilla_n_id_ventanilla = :ventanilla")
	public List<VentanillaEspecialidad> findByVentanilla(@Param(value = "ventanilla") Ventanilla ventanilla);
	
	@Query(value = "SELECT a FROM Especialidad a " + "WHERE a.n_id_especialidad "
			+ "NOT IN (SELECT v.Especialidad_n_id_especialidad FROM VentanillaEspecialidad v  WHERE v.Ventanilla_n_id_ventanilla = :ventanilla) "
			+ "ORDER BY a.s_nombre_especialidad")
	public List<Especialidad> NotInVentanilla(@Param(value = "ventanilla") Ventanilla ventanilla);
}

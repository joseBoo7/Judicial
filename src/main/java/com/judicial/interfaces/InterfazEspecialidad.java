package com.judicial.interfaces;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.judicial.modelo.Especialidad;
import com.judicial.modelo.Sede;

@Repository
public interface InterfazEspecialidad extends JpaRepository<Especialidad, Integer> {

	@Query(value = "SELECT a FROM Especialidad a " + "ORDER BY a.s_nombre_especialidad")
	public List<Especialidad> listarOrdenadamente();

	@Query(value = "SELECT a FROM Especialidad a " + "WHERE a.Sede_n_id_sede = :sede "
			+ "AND a.s_estado_especialidad = 'A'" + "ORDER BY a.s_nombre_especialidad")
	public List<Especialidad> listarPorSede(@Param(value = "sede") Sede sede);
}

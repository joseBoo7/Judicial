package com.judicial.interfaces;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.judicial.modelo.Rol;

@Repository
public interface InterfazRol extends JpaRepository<Rol, Integer> {

	@Query(value = "SELECT a FROM Rol a " + "ORDER BY a.s_nombre_rol")
	public List<Rol> listarOrdenadamente();
}

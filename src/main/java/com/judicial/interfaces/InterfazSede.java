package com.judicial.interfaces;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.judicial.modelo.Sede;

@Repository
public interface InterfazSede extends JpaRepository<Sede, Integer> {
	
	@Query(value = "SELECT a FROM Sede a " + "ORDER BY a.s_nombre_sede")
	public List<Sede> listarOrdenadamente();
}

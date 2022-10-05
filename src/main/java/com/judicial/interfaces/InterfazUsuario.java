package com.judicial.interfaces;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.judicial.modelo.Usuario;

@Repository
public interface InterfazUsuario extends JpaRepository<Usuario, Integer> {

	@Query(value = "SELECT a FROM Usuario a " + "WHERE a.s_usuario_usuario = :name")
	public List<Usuario> findByUsuarioName(@Param(value = "name") String name);
	
}

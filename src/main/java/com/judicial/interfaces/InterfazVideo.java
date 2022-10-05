package com.judicial.interfaces;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.judicial.modelo.Sede;
import com.judicial.modelo.Video;

@Repository
public interface InterfazVideo extends JpaRepository<Video, Integer> {

	@Query(value = "SELECT a FROM Video a " + "WHERE a.Sede_n_id_sede = :sede " + "AND a.s_estado_video = 'A'")
	public List<Video> findBySede(@Param(value = "sede") Sede sede);
}

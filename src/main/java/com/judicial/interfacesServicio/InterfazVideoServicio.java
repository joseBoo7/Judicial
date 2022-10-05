package com.judicial.interfacesServicio;

import java.util.List;
import java.util.Optional;

import com.judicial.modelo.Video;

public interface InterfazVideoServicio {
	public List<Video> listar();

	public Optional<Video> listarId(int id);

	public int guardar(Video u);

	public void eliminar(int id);

	public List<Video> listarPorSede(int id);
}

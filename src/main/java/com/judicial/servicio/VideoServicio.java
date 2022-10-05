package com.judicial.servicio;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.judicial.interfaces.InterfazVideo;
import com.judicial.interfacesServicio.InterfazSedeServicio;
import com.judicial.interfacesServicio.InterfazVideoServicio;
import com.judicial.modelo.Video;

@Service
public class VideoServicio implements InterfazVideoServicio {

	@Autowired
	private InterfazVideo dato;
	@Autowired
	private InterfazSedeServicio servicioSede;

	@Override
	public List<Video> listar() {
		return (List<Video>) dato.findAll();
	}

	@Override
	public Optional<Video> listarId(int id) {
		return dato.findById(id);
	}

	@Override
	public int guardar(Video u) {
		int respuesta = 0;
		Video video = dato.save(u);
		if (!video.equals(null))
			respuesta = 1;
		return respuesta;
	}

	@Override
	public void eliminar(int id) {
		dato.deleteById(id);
	}

	@Override
	public List<Video> listarPorSede(int id) {
		return dato.findBySede(servicioSede.listarId(id).get());
	}

}

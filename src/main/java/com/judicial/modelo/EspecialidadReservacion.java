package com.judicial.modelo;

import java.text.ParseException;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Especialidad_has_reservacion")
public class EspecialidadReservacion {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int n_id_especialidad_has_reservacion;
	@ManyToOne
	@JoinColumn(name = "Especialidad_n_id_especialidad")
	private Especialidad Especialidad_n_id_especialidad;
	@ManyToOne
	@JoinColumn(name = "Reservacion_n_id_reservacion")
	private Reservacion Reservacion_n_id_reservacion;
	
	public EspecialidadReservacion() throws ParseException {
		super();
		this.Especialidad_n_id_especialidad = new Especialidad();
		this.Reservacion_n_id_reservacion = new Reservacion();
	}
	
	public EspecialidadReservacion(int n_id_especialidad_has_reservacion, Especialidad especialidad_n_id_especialidad,
			Reservacion reservacion_n_id_reservacion) {
		super();
		this.n_id_especialidad_has_reservacion = n_id_especialidad_has_reservacion;
		Especialidad_n_id_especialidad = especialidad_n_id_especialidad;
		Reservacion_n_id_reservacion = reservacion_n_id_reservacion;
	}

	public int getN_id_especialidad_has_reservacion() {
		return n_id_especialidad_has_reservacion;
	}

	public void setN_id_especialidad_has_reservacion(int n_id_especialidad_has_reservacion) {
		this.n_id_especialidad_has_reservacion = n_id_especialidad_has_reservacion;
	}

	public Especialidad getEspecialidad_n_id_especialidad() {
		return Especialidad_n_id_especialidad;
	}

	public void setEspecialidad_n_id_especialidad(Especialidad especialidad_n_id_especialidad) {
		Especialidad_n_id_especialidad = especialidad_n_id_especialidad;
	}

	public Reservacion getReservacion_n_id_reservacion() {
		return Reservacion_n_id_reservacion;
	}

	public void setReservacion_n_id_reservacion(Reservacion reservacion_n_id_reservacion) {
		Reservacion_n_id_reservacion = reservacion_n_id_reservacion;
	}
	
}

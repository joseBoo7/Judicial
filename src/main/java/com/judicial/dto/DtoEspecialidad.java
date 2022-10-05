package com.judicial.dto;

import com.judicial.modelo.Sede;

public class DtoEspecialidad {
	private int n_id_especialidad;
	private String s_nombre_especialidad;
	private String s_estado_especialidad;
	private Sede Sede_n_id_sede;

	public DtoEspecialidad(int n_id_especialidad, String s_nombre_especialidad, String s_estado_especialidad,
			Sede sede_n_id_sede) {
		super();
		this.n_id_especialidad = n_id_especialidad;
		this.s_nombre_especialidad = s_nombre_especialidad;
		this.s_estado_especialidad = s_estado_especialidad;
		Sede_n_id_sede = sede_n_id_sede;
	}

	public int getN_id_especialidad() {
		return n_id_especialidad;
	}

	public void setN_id_especialidad(int n_id_especialidad) {
		this.n_id_especialidad = n_id_especialidad;
	}

	public String getS_nombre_especialidad() {
		return s_nombre_especialidad;
	}

	public void setS_nombre_especialidad(String s_nombre_especialidad) {
		this.s_nombre_especialidad = s_nombre_especialidad;
	}

	public String getS_estado_especialidad() {
		return s_estado_especialidad;
	}

	public void setS_estado_especialidad(String s_estado_especialidad) {
		this.s_estado_especialidad = s_estado_especialidad;
	}

	public Sede getSede_n_id_sede() {
		return Sede_n_id_sede;
	}

	public void setSede_n_id_sede(Sede sede_n_id_sede) {
		Sede_n_id_sede = sede_n_id_sede;
	}

}

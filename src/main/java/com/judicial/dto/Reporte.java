package com.judicial.dto;

import java.io.Serializable;

public class Reporte implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String f1;
	private String f2;
	private int sede;
	private int especialidad;
	private int usuario;

	public Reporte(String f1, String f2, int sede, int especialidad, int usuario) {
		super();
		this.f1 = f1;
		this.f2 = f2;
		this.sede = sede;
		this.especialidad = especialidad;
		this.usuario = usuario;
	}

	public String getF1() {
		return f1;
	}

	public void setF1(String f1) {
		this.f1 = f1;
	}

	public String getF2() {
		return f2;
	}

	public void setF2(String f2) {
		this.f2 = f2;
	}

	public int getSede() {
		return sede;
	}

	public void setSede(int sede) {
		this.sede = sede;
	}

	public int getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(int especialidad) {
		this.especialidad = especialidad;
	}

	public int getUsuario() {
		return usuario;
	}

	public void setUsuario(int usuario) {
		this.usuario = usuario;
	}

}

package com.judicial.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "Especialidad")
public class Especialidad {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int n_id_especialidad;
	@Length(max = 150)
	private String s_nombre_especialidad;
	@NotEmpty
	@Length(max = 1)
	private String s_estado_especialidad;
	@ManyToOne
	@JoinColumn(name = "Sede_n_id_sede")
	private Sede Sede_n_id_sede;

	public Especialidad() {
		super();
		this.s_estado_especialidad = "A";
		this.Sede_n_id_sede = new Sede();
	}

	public Especialidad(int n_id_especialidad, @Length(max = 150) String s_nombre_especialidad,
			@NotEmpty @Length(max = 1) String s_estado_especialidad, Sede sede_n_id_sede) {
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
	
	public String toString() {
		return s_nombre_especialidad;
	}
	
}

package com.judicial.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "Sede")
public class Sede {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int n_id_sede;
	@Length(max = 100)
	private String s_nombre_sede;
	@Length(max = 100)
	private String s_ubicacion_sede;
	@NotEmpty
	@Length(max = 1)
	private String s_estado_sede;

	public Sede() {
		super();
		this.s_estado_sede = "A";
	}

	public Sede(int n_id_sede, @Length(max = 60) String s_nombre_sede, @Length(max = 150) String s_ubicacion_sede,
			@NotEmpty @Length(max = 1) String s_estado_sede) {
		super();
		this.n_id_sede = n_id_sede;
		this.s_nombre_sede = s_nombre_sede;
		this.s_ubicacion_sede = s_ubicacion_sede;
		this.s_estado_sede = s_estado_sede;
	}

	public int getN_id_sede() {
		return n_id_sede;
	}

	public void setN_id_sede(int n_id_sede) {
		this.n_id_sede = n_id_sede;
	}

	public String getS_nombre_sede() {
		return s_nombre_sede;
	}

	public void setS_nombre_sede(String s_nombre_sede) {
		this.s_nombre_sede = s_nombre_sede;
	}

	public String getS_ubicacion_sede() {
		return s_ubicacion_sede;
	}

	public void setS_ubicacion_sede(String s_ubicacion_sede) {
		this.s_ubicacion_sede = s_ubicacion_sede;
	}

	public String getS_estado_sede() {
		return s_estado_sede;
	}

	public void setS_estado_sede(String s_estado_sede) {
		this.s_estado_sede = s_estado_sede;
	}

	public String toString() {
		return s_nombre_sede;
	}
}

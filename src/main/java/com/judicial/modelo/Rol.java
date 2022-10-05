package com.judicial.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "Rol")
public class Rol {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int n_id_rol;
	@Length(max = 100)
	private String s_nombre_rol;
	@NotEmpty
	@Length(max = 1)
	private String s_estado_rol;

	public Rol() {
		super();
		this.s_estado_rol = "A";
	}

	public Rol(int n_id_rol, @Length(max = 100) String s_nombre_rol, @NotEmpty @Length(max = 1) String s_estado_rol) {
		super();
		this.n_id_rol = n_id_rol;
		this.s_nombre_rol = s_nombre_rol;
		this.s_estado_rol = s_estado_rol;
	}

	public int getN_id_rol() {
		return n_id_rol;
	}

	public void setN_id_rol(int n_id_rol) {
		this.n_id_rol = n_id_rol;
	}

	public String getS_nombre_rol() {
		return s_nombre_rol;
	}

	public void setS_nombre_rol(String s_nombre_rol) {
		this.s_nombre_rol = s_nombre_rol;
	}

	public String getS_estado_rol() {
		return s_estado_rol;
	}

	public void setS_estado_rol(String s_estado_rol) {
		this.s_estado_rol = s_estado_rol;
	}

	public String toString() {
		return s_nombre_rol;
	}

}

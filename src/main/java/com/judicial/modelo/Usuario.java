package com.judicial.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "Usuario")
public class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int n_id_usuario;
	@Length(max = 100)
	private String s_nombre_usuario;
	@Length(max = 150)
	private String s_apellidos_usuario;
	@Length(max = 8)
	private String s_dni_usuario;
	@Length(max = 45)
	private String s_usuario_usuario;
	@Length(max = 128)
	private String s_contrasena_usuario;
	@Length(max = 1)
	private String s_estado_usuario;
	@ManyToOne
	@JoinColumn(name = "Sede_n_id_sede")
	private Sede Sede_n_id_sede;
	@ManyToOne
	@JoinColumn(name = "Rol_n_id_rol")
	private Rol Rol_n_id_rol;

	public Usuario() {
		super();
		this.s_estado_usuario = "A";
		this.Sede_n_id_sede = new Sede();
		this.Rol_n_id_rol = new Rol();
	}

	public Usuario(int n_id_usuario, @Length(max = 100) String s_nombre_usuario,
			@Length(max = 150) String s_apellidos_usuario, @Length(max = 8) String s_dni_usuario,
			@Length(max = 45) String s_usuario_usuario, @Length(max = 128) String s_contrasena_usuario,
			@Length(max = 1) String s_estado_usuario, Sede sede_n_id_sede, Rol rol_n_id_rol) {
		super();
		this.n_id_usuario = n_id_usuario;
		this.s_nombre_usuario = s_nombre_usuario;
		this.s_apellidos_usuario = s_apellidos_usuario;
		this.s_dni_usuario = s_dni_usuario;
		this.s_usuario_usuario = s_usuario_usuario;
		this.s_contrasena_usuario = s_contrasena_usuario;
		this.s_estado_usuario = s_estado_usuario;
		Sede_n_id_sede = sede_n_id_sede;
		Rol_n_id_rol = rol_n_id_rol;
	}

	public int getN_id_usuario() {
		return n_id_usuario;
	}

	public void setN_id_usuario(int n_id_usuario) {
		this.n_id_usuario = n_id_usuario;
	}

	public String getS_nombre_usuario() {
		return s_nombre_usuario;
	}

	public void setS_nombre_usuario(String s_nombre_usuario) {
		this.s_nombre_usuario = s_nombre_usuario;
	}

	public String getS_apellidos_usuario() {
		return s_apellidos_usuario;
	}

	public void setS_apellidos_usuario(String s_apellidos_usuario) {
		this.s_apellidos_usuario = s_apellidos_usuario;
	}

	public String getS_dni_usuario() {
		return s_dni_usuario;
	}

	public void setS_dni_usuario(String s_dni_usuario) {
		this.s_dni_usuario = s_dni_usuario;
	}

	public String getS_usuario_usuario() {
		return s_usuario_usuario;
	}

	public void setS_usuario_usuario(String s_usuario_usuario) {
		this.s_usuario_usuario = s_usuario_usuario;
	}

	public String getS_contrasena_usuario() {
		return s_contrasena_usuario;
	}

	public void setS_contrasena_usuario(String s_contrasena_usuario) {
		this.s_contrasena_usuario = s_contrasena_usuario;
	}

	public String getS_estado_usuario() {
		return s_estado_usuario;
	}

	public void setS_estado_usuario(String s_estado_usuario) {
		this.s_estado_usuario = s_estado_usuario;
	}

	public Sede getSede_n_id_sede() {
		return Sede_n_id_sede;
	}

	public void setSede_n_id_sede(Sede sede_n_id_sede) {
		Sede_n_id_sede = sede_n_id_sede;
	}

	public Rol getRol_n_id_rol() {
		return Rol_n_id_rol;
	}

	public void setRol_n_id_rol(Rol rol_n_id_rol) {
		Rol_n_id_rol = rol_n_id_rol;
	}

}

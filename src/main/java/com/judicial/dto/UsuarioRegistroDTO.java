package com.judicial.dto;

public class UsuarioRegistroDTO {
	private int n_id_usuario;
	private String s_nombre_usuario;
	private String s_apellidos_usuario;
	private String s_dni_usuario;
	private String s_usuario_usuario;
	private String s_contrasena_usuario;
	private String s_estado_usuario;

	public UsuarioRegistroDTO() {
		super();
	}

	public UsuarioRegistroDTO(int n_id_usuario, String s_nombre_usuario, String s_apellidos_usuario,
			String s_dni_usuario, String s_usuario_usuario, String s_contrasena_usuario, String s_estado_usuario) {
		super();
		this.n_id_usuario = n_id_usuario;
		this.s_nombre_usuario = s_nombre_usuario;
		this.s_apellidos_usuario = s_apellidos_usuario;
		this.s_dni_usuario = s_dni_usuario;
		this.s_usuario_usuario = s_usuario_usuario;
		this.s_contrasena_usuario = s_contrasena_usuario;
		this.s_estado_usuario = s_estado_usuario;
	}

	public UsuarioRegistroDTO(String s_nombre_usuario, String s_apellidos_usuario, String s_dni_usuario,
			String s_usuario_usuario, String s_contrasena_usuario, String s_estado_usuario) {
		super();
		this.s_nombre_usuario = s_nombre_usuario;
		this.s_apellidos_usuario = s_apellidos_usuario;
		this.s_dni_usuario = s_dni_usuario;
		this.s_usuario_usuario = s_usuario_usuario;
		this.s_contrasena_usuario = s_contrasena_usuario;
		this.s_estado_usuario = s_estado_usuario;
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

}

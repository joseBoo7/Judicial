package com.judicial.dto;

public class PersonaReniec {
	
	private String nombres;
	private String paterno;
	private String materno;
	private String usuario;
	
	public PersonaReniec() {
		super();
	}

	public PersonaReniec(String nombres, String paterno, String materno, String usuario) {
		super();
		this.nombres = nombres;
		this.paterno = paterno;
		this.materno = materno;
		this.usuario = usuario;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getPaterno() {
		return paterno;
	}

	public void setPaterno(String paterno) {
		this.paterno = paterno;
	}

	public String getMaterno() {
		return materno;
	}

	public void setMaterno(String materno) {
		this.materno = materno;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

}

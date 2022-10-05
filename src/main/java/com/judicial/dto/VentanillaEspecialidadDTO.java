package com.judicial.dto;

public class VentanillaEspecialidadDTO {
	private int n_id_ventanilla_has_especialidad;
	private int Ventanilla_n_id_ventanilla;
	private int Especialidad_n_id_especialidad;

	public VentanillaEspecialidadDTO(int n_id_ventanilla_has_especialidad, int ventanilla_n_id_ventanilla,
			int especialidad_n_id_especialidad) {
		super();
		this.n_id_ventanilla_has_especialidad = n_id_ventanilla_has_especialidad;
		Ventanilla_n_id_ventanilla = ventanilla_n_id_ventanilla;
		Especialidad_n_id_especialidad = especialidad_n_id_especialidad;
	}

	public int getN_id_ventanilla_has_especialidad() {
		return n_id_ventanilla_has_especialidad;
	}

	public void setN_id_ventanilla_has_especialidad(int n_id_ventanilla_has_especialidad) {
		this.n_id_ventanilla_has_especialidad = n_id_ventanilla_has_especialidad;
	}

	public int getVentanilla_n_id_ventanilla() {
		return Ventanilla_n_id_ventanilla;
	}

	public void setVentanilla_n_id_ventanilla(int ventanilla_n_id_ventanilla) {
		Ventanilla_n_id_ventanilla = ventanilla_n_id_ventanilla;
	}

	public int getEspecialidad_n_id_especialidad() {
		return Especialidad_n_id_especialidad;
	}

	public void setEspecialidad_n_id_especialidad(int especialidad_n_id_especialidad) {
		Especialidad_n_id_especialidad = especialidad_n_id_especialidad;
	}

}

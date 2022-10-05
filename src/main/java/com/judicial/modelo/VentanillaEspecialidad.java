package com.judicial.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Ventanilla_has_especialidad")
public class VentanillaEspecialidad {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int n_id_ventanilla_has_especialidad;
	@ManyToOne
	@JoinColumn(name = "Ventanilla_n_id_ventanilla")
	private Ventanilla Ventanilla_n_id_ventanilla;
	@ManyToOne
	@JoinColumn(name = "Especialidad_n_id_especialidad")
	private Especialidad Especialidad_n_id_especialidad;

	public VentanillaEspecialidad() {
		super();
		this.Ventanilla_n_id_ventanilla = new Ventanilla();
		this.Especialidad_n_id_especialidad = new Especialidad();
	}

	public VentanillaEspecialidad(int n_id_ventanilla_has_especialidad, Ventanilla ventanilla_n_id_ventanilla,
			Especialidad especialidad_n_id_especialidad) {
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

	public Ventanilla getVentanilla_n_id_ventanilla() {
		return Ventanilla_n_id_ventanilla;
	}

	public void setVentanilla_n_id_ventanilla(Ventanilla ventanilla_n_id_ventanilla) {
		Ventanilla_n_id_ventanilla = ventanilla_n_id_ventanilla;
	}

	public Especialidad getEspecialidad_n_id_especialidad() {
		return Especialidad_n_id_especialidad;
	}

	public void setEspecialidad_n_id_especialidad(Especialidad especialidad_n_id_especialidad) {
		Especialidad_n_id_especialidad = especialidad_n_id_especialidad;
	}
	
}

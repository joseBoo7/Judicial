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
@Table(name = "Ventanilla")
public class Ventanilla {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int n_id_ventanilla;
	private int n_numero_ventanilla;
	@Length(max = 1)
	private String s_estado_ventanilla;
	@Length(max = 1)
	private String s_preferencial_ventanilla;
	@ManyToOne
	@JoinColumn(name = "Sede_n_id_sede")
	private Sede Sede_n_id_sede;

	public Ventanilla() {
		super();
		this.s_estado_ventanilla = "A";
		this.s_preferencial_ventanilla = "I";
		this.Sede_n_id_sede = new Sede();
	}

	public Ventanilla(int n_id_ventanilla, int n_numero_ventanilla, @Length(max = 1) String s_estado_ventanilla,
			@Length(max = 1) String s_preferencial_ventanilla, Sede sede_n_id_sede) {
		super();
		this.n_id_ventanilla = n_id_ventanilla;
		this.n_numero_ventanilla = n_numero_ventanilla;
		this.s_estado_ventanilla = s_estado_ventanilla;
		this.s_preferencial_ventanilla = s_preferencial_ventanilla;
		Sede_n_id_sede = sede_n_id_sede;
	}

	public int getN_id_ventanilla() {
		return n_id_ventanilla;
	}

	public void setN_id_ventanilla(int n_id_ventanilla) {
		this.n_id_ventanilla = n_id_ventanilla;
	}

	public int getN_numero_ventanilla() {
		return n_numero_ventanilla;
	}

	public void setN_numero_ventanilla(int n_numero_ventanilla) {
		this.n_numero_ventanilla = n_numero_ventanilla;
	}

	public String getS_estado_ventanilla() {
		return s_estado_ventanilla;
	}

	public void setS_estado_ventanilla(String s_estado_ventanilla) {
		this.s_estado_ventanilla = s_estado_ventanilla;
	}

	public String getS_preferencial_ventanilla() {
		return s_preferencial_ventanilla;
	}

	public void setS_preferencial_ventanilla(String s_preferencial_ventanilla) {
		this.s_preferencial_ventanilla = s_preferencial_ventanilla;
	}

	public Sede getSede_n_id_sede() {
		return Sede_n_id_sede;
	}

	public void setSede_n_id_sede(Sede sede_n_id_sede) {
		Sede_n_id_sede = sede_n_id_sede;
	}

}

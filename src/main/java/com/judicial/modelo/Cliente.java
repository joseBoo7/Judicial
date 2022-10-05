package com.judicial.modelo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

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
@Table(name = "Cliente")
public class Cliente {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int n_id_cliente;
	@Length(max = 100)
	private String s_nombre_cliente;
	private int n_edad_cliente;
	@Length(max = 8)
	private String n_dni_cliente;
	private int n_codigo_cliente;
	@Length(max = 64)
	private String s_codigo_letra_cliente;
	@NotEmpty
	@Length(max = 1)
	private String s_preferencial_cliente;
	@NotEmpty
	@Length(max = 1)
	private String s_estado_cliente;
	private Date s_fecha_hora_cliente;
	@ManyToOne
	@JoinColumn(name = "Sede_n_id_sede")
	private Sede Sede_n_id_sede;
	
	public Cliente() {
		super();
		this.s_preferencial_cliente = "I";
		this.s_estado_cliente = "P";
		this.Sede_n_id_sede = new Sede();
	}
	
	public Cliente(String preferencial) throws ParseException {
		super();
		String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime());
        SimpleDateFormat dateParser = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        dateParser.setTimeZone(TimeZone.getTimeZone("GMT"));
		this.s_preferencial_cliente = preferencial;
		this.s_estado_cliente = "P";
		this.s_fecha_hora_cliente = dateParser.parse(timeStamp);
		this.Sede_n_id_sede = new Sede();
	}

	public Cliente(int n_id_cliente, @Length(max = 100) String s_nombre_cliente, int n_edad_cliente,
			@Length(max = 8) String n_dni_cliente, int n_codigo_cliente, @Length(max = 8) String s_codigo_letra_cliente,
			@NotEmpty @Length(max = 1) String s_preferencial_cliente,
			@NotEmpty @Length(max = 1) String s_estado_cliente, Date s_fecha_hora_cliente, Sede sede_n_id_sede) {
		super();
		this.n_id_cliente = n_id_cliente;
		this.s_nombre_cliente = s_nombre_cliente;
		this.n_edad_cliente = n_edad_cliente;
		this.n_dni_cliente = n_dni_cliente;
		this.n_codigo_cliente = n_codigo_cliente;
		this.s_codigo_letra_cliente = s_codigo_letra_cliente;
		this.s_preferencial_cliente = s_preferencial_cliente;
		this.s_estado_cliente = s_estado_cliente;
		this.s_fecha_hora_cliente = s_fecha_hora_cliente;
		Sede_n_id_sede = sede_n_id_sede;
	}

	public int getN_id_cliente() {
		return n_id_cliente;
	}

	public void setN_id_cliente(int n_id_cliente) {
		this.n_id_cliente = n_id_cliente;
	}

	public String getS_nombre_cliente() {
		return s_nombre_cliente;
	}

	public void setS_nombre_cliente(String s_nombre_cliente) {
		this.s_nombre_cliente = s_nombre_cliente;
	}

	public int getN_edad_cliente() {
		return n_edad_cliente;
	}

	public void setN_edad_cliente(int n_edad_cliente) {
		this.n_edad_cliente = n_edad_cliente;
	}

	public String getN_dni_cliente() {
		return n_dni_cliente;
	}

	public void setN_dni_cliente(String n_dni_cliente) {
		this.n_dni_cliente = n_dni_cliente;
	}

	public int getN_codigo_cliente() {
		return n_codigo_cliente;
	}

	public void setN_codigo_cliente(int n_codigo_cliente) {
		this.n_codigo_cliente = n_codigo_cliente;
	}

	public String getS_codigo_letra_cliente() {
		return s_codigo_letra_cliente;
	}

	public void setS_codigo_letra_cliente(String s_codigo_letra_cliente) {
		this.s_codigo_letra_cliente = s_codigo_letra_cliente;
	}

	public String getS_preferencial_cliente() {
		return s_preferencial_cliente;
	}

	public void setS_preferencial_cliente(String s_preferencial_cliente) {
		this.s_preferencial_cliente = s_preferencial_cliente;
	}

	public String getS_estado_cliente() {
		return s_estado_cliente;
	}

	public void setS_estado_cliente(String s_estado_cliente) {
		this.s_estado_cliente = s_estado_cliente;
	}

	public Date getS_fecha_hora_cliente() {
		return s_fecha_hora_cliente;
	}

	public void setS_fecha_hora_cliente(Date s_fecha_hora_cliente) {
		this.s_fecha_hora_cliente = s_fecha_hora_cliente;
	}

	public Sede getSede_n_id_sede() {
		return Sede_n_id_sede;
	}

	public void setSede_n_id_sede(Sede sede_n_id_sede) {
		Sede_n_id_sede = sede_n_id_sede;
	}
	
}

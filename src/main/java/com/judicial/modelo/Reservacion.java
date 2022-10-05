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

import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "Reservacion")
public class Reservacion {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int n_id_reservacion;
	@Length(max = 1)
	private String s_estado_reservacion;
	@Length(max = 1)
	private String s_preferencial_reservacion;
	private Date d_fecha_hora_reservacion;
	@ManyToOne
	@JoinColumn(name = "Cliente_n_id_cliente")
	private Cliente Cliente_n_id_cliente;
	@ManyToOne
	@JoinColumn(name = "Sede_n_id_sede")
	private Sede Sede_n_id_sede;
	
	public Reservacion() throws ParseException {
		super();
		String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime());
        SimpleDateFormat dateParser = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        dateParser.setTimeZone(TimeZone.getTimeZone("GMT"));
		this.s_estado_reservacion = "P";
		this.s_preferencial_reservacion = "I";
		this.d_fecha_hora_reservacion = dateParser.parse(timeStamp);
		this.Cliente_n_id_cliente = new Cliente();
		this.Sede_n_id_sede = new Sede();
	}

	public Reservacion(int n_id_reservacion, @Length(max = 1) String s_estado_reservacion,
			@Length(max = 1) String s_preferencial_reservacion, Date d_fecha_hora_reservacion,
			Cliente cliente_n_id_cliente, Sede sede_n_id_sede) {
		super();
		this.n_id_reservacion = n_id_reservacion;
		this.s_estado_reservacion = s_estado_reservacion;
		this.s_preferencial_reservacion = s_preferencial_reservacion;
		this.d_fecha_hora_reservacion = d_fecha_hora_reservacion;
		Cliente_n_id_cliente = cliente_n_id_cliente;
		Sede_n_id_sede = sede_n_id_sede;
	}

	public int getN_id_reservacion() {
		return n_id_reservacion;
	}

	public void setN_id_reservacion(int n_id_reservacion) {
		this.n_id_reservacion = n_id_reservacion;
	}

	public String getS_estado_reservacion() {
		return s_estado_reservacion;
	}

	public void setS_estado_reservacion(String s_estado_reservacion) {
		this.s_estado_reservacion = s_estado_reservacion;
	}

	public String getS_preferencial_reservacion() {
		return s_preferencial_reservacion;
	}

	public void setS_preferencial_reservacion(String s_preferencial_reservacion) {
		this.s_preferencial_reservacion = s_preferencial_reservacion;
	}

	public Date getD_fecha_hora_reservacion() {
		return d_fecha_hora_reservacion;
	}

	public void setD_fecha_hora_reservacion(Date d_fecha_hora_reservacion) {
		this.d_fecha_hora_reservacion = d_fecha_hora_reservacion;
	}

	public Cliente getCliente_n_id_cliente() {
		return Cliente_n_id_cliente;
	}

	public void setCliente_n_id_cliente(Cliente cliente_n_id_cliente) {
		Cliente_n_id_cliente = cliente_n_id_cliente;
	}

	public Sede getSede_n_id_sede() {
		return Sede_n_id_sede;
	}

	public void setSede_n_id_sede(Sede sede_n_id_sede) {
		Sede_n_id_sede = sede_n_id_sede;
	}
	
}

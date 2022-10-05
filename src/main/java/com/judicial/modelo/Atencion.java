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
@Table(name = "Atencion")
public class Atencion {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int n_id_atencion;
	private Date d_fecha_atencion;
	private Date d_fecha_fin_atencion;
	@Length(max = 1)
	private String s_estado_atencion;
	@Length(max = 1)
	private String s_ausente_atencion;
	@Length(max = 1)
	private String s_asistir_atencion;
	@ManyToOne
	@JoinColumn(name = "Usuario_n_id_usuario")
	private Usuario Usuario_n_id_usuario;
	@ManyToOne
	@JoinColumn(name = "Ventanilla_n_id_ventanilla")
	private Ventanilla Ventanilla_n_id_ventanilla;
	@ManyToOne
	@JoinColumn(name = "Reservacion_n_id_reservacion")
	private Reservacion Reservacion_n_id_reservacion;
	
	public Atencion() throws ParseException {
		super();
		String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime());
        SimpleDateFormat dateParser = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        dateParser.setTimeZone(TimeZone.getTimeZone("GMT"));
        this.d_fecha_atencion = dateParser.parse(timeStamp);
		this.s_estado_atencion = "P";
		this.s_ausente_atencion = "I";
		this.s_asistir_atencion = "I";
		this.Usuario_n_id_usuario = new Usuario();
		this.Ventanilla_n_id_ventanilla = new Ventanilla();
		this.Reservacion_n_id_reservacion = new Reservacion();
	}

	public Atencion(int n_id_atencion, Date d_fecha_atencion, Date d_fecha_fin_atencion,
			@Length(max = 1) String s_estado_atencion, @Length(max = 1) String s_ausente_atencion,
			@Length(max = 1) String s_asistir_atencion, Usuario usuario_n_id_usuario,
			Ventanilla ventanilla_n_id_ventanilla, Reservacion reservacion_n_id_reservacion) {
		super();
		this.n_id_atencion = n_id_atencion;
		this.d_fecha_atencion = d_fecha_atencion;
		this.d_fecha_fin_atencion = d_fecha_fin_atencion;
		this.s_estado_atencion = s_estado_atencion;
		this.s_ausente_atencion = s_ausente_atencion;
		this.s_asistir_atencion = s_asistir_atencion;
		Usuario_n_id_usuario = usuario_n_id_usuario;
		Ventanilla_n_id_ventanilla = ventanilla_n_id_ventanilla;
		Reservacion_n_id_reservacion = reservacion_n_id_reservacion;
	}

	public int getN_id_atencion() {
		return n_id_atencion;
	}

	public void setN_id_atencion(int n_id_atencion) {
		this.n_id_atencion = n_id_atencion;
	}

	public Date getD_fecha_atencion() {
		return d_fecha_atencion;
	}

	public void setD_fecha_atencion(Date d_fecha_atencion) {
		this.d_fecha_atencion = d_fecha_atencion;
	}

	public Date getD_fecha_fin_atencion() {
		return d_fecha_fin_atencion;
	}

	public void setD_fecha_fin_atencion(Date d_fecha_fin_atencion) {
		this.d_fecha_fin_atencion = d_fecha_fin_atencion;
	}

	public String getS_estado_atencion() {
		return s_estado_atencion;
	}

	public void setS_estado_atencion(String s_estado_atencion) {
		this.s_estado_atencion = s_estado_atencion;
	}

	public String getS_ausente_atencion() {
		return s_ausente_atencion;
	}

	public void setS_ausente_atencion(String s_ausente_atencion) {
		this.s_ausente_atencion = s_ausente_atencion;
	}

	public String getS_asistir_atencion() {
		return s_asistir_atencion;
	}

	public void setS_asistir_atencion(String s_asistir_atencion) {
		this.s_asistir_atencion = s_asistir_atencion;
	}

	public Usuario getUsuario_n_id_usuario() {
		return Usuario_n_id_usuario;
	}

	public void setUsuario_n_id_usuario(Usuario usuario_n_id_usuario) {
		Usuario_n_id_usuario = usuario_n_id_usuario;
	}

	public Ventanilla getVentanilla_n_id_ventanilla() {
		return Ventanilla_n_id_ventanilla;
	}

	public void setVentanilla_n_id_ventanilla(Ventanilla ventanilla_n_id_ventanilla) {
		Ventanilla_n_id_ventanilla = ventanilla_n_id_ventanilla;
	}

	public Reservacion getReservacion_n_id_reservacion() {
		return Reservacion_n_id_reservacion;
	}

	public void setReservacion_n_id_reservacion(Reservacion reservacion_n_id_reservacion) {
		Reservacion_n_id_reservacion = reservacion_n_id_reservacion;
	}

}

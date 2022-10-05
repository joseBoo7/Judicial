package com.judicial.modelo;

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
@Table(name = "Video")
public class Video {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int n_id_video;
	@Length(max = 128)
	private String s_enlace_video;
	@NotEmpty
	@Length(max = 1)
	private String s_estado_video;
	@ManyToOne
	@JoinColumn(name = "Sede_n_id_sede")
	private Sede Sede_n_id_sede;
	
	public Video() {
		super();
		this.s_estado_video = "A";
		this.Sede_n_id_sede = new Sede();
	}

	public Video(int n_id_video, @Length(max = 128) String s_enlace_video,
			@NotEmpty @Length(max = 1) String s_estado_video, Sede sede_n_id_sede) {
		super();
		this.n_id_video = n_id_video;
		this.s_enlace_video = s_enlace_video;
		this.s_estado_video = s_estado_video;
		Sede_n_id_sede = sede_n_id_sede;
	}

	public int getN_id_video() {
		return n_id_video;
	}

	public void setN_id_video(int n_id_video) {
		this.n_id_video = n_id_video;
	}

	public String getS_enlace_video() {
		return s_enlace_video;
	}

	public void setS_enlace_video(String s_enlace_video) {
		this.s_enlace_video = s_enlace_video;
	}

	public String getS_estado_video() {
		return s_estado_video;
	}

	public void setS_estado_video(String s_estado_video) {
		this.s_estado_video = s_estado_video;
	}

	public Sede getSede_n_id_sede() {
		return Sede_n_id_sede;
	}

	public void setSede_n_id_sede(Sede sede_n_id_sede) {
		Sede_n_id_sede = sede_n_id_sede;
	}
	
}
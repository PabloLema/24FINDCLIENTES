package com.demo.samt.parkdemo1;

import java.io.Serializable;
import java.util.Date;




public class Critica  {


	private int id;


	private String comentario;


	private int valoracion;


	private Date fechaActual;


	private Usuario usuario;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public int getValoracion() {
		return valoracion;
	}

	public void setValoracion(int valoracion) {
		this.valoracion = valoracion;
	}

	public Date getFechaActual() {
		return fechaActual;
	}

	public void setFechaActual(Date fechaActual) {
		this.fechaActual = fechaActual;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public String toString() {
		return "Critica{" +
				"id=" + id +
				", comentario='" + comentario + '\'' +
				", valoracion=" + valoracion +
				", fechaActual=" + fechaActual +
				", usuario=" + usuario +
				'}';
	}
}

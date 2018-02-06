package com.demo.samt.parkdemo1.Modelo;

import com.demo.samt.parkdemo1.Critica;
import com.demo.samt.parkdemo1.HorariosAtencion;
import com.demo.samt.parkdemo1.SubCategoria;
import com.demo.samt.parkdemo1.Telefono;

import java.util.ArrayList;
import java.util.List;


public class Local {


	private int id;

	private String nombre;


	private String direccion;



	private String descripcion;

	private String lat;

	private String lng;

	private List<Promocion> promociones;


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getLng() {
		return lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}



	public List<Promocion> getPromociones() {
		return promociones;
	}

	public void setPromociones(List<Promocion> promociones) {
		this.promociones = promociones;
	}
}

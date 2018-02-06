package com.demo.samt.parkdemo1.Modelo;


import java.util.List;

public class Parqueadero {



	private int id;

	private String nombre;

	private double tarifa;


	private String direccion;


	private int valoracion;



	private String lng;

	private String lat;

	private List<Imagenes> imangenesListP;
	private List<Telefono>telefonoListP;


	private int puestosDisponibles;


	public List<Imagenes> getImangenesListP() {
		return imangenesListP;
	}

	public void setImangenesListP(List<Imagenes> imangenesListP) {
		this.imangenesListP = imangenesListP;
	}

	public List<Telefono> getTelefonoListP() {
		return telefonoListP;
	}

	public void setTelefonoListP(List<Telefono> telefonoListP) {
		this.telefonoListP = telefonoListP;
	}

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

	public double getTarifa() {
		return tarifa;
	}

	public void setTarifa(double tarifa) {
		this.tarifa = tarifa;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public int getValoracion() {
		return valoracion;
	}

	public void setValoracion(int valoracion) {
		this.valoracion = valoracion;
	}

	public String getLng() {
		return lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public int getPuestosDisponibles() {
		return puestosDisponibles;
	}

	public void setPuestosDisponibles(int puestosDisponibles) {
		this.puestosDisponibles = puestosDisponibles;
	}
}

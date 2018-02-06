package com.demo.samt.parkdemo1;

import com.demo.samt.parkdemo1.Modelo.Parqueadero;

import java.util.ArrayList;
import java.util.List;

public class Usuario {

	private String usuario;
	private String contrasena;
	private String nombre;
	private String apellidos;
	private boolean admin;
	private List<Parqueadero> parqueaderos = new ArrayList<Parqueadero>();

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	public List<Parqueadero> getParqueaderos() {
		return parqueaderos;
	}

	public void setParqueaderos(List<Parqueadero> parqueaderos) {
		this.parqueaderos = parqueaderos;
	}

	@Override
	public String toString() {
		return "Usuario{" +
				"usuario='" + usuario + '\'' +
				", contrasena='" + contrasena + '\'' +
				", nombre='" + nombre + '\'' +
				", apellidos='" + apellidos + '\'' +
				", admin=" + admin +
				", parqueaderos=" + parqueaderos +
				'}';
	}

	public void addParqueadero(Parqueadero parqueadero){
		if(parqueaderos == null)
			parqueaderos = new ArrayList<Parqueadero>();
		parqueaderos.add(parqueadero);
	}



}

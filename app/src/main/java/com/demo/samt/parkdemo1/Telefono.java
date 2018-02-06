package com.demo.samt.parkdemo1;



public class Telefono  {



	private int id;

	private String numeroTelefono;

	private String tipo;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNumeroTelefono() {
		return numeroTelefono;
	}

	public void setNumeroTelefono(String numeroTelefono) {
		this.numeroTelefono = numeroTelefono;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}


	@Override
	public String toString() {
		return "Telefono{" +
				"id=" + id +
				", numeroTelefono='" + numeroTelefono + '\'' +
				", tipo='" + tipo + '\'' +
				'}';
	}
}

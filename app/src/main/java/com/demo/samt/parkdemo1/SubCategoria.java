package com.demo.samt.parkdemo1;




public class SubCategoria  {



	private int id;

	private String nombreSubCategoria;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombreSubCategoria() {
		return nombreSubCategoria;
	}

	public void setNombreSubCategoria(String nombreSubCategoria) {
		this.nombreSubCategoria = nombreSubCategoria;
	}

	@Override
	public String toString() {
		return "SubCategoria{" +
				"id=" + id +
				", nombreSubCategoria='" + nombreSubCategoria + '\'' +
				'}';
	}
}

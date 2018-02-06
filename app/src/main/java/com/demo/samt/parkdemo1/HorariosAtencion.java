package com.demo.samt.parkdemo1;



public class HorariosAtencion {


	private int id;

	private String dias;

	private String horaApertura;

	private String horaCierre;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDias() {
		return dias;
	}

	public void setDias(String dias) {
		this.dias = dias;
	}

	public String getHoraApertura() {
		return horaApertura;
	}

	public void setHoraApertura(String horaApertura) {
		this.horaApertura = horaApertura;
	}

	public String getHoraCierre() {
		return horaCierre;
	}

	public void setHoraCierre(String horaCierre) {
		this.horaCierre = horaCierre;
	}

	@Override
	public String toString() {
		return "HorariosAtencion{" +
				"id=" + id +
				", dias='" + dias + '\'' +
				", horaApertura='" + horaApertura + '\'' +
				", horaCierre='" + horaCierre + '\'' +
				'}';
	}
}

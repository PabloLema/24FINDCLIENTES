package com.demo.samt.parkdemo1;

import com.demo.samt.parkdemo1.Modelo.Parqueadero;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Created by Dario on 14/1/18.
 */

public class Cliente2 {


    private String ci;
    private String nombre;
    private String apellido;
    private String direccion;
    private String correo;
    private String usuarioCliente;
    private String passCliente;
    private List<Parqueadero> parqueaderos;

    public String getCi() {
        return ci;
    }

    public void setCi(String ci) {
        this.ci = ci;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getUsuarioCliente() {
        return usuarioCliente;
    }

    public void setUsuarioCliente(String usuarioCliente) {
        this.usuarioCliente = usuarioCliente;
    }

    public String getPassCliente() {
        return passCliente;
    }

    public void setPassCliente(String passCliente) {
        this.passCliente = passCliente;
    }

    public List<Parqueadero> getParqueaderos() {
        return parqueaderos;
    }

    public void setParqueaderos(List<Parqueadero> parqueaderos) {
        this.parqueaderos = parqueaderos;
    }

    @Override
    public String toString() {
        return "Cliente2{" +
                "ci='" + ci + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", direccion='" + direccion + '\'' +
                ", correo='" + correo + '\'' +
                ", usuarioCliente='" + usuarioCliente + '\'' +
                ", passCliente='" + passCliente + '\'' +
                ", parqueaderos=" + parqueaderos +
                '}';
    }

    public void addParqueadero(Parqueadero parqueadero) {
        if (this.parqueaderos == null)
            this.parqueaderos = new ArrayList<Parqueadero>();
        this.parqueaderos.add(parqueadero);
    }
}


package com.demo.samt.parkdemo1.Modelo;

public class Promocion {

    private int codigo;
    private String Promocion;

    private Local local;

    public int getCodigo() {
        return codigo;
    }
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    public String getPromocion() {
        return Promocion;
    }
    public void setPromocion(String promocion) {
        Promocion = promocion;
    }

    public Local getLocal() {
        return local;
    }

    public void setLocal(Local local) {
        this.local = local;
    }
}

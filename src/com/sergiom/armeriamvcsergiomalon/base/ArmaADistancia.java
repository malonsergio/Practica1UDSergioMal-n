package com.sergiom.armeriamvcsergiomalon.base;

public class ArmaADistancia extends Armas {
    int municion;
    double alcanceEfectivo;
    boolean usaPolvora;

    public double getAlcanceEfectivo() {
        return alcanceEfectivo;
    }

    public void setAlcanceEfectivo(double alcanceEfectivo) {
        this.alcanceEfectivo = alcanceEfectivo;
    }

    public int getMunicion() {
        return municion;
    }

    public void setMunicion(int municion) {
        this.municion = municion;
    }

    public boolean isUsaPolvora() {
        return usaPolvora;
    }

    public void setUsaPolvora(boolean usaPolvora) {
        this.usaPolvora = usaPolvora;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append(this.usaPolvora ? "Es un arma de fuego" : "Es un arma a distancia primitiva");
        sb.append(", Alcance efectivo del proyectil: " + this.alcanceEfectivo);
        sb.append(", Numero de proyectiles en carcaj o bolsa: " + this.municion);
        sb.append(", Comentarios: " + super.getDescripcion());

        return sb.toString();
    }
}

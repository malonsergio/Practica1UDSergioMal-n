package com.sergiom.armeriamvcsergiomalon.base;

import java.time.LocalDate;

public class ArmaADistancia extends Armas {
    public static final String[] modelosTradicionales = {"Ballesta", "Onagro", "Arco", "Honda"};
    public static final String[] modelosPolvora = {"Arcabuz", "Bombarda", "Mosquete", "Culebrina"};

    int municion;
    double alcanceEfectivo;
    boolean usaPolvora;

    public ArmaADistancia() {
    }

    public ArmaADistancia(String nombre, String modelo, String lugarDeFabricacion, String nombreFabricante,
                          String materiales, double precioEnEscudos, LocalDate fechaFabricacion,
                          int municion, double alcanceEfectivo, boolean usaPolvora) {
        super(nombre, modelo, lugarDeFabricacion, nombreFabricante, materiales, precioEnEscudos, fechaFabricacion);
        this.municion = municion;
        this.alcanceEfectivo = alcanceEfectivo;
        this.usaPolvora = usaPolvora;
    }

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

    public String isUsaPolvoraTexto() {
        return usaPolvora ? "Si" : "No";
    }



    public void setUsaPolvora(String textoXML) {
        if (textoXML.equals("Arma de fuego")) {
            usaPolvora = true;

        } else if (textoXML.equals("Arma tradicional")) {
            usaPolvora = false;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append(", Usa pólvora: " + isUsaPolvoraTexto());
        sb.append(", Alcance efectivo del proyectil: " + this.alcanceEfectivo);
        sb.append(", Numero de proyectiles: " + this.municion);

        return sb.toString();
    }
}

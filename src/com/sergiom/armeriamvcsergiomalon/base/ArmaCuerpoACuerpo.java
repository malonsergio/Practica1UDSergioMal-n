package com.sergiom.armeriamvcsergiomalon.base;

import java.time.LocalDate;

public class ArmaCuerpoACuerpo extends Armas {
    public static final String[] modelosUnfilo = {"Alabarda", "Alfanje", "Cimitarra", "Sable", "Maza", "Hacha"};
     public static final String[] modelosDosFilos = {"Claymore", "Estilete", "Gladius", "Spatha", "Estoque", "Macuahuitl"};

    boolean dosFilos;
    double longitudFilo;
    int estiloDeUso;


    public ArmaCuerpoACuerpo() {
    }


    public ArmaCuerpoACuerpo(String nombre, String modelo, String lugarDeFabricacion, String nombreFabricante,
                             String materiales, double precioEnEscudos,
                             LocalDate fechaFabricacion,
                             boolean dosFilos, double longitudFilo, String estiloDeUso) {
        super(nombre, modelo, lugarDeFabricacion, nombreFabricante, materiales, precioEnEscudos, fechaFabricacion);
        this.dosFilos = dosFilos;
        this.longitudFilo = longitudFilo;
        this.estiloDeUso = estiloDeUso.equalsIgnoreCase("Solo una mano") ? 1 : estiloDeUso.equalsIgnoreCase("Solo dos manos") ? 2 : 0;
    }

    public boolean isDosFilos() {
        return dosFilos;
    }


    public void setDosFilosTexto(String filos) {
        if (filos.equals("Dos filos")) {
            dosFilos = true;
        } else if (filos.equals("Uno o ningún filo")) {
            dosFilos = false;
        }
    }

    public double getLongitudFilo() {
        return longitudFilo;
    }

    public String isDosFilosTexto() {
        return dosFilos ? "Dos filos" : "Uno o ningún filo";
    }

    public void setLongitudFilo(double longitudFilo) {
        this.longitudFilo = longitudFilo;
    }

    public int getEstiloDeUso() {
        return estiloDeUso;
    }

    public String getEstiloDeUsoTexto() {
        return this.estiloDeUso == 1 ? "Solo una mano" : estiloDeUso == 2 ? "Solo dos manos" : "Hibrido";
    }

    public void setEstiloDeUso(String texto) {
        this.estiloDeUso = texto.equalsIgnoreCase("Solo una mano") ?
                1 : texto.equalsIgnoreCase("Solo dos manos") ?
                2 : 0;
    }



    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append(", Longitud de filo o hasta: " + this.longitudFilo);
        sb.append(", Estilo de uso: " + getEstiloDeUsoTexto());
        sb.append(", Numero de Filos: " + isDosFilosTexto());
        return sb.toString();
    }
}

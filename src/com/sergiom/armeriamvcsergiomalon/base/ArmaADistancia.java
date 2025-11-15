package com.sergiom.armeriamvcsergiomalon.base;

import java.time.LocalDate;

public class ArmaADistancia extends Armas {
    private static final String[] modelosTradicionales = { "Ballesta", "Onagro","Arco","Honda"};
    private static final String[] modelosPolvora = {"Arcabuz" ,"Bombarda","Mosquete","Culebrina"};

    int municion;
    double alcanceEfectivo;
    boolean usaPolvora;

    public ArmaADistancia() {
    }

    public ArmaADistancia(String nombre, String modelo, String lugarDeFabricacion, String nombreFabricante,
                          String materiales, double precioEnEscudos, LocalDate fechaFabricacion, String descripcion,
                          int municion, double alcanceEfectivo, boolean usaPolvora) {
        super(nombre, modelo, lugarDeFabricacion, nombreFabricante, materiales, precioEnEscudos, fechaFabricacion, descripcion);
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

    public boolean isUsaPolvora(String modelo){
        for(int i = 0; i< modelosPolvora.length;i++){
            if(modelosPolvora[i].equalsIgnoreCase(modelo)){
                return true;
            }
        }
        return false;
    }

    public void setUsaPolvora(boolean usaPolvora) {
        this.usaPolvora = usaPolvora;
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
        sb.append(this.usaPolvora ? "Es un arma de fuego" : "Es un arma tradicional");
        sb.append(", Alcance efectivo del proyectil: " + this.alcanceEfectivo);
        sb.append(", Numero de proyectiles en carcaj o bolsa: " + this.municion);
        sb.append(", Comentarios: " + super.getDescripcion());

        return sb.toString();
    }
}

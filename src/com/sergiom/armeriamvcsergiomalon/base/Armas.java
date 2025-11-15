package com.sergiom.armeriamvcsergiomalon.base;

import java.time.LocalDate;

public class Armas {
    private static final String[] modelos = {"Ropera", "Ballesta", "Arcabuz", "Onagro", "Claymore", "Alabarda", "Bombarda", "Estilete"};


    private String nombre;
    private String modelo;
    private String lugarDeFabricacion;
    private String nombreFabricante;
    private String materiales;
    private double precioEnEscudos;
    private LocalDate fechaFabricacion;
    private String descripcion;

    public Armas(){}

    public Armas(String nombre, String modelo, String lugarDeFabricacion, String nombreFabricante,
                 String materiales, double precioEnEscudos, LocalDate fechaFabricacion, String descripcion) {
        this.nombre = nombre;
        this.modelo = modelo;
        this.lugarDeFabricacion = lugarDeFabricacion;
        this.nombreFabricante = nombreFabricante;
        this.materiales = materiales;
        this.precioEnEscudos = precioEnEscudos;
        this.fechaFabricacion = fechaFabricacion;
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public static String[] getModelos() {
        return modelos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getLugarDeFabricacion() {
        return lugarDeFabricacion;
    }

    public void setLugarDeFabricacion(String lugarDeFabricacion) {
        this.lugarDeFabricacion = lugarDeFabricacion;
    }

    public String getNombreFabricante() {
        return nombreFabricante;
    }

    public void setNombreFabricante(String nombreFabricante) {
        this.nombreFabricante = nombreFabricante;
    }

    public String getMateriales() {
        return materiales;
    }

    public void setMateriales(String materiales) {
        this.materiales = materiales;
    }

    public double getPrecioEnEscudos() {
        return precioEnEscudos;
    }

    public void setPrecioEnEscudos(double precioEnEscudos) {
        this.precioEnEscudos = precioEnEscudos;
    }

    public LocalDate getFechaFabricacion() {
        return fechaFabricacion;
    }

    public void setFechaFabricacion(LocalDate fechaFabricacion) {
        this.fechaFabricacion = fechaFabricacion;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Nombre del arma: " + this.nombre);
        sb.append(", Modelo del arma: " + this.modelo);
        sb.append(", Fabricada en " + this.lugarDeFabricacion);
        sb.append(" por " + this.nombreFabricante);
        sb.append(", Materiales usados:  " + this.materiales);
        sb.append(" a fecha de: " + this.fechaFabricacion);
        sb.append(", precio total en escudos de oro: " + this.precioEnEscudos);
        return sb.toString();

    }
}

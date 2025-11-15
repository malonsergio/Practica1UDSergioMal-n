package com.sergiom.armeriamvcsergiomalon.base;

import java.time.LocalDate;

public class ArmaCuerpoACuerpo extends Armas {
    private static final String[] modelosUnfilo = {"Alabarda", "Alfanje", "Cimitarra","Sable","Maza","Hacha"};
    private static final String[] modelosDosFilos = {"Claymore", "Estilete","Gladius", "Spatha", "Estoque","Macuahuitl"};

    boolean unFilo;
    double longitudFilo;
    int estiloDeUso;


    public ArmaCuerpoACuerpo(){}

    public ArmaCuerpoACuerpo(String nombre, String modelo, String lugarDeFabricacion, String nombreFabricante,
                             String materiales, double precioEnEscudos,
                             LocalDate fechaFabricacion, String descripcion,
                             boolean unFilo, double longitudFilo, int estiloDeUso) {
        super(nombre, modelo, lugarDeFabricacion, nombreFabricante, materiales, precioEnEscudos, fechaFabricacion, descripcion);
        this.unFilo = unFilo;
        this.longitudFilo = longitudFilo;
        this.estiloDeUso = estiloDeUso;
    }

    public boolean isUnFilo() {
        return unFilo;
    }
    public boolean isUnFilo(String modelo){
        for(int i = 0; i<modelosUnfilo.length;i++){
            if(modelosUnfilo[i].equalsIgnoreCase(modelo)){
                return true;
            }
        }
        return false;
    }

    public void setUnFilo(boolean unFilo) {
        this.unFilo = unFilo;
    }

    public void setUnFilo(String filos){
        if (filos.equals("Un solo filo")){
            unFilo = true;
        } else if (filos.equals("Dos filos")){
            unFilo = false;
        }
    }

    public double getLongitudFilo() {
        return longitudFilo;
    }

    public void setLongitudFilo(double longitudFilo) {
        this.longitudFilo = longitudFilo;
    }

    public int getEstiloDeUso() {
        return estiloDeUso;
    }

    public void setEstiloDeUso(int estiloDeUso) {
        this.estiloDeUso = estiloDeUso;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append(", Longitud de filo o hasta: " + this.longitudFilo);
        sb.append( ", Estilo de uso: " + (estiloDeUso== 0? "una mano": estiloDeUso == 1? "dos manos" :"hibrido"));
        sb.append(unFilo? ", Con un solo filo" : "Con dos filos");
        sb.append(", Comentarios: "  + super.getDescripcion());
        return sb.toString();
    }
}

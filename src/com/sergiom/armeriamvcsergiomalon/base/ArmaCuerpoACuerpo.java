package com.sergiom.armeriamvcsergiomalon.base;

public class ArmaCuerpoACuerpo extends Armas {
    boolean unFilo;
    double longitudFilo;
    int estiloDeUso;

    public boolean isUnFilo() {
        return unFilo;
    }

    public void setUnFilo(boolean unFilo) {
        this.unFilo = unFilo;
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

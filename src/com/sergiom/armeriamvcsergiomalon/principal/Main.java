package com.sergiom.armeriamvcsergiomalon.principal;

import com.sergiom.armeriamvcsergiomalon.gui.Controlador;
import com.sergiom.armeriamvcsergiomalon.gui.Modelo;
import com.sergiom.armeriamvcsergiomalon.gui.Vista;

public class Main {
    public static void main(String[] args) {
        Vista vista =  new Vista();
        Modelo modelo = new Modelo();
        Controlador controlador = new Controlador(vista,modelo);



    }
}

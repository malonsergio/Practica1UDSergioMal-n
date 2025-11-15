package com.sergiom.armeriamvcsergiomalon.gui;

import com.sergiom.armeriamvcsergiomalon.base.ArmaCuerpoACuerpo;
import com.sergiom.armeriamvcsergiomalon.base.Armas;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;

public class Controlador implements ActionListener, ListSelectionListener, WindowListener {
    private Vista vista;
    private Modelo modelo;
    private File ultimoPath;


    public Controlador(Vista vista, Modelo modelo) {
        this.vista = vista;
        this.modelo = modelo;
    }


    public boolean camposVacios() {
        for (int i = 0; i < vista.listaCamposTexto.length; i++) {
            if (vista.listaCamposTexto[i].getText().equals(null)) {
                return true;
            }
        }
        if (vista.fechaFabricacionDP.getText().equals(null)) {
            return true;
        }
        return false;
    }

    public void refresh() {
        vista.dlmArmas.clear();
        for (Armas a : modelo.getListaArmas()) {
            vista.dlmArmas.addElement(a);
        }
    }
    public void limpiarCampos() {
        for (int i = 0; i < vista.listaCamposTexto.length; i++) {
            vista.listaCamposTexto[i].setText(null);

        }

        vista.fechaFabricacionDP.setDate(null);
    }

    private void cargarDatosConfiguracion() throws IOException {
        Properties configuracion = new Properties();
        configuracion.load(new FileReader("armas.conf"));
        ultimoPath = new File(configuracion.getProperty("ultimaRutaExportada"));
    }

    private void actualizarDatosConfiguracion(File ultimaRutaExportada) {
        this.ultimoPath = ultimaRutaExportada;
    }

    private void guardarConfiguracion() throws IOException {
        Properties configuracion = new Properties();
        configuracion.setProperty("ultimaRutaExportada", ultimoPath.getAbsolutePath());
        configuracion.store(new PrintWriter("armas.conf"), "Datos configuracion arma");
    }

    public void addActionListener(ActionListener listener){
        vista.nuevoBtn.addActionListener(listener);
        vista.borrarBtn.addActionListener(listener);
        vista.radioButtonArmaADistancia.addActionListener(listener);
        vista.radioButtonCuerpoACuerpo.addActionListener(listener);

    }
    @Override
    public void actionPerformed(ActionEvent actionEvent) {

    }

    @Override
    public void windowOpened(WindowEvent windowEvent) {

    }

    @Override
    public void windowClosing(WindowEvent windowEvent) {

    }

    @Override
    public void windowClosed(WindowEvent windowEvent) {

    }

    @Override
    public void windowIconified(WindowEvent windowEvent) {

    }

    @Override
    public void windowDeiconified(WindowEvent windowEvent) {

    }

    @Override
    public void windowActivated(WindowEvent windowEvent) {

    }

    @Override
    public void windowDeactivated(WindowEvent windowEvent) {

    }

    @Override
    public void valueChanged(ListSelectionEvent listSelectionEvent) {
        if (listSelectionEvent.getValueIsAdjusting()) {
            Armas seleccionado = vista.list1.getSelectedValue();
            vista.textFieldNombre.setText(seleccionado.getNombre());
            vista.comboBoxModelo.setSelectedItem(seleccionado.getModelo());
            vista.textFieldLugar.setText(seleccionado.getLugarDeFabricacion());
            vista.textFieldMaterial.setText(seleccionado.getMateriales());
            vista.textFieldPrecio.setText(String.valueOf(seleccionado.getPrecioEnEscudos()));
            vista.fechaFabricacionDP.setText(String.valueOf(seleccionado.getFechaFabricacion()));
            vista.descripcionTxt.setText(seleccionado.getDescripcion());

            if (seleccionado instanceof ArmaCuerpoACuerpo) {
                vista.radioButtonCuerpoACuerpo.doClick();
                vista.longAlcanceTxt.setText(String.valueOf(((ArmaCuerpoACuerpo) seleccionado).getLongitudFilo()));
                if(vista)
                vista.longAlcanceTxt.setText(String.valueOf(((ArmaCuerpoACuerpo) seleccionado).getLongitudFilo()));

            } else {
                vista.motoRadioButton.doClick();
                vista.kmPlazasTxt.setText(String.valueOf(((Moto) seleccionado).getKm()));
            }
        }
    }
}

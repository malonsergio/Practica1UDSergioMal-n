package com.sergiom.armeriamvcsergiomalon.gui;

import com.sergiom.armeriamvcsergiomalon.base.ArmaADistancia;
import com.sergiom.armeriamvcsergiomalon.base.ArmaCuerpoACuerpo;
import com.sergiom.armeriamvcsergiomalon.base.Armas;
import com.sergiom.armeriamvcsergiomalon.utils.Utils;
import com.sun.tools.javac.Main;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.*;
import java.util.Arrays;
import java.util.Properties;

public class Controlador implements ActionListener, ListSelectionListener, WindowListener {
    private Vista vista;
    private Modelo modelo;
    private File ultimoPath;


    public Controlador(Vista vista, Modelo modelo) {
        this.vista = vista;
        this.modelo = modelo;
        addListSelectionListener(this);
        addActionListener(this);
        addWindowListener(this);
        vista.radioButtonCuerpoACuerpo.doClick();


        try {
            cargarDatosConfiguracion();

        } catch (IOException e) {
            System.out.println("Error");
        }
    }

    private void addWindowListener(WindowListener listener) {
        vista.frame.addWindowListener(listener);
    }

    private void addListSelectionListener(ListSelectionListener listener) {
        vista.list1.addListSelectionListener(listener);
    }

    public boolean camposVaciosADistancia() {
        for (int i = 0; i < vista.listaCamposTextoADistancia.length; i++) {
            String[] campos = {"Nombre", "Lugar de fabricación", " Fabricante", "Material", "Precio", "Munición", "Alcance"};
            if (vista.listaCamposTextoADistancia[i].getText().equals("")) {
                Utils.mensajeError("El campo '" + campos[i] + "' esta vacio");
                return true;
            }
        }
        if (fechaVacia()) return true;
        return false;
    }


    public boolean camposVaciosCaC() {
        for (int i = 0; i < vista.listaCamposTextoCaC.length; i++) {
            String[] campos = {"Nombre", "Lugar de fabricación", " Fabricante", "Material", "Precio", "Longitud del filo"};
            if (vista.listaCamposTextoCaC[i].getText().equals("")) {
                Utils.mensajeError("El campo '" + campos[i] + "' esta vacio");
                return true;
            }
        }
        if (fechaVacia()) return true;
        return false;
    }

    private boolean fechaVacia() {
        if (vista.fechaFabricacionDP.getText().equals("")) {
            Utils.mensajeError("El campo 'Fecha de fabricación' esta vacio o tiene datos no válidos");

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
        for (int i = 0; i < vista.listaCamposTextoADistancia.length; i++) {
            vista.listaCamposTextoADistancia[i].setText(null);

        }

        vista.fechaFabricacionDP.setDate(null);
    }

    private void cargarDatosConfiguracion() throws IOException {
        Properties configuracion = new Properties();
        configuracion.load(new FileReader("armas.conf"));
        ultimoPath = new File(configuracion.getProperty("ultimaRutaExportada"));
    }



    private void guardarConfiguracion() {
        try {
            Properties configuracion = new Properties();
            configuracion.setProperty("ultimaRutaExportada", ultimoPath.getAbsolutePath());

            String userHome = System.getProperty("user.home");
            File configFile = new File(userHome, "armas.conf");

            try (FileWriter writer = new FileWriter(configFile)) {
                configuracion.store(writer, "Datos configuracion arma");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void addActionListener(ActionListener listener) {
        vista.nuevoBtn.addActionListener(listener);
        vista.borrarBtn.addActionListener(listener);
        vista.sobreescribirTxt.addActionListener(listener);
        vista.radioButtonArmaADistancia.addActionListener(listener);
        vista.radioButtonCuerpoACuerpo.addActionListener(listener);
        vista.itemExportar.addActionListener(listener);
        vista.itemImportar.addActionListener(listener);

    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        String command = actionEvent.getActionCommand();
        int respuesta;
        switch (command) {
            case "Nuevo":

                if (modelo.buscarArma(vista.textFieldNombre.getText()) != null) {
                    Utils.mensajeError("Dos armas no se pueden llamar igual");
                    break;

                }
                try {
                    if (vista.radioButtonCuerpoACuerpo.isSelected()) {
                        if (camposVaciosCaC()) {
                            break;
                        }
                        modelo.altaArmaCaC(vista.textFieldNombre.getText(), vista.comboBoxModelo.getSelectedItem().toString()
                                , vista.textFieldLugar.getText(), vista.textFieldFabricante.getText(), vista.textFieldMaterial.getText(),
                                Double.parseDouble(vista.textFieldPrecio.getText()), vista.fechaFabricacionDP.getDate(), vista.checkBoxPolvoraFilo.isSelected() ? true : false,
                                Double.parseDouble(vista.longAlcanceTxt.getText()), String.valueOf(vista.dcbEstilos.getSelectedItem()));

                    } else if (vista.radioButtonArmaADistancia.isSelected()) {
                        if (camposVaciosADistancia()) {
                            break;
                        }
                        modelo.altaArmaADistancia(vista.textFieldNombre.getText(), vista.comboBoxModelo.getSelectedItem().toString()
                                , vista.textFieldLugar.getText(), vista.textFieldFabricante.getText(), vista.textFieldMaterial.getText(),
                                Double.parseDouble(vista.textFieldPrecio.getText()), vista.fechaFabricacionDP.getDate(), Integer.parseInt(vista.municionTxt.getText()),
                                Double.parseDouble(vista.longAlcanceTxt.getText()), vista.checkBoxPolvoraFilo.isSelected());

                    }
                } catch (NumberFormatException e) {
                    Utils.mensajeError("Por favor, introduce números válidos en los campos correspondientes");
                }
                limpiarCampos();
                refresh();
                break;
            case "Borrar":
                respuesta = Utils.mensajeConfirmacion("Seguro que deseas eliminar tu: " +
                        String.valueOf(vista.comboBoxModelo.getSelectedItem()), "¿Estas seguro?");
                if (respuesta == JOptionPane.OK_OPTION) {
                    modelo.borrarArma(vista.textFieldNombre.getText());
                    limpiarCampos();
                    refresh();
                }
                break;
            case "Sobreescribir":
                try {
                    Armas aCambiar = modelo.buscarArma(vista.textFieldNombre.getText());
                    respuesta = Utils.mensajeConfirmacion("Confirma que quieres sobrescribir datos", "¿Estás seguro?");
                    if (respuesta == JOptionPane.OK_OPTION) {
                        aCambiar.setModelo((String) vista.comboBoxModelo.getSelectedItem());
                        aCambiar.setFechaFabricacion(vista.fechaFabricacionDP.getDate());
                        aCambiar.setLugarDeFabricacion(vista.textFieldLugar.getText());
                        aCambiar.setPrecioEnEscudos(Double.parseDouble(vista.textFieldPrecio.getText()));
                        aCambiar.setMateriales(vista.textFieldMaterial.getText());
                        aCambiar.setNombreFabricante(vista.textFieldFabricante.getText());
                        if (aCambiar instanceof ArmaCuerpoACuerpo) {
                            ((ArmaCuerpoACuerpo) aCambiar).setLongitudFilo(Double.parseDouble(vista.longAlcanceTxt.getText()));
                            ((ArmaCuerpoACuerpo) aCambiar).setEstiloDeUso((String) vista.combooBoxEstilo.getSelectedItem());
                        } else if (aCambiar instanceof ArmaADistancia) {
                            ((ArmaADistancia) aCambiar).setMunicion(Integer.parseInt(vista.municionTxt.getText()));
                            ((ArmaADistancia) aCambiar).setAlcanceEfectivo(Double.parseDouble(vista.longAlcanceTxt.getText()));
                        }

                        refresh();
                        vista.sobreescribirTxt.setEnabled(false);
                    }
                } catch (NumberFormatException e) {
                    Utils.mensajeError("Por favor, introduce números válidos en los campos correspondientes");

                }
                break;
            case "A distancia":

                vista.llenarModelosADistancia();
                vista.mostrarTextoMunicion();
                accionComboBoxADistancia();
                vista.municionEstiloLbl.setText("Munición");
                vista.alcanceLongitud.setText("Alcance efectivo(m)");
                vista.usaPolvoraDosFilos.setText("Usa pólvora");
                break;
            case "Cuerpo a Cuerpo":
                vista.llenarModelosCuerpoACuerpo();
                vista.mostrarComboEstilos();
                accionComboBoxCaC();
                vista.municionEstiloLbl.setText("Estilo de uso");
                vista.alcanceLongitud.setText("Longitud arma (cm)");
                vista.usaPolvoraDosFilos.setText("Dos filos");
                break;
            case "Exportar":
                JFileChooser selectorFichero2 = Utils.crearSelectorFichero(ultimoPath,
                        "Archivos XML", "xml");
                int opc2 = selectorFichero2.showSaveDialog(null);
                if (opc2 == JFileChooser.APPROVE_OPTION) {
                    File archivo = selectorFichero2.getSelectedFile();
                    if (!archivo.getName().toLowerCase().endsWith(".xml")) {
                        archivo = new File(archivo.getParentFile(), archivo.getName() + ".xml");
                    }
                    modelo.exportarXML(archivo);
                }
                break;
            case "Importar":
                JFileChooser selectorFicheros = Utils.crearSelectorFichero(ultimoPath,
                        "Archivos XML", "xml");
                int op = selectorFicheros.showOpenDialog(null);
                if (op == JFileChooser.APPROVE_OPTION) {
                    modelo.importarXML(selectorFicheros.getSelectedFile());
                    refresh();

                }


        }
    }

    public void accionComboBoxADistancia() {
        removeActionListenerComboBox();
        vista.comboBoxModelo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (vista.comboBoxModelo.getSelectedItem() == null) {
                    return;
                }

                if (Arrays.toString(ArmaADistancia.modelosPolvora).contains(vista.comboBoxModelo.getSelectedItem().toString())) {
                    vista.checkBoxPolvoraFilo.setSelected(true);
                } else {
                    vista.checkBoxPolvoraFilo.setSelected(false);
                }
            }
        });
    }

    public void removeActionListenerComboBox() {
        for (ActionListener a : vista.comboBoxModelo.getActionListeners()) {
            vista.comboBoxModelo.removeActionListener(a);
        }
    }

    public void accionComboBoxCaC() {
        removeActionListenerComboBox();
        vista.comboBoxModelo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (vista.comboBoxModelo.getSelectedItem() == null) {
                    return;
                }
                if (Arrays.toString(ArmaCuerpoACuerpo.modelosDosFilos).contains(vista.comboBoxModelo.getSelectedItem().toString())) {
                    vista.checkBoxPolvoraFilo.setSelected(true);
                } else {
                    vista.checkBoxPolvoraFilo.setSelected(false);
                }
            }
        });

    }

    @Override
    public void windowOpened(WindowEvent windowEvent) {

    }

    @Override
    public void windowClosing(WindowEvent windowEvent) {
        int respuesta = Utils.mensajeConfirmacion("¿Desea cerrar ventana?", "Salir");
        if (respuesta == JOptionPane.YES_OPTION) {
            guardarConfiguracion();
            System.exit(0);


        }

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
        if (!listSelectionEvent.getValueIsAdjusting()) {
            vista.sobreescribirTxt.setEnabled(true);
            if (vista.list1.getSelectedIndex() != -1) {
                vista.textFieldNombre.setEnabled(false);
                Armas seleccionado = vista.list1.getSelectedValue();
                vista.textFieldNombre.setText(seleccionado.getNombre());
                vista.textFieldLugar.setText(seleccionado.getLugarDeFabricacion());
                vista.textFieldFabricante.setText(seleccionado.getNombreFabricante());
                vista.textFieldMaterial.setText(seleccionado.getMateriales());
                vista.fechaFabricacionDP.setDate(seleccionado.getFechaFabricacion());
                vista.textFieldPrecio.setText(String.valueOf(seleccionado.getPrecioEnEscudos()));

                if (seleccionado instanceof ArmaCuerpoACuerpo) {
                    vista.radioButtonCuerpoACuerpo.doClick();
                    vista.comboBoxModelo.setSelectedItem(seleccionado.getModelo());
                    vista.longAlcanceTxt.setText(String.valueOf(((ArmaCuerpoACuerpo) seleccionado).getLongitudFilo()));
                    vista.combooBoxEstilo.setSelectedItem(((ArmaCuerpoACuerpo) seleccionado).getEstiloDeUsoTexto());
                    if (((ArmaCuerpoACuerpo) seleccionado).isDosFilos()) {
                        vista.checkBoxPolvoraFilo.isSelected();
                    }
                } else if (seleccionado instanceof ArmaADistancia) {
                    vista.comboBoxModelo.setSelectedItem(seleccionado.getModelo());
                    vista.radioButtonArmaADistancia.doClick();
                    vista.longAlcanceTxt.setText(String.valueOf(((ArmaADistancia) seleccionado).getAlcanceEfectivo()));
                    vista.municionTxt.setText(String.valueOf(((ArmaADistancia) seleccionado).getMunicion()));
                    if (((ArmaADistancia) seleccionado).isUsaPolvora()) {
                        vista.checkBoxPolvoraFilo.isSelected();
                    }
                }
            } else {
                vista.textFieldNombre.setEnabled(true);
            }

        }
    }
}

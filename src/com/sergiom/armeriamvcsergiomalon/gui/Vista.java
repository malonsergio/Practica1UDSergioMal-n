package com.sergiom.armeriamvcsergiomalon.gui;

import com.github.lgooddatepicker.components.DatePicker;
import com.sergiom.armeriamvcsergiomalon.base.Armas;

import javax.swing.*;

public class Vista {
    private static final String[] todosModelos = {"Ballesta", "Onagro", "Arco", "Honda", "Arcabuz", "Bombarda",
            "Mosquete", "Culebrina", "Alabarda", "Alfanje", "Cimitarra", "Sable", "Maza", "Hacha", "Claymore",
            "Estilete", "Gladius", "Spatha", "Estoque", "Macuahuitl"};
    private static final String[] modelosADistancia = {"Ballesta", "Onagro", "Arco", "Honda", "Arcabuz", "Bombarda", "Mosquete", "Culebrina"};
    private static final String[] modelosCuerpoACuerpo = {"Alabarda", "Alfanje", "Cimitarra", "Sable", "Maza", "Hacha", "Claymore", "Estilete", "Gladius", "Spatha", "Estoque", "Macuahuitl"};
    private static final String[] estilosCuerpoACuerpo = {"Solo una mano", "Solo dos manos", "Hibrido"};
    private JPanel panel1;
    JRadioButton radioButtonArmaADistancia;
    JRadioButton radioButtonCuerpoACuerpo;
    JTextField textFieldNombre;
    JTextField textFieldLugar;
    JTextField textFieldFabricante;
    JTextField textFieldMaterial;
    JTextField textFieldPrecio;
    JCheckBox checkBoxPolvoraFilo;
    JComboBox comboBoxModelo;
    JComboBox combooBoxEstilo;
    JLabel alcanceLongitud;
    JLabel usaPolvoraDosFilos;
    JLabel municionEstiloLbl;
    JList<Armas> list1;
    DatePicker fechaFabricacionDP;
    JTextField municionTxt;
    JTextField longAlcanceTxt;
    JButton nuevoBtn;
    JButton borrarBtn;
    private JPanel panelMunEst;
    JButton sobreescribirTxt;
    JFrame frame;
    public JTextField[] listaCamposTextoADistancia = {textFieldNombre, textFieldLugar, textFieldFabricante, textFieldMaterial,
            textFieldPrecio, municionTxt, longAlcanceTxt};
    public JTextField[] listaCamposTextoCaC = {textFieldNombre, textFieldLugar, textFieldFabricante, textFieldMaterial,
            textFieldPrecio, longAlcanceTxt};
    DefaultComboBoxModel<String> dcbModelos;
    DefaultComboBoxModel<String> dcbEstilos;
    DefaultListModel<Armas> dlmArmas;
    JMenuItem itemImportar;
    JMenuItem itemExportar;
    JMenuBar barra;
    JMenu menu;

    public Vista() {
        frame = new JFrame("ArmeriaMVC");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ImageIcon img = new ImageIcon("img/icono.png");
        frame.setIconImage(img.getImage());
        frame.pack();
        frame.setVisible(true);
        llenarModelos();
        initList();
        sobreescribirTxt.setEnabled(false);
        checkBoxPolvoraFilo.setEnabled(false);
        radioButtonArmaADistancia.setSelected(true);
        dcbModelos = new DefaultComboBoxModel<>();
        dcbEstilos = new DefaultComboBoxModel<>();
        crearBarraMenu();

    }

    public void initList() {
        dlmArmas = new DefaultListModel<>();
        list1.setModel(dlmArmas);
    }

    private void crearBarraMenu() {
        barra = new JMenuBar();
        menu = new JMenu("Archivo");
        itemExportar = new JMenuItem("Exportar");
        itemImportar = new JMenuItem("Importar");
        itemExportar.setActionCommand("Exportar");
        itemImportar.setActionCommand("Importar");
        menu.add(itemExportar);
        menu.add(itemImportar);
        barra.add(menu);
        frame.setJMenuBar(barra);
        frame.revalidate();
        frame.repaint();
    }


    public void llenarModelos() {
        for (int i = 0; i < todosModelos.length; i++) {
            comboBoxModelo.addItem(todosModelos[i]);
        }
    }

    public void llenarModelosADistancia() {
        comboBoxModelo.removeAllItems();
        for (int i = 0; i < modelosADistancia.length; i++) {
            comboBoxModelo.addItem(modelosADistancia[i]);
        }
    }

    public void llenarModelosCuerpoACuerpo() {
        comboBoxModelo.removeAllItems();
        for (int i = 0; i < modelosCuerpoACuerpo.length; i++) {
            comboBoxModelo.addItem(modelosCuerpoACuerpo[i]);
        }
    }

    public void mostrarTextoMunicion() {
        panelMunEst.removeAll();
        panelMunEst.revalidate();
        panelMunEst.repaint();
        panelMunEst.add(municionTxt);
    }

    public void mostrarComboEstilos() {
        panelMunEst.removeAll();
        panelMunEst.revalidate();
        panelMunEst.repaint();
        combooBoxEstilo = new JComboBox();
        panelMunEst.add(combooBoxEstilo);
        dcbEstilos.removeAllElements();
        for (int i = 0; i < estilosCuerpoACuerpo.length; i++) {
            dcbEstilos.addElement(estilosCuerpoACuerpo[i]);
        }
        combooBoxEstilo.setModel(dcbEstilos);
    }


}

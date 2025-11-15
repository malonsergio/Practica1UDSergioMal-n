package com.sergiom.armeriamvcsergiomalon.gui;

import com.github.lgooddatepicker.components.DatePicker;
import com.sergiom.armeriamvcsergiomalon.base.Armas;

import javax.swing.*;
import java.util.ArrayList;

public class Vista {
    private static final String[] modelos = {"Ropera", "Ballesta", "Arcabuz", "Onagro", "Claymore", "Alabarda", "Bombarda", "Estilete"};
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
    JLabel alcanceLongitud;
    JLabel usaPolvoraUnFilo;
    JLabel municionEstiloLbl;
    JList<Armas> list1;
    DatePicker fechaFabricacionDP;
    JTextField municionEstiloTxt;
    JTextField longAlcanceTxt;
     JButton nuevoBtn;
     JButton borrarBtn;
     JTextArea descripcionTxt;
    private JFrame frame;
    JTextField[] listaCamposTexto = {textFieldNombre, textFieldLugar, textFieldFabricante, textFieldMaterial,
            textFieldPrecio, municionEstiloTxt, longAlcanceTxt};
    DefaultComboBoxModel<String> dcbModelos;
    DefaultListModel<Armas> dlmArmas;

    public Vista() {
        frame = new JFrame("ArmeriaMVC");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initComponents();
        frame.pack();
        frame.setVisible(true);
    }

    public void initComponents() {
        dlmArmas = new DefaultListModel<>();
        dcbModelos = new DefaultComboBoxModel<>();
        list1.setModel(dlmArmas);
        for (int i = 0; i < modelos.length; i++) {
            dcbModelos.addElement(modelos[i]);
        }
        comboBoxModelo.setModel(dcbModelos);
    }


}

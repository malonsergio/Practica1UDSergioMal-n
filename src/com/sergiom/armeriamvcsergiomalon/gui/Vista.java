package com.sergiom.armeriamvcsergiomalon.gui;

import com.sergiom.armeriamvcsergiomalon.base.Armas;

import javax.swing.*;
import java.util.ArrayList;

public class Vista {
    private JPanel panel1;
    private JRadioButton radioButtonArmaADistancia;
    private JRadioButton radioButtonCuerpoACuerpo;
    private JTextField textFieldNombre;
    private JTextField textFieldLugar;
    private JTextField textFieldFabricante;
    private JTextField textFieldMaterial;
    private JTextField textFieldPrecio;
    private JCheckBox checkBoxPolvora;
    private JComboBox comboBoxModelo;
    private JLabel alcanceLongitud;
    private JLabel usaPolvoraUnFilo;
    private JLabel municionEstilo;
    private JList<Armas> list1;
    JFrame frame;

    DefaultListModel<Armas> dlmArmas;

    public Vista() {
        frame = new JFrame("ArmeriaMVC");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initComponents();
        frame.pack();
        frame.setVisible(true);
    }

    public void initComponents(){
        dlmArmas = new DefaultListModel<>();
        list1.setModel(dlmArmas);
    }

}

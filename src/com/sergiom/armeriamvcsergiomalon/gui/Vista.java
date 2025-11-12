package com.sergiom.armeriamvcsergiomalon.gui;

import com.sergiom.armeriamvcsergiomalon.base.Armas;

import javax.swing.*;
import java.util.ArrayList;

public class Vista {
    private JPanel panel1;
    private JRadioButton radioButtonArmaADistancia;
    private JRadioButton radioButtonCuerpoACuerpo;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JCheckBox checkBox1;
    private JComboBox comboBox1;
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

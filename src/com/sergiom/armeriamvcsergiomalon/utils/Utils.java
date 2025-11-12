package com.sergiom.armeriamvcsergiomalon.utils;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;

public class Utils {

    public static void mensajeError(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public static void mensajeConfirmacion(String mensaje, String titulo) {
        JOptionPane.showConfirmDialog(null, mensaje, titulo, JOptionPane.YES_NO_OPTION);
    }

    public static JFileChooser crearSelectorFichero(File ruta, String tipoArchivo, String extension) {
        JFileChooser selectorArchivo = new JFileChooser();
        if (ruta != null) {
            selectorArchivo.setCurrentDirectory(ruta);
        }
        if (extension != null && tipoArchivo != null) {
            FileNameExtensionFilter filtro = new FileNameExtensionFilter(tipoArchivo, extension);
            selectorArchivo.setFileFilter(filtro);
        }
        return selectorArchivo;
    }
}

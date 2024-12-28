/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package algebraproyecto;

import java.awt.BorderLayout;
import java.awt.Color;

import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author User
 */
public class ProcedimientoDeterminante {
    private static JPanel panelTablas;
    private static JFrame frame;

    // Bloque de inicialización estática
    private static void inicializarVentana() {
        frame = new JFrame("ProcedimientoDeterminante");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Solo cierra la ventana
        frame.setSize(800, 600); // Tamaño del JFrame

        // Panel principal para organizar las tablas
        panelTablas = new JPanel();
        panelTablas.setLayout(new GridLayout(0, 3, 10, 10)); // 3 columnas, espacio horizontal y vertical

        // Agregar el panel principal a un JScrollPane
        JScrollPane scrollPanePrincipal = new JScrollPane(panelTablas);
        frame.add(scrollPanePrincipal);

        // Mostrar la ventana principal
        frame.setVisible(true);
    }

    public static void generarTabla(double[][] subMatrix, String info) {
        if (frame == null || !frame.isDisplayable()) {
            inicializarVentana(); // Recrear la ventana si fue cerrada
        }

        // Inicializar datos y nombres de las columnas
        Object[][] datos = new Object[subMatrix.length][subMatrix[0].length];
        String[] nombre = new String[subMatrix[0].length];

        // Llenar los datos y los nombres de las columnas
        for (int i = 0; i < subMatrix.length; i++) {
            for (int j = 0; j < subMatrix[i].length; j++) {
                datos[i][j] = subMatrix[i][j]; // Copiar valores de subMatrix a datos
            }
        }

        for (int i = 0; i < nombre.length; i++) {
            nombre[i] = "Col: " + i; // Asignar nombres a las columnas
        }

        // Crear el modelo de tabla
        DefaultTableModel modelo = new DefaultTableModel(datos, nombre);

        // Crear la tabla y establecer tamaño fijo
        JTable tabla = new JTable(modelo);
        tabla.setPreferredScrollableViewportSize(new Dimension(200, 70)); // Ajustar tamaño de la tabla
        tabla.setFillsViewportHeight(true);

        // Crear un JScrollPane para la tabla
        JScrollPane scrollPane = new JScrollPane(tabla);

        // Crear un nuevo panel para esta tabla
        JPanel panelTabla = new JPanel(new BorderLayout());
        panelTabla.add(scrollPane, BorderLayout.CENTER);
        panelTabla.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Borde alrededor de cada tabla

        // Crear una etiqueta debajo de la tabla
        JLabel etiqueta = new JLabel(info);
        etiqueta.setHorizontalAlignment(SwingConstants.CENTER);
        panelTabla.add(etiqueta, BorderLayout.SOUTH);

        // Agregar el panel de la tabla al panel principal
        panelTablas.add(panelTabla); // Agregar al final, respetando el orden

        // Actualizar la interfaz gráfica
        panelTablas.revalidate();
        panelTablas.repaint();
    }
}



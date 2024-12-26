/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package algebraproyecto;

import java.awt.Component;
import java.awt.GridLayout;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author elian
 */
public class CalculoInversa {

    public void inversa(Tabla tabla) {
        String input = tabla.textField.getText();
        if (!input.isEmpty()) {
            try {
                Component[] components = tabla.matrixPanel.getComponents();
                int size = (int) Math.sqrt(components.length);
                double[][] matrix = new double[size][size];

                for (int i = 0; i < size; i++) {
                    for (int j = 0; j < size; j++) {
                        JTextField cell = (JTextField) components[i * size + j];
                        matrix[i][j] = Double.parseDouble(cell.getText());
                    }
                }

                double determinant = CalculoDeterminante.calculateDeterminant(matrix);
                if (determinant == 0) {
                    JOptionPane.showMessageDialog(tabla.frame, "La matriz no tiene inversa (determinante es 0).", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                double[][] adjoint = CalculoAdjunta.calculateAdjoint(matrix);
                double[][] inverse = new double[size][size];

                for (int i = 0; i < size; i++) {
                    for (int j = 0; j < size; j++) {
                        inverse[i][j] = adjoint[i][j] / determinant;
                    }
                }

                tabla.inversePanel.removeAll();
                tabla.inversePanel.setLayout(new GridLayout(size, size));

                for (int i = 0; i < size; i++) {
                    for (int j = 0; j < size; j++) {
                        JTextField cell = new JTextField(String.valueOf(inverse[i][j]));
                        cell.setHorizontalAlignment(JTextField.CENTER);
                        cell.setEditable(false);
                        tabla.inversePanel.add(cell);
                    }
                }

                tabla.inversePanel.revalidate();
                tabla.inversePanel.repaint();
                tabla.invFrame.setVisible(true);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(tabla.frame, "Asegúrese de llenar correctamente todos los valores de la matriz.", "Error", JOptionPane.ERROR_MESSAGE);
            }
            
        } else {
            JOptionPane.showMessageDialog(tabla.frame, "Por favor, ingrese un número.", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }
}

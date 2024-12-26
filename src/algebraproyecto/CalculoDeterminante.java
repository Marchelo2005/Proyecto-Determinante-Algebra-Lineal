/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package algebraproyecto;

import java.awt.Component;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author elian
 */
public class CalculoDeterminante {
    public void determinante(Tabla tabla){
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

                double determinant = calculateDeterminant(matrix);
                tabla.resultArea.setText("Determinante: " + determinant);
                JOptionPane.showMessageDialog(tabla.frame,"la determinante es: "+determinant);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(tabla.frame, "Asegúrese de llenar correctamente todos los valores de la matriz.", "Error", JOptionPane.ERROR_MESSAGE);
            }
            } else {
                JOptionPane.showMessageDialog(tabla.frame, "Por favor, ingrese un número.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        
    }
    public static double calculateDeterminant(double[][] matrix) {
        int size = matrix.length;
        if (size == 1) {
            return matrix[0][0];
        }
        if (size == 2) {
            return matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0];
        }

        double determinant = 0;
        for (int i = 0; i < size; i++) {
            double[][] subMatrix = new double[size - 1][size - 1];
            for (int j = 1; j < size; j++) {
                for (int k = 0, col = 0; k < size; k++) {
                    if (k != i) {
                        subMatrix[j - 1][col++] = matrix[j][k];
                    }
                }
            }
            //pow = potencia donde (-1)es el valor a potenciar e i es el valor al cual será potenciado
            determinant += Math.pow(-1, i) * matrix[0][i] * calculateDeterminant(subMatrix);
        }
        return determinant;
    }
}

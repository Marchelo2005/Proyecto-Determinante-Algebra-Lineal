package algebraproyecto;

import java.awt.Component;
import java.awt.GridLayout;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class CalculoAdjunta {

    public void adjunta(Tabla tabla) {
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

                double[][] adjoint = calculateAdjoint(matrix);
                tabla.adjointPanel.removeAll();
                tabla.adjointPanel.setLayout(new GridLayout(size, size));

                for (int i = 0; i < size; i++) {
                    for (int j = 0; j < size; j++) {
                        JTextField cell = new JTextField(String.valueOf(adjoint[i][j]));
                        cell.setHorizontalAlignment(JTextField.CENTER);
                        cell.setEditable(false);
                        tabla.adjointPanel.add(cell);
                    }
                }

                tabla.adjointPanel.revalidate();
                tabla.adjointPanel.repaint();
                tabla.adjFrame.setVisible(true);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(tabla.frame, "Asegúrese de llenar correctamente todos los valores de la matriz.", "Error", JOptionPane.ERROR_MESSAGE);
            }
            
        } else {
            JOptionPane.showMessageDialog(tabla.frame, "Por favor, ingrese un número.", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }

    public static double[][] calculateAdjoint(double[][] matrix) {
        int size = matrix.length;
        double[][] adjoint = new double[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                double[][] subMatrix = new double[size - 1][size - 1];
                for (int row = 0, subRow = 0; row < size; row++) {
                    if (row == i) {
                        continue;
                    }
                    for (int col = 0, subCol = 0; col < size; col++) {
                        if (col == j) {
                            continue;
                        }
                        subMatrix[subRow][subCol++] = matrix[row][col];
                    }
                    subRow++;
                }
                adjoint[j][i] = Math.pow(-1, i + j) * CalculoDeterminante.calculateDeterminant(subMatrix);
            }
        }

        return adjoint;
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package algebraproyecto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class CalculoCramer {

    JFrame frame;
    JTextField textField;
    JButton generarMatrizAButton;
    JButton generarMatrizBButton;
    JButton solveButton;
    JButton verifyButton;
    JButton volverButton;
    JPanel matrixAPanel;
    JPanel matrixBPanel;
    JTextArea resultArea;

    ArrayList<JTextField> matrixAFields = new ArrayList<>();
    ArrayList<JTextField> matrixBFields = new ArrayList<>();

    public CalculoCramer() {
        
        frame = new JFrame("Método de Cramer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1400, 800);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JLabel label = new JLabel("Ingrese el número de ecuaciones:");
        textField = new JTextField(10);
        generarMatrizAButton = new JButton("Generar Matriz A");
        generarMatrizBButton = new JButton("Generar Matriz B");
        solveButton = new JButton("Resolver");
        verifyButton = new JButton("Verificar");
        volverButton = new JButton("Volver al menú");

        matrixAPanel = new JPanel();
        matrixBPanel = new JPanel();
        resultArea = new JTextArea("Resultado del sistema de Cramer:");

        resultArea.setFont(new Font("Arial", Font.BOLD, 16));
        resultArea.setEditable(false);

        JPanel inputPanel = new JPanel();
        inputPanel.add(label);
        inputPanel.add(textField);
        inputPanel.add(generarMatrizAButton);
        inputPanel.add(generarMatrizBButton);
        inputPanel.add(solveButton);
        inputPanel.add(verifyButton);
        inputPanel.add(volverButton);

        panel.add(inputPanel, BorderLayout.NORTH);
        panel.add(new JScrollPane(matrixAPanel), BorderLayout.CENTER);
        panel.add(new JScrollPane(matrixBPanel), BorderLayout.EAST);
        panel.add(new JScrollPane(resultArea), BorderLayout.SOUTH);

        frame.add(panel);

        generarMatrizAButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int size = Integer.parseInt(textField.getText());
                if (size > 0) {
                    matrixAFields.clear();
                    matrixBFields.clear();
                    matrixAPanel.removeAll();
                    matrixBPanel.removeAll();

                    matrixAPanel.setLayout(new GridLayout(size, size, 10, 10)); 

                    for (int i = 0; i < size * size; i++) {
                        JTextField campo = new JTextField(3);
                        campo.setHorizontalAlignment(JTextField.CENTER);
                        campo.setFont(new Font("Arial", Font.BOLD, 20)); 
                        campo.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2)); 
                        matrixAPanel.add(campo);
                        matrixAFields.add(campo);
                    }

                    matrixAPanel.revalidate();
                    matrixAPanel.repaint();
                } else {
                    JOptionPane.showMessageDialog(frame, "Ingrese un tamaño válido.");
                }
            }
        });

        generarMatrizBButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int size = Integer.parseInt(textField.getText());
                if (size > 0) {
                    matrixBFields.clear();
                    matrixBPanel.removeAll();

                    matrixBPanel.setLayout(new GridLayout(size, 1, 10, 10)); 

                    for (int i = 0; i < size; i++) {
                        JTextField campo = new JTextField(20);
                        campo.setHorizontalAlignment(JTextField.CENTER);
                        campo.setFont(new Font("Arial", Font.BOLD, 20)); 
                        campo.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2)); 
                        matrixBPanel.add(campo);
                        matrixBFields.add(campo);
                    }

                    matrixBPanel.revalidate();
                    matrixBPanel.repaint();
                } else {
                    JOptionPane.showMessageDialog(frame, "Ingrese un tamaño válido.");
                }
            }
        });
        
        solveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int size = Integer.parseInt(textField.getText());
                    double[][] matrixA = new double[size][size];
                    double[] matrixB = new double[size];

                    for (int i = 0; i < size; i++) {
                        for (int j = 0; j < size; j++) {
                            matrixA[i][j] = Double.parseDouble(matrixAFields.get(i * size + j).getText());
                        }
                        matrixB[i] = Double.parseDouble(matrixBFields.get(i).getText());
                    }

                    double detA = determinant(matrixA);

                    if (detA == 0) {
                        resultArea.setText("El determinante de la matriz es 0. El sistema no tiene solución única.");
                        return;
                    }

                    double[] solutions = new double[size];
                    for (int i = 0; i < size; i++) {
                        double[][] modifiedMatrix = modifyMatrixForCramer(matrixA, matrixB, i);
                        solutions[i] = determinant(modifiedMatrix) / detA;
                    }

                    StringBuilder sb = new StringBuilder("Soluciones:\n");
                    for (int i = 0; i < size; i++) {
                        sb.append("x").append(i + 1).append(" = ").append(String.format("%.2f", solutions[i])).append("\n");
                    }
                    resultArea.setText(sb.toString());

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Por favor, ingrese solo números válidos.");
                }
            }
        });

        verifyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int size = Integer.parseInt(textField.getText());
                    double[][] matrixA = new double[size][size];
                    double[] matrixB = new double[size];
                    double[] solutions = new double[size];

                    for (int i = 0; i < size; i++) {
                        for (int j = 0; j < size; j++) {
                            matrixA[i][j] = Double.parseDouble(matrixAFields.get(i * size + j).getText());
                        }
                        matrixB[i] = Double.parseDouble(matrixBFields.get(i).getText());
                    }

                    for (int i = 0; i < size; i++) {
                        solutions[i] = Double.parseDouble(JOptionPane.showInputDialog("Ingrese la solución para x" + (i + 1) + ":"));
                    }

                    StringBuilder sb = new StringBuilder("Verificación de soluciones:\n");
                    boolean isValid = true;

                    for (int i = 0; i < size; i++) {
                        double result = 0;
                        for (int j = 0; j < size; j++) {
                            result += matrixA[i][j] * solutions[j];
                        }
                        sb.append("Ecuación ").append(i + 1).append(": ");
                        for (int j = 0; j < size; j++) {
                            sb.append(matrixA[i][j]).append("*").append(String.format("%.2f", solutions[j]));
                            if (j < size - 1) sb.append(" + ");
                        }
                        sb.append(" = ").append(String.format("%.2f", result)).append(" (debería ser ").append(matrixB[i]).append(")\n");

                        if (Math.abs(result - matrixB[i]) > 0.2) {
                            isValid = false;
                        }
                    }

                    if (isValid) {
                        sb.append("Las soluciones son correctas.");
                    } else {
                        sb.append("Las soluciones no son correctas.");
                    }

                    resultArea.setText(sb.toString());

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Por favor, ingrese solo números válidos.");
                }
            }
        });

        volverButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Ventana ventana = new Ventana();
                ventana.mostrarVentana();
                frame.setVisible(false);
            }
        });
    }

    private double determinant(double[][] matrix) {
        int size = matrix.length;
        if (size == 2) {
            return matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0];
        }

        double det = 0;
        for (int i = 0; i < size; i++) {
            det += (i % 2 == 0 ? 1 : -1) * matrix[0][i] * determinant(getMinor(matrix, 0, i));
        }
        return det;
    }

    private double[][] getMinor(double[][] matrix, int row, int col) {
        int size = matrix.length;
        double[][] minor = new double[size - 1][size - 1];
        int r = 0;
        for (int i = 0; i < size; i++) {
            if (i == row) continue;
            int c = 0;
            for (int j = 0; j < size; j++) {
                if (j == col) continue;
                minor[r][c] = matrix[i][j];
                c++;
            }
            r++;
        }
        return minor;
    }

    private double[][] modifyMatrixForCramer(double[][] matrix, double[] result, int colToReplace) {
        int size = matrix.length;
        double[][] modifiedMatrix = new double[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                modifiedMatrix[i][j] = (j == colToReplace) ? result[i] : matrix[i][j];
            }
        }
        return modifiedMatrix;
    }

    public void mostrarVentana() {
        frame.setVisible(true);
    }
}
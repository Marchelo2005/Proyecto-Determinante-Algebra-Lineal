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
        // Crear la ventana principal
        frame = new JFrame("Método de Cramer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1400, 800);

        // Panel principal
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Componentes de la ventana
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

        // Configurando el área de resultados
        resultArea.setFont(new Font("Arial", Font.BOLD, 16));
        resultArea.setEditable(false);

        // Paneles de entrada
        JPanel inputPanel = new JPanel();
        inputPanel.add(label);
        inputPanel.add(textField);
        inputPanel.add(generarMatrizAButton);
        inputPanel.add(generarMatrizBButton);
        inputPanel.add(solveButton);
        inputPanel.add(verifyButton);
        inputPanel.add(volverButton);

        // Agregando los componentes al panel principal
        panel.add(inputPanel, BorderLayout.NORTH);
        panel.add(new JScrollPane(matrixAPanel), BorderLayout.CENTER);
        panel.add(new JScrollPane(matrixBPanel), BorderLayout.EAST);
        panel.add(new JScrollPane(resultArea), BorderLayout.SOUTH);

        // Agregar el panel principal al frame
        frame.add(panel);

        // Generar la matriz A cuando el usuario ingrese el número de ecuaciones
        generarMatrizAButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int size = Integer.parseInt(textField.getText());
                if (size > 0) {
                    matrixAFields.clear();
                    matrixBFields.clear();
                    matrixAPanel.removeAll();
                    matrixBPanel.removeAll();

                    // Configurar panel de la matriz A
                    matrixAPanel.setLayout(new GridLayout(size, size));

                    // Crear campos para la matriz A
                    for (int i = 0; i < size * size; i++) {
                        JTextField campo = new JTextField(3);
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

        // Generar la matriz B cuando el usuario haga clic en "Generar Matriz B"
        generarMatrizBButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int size = Integer.parseInt(textField.getText());
                if (size > 0) {
                    matrixBFields.clear();
                    matrixBPanel.removeAll();

                    // Configurar panel de la matriz B (vector de resultados)
                    matrixBPanel.setLayout(new GridLayout(size, 1));

                    // Crear campos para la matriz B
                    for (int i = 0; i < size; i++) {
                        JTextField campo = new JTextField(20);
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

        // Resolver el sistema usando el método de CalculoCramer
        solveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int size = Integer.parseInt(textField.getText());
                    double[][] matrixA = new double[size][size];
                    double[] matrixB = new double[size];

                    // Rellenar la matriz A y la matriz B
                    for (int i = 0; i < size; i++) {
                        for (int j = 0; j < size; j++) {
                            matrixA[i][j] = Double.parseDouble(matrixAFields.get(i * size + j).getText());
                        }
                        matrixB[i] = Double.parseDouble(matrixBFields.get(i).getText());
                    }

                    // Calcular el determinante de la matriz A
                    double detA = determinant(matrixA);

                    if (detA == 0) {
                        resultArea.setText("El determinante de la matriz es 0. El sistema no tiene solución única.");
                        return;
                    }

                    // Resolver el sistema usando CalculoCramer
                    double[] solutions = new double[size];
                    for (int i = 0; i < size; i++) {
                        double[][] modifiedMatrix = modifyMatrixForCramer(matrixA, matrixB, i);
                        solutions[i] = determinant(modifiedMatrix) / detA; // Sin redondeo
                    }

                    // Mostrar las soluciones
                    StringBuilder sb = new StringBuilder("Soluciones:\n");
                    for (int i = 0; i < size; i++) {
                        sb.append("x" + (i + 1) + " = " + solutions[i] + "\n");
                    }
                    resultArea.setText(sb.toString());

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Por favor, ingrese solo números válidos.");
                }
            }
        });

        // Verificar las soluciones
        verifyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int size = Integer.parseInt(textField.getText());
                    double[][] matrixA = new double[size][size];
                    double[] matrixB = new double[size];
                    double[] solutions = new double[size];

                    // Rellenar la matriz A, la matriz B y las soluciones
                    for (int i = 0; i < size; i++) {
                        for (int j = 0; j < size; j++) {
                            matrixA[i][j] = Double.parseDouble(matrixAFields.get(i * size + j).getText());
                        }
                        matrixB[i] = Double.parseDouble(matrixBFields.get(i).getText());
                    }

                    // Verificar si las soluciones satisfacen el sistema
                    for (int i = 0; i < size; i++) {
                        solutions[i] = Double.parseDouble(JOptionPane.showInputDialog("Ingrese la solución para x" + (i + 1) + ":"));
                    }

                    double[] resultCheck = new double[size];
                    for (int i = 0; i < size; i++) {
                        resultCheck[i] = 0;
                        for (int j = 0; j < size; j++) {
                            resultCheck[i] += matrixA[i][j] * solutions[j];
                        }
                    }

                    // Verificar si el resultado obtenido es igual al vector B
                    boolean isValid = true;
                    for (int i = 0; i < size; i++) {
                        if (Math.abs(resultCheck[i] - matrixB[i]) > 1e-6) {
                            isValid = false;
                            break;
                        }
                    }

                    if (isValid) {
                        resultArea.setText("Las soluciones son correctas.");
                    } else {
                        resultArea.setText("Las soluciones no son correctas.");
                    }

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Por favor, ingrese solo números válidos.");
                }
            }
        });

        // Volver al menú principal
        volverButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Ventana ventana = new Ventana();
                ventana.mostrarVentana();
                frame.setVisible(false);
            }
        });
    }

    // Método para calcular el determinante de una matriz
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

    // Método para obtener la submatriz menor
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

    // Método para modificar la matriz para el método de CalculoCramer
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


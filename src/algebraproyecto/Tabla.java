package algebraproyecto;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Tabla {

    JFrame frame;
    JTextField textField;
    JButton generateButton;
    JButton calculateButton;
    JButton adjointButton;
    JButton inverseButton;
    JPanel matrixPanel;
    JTextArea resultArea;
    JPanel adjointPanel;
    JPanel inversePanel;
    CalculoDeterminante det = new CalculoDeterminante();
    CalculoAdjunta adj = new CalculoAdjunta();
    CalculoInversa inv = new CalculoInversa();
    public Tabla() {
        //Creando la ventana y sus dimensiones
        frame = new JFrame("Cálculo de Matrices");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 800);
        //Creando el panel general
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        //Creando los componentes del panel
        JLabel label = new JLabel("Ingrese un número (tamaño de la matriz):");
        textField = new JTextField(10);
        generateButton = new JButton("Generar Matriz");
        calculateButton = new JButton("Calcular Determinante");
        adjointButton = new JButton("Calcular Matriz Adjunta");
        inverseButton = new JButton("Calcular Matriz Inversa");
        resultArea = new JTextArea("Resultado de la Determinante:");
        //Creando el panel para mostrar la matriz
        matrixPanel = new JPanel();
        matrixPanel.setSize(500, 500);
        //Ajustando algunos componentes del panel general
        resultArea.setFont(new Font("Arial", Font.BOLD, 16));
        resultArea.setEditable(false);
        //generando paneles de posición
        adjointPanel = new JPanel();
        inversePanel = new JPanel();
        JPanel inputPanel = new JPanel();
        //Añadiendo los componentes del panel al panel del norte
        inputPanel.add(label);
        inputPanel.add(textField);
        inputPanel.add(generateButton);
        inputPanel.add(calculateButton);
        inputPanel.add(adjointButton);
        inputPanel.add(inverseButton);
        //Especificando la organizacion de componentes
        matrixPanel.setLayout(new GridLayout(0, 1));
        adjointPanel.setLayout(new GridLayout(0, 1));
        inversePanel.setLayout(new GridLayout(0, 1));
        //Designando la posicion de cada panel y componente de la ventana
        panel.add(inputPanel, BorderLayout.NORTH);
        panel.add(new JScrollPane(matrixPanel), BorderLayout.CENTER);
        panel.add(new JScrollPane(resultArea), BorderLayout.SOUTH);
        panel.add(new JScrollPane(adjointPanel), BorderLayout.EAST);
        panel.add(new JScrollPane(inversePanel), BorderLayout.WEST);
        //Añadiendo el panel principal a la ventana y haciendolo visible
        frame.add(panel);
        //Validanddo datos
        textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c)) {
                    e.consume(); // Ignorar el carácter si no es un dígito
                }
            }
        });
        //Generando la matriz a partir de los datos de textField
        generateButton.addActionListener(e -> {
            String input = textField.getText();
            if (!input.isEmpty()) {
                try {
                    int size = Integer.parseInt(input);
                    matrixPanel.removeAll();
                    matrixPanel.setLayout(new GridLayout(size, size));

                    for (int i = 0; i < size; i++) {
                        for (int j = 0; j < size; j++) {
                            JTextField cell = new JTextField(5);
                            cell.setHorizontalAlignment(JTextField.CENTER);
                            matrixPanel.add(cell);
                        }
                    }

                    matrixPanel.revalidate();
                    matrixPanel.repaint();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Por favor, ingrese un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(frame, "Por favor, ingrese un número.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        calculateButton.addActionListener(e -> {
            det.determinante(this);
        });
        adjointButton.addActionListener(e -> {
            adj.adjunta(this);
        });
        inverseButton.addActionListener(e -> {
            inv.inversa(this);
        });
    }

    public void mostrarTabla() {
        frame.setVisible(true);
    }
}

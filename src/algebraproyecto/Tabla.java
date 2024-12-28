package algebraproyecto;

import java.awt.*;
import java.awt.event.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;



public class Tabla {

    JFrame frame;
    JFrame invFrame;
    JFrame adjFrame;
    JTextField textField;
    JButton generateButton;
    JButton calculateButton;
    JButton volver;
    JButton adjointButton;
    JButton inverseButton;
    JPanel matrixPanel;
    JTextArea resultArea;
    JPanel adjointPanel;
    JPanel inversePanel;
    CalculoDeterminante det = new CalculoDeterminante();
    CalculoAdjunta adj = new CalculoAdjunta();
    CalculoInversa inv = new CalculoInversa();
    BotonGenerar gen = new BotonGenerar();

    public Tabla() {
        //Creando la ventana principal y sus dimensiones
        frame = new JFrame("Cálculo de Matrices");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1400, 800);
        //Creando la ventana para mostrar la matriz  adjunta
        adjFrame = new JFrame("Matriz Adjunta");
        adjFrame.setSize(500, 500);
        //Creando la ventana para mostrar la matriz inversa 
        invFrame = new JFrame("Matriz Inversa");
        invFrame.setSize(500, 500);
        //Creando el panel general
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        //Creando los componentes del panel
        JLabel label = new JLabel("Ingrese un número (tamaño de la matriz):");
        textField = new JTextField(10);
        generateButton = new JButton("Generar Matriz");
        volver = new JButton("Volver al menú");
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
        //generando paneles para mostrar las matrices inversa y adjunta
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
        inputPanel.add(volver);
        //Especificando la organizacion de componentes
        matrixPanel.setLayout(new GridLayout(0, 1));
        adjointPanel.setLayout(new GridLayout(0, 1));
        inversePanel.setLayout(new GridLayout(0, 1));
        //Designando la posicion de cada panel y componente de la ventana
        panel.add(inputPanel, BorderLayout.NORTH);
        panel.add(new JScrollPane(matrixPanel), BorderLayout.CENTER);
        panel.add(new JScrollPane(resultArea), BorderLayout.SOUTH);
        //Añadiendo el panel principal a la ventana y los otros paneles a sus respectivas ventanas
        frame.add(panel);
        invFrame.add(inversePanel);
        adjFrame.add(adjointPanel);
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
            gen.generar(this);
        });
        //Agregando funcionalidades a los botones
        calculateButton.addActionListener(e -> {

            det.determinante(this);
        });
        adjointButton.addActionListener(e -> {
            adj.adjunta(this);
        });
        inverseButton.addActionListener(e -> {
            inv.inversa(this);
            
        });
        volver.addActionListener(e -> {
            Ventana ven = new Ventana();
            ven.mostrarVentana();
            frame.setVisible(false);
            
        });
    }

    public void mostrarTabla() {
        frame.setVisible(true);
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package algebraproyecto;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 * @author elian
 */
public class Ventana {

    JFrame frame = new JFrame("Calculos");
    JLabel text = new JLabel("   <-----------Seleccione que tipo de cálculo desea realizar----------->");
    JButton matrix = new JButton("Cálculo de matrices");
    JButton cramer = new JButton("Ecuaciones por el método de Cramer");
    JPanel panel = new JPanel();
    JPanel aux = new JPanel();

    public Ventana() {
        aux.setLayout(new BorderLayout());
        aux.add(text, BorderLayout.CENTER);
        panel.add(aux);
        frame.setSize(800, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        text.setFont(new Font("Arial", Font.BOLD, 25));
        matrix.setFont(new Font("Arial", Font.BOLD, 25));
        cramer.setFont(new Font("Arial", Font.BOLD, 25));
        panel.setLayout(new GridLayout(0, 1));
        panel.add(matrix);
        panel.add(cramer);
        frame.add(panel);
        matrix.addActionListener(e -> {
            Tabla tabla = new Tabla();
            tabla.mostrarTabla();
            frame.setVisible(false);
        });
       cramer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CalculoCramer cramerFrame = new CalculoCramer();
                cramerFrame.mostrarVentana();
                frame.setVisible(false);
            }
        });
    }
    public void mostrarVentana() {
        frame.setVisible(true);
    }

}


package algebraproyecto;

import java.awt.GridLayout;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class BotonGenerar {
    /*El metodo se encarga de obtener la informacion del textField y en base a eso generar textFields para
    que el usuario pueda colocar los datos que desea
    */
    public void generar(Tabla tabla){
        String input = tabla.textField.getText();
            if (!input.isEmpty()) {
                try {
                    int size = Integer.parseInt(input);
                    tabla.matrixPanel.removeAll();
                    tabla.matrixPanel.setLayout(new GridLayout(size, size));

                    for (int i = 0; i < size; i++) {
                        for (int j = 0; j < size; j++) {
                            JTextField cell = new JTextField(5);
                            cell.setHorizontalAlignment(JTextField.CENTER);
                            tabla.matrixPanel.add(cell);
                        }
                    }

                    tabla.matrixPanel.revalidate();
                    tabla.matrixPanel.repaint();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(tabla.frame, "Por favor, ingrese un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(tabla.frame, "Por favor, ingrese un número.", "Error", JOptionPane.ERROR_MESSAGE);
            }
    }
}

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
    private static int d1;
    private static int cambio;
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
                int d=0;
                double determinant=0;
    if ((size-1)==0 || (size-1)==1){
        determinant = calculateDeterminant(matrix,d);
    }else{
        
    
    do{
        d =Integer.parseInt(JOptionPane.showInputDialog(null,"Ingrese el numero de fila por el cual desea empezar: "));
        if (d>size){
            JOptionPane.showMessageDialog(null, "La fila selecciona supera el numero de filas de la matriz");
        }else if (d<1){
            JOptionPane.showMessageDialog(null, "No exiten filas negativas");
        }
    }while(d>size || d<1 );       
    d-=1;
   
                determinant = calculateDeterminant(matrix,d);
                }
                tabla.resultArea.setText("Determinante: " + determinant);
                JOptionPane.showMessageDialog(tabla.frame,"la determinante es: "+determinant);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(tabla.frame, "Asegúrese de llenar correctamente todos los valores de la matriz.", "Error", JOptionPane.ERROR_MESSAGE);
            }
            } else {
                JOptionPane.showMessageDialog(tabla.frame, "Por favor, ingrese un número.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        
    }
    public static double calculateDeterminant(double[][] matrix,int d) {
        int size = matrix.length;
        if (size == 1) {
            return matrix[0][0];
        }
        if (size == 2) {
            return matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0];
        }

        double determinant = 0;
        if (CalculoDeterminante.d1>(size-1)){
        int i=0;
        i+=1;
        if(i==1){
            CalculoDeterminante.cambio=CalculoDeterminante.d1;
        }
            CalculoDeterminante.d1=(size-1);
        }
 
    
    for (int i = 0; i < size; i++) {
            
            double[][] subMatrix = new double[size - 1][size - 1];
            int subRow = 0;
            for (int j = 0; j < size; j++) {
            if (j == d) {
                continue; // Omitir la fila seleccionada por el usuario
            }
            int subCol = 0;
            for (int k = 0; k < size; k++) {
                if (k == i) {
                    continue; // Omitir la columna actual
                }
                subMatrix[subRow][subCol++] = matrix[j][k];
               
            }

            subRow++;
        
            }
            //pow = potencia donde (-1)es el valor a potenciar e i es el valor al cual será potenciado
            
            
               
        double subDeterminant = calculateDeterminant(subMatrix, 0);
        determinant += Math.pow(-1, d + i) * matrix[d][i] * subDeterminant;

        // Generar tabla solo una vez por subDeterminante calculado
        ProcedimientoDeterminante.generarTabla(
            subMatrix,
            "Determinante: " + Math.pow(-1, d + i) + " (" + matrix[d][i] + " x " + subDeterminant + ") = " + determinant
        );
               
           
        
        }
        return determinant;
}
    
}

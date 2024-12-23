
package algebraproyecto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Tabla {
   
 
    private int row;
 public Tabla(int row){
     this.row=row;
 }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }
    
    public void showTable(){
        JFrame frame = new JFrame("Matriz");
        frame.setSize(1000,860);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         String[][] data = new String[this.row][this.row];

        String[] columnNames = new String[this.row];

        DefaultTableModel model = new DefaultTableModel(data, columnNames);

        JTable table = new JTable(model);

        JScrollPane scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);

        frame.add(scrollPane);
        frame.setVisible(true);
      
        
    }
    public Tabla(){
        
    }
    
}

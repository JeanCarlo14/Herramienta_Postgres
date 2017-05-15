/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package herramienta_postgres;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Fabiola
 */
public class RespaldoExcel extends javax.swing.JFrame {

    
   
    String url = "jdbc:postgresql://localhost:5432/BD_SITUN"; 
    Connection con = null;
    HashMap<Integer,List<ArrayList>> registros = null;
    /**
     * Creates new form RespaldoExcel
     */
    public RespaldoExcel() {
        try {
            Class.forName("org.postgresql.Driver");
            initComponents();
            con = DriverManager.getConnection(url, "postgres","SITUN");
            registros = new HashMap();
        } catch (SQLException ex) {
            Logger.getLogger(RespaldoExcel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RespaldoExcel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setText("Iniciar Respaldo");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(157, 157, 157)
                .addComponent(jButton1)
                .addContainerGap(134, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(110, 110, 110)
                .addComponent(jButton1)
                .addContainerGap(167, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            // TODO add your handling code here:
            recuperarRegistros();
            cargarExcel();
        } catch (SQLException ex) {
            Logger.getLogger(RespaldoExcel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RespaldoExcel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    
    private void recuperarRegistros() throws SQLException
    {
        String sql = "select * from tc order by tc_2 asc";
        PreparedStatement ps = con.prepareStatement(sql);
        
        ResultSet rs = ps.executeQuery();
        while(rs.next())
        {
            System.out.println(rs.getString(2).substring(0, 4));
            int anno = Integer.valueOf(rs.getString(2).substring(0, 4));
            ArrayList<String> arr = new ArrayList();
            //arr.add(rs.getString(1));
            arr.add(fecha(rs.getString("tc_2")));
            arr.add(rs.getString("tc_3"));
            arr.add(fecha(rs.getString("tc_4")));
            arr.add(rs.getString("tc_5"));
            arr.add(rs.getString("tc_6"));
            arr.add(rs.getString("tc_7"));
            arr.add(rs.getString("tc_8"));
            arr.add(rs.getString("tc_9"));
            arr.add(rs.getString("tc_10"));
            if(registros.get(anno) != null)
                registros.get(anno).add(arr);
            else
            {
                List<ArrayList> l = new ArrayList();
                l.add(arr);
                registros.put(anno, l);
            }
        }
        
        
        //System.out.println("primer celda ... -> " + rs.getString(3));
    }
    
    public void cargarExcel() throws IOException
    {
        XSSFWorkbook libroTrabajo = new XSSFWorkbook();
        Set set = registros.entrySet();
        Iterator iterator = set.iterator();
        while(iterator.hasNext()) {
            Map.Entry mentry = (Map.Entry)iterator.next();
            escribirLibro((int)mentry.getKey(),(List)mentry.getValue(),libroTrabajo);
            //System.out.print("key is: "+ mentry.getKey() + " & Value is: ");
            //System.out.println(mentry.getValue());
            
      }
        
        FileOutputStream fileOut = new FileOutputStream("C:\\Users\\Fabiola\\Desktop\\miLibro.xlsx");
 
        //escribir este libro en un OutputStream.
        libroTrabajo.write(fileOut);
        fileOut.flush();
        fileOut.close();
    }
    
    public void escribirLibro(int k,List<String> l, XSSFWorkbook libro)
    {
        XSSFSheet hoja1 = libro.createSheet(String.valueOf(k));
        escribirEncabezado(hoja1);
        escribirData(hoja1,l);
        System.out.println("Escribiendo en libro: " +k+" "+l.size()+" Registros");
    }
    public void escribirData(XSSFSheet hoja,List l)
    {
        Iterator it = l.iterator();
        int i = 1;
        while(it.hasNext())
        {
            ArrayList k = ((ArrayList)it.next());
            XSSFRow row = hoja.createRow(hoja.getLastRowNum()+1);
           
            Iterator it1 = k.iterator();
            int c = 0;
            while(it1.hasNext())
            {
                String n = (String) it1.next();
                XSSFCell cell = row.createCell(c++);
                cell.setCellValue(n);
            }
            
            
        }
    }
    public void escribirEncabezado(XSSFSheet hoja)
    {
        String[] encabezado = {"Fecha de recibido","Número de oficio","Fecha de oficio","Destinatario","Copia","Remitente","Asunto","Recibido","Traslado a"};
       //for (int r=0;r < encabezado.length; r++ ) {
            XSSFRow row = hoja.createRow(0);
             
            //iterando numero de columnas (c)
            for (int c=0;c < encabezado.length; c++ ){
                XSSFCell cell = row.createCell(c);
                cell.setCellValue(encabezado[c]);
            }
      //  }
    }
    
    public String fecha(String v)
    {
        String []data = v.split("-");
        String fecha = data[2];
        fecha+=" de "+mes(Integer.valueOf(data[1])) + " del " + data[0];
        return fecha;
    }
    
    public String mes(int m)
    {
        switch(m)
        {
            case 1:
                return "enero";
            case 2:
                return "febrero";
            case 3:
                return "marzo";
            case 4:
                return "abril";
            case 5:
                return "mayo";
            case 6:
                return "junio";
            case 7:
                return "julio";
            case 8:
                return "agosto";
            case 9:
                return "setiembre";
            case 10:
                return "octubre";
            case 11:
                return "novimbre";
            case 12:
                return "diciembre";
        }
        return "";
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(RespaldoExcel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RespaldoExcel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RespaldoExcel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RespaldoExcel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RespaldoExcel().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    // End of variables declaration//GEN-END:variables
}
